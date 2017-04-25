package chain.test;

public class SocketServer extends Job{

	@Override
	protected void start() {
		System.out.println("socketServer start");
		startNext();
	}

	@Override
	protected void stop() {
		System.out.println("socketServer stop");
		stopNext();
	}

}
