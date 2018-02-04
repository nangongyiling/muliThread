package com.zp.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class TwoThreadsLock implements Lock{
	
	private static class Sync extends AbstractQueuedSynchronizer{

		Sync(int count)
		{
			if(count<=0){
				throw new IllegalArgumentException("count must large than zero");
			}
			setState(count);
		}
		@Override
		protected boolean tryAcquire(int arg) {
			// TODO Auto-generated method stub
			return super.tryAcquire(arg);
		}

		@Override
		protected boolean tryRelease(int arg) {
			// TODO Auto-generated method stub
			return super.tryRelease(arg);
		}

		@Override
		protected int tryAcquireShared(int arg) {
			for(;;){
				int current = getState();
				int newCount  =current - arg;
				if(newCount <0 || compareAndSetState(current, newCount)){
					return newCount;
				}
				
			}
		}

		@Override
		protected boolean tryReleaseShared(int arg) {
			for(;;){
				int current = getState();
				int newCount = current + arg;
				if(compareAndSetState(current, newCount)){
					return true;
				}
			}
		}

		@Override
		protected boolean isHeldExclusively() {
			// TODO Auto-generated method stub
			return super.isHeldExclusively();
		}
		
		final Condition newCondition(){
			return new ConditionObject();
		}
	}

	private final Sync sync = new Sync(2);
	@Override
	public void lock() {
		sync.tryAcquireShared(1);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		sync.acquireInterruptibly(1);
	}

	@Override
	public boolean tryLock() {
		return sync.tryAcquireShared(1)>=0;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return sync.tryAcquireSharedNanos(1, unit.toNanos(time));
	}

	@Override
	public void unlock() {
		sync.releaseShared(1);
	}

	@Override
	public Condition newCondition() {
		return sync.newCondition();
	}

}
