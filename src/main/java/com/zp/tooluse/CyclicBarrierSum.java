package com.zp.tooluse;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierSum {

	static CyclicBarrier c = new CyclicBarrier(5,new SumThread());
	//子线程结果存放的缓存
	private static ConcurrentHashMap<String,Integer> resultMap = new ConcurrentHashMap<String,Integer>();
	
	//所有子线程达到屏障后，会执行这个Runnable的任务
	private static class SumThread implements Runnable{

		@Override
		public void run() {
			int result =0;
			for(Map.Entry<String,Integer> workResult:resultMap.entrySet()){
				result = result+workResult.getValue();
			}
			System.out.println("result="+result);
			System.out.println("完全可以做与子线程，统计无关的事情。。。。什么事都行");
		}
		
	}
	
	//工作线程，也就是子线程
	private static class WorkThread implements Runnable{

		private Random r= new Random();
		@Override
		public void run() {
			// TODO Auto-generated method stub
			int t = r.nextInt(1000)+300;
			System.out.println(Thread.currentThread().getId()+":t="+t);
			resultMap.put(Thread.currentThread().getId()+"", t);
			try {
				Thread.sleep(t+100);
				c.await();
				 
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		for(int i=0;i<5;i++){
			Thread thread = new Thread(new WorkThread());
			thread.start();
		}
	}
}
