package com.zp.bq;

public class BqLCTest {

	public static void main(String[] args) {
		BlockQueueLC<Integer> bq = new BlockQueueLC<Integer>(15);
		Thread push = new Thread(new PushThread(bq));
		Thread pop = new Thread(new PopThread(bq));
		pop.start();
		push.start();
	}
	
	private static class PushThread implements Runnable{

		BlockQueueLC<Integer> bq;
		public PushThread(BlockQueueLC<Integer> bq){
			this.bq = bq;
		}
		@Override
		public void run() {
			String name = Thread.currentThread().getName();
			int i =20;
			while(i>0){
				try {
					Thread.sleep(1000);
                    System.out.println(" i="+i+"will push");
					bq.enqueue(i--);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	private static class PopThread implements Runnable{

		BlockQueueLC<Integer> bq;
		public PopThread(BlockQueueLC<Integer> bq){
			this.bq = bq;
		}
		
		@Override
		public void run() {
			String name = Thread.currentThread().getName();
			while(true){
				try {
					System.out.println(name+" will pop......");
                    Integer i =bq.dequeue();
                    System.out.println("i="+i+" alread pop");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}
}
