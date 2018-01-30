package com.zp;

public class JoinTest {

    static class CutInline implements  Runnable{
        private  Thread thread;

        public CutInline(Thread t){
            this.thread = t;
        }

        public void run() {
            try {
                //在被插队的线程里面调用插队线程的join方法
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        Thread t = Thread.currentThread();
        for(int i=0;i<10;i++){
            Thread thread = new Thread(t,String.valueOf(i));
            System.out.println(t.getId()+" out in the thread"+thread.getName());
            thread.start();
            t=thread;
        }
    }
}
