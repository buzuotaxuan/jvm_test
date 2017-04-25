package chain.test;

public class Chain {
	
	Job boot=new Job(){

		@Override
		protected void start() {
			System.out.println("boot start");
			startNext();
		}

		@Override
		protected void stop() {
			System.out.println("boot stop");
			stopNext();
		}
		
	};
	
	public Chain boot(){
		return this;
	}
	
	private Job last=boot;
	public Chain setNext(Job job){
		
		this.last=last.setNext(job);
		return this;
	}
	
	public static Chain chain(){
		return new Chain();
	}
	
	public void start(){
		boot.start();
	}
	
	public void stop(){
		boot.stop();
	}
}
