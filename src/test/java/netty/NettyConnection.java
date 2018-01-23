package netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

public class NettyConnection implements Connection , ChannelFutureListener{
	
	  private SessionContext context;
	    private Channel channel;
	    private long lastReadTime;
	    private long lastWriteTime;
	    private volatile byte status = STATUS_NEW;
	
	@Override
	public void init(Channel channel, boolean security) {
		this.channel=channel;
		this.context=new SessionContext();
		this.lastReadTime=System.currentTimeMillis();
		  this.status = STATUS_CONNECTED;
		
	}

	@Override
	public SessionContext getSessionContext() {
		return null;
	}

	@Override
	public void setSessionContext(SessionContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ChannelFuture send(Packet packet) {
		if(channel.isActive()){
			
		}
		return null;
	}

	@Override
	public ChannelFuture send(Packet packet, ChannelFutureListener listener) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChannelFuture close() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isConnected() {
		 return status == STATUS_CONNECTED;
	}

	@Override
	public boolean isReadTimeout() {
		return System.currentTimeMillis() - lastReadTime > context.heartbeat + 1000;
	}

	@Override
	public boolean isWriteTimeout() {
		return System.currentTimeMillis() - lastWriteTime > context.heartbeat - 1000;
	}

	@Override
	public void updateLastReadTime() {
		 lastReadTime = System.currentTimeMillis();
		
	}

	@Override
	public void updateLastWriteTime() {
		 lastWriteTime = System.currentTimeMillis();
		
	}

	@Override
	public Channel getChannel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void operationComplete(ChannelFuture future) throws Exception {
		 if (future.isSuccess()) {
	            lastWriteTime = System.currentTimeMillis();
	        } 
		
	}

}
