package chain.test;

public class HttpServer extends Job{

	@Override
	protected void start() {
		System.out.println("http server start");
		startNext();
	}

	@Override
	protected void stop() {
		System.out.println("http server stop");
		stopNext();
	}

}
