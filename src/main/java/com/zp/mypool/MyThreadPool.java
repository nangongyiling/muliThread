package com.zp.mypool;

import java.util.LinkedList;
import java.util.List;

public class MyThreadPool {

	//默认的线程个数
	private int work_num=5;
	
	//线程的容器
	private WorkThread[] workThreads;
	
	//任务队列
	private List<Runnable> taskQueue = new LinkedList<>();
	
	public MyThreadPool(int workNum){
		this.work_num = workNum;
		this.workThreads = new WorkThread[workNum];
		for(int i=0;i<workNum;i++){
			workThreads[i] = new WorkThread();
			workThreads[i].start();
		}
	}
	
	private class WorkThread extends Thread{
		
		//开关
		private volatile boolean on = true;

		@Override
		public void run() {
			Runnable r = null;
			try{
				//线程非中断，详见Threadstatus
				while(on && !isInterrupted()){
					synchronized (taskQueue) {
						//任务队列里没有任务，工作线程等待
						while(on && !isInterrupted() && taskQueue.isEmpty()){
							taskQueue.wait(1000);
						}
						//任务队列里有任务，从头取出执行
						if(on && !isInterrupted() && !taskQueue.isEmpty()){
							r = taskQueue.remove(0);
						}
					}
					if(r!=null){
						System.out.println(getId()+"   ready execute......");
						r.run();
					}
					//加速回收
					r = null;
				}
			}catch(Exception e){
				System.out.println(getId()+"  is Interrupted");
			}
		}
		
		public void stopWorker(){
			on = false;
			interrupt();
		}
	}
	
	//提交任务
	public void execute(Runnable task){
		synchronized (taskQueue) {
			taskQueue.add(task);
			taskQueue.notify();
		}
	}
	
	//销毁线程池
	public void destory(){
		System.out.println("ready stop pool.....");
		for(int i=0;i<work_num;i++){
			workThreads[i].stopWorker();
			//加速垃圾回收
			workThreads[i] = null;
		}
		taskQueue.clear();
	}
}
