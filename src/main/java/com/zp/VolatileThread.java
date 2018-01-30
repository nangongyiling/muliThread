package com.zp;

public class VolatileThread extends Thread {

    private volatile int a=0;
    @Override
    public void run() {
       synchronized (this){
           a=a+1;
           System.out.println(Thread.currentThread().getName()+"-------"+a);
           try {
               Thread.sleep(1000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           a=a+1;
           System.out.println(Thread.currentThread().getName()+"-------"+a);

       }
    }
}
