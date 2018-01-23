package java8.test;

public interface ChannelFeature <F extends Feature>{
	public void complete(F f);
}
