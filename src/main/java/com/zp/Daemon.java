package com.zp;

import com.zp.threadstate.SleepUtils;

public class Daemon {
    public static void main(String[] args) {
        Thread thread = new Thread(new DaemonRunner());
        thread.setDaemon(true);
        SleepUtils.second(100);
        thread.start();
    }

    static class DaemonRunner implements Runnable{
        public void run() {
            try{
                SleepUtils.second(100);
            }catch (Exception e){

            }finally{
                System.out.println("DaemonThread finally run.");
            }

        }
    }
}
