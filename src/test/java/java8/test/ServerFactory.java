package java8.test;

import chain.test.HttpServer;
import chain.test.Job;

public class ServerFactory implements Factory<Job>{

	@Override
	public Job get() {
		return new HttpServer();
	}

	public ServerFactory create(Factory<Job> job){
		return this;
	}
	
}
