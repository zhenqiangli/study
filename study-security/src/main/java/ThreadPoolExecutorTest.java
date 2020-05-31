import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.print.DocFlavor.READER;

public class ThreadPoolExecutorTest {

	static ExecutorService cachedThreadPool;

	static ExecutorService fixedThreadPool;

	public static void main(String[] args) throws InterruptedException {
		
		
		ConcurrentHashMap<String, String> concurrentHashMap = null;

		cachedThreadPool = Executors.newCachedThreadPool();

		fixedThreadPool = Executors.newFixedThreadPool(1);

		fixedThreadPool = Executors.newSingleThreadExecutor();

		fixedThreadPool = Executors.newScheduledThreadPool(1);

		int corePoolSize = 10;

		int maximumPoolSize = 10;

		long keepAliveTime = 1000L;

		RejectedExecutionHandler handle = null;

		ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,
				maximumPoolSize, keepAliveTime, TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<Runnable>(100), handle);

		Runnable runnable = null;
		executor.execute(runnable);

		ConcurrentHashMap map = new ConcurrentHashMap();
		map.put("a", "b");
		map.get("a");
		map.size();

		ThreadLocal<String> threadLocal = new ThreadLocal<String>();

		Thread.interrupted();

		Thread.sleep(10000L);

		ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	}

}
