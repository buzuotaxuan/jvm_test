package netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

@ChannelHandler.Sharable
public class ServerChannelHandler extends ChannelInboundHandlerAdapter{
	
	
	private final boolean security; //是否启用加密
    private final ServerConnectionManager connectionManager;
    private final PacketReceiver receiver;
	
	public ServerChannelHandler(boolean security, ServerConnectionManager connectionManager, PacketReceiver receiver){
		this.security=security;
		this.connectionManager=connectionManager;
		this.receiver=receiver;
	}
	
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		 Connection connection = new NettyConnection();
	     connection.init(ctx.channel(), security);
	     
	     connectionManager.add(connection);
	     
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		Packet packet=(Packet)msg;
		
		receiver.onReceive(packet, connectionManager.get(ctx.channel()));
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		
	}


	
	
}
