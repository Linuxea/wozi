package dao;

import org.aspectj.lang.annotation.SuppressAjWarnings;

/*
 *@author Linuxea
 *@date Dec 22, 2016  10:26:19 PM
 *@version 1.0
 *@desc 
 */
@SuppressWarnings("all")
public class TestStringStringBuffer {
	
	@org.junit.Test
	public void test() {
		
		String str = "new";
		String str2 = "new";
		System.out.println(str==str2);//true
		
		String s1 = new String("abc");
		String s2 = new String("abc");
		System.out.println(s1==s2);//false
		
		String s3 = "abc";
		s3 = "dd";//在字符串常量池中创建dd,并把引用指向它
		System.out.println(s3);
		
	}
	
	@org.junit.Test
	public void test3() {
		int i = 3;
		System.out.println(i++ == i--);
		System.out.println(i);
		
		int j = 3;
		int k = 0;
		System.out.println(j++ == j--?j++:j--);
		k = j--;
		System.out.println("k="+k);
	}

}
