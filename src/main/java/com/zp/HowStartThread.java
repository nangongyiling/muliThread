package com.zp;

public class HowStartThread {
    private static class TestThread extends  Thread{
        @Override
        public void run() {
            System.out.print("test1 start");
        }
    }
    private static class TestRunable implements Runnable{
        public void run() {
            System.out.print("test2 start");
        }
    }
    public static void main(String []args){
        Thread t1 = new Thread(new TestThread());
        Thread t2 = new Thread(new TestRunable());
        t1.start();
        t2.start();
    }
}
