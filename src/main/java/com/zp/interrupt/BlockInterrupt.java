package com.zp.interrupt;

public class BlockInterrupt {
    private static volatile boolean on =true;

    private static class  WhenBolck implements  Runnable{

        public void run() {
            while (on && !Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(1000000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void cancel(){
            on = false;
            Thread.currentThread().interrupt();
        }
    }

}
