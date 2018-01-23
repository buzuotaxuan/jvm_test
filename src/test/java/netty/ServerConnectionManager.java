package netty;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;

public class ServerConnectionManager implements ConnectionManager{
	private HashedWheelTimer timer;
	
	 private final ConcurrentMap<ChannelId, ConnectionHolder> connections = new ConcurrentHashMap<>();
	 
	 
	 private ConnectionHolderFactory holderFactory;
	 
	 
	 
	 public ServerConnectionManager(){
		 this.holderFactory = HeartbeatCheckTask::new;
	 }
	 
	public void init() {

		long tickTime = TimeUnit.SECONDS.toMillis(1);

		long wheel = TimeUnit.SECONDS.toMillis(3) / tickTime;

		this.timer = new HashedWheelTimer(new NamedFactory("checkTimer"), tickTime, TimeUnit.MILLISECONDS, (int) wheel);

	}

	@Override
	public Connection get(Channel channel) {
		return null;
	}

	@Override
	public Connection removeAndClose(Channel channel) {
		return null;
	}

	@Override
	public void add(Connection connection) {
		connections.putIfAbsent(connection.getChannel().id(),holderFactory.create(connection));
	}
	
	
	private class HeartbeatCheckTask implements ConnectionHolder, TimerTask{

		
		private byte timeoutTimes = 0;
        private Connection connection;
        
        
		HeartbeatCheckTask(Connection connection){
			this.connection=connection;
			
			this.startTimeout();
		}
		
		
		
		private void startTimeout() {
			  Connection connection = this.connection;
			  
	            if (connection != null && connection.isConnected()) {
	                int timeout = connection.getSessionContext().heartbeat;
	                timer.newTimeout(this, timeout, TimeUnit.MILLISECONDS);
	            }
		}


		@Override
		public void run(Timeout timeout) throws Exception {
			Connection connection = this.connection;
			
			if(connection==null||!connection.isConnected()){
				return;
			}
			if(connection.isReadTimeout()){
				if(++timeoutTimes>3){
					  connection.close();
				}
			}else{
				timeoutTimes=0;
			}
			
			startTimeout();
			
		}

		@Override
		public Connection get() {
			return connection;
		}

		@Override
		public void close() {
			if (connection != null) {
                connection.close();
                connection = null;
            }
		}
		
	}

	@Override
	public int getConnNum() {
		return 0;
	}

	@Override
	public void destroy() {
		
	}
	
	 private interface ConnectionHolder {
	        Connection get();

	        void close();
	    }
	
	 
	 @FunctionalInterface
	    private interface ConnectionHolderFactory {
	        ConnectionHolder create(Connection connection);
	    }
	 
}
