package netty;

public interface PacketReceiver {
	  void onReceive(Packet packet, Connection connection);
}
