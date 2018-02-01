package com.zp.rwLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RwNumImpl implements GoodsService{
	
	private GoodsVo goodsVo;
	
	private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	
	private final Lock r = lock.readLock();
	
	private final Lock w = lock.writeLock();

	public RwNumImpl(GoodsVo goodsVo){
		this.goodsVo =  goodsVo;
	}
	@Override
	public GoodsVo getGoodsVo() {
		r.lock();
		try {
			try {
				Thread.sleep(5);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return this.goodsVo;
		} finally{
			r.unlock();
		}
	}

	@Override
	public void changeNum(int changeNum) {
		w.lock();
		try {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.goodsVo.setGoodsVoNumber(changeNum);
		} finally{
			w.unlock();
		}
	}

}
