package dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

/*
 *@author Linuxea
 *@date Dec 24, 2016  12:49:56 AM
 *@version 1.0
 *@desc 
 */
@SuppressWarnings("all")
public class TestHashMap {
	@org.junit.Test
	public void test1() {
		Map<String,String> myMap = new HashMap<>();
		myMap.put("a", "a");//hashMap底层是数组+链表  采用数组table用来存在Node节点信息,包括hashCode,next指针,key,value,
		myMap.put("b", "b");
		
		Set<Entry<String, String>> keySets = myMap.entrySet();
		Iterator iter = keySets.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
		
		System.out.println("---------");
		
		Map<String, Integer> myTreeMap = new TreeMap<>();
		myTreeMap.put("c", 1);//红黑树的根节点为"c"
		myTreeMap.put("d", 2);//因为"d">"c",所以d处于c的右子树位置
		Set<Entry<String,Integer>> keySets2 = myTreeMap.entrySet();
		Iterator iter2 = keySets2.iterator();
		while(iter2.hasNext()){
			System.out.println(iter2.next());
		}
	}
}
