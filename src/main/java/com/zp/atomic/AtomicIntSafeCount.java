package com.zp.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntSafeCount {

	private static AtomicInteger ai = new AtomicInteger(0);
	
	public void safeCount() {
		for (;;) {
			int i = ai.get();
			boolean flag = ai.compareAndSet(i, i++);
			if(flag){
				break;
			}
		}
	}
}
