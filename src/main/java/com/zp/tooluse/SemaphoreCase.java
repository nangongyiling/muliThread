package com.zp.tooluse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * 用信号量实现有界缓存
 * @author lenovo
 *
 * @param <T>
 */
public class SemaphoreCase<T> {

	private final Semaphore items;//有多少元素可拿
	private final Semaphore space;//有多少空位可放元素
	private List<T> queue = new ArrayList<T>();
	public SemaphoreCase(Semaphore items, Semaphore space) {
		super();
		this.items = items;
		this.space = space;
	}
	
	//放入数据
	public void put(T x) throws InterruptedException {
		space.acquire();//拿空位的许可，没有空位线程会在这个方法上阻塞
		synchronized (queue) {
			queue.add(x);
		}
		items.release();//有元素了，可以释放一个拿元素的许可
	}
	//去数据
	public T take() throws InterruptedException {
		//拿元素的许可，没有元素线程会在这方法上阻塞
		items.acquire();
		T t;
		synchronized (queue) {
			t = queue.get(0);
			queue.remove(0);
		}
		//有空位了，可以释放一个存在空位的许可
		space.release();
		return t;
	}
}
