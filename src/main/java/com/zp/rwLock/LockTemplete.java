package com.zp.rwLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTemplete {
	
	public static void main(String[] args) {
		Lock lock = new ReentrantLock();
		lock.lock();
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			lock.unlock();
		}
	}
}
