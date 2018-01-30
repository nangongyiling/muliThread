package com.zp.waitnotify;

public class User {
    public static final String CITY1 = "NewYork";
    private int age;
    private String city;

    public User(int age,String city){
        this.age=age;
        this.city = city;
    }

    public User(){

    }

    //修改用户的城市后，发出通知
    public synchronized void changeCity(){
        this.city="London";
        notify();
    }

    //修改用户的年龄后，发出通知
    public synchronized void changeAge(){
        this.age=31;
        notify();
    }

    //等待用户的年龄变化的方法，接收到通知，检查发现用户年龄大于30时，进行业务工作，否则一直等待
    //阻塞方法
    public synchronized void waitAge(){
        while (this.age<=30){
            try {
                wait();
                System.out.println("wait age ["+Thread.currentThread().getId()+"] is notified!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("this.age is"+this.age);
    }

    //等待用户的城市变化的方法，接收到通知，检查发现用户城市不是NewYork时，进行业务工作，否则一直等待
    //阻塞方法
    public synchronized void waitCity(){
        while (this.city.equals(CITY1)){
            try {
                wait();
                System.out.println("wait city ["+Thread.currentThread().getId()+"] is notified!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("this.city is"+this.city);
    }
}
