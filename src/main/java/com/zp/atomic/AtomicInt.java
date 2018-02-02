package com.zp.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicInt {
	
	private static AtomicInteger ai = new AtomicInteger(1);
	public static void main(String[] args) {
		ai.getAndAdd(1);
//		System.out.println(ai.getAndAdd(1));
//		System.out.println(ai.get());
		System.out.println(ai.incrementAndGet());
	}
}
