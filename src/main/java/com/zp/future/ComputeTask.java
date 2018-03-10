package com.zp.future;

import java.util.concurrent.Callable;

public class ComputeTask implements Callable<Integer>{

	private Integer result = 0;
	private String taskName="";
	

	public ComputeTask(Integer result, String taskName) {
		super();
		this.result = result;
		this.taskName = taskName;
		System.out.println(taskName+"子任务已经创建");
	}


	@Override
	public Integer call() throws Exception {
		for(int i=0;i<100 ;i++){
			result+=i;
		}
		Thread.sleep(1000);
		System.out.println(taskName+" 子任务已经完成");
		return result;
	}

}
