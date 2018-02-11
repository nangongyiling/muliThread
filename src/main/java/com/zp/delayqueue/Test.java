package com.zp.delayqueue;

import java.util.concurrent.DelayQueue;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		DelayQueue<CacheBean<User>> queue = new DelayQueue<CacheBean<User>>();
		
		new Thread(new PutInCache(queue)).start();
		new Thread(new GetFromCache(queue)).start();
		for (int i = 0; i < 5; i++) {
			Thread.sleep(500);
			System.out.println(i*500);
		}
	}
}
