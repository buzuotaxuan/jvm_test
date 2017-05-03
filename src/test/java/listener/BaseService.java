package listener;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class BaseService implements Service{
	
	protected final AtomicBoolean started = new AtomicBoolean();
	

	@Override
	public void start(Listener listener) {
		tryStart(listener, this::doStart);
	}

	@Override
	public void stop(Listener listener) {
		
	}

	@Override
	public CompletableFuture<Boolean> start() {
		Listener listener=new Listener(started);
		
		start(listener);
		
		return listener;
	}
	
	public void tryStart(Listener listener,FuncionEx fe){
		
		if(started.compareAndSet(false, true)){
			init();
			fe.applay(listener);
		}
	}

	
	public void doStart(Listener listener){
		listener.onSuccess();
	}
	
	
	@Override
	public CompletableFuture<Boolean> stop() {
		return null;
	}

	@Override
	public boolean syncStart() {
		return start().join();
	}

	@Override
	public boolean syncStop() {
		return stop().join();
	}

	@Override
	public void init() {
		System.out.println("init base");
	}

	@Override
	public boolean isRunning() {
		return started.get();
	}
	
	@FunctionalInterface
	protected interface FuncionEx{
		void applay(Listener listener);
	}
}
