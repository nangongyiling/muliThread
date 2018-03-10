package com.zp.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FutureSample {

	public static void main(String[] args) {
		FutureSample futureSample = new FutureSample();
		//创建任务集合
		List<FutureTask<Integer>> taskList = new ArrayList<FutureTask<Integer>>();
		
		//另一种方式
		List<Future<Integer>> futureList = new ArrayList<Future<Integer>>();
		ExecutorService exec = Executors.newFixedThreadPool(5);
		for(int i=0;i<10;i++){
			// 传入Callable对象创建FutureTask对象
			FutureTask<Integer> ft = new
					FutureTask<Integer>(new ComputeTask(i,"task_"+i));
//			taskList.add(ft);
//			exec.submit(ft);
			Future<Integer> result = exec.submit(new ComputeTask(i,"task_"+i));
			futureList.add(result);
		}
		System.out.println("主线程已经提交任务，做自己的事!");
		
		//开始统计各计算线程计算结果
		int totalResult =0;
		for(Future<Integer> ft:taskList){
			try {
				//FutureTask的get方法会自动阻塞，知道获取计算结果为止
				totalResult+=ft.get();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		System.out.println("total = "+totalResult);
		exec.shutdown();
	}

}
