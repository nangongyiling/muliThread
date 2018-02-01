package com.zp.rwLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RWLockTemplete {

	static final Map<String ,String> map = new HashMap<String ,String>();
	static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
	static Lock r = reentrantReadWriteLock.readLock();
	static Lock w = reentrantReadWriteLock.writeLock();
	
	public void put(){
		w.lock();
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			w.unlock();
		}
	}
	
	public void get(){
		r.lock();
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			r.unlock();
		}
	}
}
