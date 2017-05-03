package listener;

public class ZKClient extends BaseService{

	@Override
	public void start(Listener listener){
		if(isRunning()){
			
		}else{
			super.start(listener);
		}
	}
	@Override
	public void init(){
		System.out.println("init zk client");
	}
}
