package com.zp.forkjoin.service;

import com.zp.forkjoin.vo.PanTao;

/**
 * 摘桃子
 * @author lenovo
 *
 */
public interface IPickTaoZi {
	
	boolean pick(PanTao[] src,int index);
}
