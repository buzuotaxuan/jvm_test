package netty;

public class HeartBeatHandler implements MessageHandler{

	@Override
	public void handler(Packet packet, Connection connection) {
		connection.send(packet);
	}

}
