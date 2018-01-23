package test;

 class ListeneA implements Listener{

	Listener listener;
	public void teset(Listener listener) {
		this.listener=listener;
	}
	@Override
	public void teset() {
		listener.teset();
	}

}
