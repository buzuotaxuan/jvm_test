package test;

 class Config {
	final ListeneA listener=new ListeneA();
	public static Config I=new Config(); 
	
	public static Config build(){
		return I;
	}

	public void setListener(Listener listener){
		this.listener.teset(listener);
	}
	
	public Listener getListener(){
		return listener;
	}
	
}
