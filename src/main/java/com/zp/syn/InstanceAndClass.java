package com.zp.syn;

import com.zp.threadstate.SleepUtils;

public class InstanceAndClass {

    //类锁
    private static class TestClassSyn extends Thread{
        @Override
        public void run() {
            System.out.println("TestClassSyn is start.......");
            synClass();
        }
    }

    private static class TestInstanceSyn extends Thread{

        private InstanceAndClass instanceAndClass;

        public TestInstanceSyn (InstanceAndClass instanceAndClass) {
            this.instanceAndClass = instanceAndClass;
        }

        @Override
        public void run() {
            System.out.println("TestInstanceSyn is start.......");
            synInstance();
        }
    }

    private static class TestInstance2Syn extends Thread{

        private InstanceAndClass instanceAndClass;

        public TestInstance2Syn (InstanceAndClass instanceAndClass) {
            this.instanceAndClass = instanceAndClass;
        }

        @Override
        public void run() {
            System.out.println("TestInstance2Syn is start.......");
            synInstance2();
        }
    }

    public static synchronized void synClass(){
        SleepUtils.second(3);
        System.out.println("synClass is start.......");
        SleepUtils.second(3);
    }

    public static synchronized void synInstance(){
        SleepUtils.second(3);
        System.out.println("synInstance is start.......");
        SleepUtils.second(3);
    }

    public static synchronized void synInstance2(){
        SleepUtils.second(3);
        System.out.println("synInstance2 is start.......");
        SleepUtils.second(3);
    }

    public static void main(String[] args) {

        InstanceAndClass instanceAndClass = new InstanceAndClass();
        Thread t1 = new TestClassSyn();
        Thread t2 = new Thread(new TestInstance2Syn(instanceAndClass));
        Thread t3 = new Thread(new TestInstanceSyn(instanceAndClass));
        t2.start();
        t3.start();
        SleepUtils.second(1);
        t1.start();
    }
}
