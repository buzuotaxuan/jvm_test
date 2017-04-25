package chain.test;

public class Server {
	
	Chain chain=new Chain();
	
	public Server(){
		chain.boot().setNext(new HttpServer())
		.setNext(new SocketServer());
		
	}
	
	public void start(){
		chain.start();
	}
	
	public void stop(){
		chain.stop();
	}
	
}
