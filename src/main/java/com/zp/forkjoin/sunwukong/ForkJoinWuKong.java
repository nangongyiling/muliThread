package com.zp.forkjoin.sunwukong;

import java.util.concurrent.RecursiveTask;

import com.zp.forkjoin.service.IPickTaoZi;
import com.zp.forkjoin.vo.PanTao;

public class ForkJoinWuKong {

	private static class XiaoWuKong extends RecursiveTask<Integer>{

		private final static int THRESHOLD = 100;//阈值，数组大小，进行具体的业务操作
		private PanTao[] src;
		private int fromIndex;//数组起始下标
		private int toIndex;//数组结束下标
		private IPickTaoZi pickTaoZi;
		


		public XiaoWuKong(PanTao[] src, int fromIndex, int toIndex,
				IPickTaoZi pickTaoZi) {
			super();
			this.src = src;
			this.fromIndex = fromIndex;
			this.toIndex = toIndex;
			this.pickTaoZi = pickTaoZi;
		}



		@Override
		protected Integer compute() {
			if((toIndex-fromIndex)<=THRESHOLD){
				int count = 0;
				for(int i=fromIndex;i<toIndex;i++){
					if(pickTaoZi.pick(src,i)){
						count++;
					}
				}
				return count;
			}else{
				//计算中间下标
				int mid = (fromIndex+toIndex)/2;
				XiaoWuKong left = new XiaoWuKong(src,fromIndex,mid,pickTaoZi);
				XiaoWuKong right = new XiaoWuKong(src,mid,toIndex,pickTaoZi);
				// TODO 固定写法
				invokeAll(left,right);
				return left.join()+right.join();
			}
		}
		
	}
}
