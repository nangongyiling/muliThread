package com.zp.completionService;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;

public class CompletionTest {

	private final int POOL_SIZE=5;
	private final int TOTAL_TASK = 10;
	
	//方法一，自己写集合来实现获取线程池中任务的返回结果
	public void testByQueue() throws InterruptedException, ExecutionException{
		ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);
		BlockingQueue<Future<String>> queue = new LinkedBlockingDeque<Future<String>>();
		
		//向里面扔任务
		for(int i=0;i<TOTAL_TASK;i++){
			Future<String> future = pool.submit(new WorkTask("ExecTask_"+i));
			queue.add(future);
		}
		
		for(int i=0;i<TOTAL_TASK;i++){
			System.out.println("ExecTask:"+queue.take().get());
			
		}
		pool.shutdown();
	}
	
	//方法二，通过CompletionService来实现获取线程池中任务的返回结果
	public void testByCompletion() throws InterruptedException, ExecutionException{
		ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);
		CompletionService<String> service = new ExecutorCompletionService<String>(pool);
		//向里面扔任务
		for(int i=0;i<TOTAL_TASK;i++){
			service.submit(new WorkTask("ExecTask_"+i));
			
		}
		
		//检查线程池任务执行结果
		for(int i=0;i<TOTAL_TASK;i++){
			Future<String> future = service.take();
			System.out.println("CompletionService:"+future.get());
		}
		
		pool.shutdown();
	}
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CompletionTest test = new CompletionTest();
//		test.testByQueue();
		test.testByCompletion();
	}
	
}
