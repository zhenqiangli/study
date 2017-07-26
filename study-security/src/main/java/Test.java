import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test {

	static ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 6, 5,
			TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),
			new ThreadPoolExecutor.AbortPolicy());
	
	static Executor cachedThreadPool = Executors.newCachedThreadPool();
	static Executor fixedThreadPool = Executors.newFixedThreadPool(5);

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			fixedThreadPool.execute(new PrintRunnable(i));
		}
	}

	static class PrintRunnable implements Runnable {

		int num;

		public PrintRunnable(int num) {
			this.num = num;
		}

		@Override
		public void run() {
			System.out.println("Thread " + num + " is running.");
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
