package com.zp.tooluse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * 只适用于两个线程交换数据，多余两个时会把先执行到ex.exchange()的两个进行交换
 * @author lenovo
 *
 */
public class ExchangeCase {
	private static Exchanger<List<String>> ex = new Exchanger<List<String>>();
	
	public static void main(String[] args) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				List<String> list = new ArrayList<String>();
				list.add(Thread.currentThread().getId()+" is A1");
				list.add(Thread.currentThread().getId()+" is A2");
				list.add(Thread.currentThread().getId()+" is A3");
				System.out.println(list+"    A");
				try {
					list = ex.exchange(list);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for(String item:list){
                    System.out.println(Thread.currentThread().getId()+":"+item);
                }
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				List<String> list = new ArrayList<String>();
				list.add(Thread.currentThread().getId()+" is B1");
				list.add(Thread.currentThread().getId()+" is B2");
				list.add(Thread.currentThread().getId()+" is B3");
				System.out.println(list+"    B");
				try {
					Thread.sleep(1000000);
					list = ex.exchange(list);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for(String item:list){
                    System.out.println(Thread.currentThread().getId()+":"+item);
                }
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				List<String> list = new ArrayList<String>();
				list.add(Thread.currentThread().getId()+" is C1");
				list.add(Thread.currentThread().getId()+" is C2");
				list.add(Thread.currentThread().getId()+" is C3");
				System.out.println(list+"    C");
				try {
					list = ex.exchange(list);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for(String item:list){
                    System.out.println(Thread.currentThread().getId()+":"+item);
                }
			}
		}).start();
	}
}
