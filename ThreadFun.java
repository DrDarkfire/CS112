import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadFun {

	int sum = 0;
	AtomicInteger atomicSum = new AtomicInteger(0);
	
	public ThreadFun() throws InterruptedException {
		repeatAddTest();
		printTest();
	}
	
	class RepeatAdder implements Runnable {
		int amount, numIter;
		
		public RepeatAdder(int amount, int numIter) {
			this.amount = amount;
			this.numIter = numIter;
		}

		@Override
		public void run() { // a task run on a separate thread when started
			for (int i = 0; i < numIter; i++) {
				// Not thread-safe:
//				sum += amount;
				// Even worse:
				int newSum = sum + amount;
				// first thread reads sum (0) and adds 1 (newSum == 1)
				// second thread reads sum (0) and adds 2 (newSum == 2)
				
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				sum = newSum;
				// first thread writes newSum (1) to sum
				// second thread writes newSum (2) to sum
				
				// thread-safe
				atomicSum.getAndAdd(amount);
			}
		}
		
	}
	
	private void repeatAddTest() throws InterruptedException {
		Thread[] thread = new Thread[3];
		for (int i = 0; i < 3; i++) {
			thread[i] = new Thread(new RepeatAdder(i + 1, 1000));
			thread[i].start();
		}
		for (int i = 0; i < 3; i++)
			thread[i].join(); // wait for thread[i] to complete before continuing
		System.out.println(sum); // 6000 w/out thread problems...
		// ... but non-thread-safe programming causes problems.
		System.out.println(atomicSum);
	}
	
	class PrintTask implements Runnable {
		char ch;
		int numIter;
		
		public PrintTask(char ch, int numIter) {
			this.ch = ch;
			this.numIter = numIter;
		}

		@Override
		public void run() {
			for (int i = 0; i < numIter; i++)
				System.out.print(ch);
		}		
	}
	
	private void printTest() throws InterruptedException {
		ExecutorService executor = Executors.newCachedThreadPool();
		executor.execute(new PrintTask('A', 25));
		executor.execute(new PrintTask('B', 25));
		executor.execute(new PrintTask('C', 25));
		executor.shutdown(); // shutdown the executor, not allowing new tasks
							 // but allowing tasks in process to complete
		executor.awaitTermination(1000, TimeUnit.MILLISECONDS);
		// awaits either (1) all tasks completed, or (2) time to elapse
		System.out.println();
		
		// experimenting with thread priorities
		Thread max = new Thread(new PrintTask('A', 25));
		max.setPriority(Thread.MAX_PRIORITY);
		Thread norm = new Thread(new PrintTask('B', 25));
		norm.setPriority(Thread.NORM_PRIORITY);
		Thread min = new Thread(new PrintTask('C', 25));
		min.setPriority(Thread.MIN_PRIORITY);
		max.start();
		norm.start();
		min.start();
		// In Windows and Linux, we don't necessarily see the prioritization
		// behavior that we would expect.
		max.join();
		norm.join();
		min.join();
		System.out.println();
	}

	public static void main(String[] args) throws InterruptedException {
		new ThreadFun();
	}

}
