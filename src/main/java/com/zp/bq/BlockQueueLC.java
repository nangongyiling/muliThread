package com.zp.bq;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockQueueLC<T> {
	private List<T> queue = new LinkedList<T>();
	private final Lock lock = new ReentrantLock();
	private final Condition nullCondition = lock.newCondition();
	private final Condition notNullCondition = lock.newCondition();
    private final int limit;

    public BlockQueueLC(int limit){
        this.limit = limit;
    }

    //入队
    public  void enqueue(T item) throws InterruptedException {
    	lock.lock();
    	try {
    		while(this.queue.size() == this.limit){
    			notNullCondition.await();
            }
    		this.queue.add(item);
    		nullCondition.signal();
		} finally{
			lock.unlock();
		}
        
    }

    public T dequeue() throws InterruptedException {
    	lock.lock();
    	try {
    		while(this.queue.size() == 0){
    			nullCondition.await();
            }
    		notNullCondition.signal();
            return (T) this.queue.remove(0);
		} finally{
			lock.unlock();
		}
        
        
    }
}
