package com.zp.forkjoin.sunwukong.impl;

import com.zp.forkjoin.service.IPickTaoZi;
import com.zp.forkjoin.service.IProcessTaoZi;
import com.zp.forkjoin.vo.Color;
import com.zp.forkjoin.vo.PanTao;
import com.zp.forkjoin.vo.Size;

public class WuKongPickImpl implements IPickTaoZi{

	private IProcessTaoZi processTaoZi;
	
	public WuKongPickImpl(IProcessTaoZi processTaoZi) {
		this.processTaoZi = processTaoZi;
	}

	@Override
	public boolean pick(PanTao[] src, int index) {
		if(src[index].getColor() == Color.RED &&
				src[index].getSize() == Size.BIG&&
				src[index].getYear() >=6000){
			processTaoZi.processTaoZi(src[index]);
			return true;
		}else{
			return false;
		}
	}

}
