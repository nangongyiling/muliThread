package com.zp.forkjoin.bajie;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

import com.zp.forkjoin.MakePanTaoArray;
import com.zp.forkjoin.service.IPickTaoZi;
import com.zp.forkjoin.service.IProcessTaoZi;
import com.zp.forkjoin.sunwukong.impl.WuKongPickImpl;
import com.zp.forkjoin.sunwukong.impl.WuKongProcessImpl;
import com.zp.forkjoin.vo.PanTao;

public class ForkJoinBaJieAsyn {
	
	private static class Xiaobajie extends RecursiveAction {
		
		private final static int THRESHOLD=100;
		
		private PanTao[] src;
		
		private int fromIndex;
		
		private int toIndex;
		
		private IPickTaoZi pickTaoZi;

		
		public Xiaobajie(PanTao[] src, int fromIndex, int toIndex,
				IPickTaoZi pickTaoZi) {
			super();
			this.src = src;
			this.fromIndex = fromIndex;
			this.toIndex = toIndex;
			this.pickTaoZi = pickTaoZi;
		}


		@Override
		protected void compute() {
			if(toIndex-fromIndex<THRESHOLD){
				System.out.println(" form index="+fromIndex+" toIndex="+toIndex);
				int count=0;
				for(int i = fromIndex;i<=toIndex;i++){
					if(pickTaoZi.pick(src, i)){
						count++;
					}
				}
				
			}else{
				int midIndex = (toIndex+fromIndex)/2;
				Xiaobajie left = new Xiaobajie(src,fromIndex,midIndex,pickTaoZi);
				Xiaobajie right = new Xiaobajie(src,midIndex,toIndex,pickTaoZi);
				invokeAll(left, right);
			}
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		ForkJoinPool pool = new ForkJoinPool();
		PanTao[] src = MakePanTaoArray.makeArray();
		IProcessTaoZi process = new WuKongProcessImpl();
		IPickTaoZi pick = new WuKongPickImpl(process);
		long start = System.currentTimeMillis();
		Xiaobajie xiaobajie = new Xiaobajie(src,0,src.length-1,pick);
		pool.execute(xiaobajie);
		System.out.println("Bajie is picking.....");
		Thread.sleep(10);
		while(!xiaobajie.isDone()){
			showLog(pool);
			TimeUnit.MICROSECONDS.sleep(100);
		}
		//池终止
		pool.shutdown();
		//forkJoin阻塞,shutdown执行后这个方法没有效
		pool.awaitTermination(1, TimeUnit.MINUTES);
		showLog(pool);
		
		
		xiaobajie.join();
		System.out.println(" spend time:"+(System.currentTimeMillis()-start)+"ms");
	}

	//监控forkJoin池的相关方法
	public static void showLog(ForkJoinPool pool){
		System.out.printf("***************************\n");
		System.out.printf("线程池的worker线程们的数量：%d\n",pool.getPoolSize());
		System.out.printf("当前执行任务的线程的数量：%d\n",pool.getActiveThreadCount());
		System.out.printf("没有被阻塞的正在工作的线程：%d\n",pool.getRunningThreadCount());
		System.out.printf("已经提交给池还没有开始执行的任务数：%d\n",pool.getQueuedSubmissionCount());
		System.out.printf("已经提交给池已经开始执行任务的任务数：%d\n",pool.getQueuedTaskCount());
		System.out.printf("线程偷取任务数：%d\n",pool.getStealCount());
		System.out.printf("池是否已经终止：%s\n",pool.isTerminated());
		System.out.printf("****************\n");
	}
}
