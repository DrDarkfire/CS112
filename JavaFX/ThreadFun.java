import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadFun {
	int sum = 0;
	AtomicInteger atomicSum = new AtomicInteger(0);

	public ThreadFun() throws InterruptedException {
		repeatAddTest();
		printTest();
		synchTest();
		conditionTest();

	}

	class RepeatAdder implements Runnable {
		int amount, numIter;

		public RepeatAdder(int amount, int numIter) {
			this.amount = amount;
			this.numIter = numIter;
		}

		@Override
		public void run() { // a task run on a seperate thread when the thread is started
			for (int i = 0; i < numIter; i++) {
				// not thread-safe
				sum += amount;
				// even worse:
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
		System.out.println(sum); // 6000 if there are no thread problems...
		//... but non-thread-safe programming causes problem
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
		executor.shutdown(); // shuts down executor not allowing new tasks 
		// but allowing tasks in process to complete
		executor.awaitTermination(1000, TimeUnit.MILLISECONDS);
		// awaits either (1) all tasks completed, or (2) time to elapse
		System.out.println();
		Thread max = new Thread(new PrintTask('A', 25));
		max.setPriority(Thread.MAX_PRIORITY);
		Thread norm = new Thread(new PrintTask('B', 25));
		norm.setPriority(Thread.NORM_PRIORITY);
		Thread min = new Thread(new PrintTask('C', 25));
		min.setPriority(Thread.MIN_PRIORITY);
		max.start();
		norm.start();
		min.start();
		// In Windows and Linux, we normally don't see the prioritization
		// behavior that we would expect
		max.join();
		norm.join();
		min.join();
		System.out.println();
	}
	private void synchTest() throws InterruptedException {
		sum = 0;
		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i = 0; i < 100; i++)
			executor.execute(() -> unsafeIncrement());
		executor.awaitTermination(1000, TimeUnit.MILLISECONDS);
		System.out.println("Sum after 100 unsafe increments: " + sum);
		sum = 0;
		for (int i = 0; i < 100; i++)
			executor.execute(() -> safeIncrement());
		executor.awaitTermination(1000, TimeUnit.MILLISECONDS);
		System.out.println("Sum after 100 safe increments (synchronized method): " + sum);
		sum = 0;
		for (int i = 0; i < 100; i++)
			executor.execute(() -> safeIncrement2());
		executor.awaitTermination(1000, TimeUnit.MILLISECONDS);
		System.out.println("Sum after 100 safe increments (synchronized code block): " + sum);          
		sum = 0;
		for (int i = 0; i < 100; i++)
			executor.execute(() -> safeIncrement3());
		executor.awaitTermination(1000, TimeUnit.MILLISECONDS);
		System.out.println("Sum after 100 safe increments (lock/unlock): " + sum);        
		executor.shutdown();
	}

	private void unsafeIncrement() {
		int newSum = sum + 1;
		sleep(1);
		sum = newSum;
	}

	private synchronized void safeIncrement() {
		int newSum = sum + 1;
		sleep(1);
		sum = newSum;
	}      

	private void safeIncrement2() {
		synchronized (this) {
			int newSum = sum + 1;
			sleep(1);
			sum = newSum;
		}
	}      

	private Lock incrementLock = new ReentrantLock();

	private void safeIncrement3() {
		incrementLock.lock();
		int newSum = sum + 1;
		sleep(1);
		sum = newSum;
		incrementLock.unlock();
	}      

	private Lock changeLock = new ReentrantLock();
	private Condition newAdd = changeLock.newCondition();

	private void conditionTest() throws InterruptedException {
		sum = 0;
		int max = 99;
		ArrayList<Integer> amounts = new ArrayList<>();
		for (int i = 1; i <= max; i++)
			amounts.add(i);
		Collections.shuffle(amounts);    
		ExecutorService executor = Executors.newCachedThreadPool();
		for (int amount : amounts)
			executor.execute(() -> subtract(amount));
		Collections.shuffle(amounts);
		for (int amount : amounts)
			executor.execute(() -> add(amount));
		executor.shutdown();
		executor.awaitTermination(1000, TimeUnit.MILLISECONDS);
		System.out.println("Done.");
	}

	private void add(int amount) {
		changeLock.lock();
		try {
			int newSum = sum + amount;
			System.out.printf("%4d + %2d = %4d\n", sum, amount, newSum);
			sum = newSum;
			newAdd.signalAll();
		}
		finally { // ensures that we don't create a deadlock
			changeLock.unlock();
		}
	}

	private void subtract(int amount) {
		changeLock.lock();
		try {
			while (sum < amount)
				newAdd.await();
			int newSum = sum - amount;
			System.out.printf("%4d - %2d = %4d\n", sum, amount, newSum);
			sum = newSum;
			newAdd.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		finally { // ensures that we don't create a deadlock
			changeLock.unlock();
		}
	}
	
	public static void sleep(long millis) {
        try {
               Thread.sleep(millis); // sleep the current thread a given number of ms
        } catch (InterruptedException e) { // rarely occurs
               e.printStackTrace();
        }
  }



	public static void main(String[] arghs) throws InterruptedException {
		new ThreadFun();

	}

}
