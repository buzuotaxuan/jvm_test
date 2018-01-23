package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;
import io.netty.channel.WriteBufferWaterMark;

public class TCPServer extends Server {

	ServerConnectionManager connectionManager = new ServerConnectionManager();
	 private ServerChannelHandler channelHandler;
	
	
	

	public TCPServer(int port) {
		super(port);
	}

	public void init(){
		connectionManager.init();
		MessageDispacher msgDispatcher=new MessageDispacher();
		msgDispatcher.register(Command.HEARTBEAT, new HeartBeatHandler());
		channelHandler=new ServerChannelHandler(false,connectionManager,msgDispatcher);
		
		
		
	}
	
	
	@Override
	public ChannelHandler getChannelHandler() {
		return channelHandler;
	}

	
	
	@Override
	protected void initOpts(ServerBootstrap b) {
		super.initOpts(b);
		b.option(ChannelOption.SO_BACKLOG, 1024);
		b.childOption(ChannelOption.SO_SNDBUF, 32);
		b.childOption(ChannelOption.SO_RCVBUF, 32);
		b.childOption(ChannelOption.WRITE_BUFFER_WATER_MARK, new WriteBufferWaterMark(32, 64));
	}

}
