package com.zp.interrupt;

public class SafeInterrupt implements Runnable {

    private volatile boolean  on =true;

    private long i=0;
    public void run() {
        while (on&&Thread.currentThread().isInterrupted()){
            i++;
            //阻塞方法，on不起作用
            //wait(); sleep blockingqueue(put,task)会阻塞，他们对定期检查中断标志位
        }
        System.out.println("SafeInterrupt is runing :"+i);
    }

    public void cancel(){
        on = false;
        Thread.currentThread().interrupt();
    }
}
