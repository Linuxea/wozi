package dao;

/*
 *@author Linuxea
 *@date Dec 24, 2016  1:38:32 AM
 *@version 1.0
 *@desc 
 */

public class TestHash {
	
	@org.junit.Test
	public void test1() {
		Object obj = new Object();
		Object obj2 = new Object();
		
		System.out.println(obj == obj2);
		
		System.out.println("打印对象的hashcode");
		
		System.out.println("obj 的hashCode" + obj.hashCode());
		System.out.println("obj2的hashCode" + obj2.hashCode());
		
		System.out.println(Integer.toBinaryString(Integer.parseInt(Integer.toOctalString(obj.hashCode()))));
		
	}
}
