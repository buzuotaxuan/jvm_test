package chain.test;

public abstract class Job {
	
	protected abstract void start();
	
	protected abstract void stop();
	
	protected Job next;
	
	public Job setNext(Job job){
		this.next=job;
		return job;
	}
	
	
	public void startNext(){
		if(null!=next)
			next.start();
	}
	
	public void stopNext(){
		if(null!=next)
			next.stop();
	}
}
