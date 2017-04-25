package jvm_test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReeterLock implements Runnable {
	public static final ReentrantLock lock = new ReentrantLock();
	
	public static Condition c1=lock.newCondition();
	public static int i;

	public void run() {
		try {
			lock.lock();
			c1.await();
			System.out.println("---------");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {

		ReeterLock l = new ReeterLock();

		Thread t = new Thread(l);
		t.start();
		try {	
			Thread.sleep(3000l);
		
			lock.lock();
			c1.signal();
			Thread.sleep(3000l);
			lock.unlock();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*Thread t2 = new Thread(l);
		t.start();
		t2.start();
		try {
			t.join();
			t.join();
			System.out.println(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
	}
}
