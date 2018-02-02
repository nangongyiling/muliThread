package com.zp.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicArray {

	private static int[] value = new int[]{1,2};
	private static AtomicIntegerArray ai = new AtomicIntegerArray(value);
	
	public static void main(String[] args) {
		ai.addAndGet(0, 1);
		//数组的原子操作是复制一个数据，所有的改动对原数组不影响
		System.out.println(ai.get(0));
		System.out.println(value[0]);
	}
}
