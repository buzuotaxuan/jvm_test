package test;

public class Client {
	public static void main(String[] args) {
		Config c=Config.build();
		c.setListener(new ListeneB());
		
		
		Listener a=Config.I.getListener();
		
		a.teset();
	}
}
