package com.zp.rwLock;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class SyncTest {
	static final int threadRatio = 10;
	static final int threadBaseCount = 3;
	static CountDownLatch countDownLatch = new CountDownLatch(1);
	
	private static class ReadThread implements Runnable {
		
		private GoodsService goodService;
		
		public ReadThread(GoodsService goodsService){
			this.goodService = goodsService;
		}
		
		@Override
		public void run() {
			try {
				//发令枪，等待所有线程准备好同时开始
				countDownLatch.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long start = System.currentTimeMillis();
			for(int i = 0;i<100;i++){
				goodService.getGoodsVo();
			}
			long dong = System.currentTimeMillis() - start;
			System.out.println(Thread.currentThread().getName()+"读取数据库耗时(ms)："+dong);
		}
		
	}
	
	private static class WriteThread implements Runnable{

		private GoodsService goodService;
		public WriteThread(GoodsService goodsService){
			this.goodService = goodsService;
		}
		@Override
		public void run() {
			try {
				//发令枪，等待所有线程准备好同时开始
				countDownLatch.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long start = System.currentTimeMillis();
			Random r = new Random();
			for(int i = 0;i<10;i++){
				try {
					Thread.sleep(50);
				} catch (Exception e) {
					// TODO: handle exception
				}
				goodService.changeNum(r.nextInt(100));
			}
			long dong = System.currentTimeMillis() - start;
			System.out.println(Thread.currentThread().getName()+"写取数据库耗时（ms）："+dong);
		}
		
	}
	
	
	public static void main(String[] args) {
		GoodsVo goodsVo = new GoodsVo("testgood001",1000000, 1000);
		GoodsService rw = new SyncNumServiceImpl(goodsVo);
		//读线程
		for(int i=0;i<threadBaseCount*threadRatio;i++){
			Thread readT = new Thread(new ReadThread(rw));
			readT.start();
		}
		//写线程
		for(int i=0;i<threadBaseCount;i++){
			Thread writeT = new Thread(new WriteThread(rw));
			writeT.start();
		}
		countDownLatch.countDown();
	}
}
