package com.zp.tooluse;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierBase {

	static CyclicBarrier c = new CyclicBarrier(2);
	
	public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getId());
				try {
					c.await();
					System.out.println(Thread.currentThread().getId()+" is going");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("sleeping......");
			}
		}).start();
		
		System.out.println("main will sleep....");
		Thread.sleep(2000);
		c.await();
		System.out.println("All are complete....");
	}
}
