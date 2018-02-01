package com.zp.rwLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTemplete {
	
	static Lock lock = new ReentrantLock();
	static Condition c = lock.newCondition();
	
	public void await(){
		lock.lock();
		try {
			c.await();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			lock.unlock();
		}
	}
	
	public void waitNotify(){
		lock.lock();
		try {
			c.signal();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			lock.unlock();
		}
	}
	
}
