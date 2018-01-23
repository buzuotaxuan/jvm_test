package netty;

import java.util.HashMap;
import java.util.Map;

public class MessageDispacher implements PacketReceiver{

	
	Map<Byte,MessageHandler> handlers=new HashMap<>();
	
	
	public void register(Command command, MessageHandler handler){
		handlers.put(command.cmd, handler);
	}
	
	@Override
	public void onReceive(Packet packet, Connection connection) {
		MessageHandler handler=handlers.get(packet.cmd);
		handler.handler(packet, connection);
	}

}
