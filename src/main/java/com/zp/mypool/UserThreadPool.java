package com.zp.mypool;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class UserThreadPool {

	static class MyTask implements Runnable{
		
		private String name;
		
		public MyTask(String name){
			this.name = name;
		}

		
		public String getName() {
			return name;
		}


		@Override
		public void run() {
			try {
				Random r = new Random();
				Thread.sleep(r.nextInt(1000)+2000);
				System.out.println(Thread.currentThread().getId()+" start");
			} catch (Exception e) {
				System.out.println(Thread.currentThread().getId()+" sleep");
			}
		}
	}
	
	public static void main(String[] args) {
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,4,60,TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(10));
		for (int i = 0; i <=5; i++) {
			MyTask task = new MyTask("MyTask_"+i);
			System.out.println("A new task will add:"+task.getName());
			threadPoolExecutor.execute(task);
		}
		//销毁线程池,结果只有2个线程在运行
		threadPoolExecutor.shutdown();
	}
}
