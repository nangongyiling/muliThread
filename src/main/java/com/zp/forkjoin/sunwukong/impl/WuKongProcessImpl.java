package com.zp.forkjoin.sunwukong.impl;

import com.zp.forkjoin.service.IProcessTaoZi;
import com.zp.forkjoin.vo.PanTao;

public class WuKongProcessImpl implements IProcessTaoZi{

	@Override
	public void processTaoZi(PanTao taozi) {
		//看看桃子，放到口袋里
		inBag();
	}

	private void inBag(){
		try {
			Thread.sleep(1);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
