package com.zp.waitnotify;

public class TestUser {
    private static User user = new User(30,User.CITY1);
    private static class CheckAge extends Thread{
        public void run(){
            user.waitAge();
        }
    }
    private static class CheckCity extends Thread{
        @Override
        public void run() {
            user.waitCity();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<3;i++){
            //启动三个等待用户年龄变化的线程
            new CheckAge().start();
        }
        for(int i=0;i<3;i++){
            //启动三个等待用户城市变化的线程
            new CheckCity().start();
        }
        Thread.sleep(1000);
        user.changeCity();
    }
}
