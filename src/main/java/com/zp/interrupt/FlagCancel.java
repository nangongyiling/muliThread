package com.zp.interrupt;

public class FlagCancel {

    private static volatile boolean on =true;

    private static class  WhenBolck implements  Runnable{

        public void run() {
            while (on ) {
                try {
                    Thread.sleep(1000000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void cancel(){
            on = false;
        }
    }
}
