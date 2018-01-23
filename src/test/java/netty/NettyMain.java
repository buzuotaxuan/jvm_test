package netty;

import java.io.UnsupportedEncodingException;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.HashedWheelTimer;

public class NettyMain {
	public static void main(String[] args) {
		
		
		
		
	    ByteBuf heapBuffer = Unpooled.buffer();  
	    
	    try {
	    	byte[] data="1".getBytes("UTF-8");
	    	
			heapBuffer.writeShort(data.length).writeBytes(data);
			
			heapBuffer.writeInt(98);
			
			/*	int fieldLength = heapBuffer.readShort();
			
			 byte[] bytes = new byte[fieldLength];
			 heapBuffer.readBytes(bytes);
			
			 
			 System.out.println(heapBuffer.readInt());
			 
			 System.out.println(new String (bytes));
			 */
			
			System.out.println(heapBuffer.readableBytes());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void test(){
		//创建Timer, 精度为100毫秒, 
        HashedWheelTimer timer = new HashedWheelTimer(100, TimeUnit.MILLISECONDS, 16);

        System.out.println(LocalTime.now());

        timer.newTimeout((timeout) -> {
            System.out.println(LocalTime.now());
            System.out.println(timeout);
        }, 5, TimeUnit.SECONDS);
	}
		
}
