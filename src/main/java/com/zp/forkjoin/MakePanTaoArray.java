package com.zp.forkjoin;

import java.util.Random;

import com.zp.forkjoin.vo.Color;
import com.zp.forkjoin.vo.PanTao;
import com.zp.forkjoin.vo.Size;

public class MakePanTaoArray {
	
	//数组长度
	public static final int ARRAY_LENGTH = 40000;
	
	//作为基准的值
	public static final int STANDARD_VAL = 66694523;
	
	public static PanTao[] makeArray(){
		//new 三个随机数发生器
		Random rColor = new Random();
		Random rSize = new Random();
		Random rYear = new Random();
		PanTao[] result = new PanTao[ARRAY_LENGTH];
		for(int i=0;i<ARRAY_LENGTH;i++){
			PanTao panTao = new PanTao(
					rColor.nextBoolean()?Color.RED:Color.GREEN,
					rSize.nextBoolean()?Size.BIG:Size.SMALL,
					rYear.nextInt(9001));
			result[i] = panTao;
		}
		return result;
	}

	public static void main(String[] args) {
		PanTao[] p=makeArray();
		for(PanTao panTao:p){
			System.out.println(panTao);
		}
	}
}
