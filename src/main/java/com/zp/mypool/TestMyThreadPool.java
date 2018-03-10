package com.zp.mypool;

import java.util.Random;

public class TestMyThreadPool {

	public static void main(String[] args) throws InterruptedException {
		MyThreadPool t = new MyThreadPool(3);
		t.execute(new MyTask("taskA"));
		t.execute(new MyTask("taskB"));
		t.execute(new MyTask("taskC"));
		t.execute(new MyTask("taskD"));
		t.execute(new MyTask("taskE"));
		t.execute(new MyTask("taskF"));
		t.execute(new MyTask("taskG"));
		System.out.println(t);
		Thread.sleep(3000);
		t.destory();
		System.out.println(t);
	}
	
	static class MyTask implements Runnable{

		private String name;
		private Random r = new Random();
		
		public MyTask(String name){
			this.name = name;
		}
		
		public String getName(){
			return this.name;
		}
		
		@Override
		public void run() {
			try {
				Thread.sleep(r.nextInt(1000)+2000);
				System.out.println("任务  "+ name + "   开始");
			} catch (Exception e) {
				System.out.println(Thread.currentThread().getId()+" sleep InterruptedException:"
						+Thread.currentThread().isInterrupted());
			}
			System.out.println("任务  "+ name + "   完成");
		}
		
	}
}
