package netty;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class PacketDecode extends ByteToMessageDecoder{

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		decodeHearBeat(in,out);
		decodeFrame(in,out);
	}

	private void decodeFrame(ByteBuf in, List<Object> out) {
		if(in.readableBytes()>Packet.HEADER_LEN){
			in.markReaderIndex();
			
			Packet packet=decode(in);
			
			if(null!=packet){
				out.add(packet);
			}
		}
	}

	private Packet decode(ByteBuf in) {
		int readables=in.readableBytes();
		
		int bodyLength=in.readInt();
		
		if(readables<bodyLength+Packet.HEADER_LEN){
			return null;
		}
		return Packet.decodePacket(new Packet(in.readByte()), in, bodyLength);
	}

	private void decodeHearBeat(ByteBuf in, List<Object> out) {
		while(in.isReadable()){
			if(in.readByte()==Packet.HB_PACKET_BYTE){
				out.add(Packet.HB_PACKET_BYTE);
			}else{
				in.readerIndex(in.readerIndex()-1);
				break;
			}
		}
	}

}
