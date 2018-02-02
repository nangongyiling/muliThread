package com.zp.bq;

public class BqWNTest {

    public static void main(String[] args) {
        BlockQueueWN bq = new BlockQueueWN(10);
        Thread threadA = new ThreadPush(bq);
        threadA.setName("Push");
        Thread threadB = new ThreadPop(bq);
        threadB.setName("pop");
        threadA.start();
        threadB.start();
    }

    //数据入队列
    private static class ThreadPush extends Thread{
        BlockQueueWN<Integer> bq;

        public ThreadPush(BlockQueueWN<Integer> bq){
            this.bq = bq;
        }
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            int i=20;
            while (i>0) {
                try {
                    Thread.sleep(1000);
                    System.out.println(" i="+i+"will push");
                    bq.enqueue(i--);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //数据入队列
    private static class ThreadPop extends Thread{
        BlockQueueWN<Integer> bq;

        public ThreadPop(BlockQueueWN<Integer> bq){
            this.bq = bq;
        }
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            while (true) {
                try {
                    System.out.println(name+" will pop......");
                    Integer i =bq.dequeue();
                    System.out.println("i="+i+" alread pop");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
