package listener;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

public class Listener extends CompletableFuture<Boolean>{

	 private final AtomicBoolean started;
	
	 public Listener(AtomicBoolean started) {
	        this.started = started;
	    }
	 
	public void onSuccess(){
		if(isDone()) return;
			complete(started.get());
	}
	
	public void onFail(){
		
	}
	
	
	public void tryStart(){
		
	}
}
