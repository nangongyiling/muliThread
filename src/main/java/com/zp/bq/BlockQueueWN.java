package com.zp.bq;

import java.util.LinkedList;
import java.util.List;

/**
 * 有界阻塞队列
 * @param <T>
 */
public class BlockQueueWN<T> {

    private List<T> queue = new LinkedList<T>();
    private final int limit;

    public BlockQueueWN(int limit){
        this.limit = limit;
    }

    //入队
    public  synchronized void enqueue(T item) throws InterruptedException {
        while(this.queue.size() == this.limit){
            wait();
        }
        if(this.queue.size() == 0){
            notifyAll();
        }
        this.queue.add(item);
    }

    public  synchronized T dequeue() throws InterruptedException {
        while(this.queue.size() == 0){
            wait();
        }
        if(this.queue.size() == this.limit){
            notifyAll();
        }
        return (T) this.queue.remove(0);
    }
}
