package me.study.jvm;

import java.io.InputStream;

public class ClassLoaderDemo {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		ClassLoader classLoader = new ClassLoader() {
			@Override
			public Class<?> loadClass(String name) throws ClassNotFoundException {
				try {
					String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
					InputStream inputStream = getClass().getResourceAsStream(fileName);
					if (inputStream == null) {
						return super.loadClass(name);
					}

					byte[] b = new byte[inputStream.available()];
					inputStream.read(b);
					return defineClass(name, b, 0, b.length);
				} catch (Exception e) {
					throw new ClassNotFoundException(name);
				}
			}
		};
		
		Object object = classLoader.loadClass("me.study.jvm.ClassLoaderDemo").newInstance();
		
		System.out.println(object.getClass());
		System.out.println(object instanceof me.study.jvm.ClassLoaderDemo);

	}

}
