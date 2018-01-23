package client;


public interface CallBack {
	 void onSuccess(Packet response);

	    void onTimeout(Packet request);
}
