package jvm_test;

public class SimpleNTA {
	static final Object obj=new Object();
	
	public static class T1 extends Thread{
		public void run()
		{
			synchronized(obj){
				System.out.println("i m t1");
				try {
					obj.wait();
					System.out.println("t1 end");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
				
		}
	}
	
	public static class T2 extends Thread{
		public void run(){
			synchronized (obj) {
				System.out.println("i m t2");
				obj.notifyAll();
				try {
					Thread.sleep(1000l);
					System.out.println("t2 end");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	public static void main(String[] args) {
		Thread t1=new T1();
		t1.start();
		
		Thread t2=new T1();
		t2.start();
		
		Thread t3=new T2();
		t3.start();
	}
}
