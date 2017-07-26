package me.study.concurrency;

import java.util.concurrent.TimeUnit;

public class WaitNotify {
	
	static boolean flag = true;
	static Object lock = new Object();
	
	public static void main(String[] args) throws InterruptedException {
		
		Thread waitThread = new Thread(new Wait(), "WaitThread");
		waitThread.start();
		TimeUnit.SECONDS.sleep(1);
		
		Thread notifyThread = new Thread(new Notify(), "NotifyThread");
		notifyThread.start();
		
	}
	
	static class Wait implements Runnable {

		@Override
		public void run() {
			synchronized (lock) {
				while(flag) {
					try {
						System.out.println(Thread.currentThread() + " flag is ture. wait");
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				System.out.println(Thread.currentThread() + " flag is false. wait");
			}
		}
		
	}
	
	static class Notify implements Runnable {
		
		@Override
		public void run() {
			synchronized (lock) {
				System.out.println(Thread.currentThread() + " hold lock. notify");
				lock.notifyAll();
				flag = false;
				sleepSecond(6);
			}
			
			synchronized (lock) {
				System.out.println(Thread.currentThread() + " hold lock again. sleep");
				sleepSecond(1);
			}
			
		}
		
	}
	
	static void sleepSecond(int second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
	

}
