package me.study.designpattern.singleton;

/**
 * 单例模式通用代码
 * 
 * @author zhenqiang
 *
 */
public class Singleton {
	
	private static Singleton singleton = new Singleton();
	
	/**
	 * 限制产生多个对象
	 */
	private Singleton() {
		
	}
	
	/**
	 * 通过该方法获得实例对象
	 * @return
	 */
	public static Singleton getSingleton() {
		return singleton;
	}
	
	/**
	 * 类中的其他方法，尽量是static的
	 */
	public static void doSomething() {
		
	}

}
