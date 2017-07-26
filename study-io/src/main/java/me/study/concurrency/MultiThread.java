package me.study.concurrency;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class MultiThread {

	public static void main(String[] args) {
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		
		ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
		for(ThreadInfo threadInfo : threadInfos) {
			System.out.println("threadId:" + threadInfo.getThreadId() 
					+ " threadName:" + threadInfo.getThreadName());
		}
	}

}
