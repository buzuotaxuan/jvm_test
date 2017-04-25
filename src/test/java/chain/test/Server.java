package chain.test;

public class Server {
	
	Chain chain=new Chain();
	
	public Server(){
		chain.boot().setNext(new HttpServer())
		.setNext(new SocketServer()).setNext(()-> new HttpServer());
		
	}
	
	public void start(){
		chain.start();
	}
	
	public void stop(){
		chain.stop();
	}
	
}
