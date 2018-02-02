package com.zp.rwLock;

public class SyncNumServiceImpl implements GoodsService{

	private GoodsVo goodVo;
	
	public SyncNumServiceImpl(GoodsVo goodVo) {
		this.goodVo = goodVo;
	}
	@Override
	public synchronized GoodsVo getGoodsVo() {
		try {
			Thread.sleep(5);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return this.goodVo;
	}

	@Override
	public synchronized void changeNum(int changeNum) {
		try {
			Thread.sleep(50);
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.goodVo.setGoodsVoNumber(changeNum);
	}

}
