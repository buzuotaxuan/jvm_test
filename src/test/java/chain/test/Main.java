package chain.test;

import org.junit.Test;

import java8.test.ServerFactory;

public class Main {

	@Test
	public void main(){
		Server server=new Server();
		server.start();
	}
	@Test
	public void jdk(){
		ServerFactory s=new ServerFactory();
		s.create(()->new HttpServer());
//		Job job=s.get();
	}
}
