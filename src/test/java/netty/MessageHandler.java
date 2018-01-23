package netty;

public interface MessageHandler {
	void handler(Packet packet, Connection connection);
}
