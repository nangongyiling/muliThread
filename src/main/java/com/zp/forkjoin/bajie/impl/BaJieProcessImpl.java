package com.zp.forkjoin.bajie.impl;

import com.zp.forkjoin.service.IProcessTaoZi;
import com.zp.forkjoin.vo.PanTao;

public class BaJieProcessImpl implements IProcessTaoZi{

	@Override
	public void processTaoZi(PanTao taozi) {
		eat();
	}

	private void eat(){
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
