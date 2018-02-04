package com.zp.aqs;

import java.util.concurrent.locks.Lock;

import com.zp.threadstate.SleepUtils;

public class TestMyLock {

	public void test(){
		final Lock lock = new TwoThreadsLock();
		class Worker extends Thread{

			@Override
			public void run() {
				while(true){
					lock.lock();
					try {
						SleepUtils.second(1);
						System.out.println(Thread.currentThread().getName());
						SleepUtils.second(1);
					} finally {
						lock.unlock();
					}
					SleepUtils.second(2);
				}
			}
			
		}
		
		//启动10个子线程
		for(int i =0;i<10;i++){
			Worker w = new Worker();
			w.setDaemon(true);
			w.start();
		}
		//主线程每隔1s换行
		for(int i=0;i<10;i++){
			SleepUtils.second(1);
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		TestMyLock test = new TestMyLock();
		test.test();
	}
}
