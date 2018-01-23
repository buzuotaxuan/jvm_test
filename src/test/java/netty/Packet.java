package netty;

import io.netty.buffer.ByteBuf;

public class Packet {
	
	 public static final int HEADER_LEN = 13;
	
	 public static final byte HB_PACKET_BYTE = -33;
	 
	 
	 public byte cmd; //命令
	    transient public short cc; //校验码 暂时没有用到
	    public byte flags; //特性，如是否加密，是否压缩等
	    public int sessionId; // 会话id。客户端生成。
	    transient public byte lrc; // 校验，纵向冗余校验。只校验head
	    transient public byte[] body;

	    
	    public Packet(byte cmd){
	    	this.cmd=cmd;
	    }
	 
	 
	 public static Packet decodePacket(Packet packet,ByteBuf in,int bodyLength){
		 packet.cc = in.readShort();//read cc
	        packet.flags = in.readByte();//read flags
	        packet.sessionId = in.readInt();//read sessionId
	        packet.lrc = in.readByte();//read lrc
	        
	        if (bodyLength > 0) {
	            in.readBytes(packet.body = new byte[bodyLength]);
	        }
	        return packet;
	 }
	 
	 
	 public static void encodePacket(Packet packet, ByteBuf out) {
	        if (packet.cmd == Command.HEARTBEAT.cmd) {
	            out.writeByte(Packet.HB_PACKET_BYTE);
	        } else {
	            out.writeInt(packet.getBodyLength());
	            out.writeByte(packet.cmd);
	            out.writeShort(packet.cc);
	            out.writeByte(packet.flags);
	            out.writeInt(packet.sessionId);
	            out.writeByte(packet.lrc);
	            if (packet.getBodyLength() > 0) {
	                out.writeBytes(packet.body);
	            }
	        }
	        packet.body = null;
	    }
	 
	 public int getBodyLength() {
	        return body == null ? 0 : body.length;
	    }
}
