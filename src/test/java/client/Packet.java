package client;

import netty.Command;

public class Packet {

	
	public static final int HEADER_LENGTH=13;
	
	 public static final byte HB_PACKET_BYTE = -33;
	  public static final Packet HB_PACKET = new Packet(Command.HEARTBEAT);
	
	
	public byte cmd;
	public short cc;
	public int sessionId;
	public byte[] body;
	
	public Packet(Command heartbeat) {
		this.cmd=heartbeat.cmd;
	}
	
	
}
