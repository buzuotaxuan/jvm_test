package netty;

import java.nio.channels.spi.SelectorProvider;
import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public abstract class Server {
	
	protected final int port;
    protected EventLoopGroup bossGroup;
    protected EventLoopGroup workerGroup;
	
    public Server(int port){
    	this.port=port;
    }
    
	public void start(Listener listener) {
		createNioServer(listener);
	}
	
	private void createNioServer(Listener listener) {
		
		bossGroup=new NioEventLoopGroup(1, new NamedFactory("boss"), SelectorProvider.provider());
		
		workerGroup=new NioEventLoopGroup(0, new NamedFactory("work"),SelectorProvider.provider());
		
		createServer(listener, bossGroup, workerGroup, NioServerSocketChannel::new);
		
	}
	
	
	public void createServer(Listener listener,EventLoopGroup boss,EventLoopGroup work,ChannelFactory<? extends ServerChannel> serverChannel){
		ServerBootstrap boot=new ServerBootstrap();
		
		boot.group(boss, work);
		
		boot.channelFactory(serverChannel);
		
		boot.childHandler(new ChannelInitializer<Channel>() {

			@Override
			protected void initChannel(Channel ch) throws Exception {
				initPipleLine(ch.pipeline());
			}
			
		});
		
		initOpts(boot);
		
		boot.bind(port).addListener(future->{
			if(future.isSuccess()){
				System.out.println("server start success.....");
			}
		});
		
	}

	protected void initOpts(ServerBootstrap boot) {
		boot.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
		boot.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
	}

	private void initPipleLine(ChannelPipeline pipeline) {
		 pipeline.addLast("decoder", getDecoder());
	        pipeline.addLast("encoder", getEncoder());
	        pipeline.addLast("handler", getChannelHandler());
	}
	
	
	
	private ChannelHandler getEncoder() {
		return new PacketEncoder();
	}

	private ChannelHandler getDecoder() {
		 return new PacketDecode();
	}

	public abstract ChannelHandler getChannelHandler();

	public static void main(String[] args) {
		System.out.println(TimeUnit.SECONDS.toMillis(4));
	}
	
	
}
