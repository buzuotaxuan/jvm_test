package chain.test;

public class JsServer  extends Job{

	String input;
	
	/*public  JsServer(String input) {
		this.input=input;
	}*/
	@Override
	protected void start() {
		System.out.println("JsServer start "+this.input);
		startNext();
	}

	@Override
	protected void stop() {
		System.out.println("JsServer stop");
		stopNext();
	}

}
