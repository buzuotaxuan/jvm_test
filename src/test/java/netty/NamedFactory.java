package netty;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NamedFactory implements ThreadFactory{
	private final AtomicInteger threadNumber=new AtomicInteger(1);
	
	private final ThreadGroup threadGroup;
	
	private final String preFixName;
	
	private static final String defaultThreadName="test-thrid";
	public NamedFactory(){
		this(defaultThreadName);
	}
	
	public NamedFactory(String preFixName){
		this.preFixName=preFixName;
		this.threadGroup=Thread.currentThread().getThreadGroup();
	}
	
	@Override
	public Thread newThread(Runnable r) {
		return newThread(r,"none");
	}

	public Thread newThread(Runnable r,String name){
		Thread thread=new Thread(threadGroup,this.preFixName+"-"+name);
		thread.setDaemon(true);
		return thread;
	}
}
