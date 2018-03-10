package com.zp.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScheduleTask implements Runnable{
	
	public static enum OperType{
		None,OnlyThrowException,CatheException
	}

	public static SimpleDateFormat formater = new SimpleDateFormat("yyyy-mm-dd hh24:mm:ss");
	
	private OperType operType;
	
	
	public ScheduleTask(OperType operType) {
		super();
		this.operType = operType;
	}


	@Override
	public void run() {
		switch(operType){
			case OnlyThrowException:
				System.out.println("Exception not catch:"+formater.format(new Date()));
				throw new RuntimeException("OnlyThrowException");
			case CatheException:
				try {
					throw new RuntimeException("CatheException");
				} catch (Exception e) {
					System.out.println("Exception be catched:"+formater.format(new Date()));
					
				}
				break;
			case None:
				System.out.println("None :"+formater.format(new Date()));
		}
	}

}
