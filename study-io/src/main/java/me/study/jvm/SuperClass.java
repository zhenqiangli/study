package me.study.jvm;

public class SuperClass {

	static {
		System.out.println("SuperClass init.");
	}
	
	public static int value = 123;
	
	public static void method() {
		System.out.println("super Method init.");
	}
	
}
