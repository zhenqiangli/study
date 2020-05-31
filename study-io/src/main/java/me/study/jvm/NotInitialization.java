package me.study.jvm;

/**
 * @author zhenqiang-li
 */
public class NotInitialization {

	public static void main(String[] args) {

		SubClass.method();
//		SubClass[] subClasses = new SubClass[10];

	}

}
