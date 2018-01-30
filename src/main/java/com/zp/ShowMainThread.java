package com.zp;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class ShowMainThread {
    public static void main(String[] args){
        //java虚拟机线程管理系统
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false,false);
        for(ThreadInfo info :threadInfos){
            System.out.print(info);
        }
    }
}
