package jvm_test;

import org.junit.Test;

import io.netty.util.internal.StringUtil;

public class JVM_Test {
	@Test
	public void testShift(){
		int a=-6;
		for(int i=0;i<32;i++){
			int t=(a&0x80000000>>>i)>>>(31-i);
			System.err.print(t);
		}
		System.out.println(1 << 3);
		System.out.println(2 & 7);
		System.out.println(1 << 3 + 2 & 7);
		
		System.out.println((1 << (3 + 2))&7);
		
		System.out.println(3>>>3);
	}
	
	@Test
	public void testAtomicInteger()
	{
		
//		AtomicInteger ai=new AtomicInteger();
//		System.out.println(ai.get());
		/* System.out.println(2^30);
		 
		 System.out.println((Integer)129==(Integer)129);*/
		
		
	}
	
}