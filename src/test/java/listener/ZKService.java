package listener;

public class ZKService extends BaseService{
	ZKClient client;
	public ZKService(){
		client=new ZKClient();
	}
	
	@Override
	public void start(Listener listener){
		if(this.isRunning())
		{
			System.out.println("success");
		}else{
			super.start(listener);
		}
	}
	
	public void doStart(Listener listener)
	{
		client.start(listener);
	}
}
