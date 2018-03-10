package com.zp.delayqueue;

import java.util.concurrent.DelayQueue;

public class PutInCache implements Runnable{
	
	private DelayQueue<CacheBean<User>> queue;
	
	

	public PutInCache(DelayQueue<CacheBean<User>> queue) {
		super();
		this.queue = queue;
	}


	@Override
	public void run() {
		CacheBean cacheBean = new CacheBean("1","5秒",new User("Mark"),5000);
		CacheBean cacheBean2 = new CacheBean("2","3秒",new User("Mike"),3000);
		queue.offer(cacheBean);
		System.out.println("put in cache:"+cacheBean.getId()+":"+cacheBean.getName());
		queue.offer(cacheBean2);
		System.out.println("put in cache:"+cacheBean2.getId()+":"+cacheBean2.getName());
	}

}
