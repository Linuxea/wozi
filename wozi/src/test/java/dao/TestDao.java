package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.linuxea.baseManager.daoUtil.DaoUtil;
import com.mchange.v2.c3p0.ComboPooledDataSource;


/*
 *@author Linuxea
 *@date Dec 14, 2016-9:29:18 PM
 *@version 1.0
 *@desc 
 */

public class TestDao {
	@org.junit.Test
	public void test1() throws SQLException {
		Connection conn = DaoUtil.getConn();
		System.out.println(conn);
		conn.close();
	}
	
	@org.junit.Test
	public void test2() throws SQLException {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		DataSource ds=(DataSource)context.getBean("dataSource");

		Connection conn=ds.getConnection();

		System.out.println(conn);
	}
	
	@org.junit.Test
	public void test3() throws SQLException {
		Connection conn;
		PreparedStatement pstmt;
		conn = DaoUtil.getConn();
		String dataSql = "insert into tbwoziuser (id,username,email,password,registdate) "
				+ "  values (?,?,?,?,?)";
		pstmt = conn.prepareStatement(dataSql);
		pstmt.setString(1, UUID.randomUUID().toString());
		pstmt.setString(2,"username");
		pstmt.setString(3, "email");
		pstmt.setString(4, "password123");
		pstmt.setDate(5, new Date(System.currentTimeMillis()));
		pstmt.execute();
		conn.close();
	}
	
	@org.junit.Test
	public void test4() throws SQLException {
		ComboPooledDataSource data = new ComboPooledDataSource();
		Connection conn = data.getConnection();
		String sql = "select * from tbwoziuser";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			System.out.println(rs.getString(2));
		}
	}
	
	@org.junit.Test
	public void test() {
		int i = 3;
		int j = (i++ ==i--)?i++:i--;
		System.out.println(j);
		System.out.println("hello");
	}
	
	@SuppressWarnings("all")
	@org.junit.Test
	public void test43() {
		Map<String,String> myMap = new HashMap<>();
		myMap.put("test", "test");
		myMap.put("name", "linuxea");
		Set<String> sets = myMap.keySet();
		for(String temp:sets) {
			System.out.println(myMap.get(temp));
		}
		
		Set<Entry<String, String>> tt = myMap.entrySet();
		Iterator iter =  tt.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
		
	}
	
	@org.junit.Test
	public void test55() {
		String s = "a\0bc";
		System.out.println(s);
	}
	
	@org.junit.Test
	public void test99() {
		        Callable<Integer> callable = new Callable<Integer>() {
		            public Integer call() throws Exception {
		                return new Random().nextInt(100);
		            }
		        };
		        FutureTask<Integer> future = new FutureTask<Integer>(callable);
		        new Thread(future).start();
		        try {
		            Thread.sleep(5000);// 可能做一些事情
		            System.out.println(future.get());
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        } catch (ExecutionException e) {
		            e.printStackTrace();
		    }
	}
	
	@org.junit.Test
	public void test9(){
		TreeSet set = new TreeSet();
		set.add("a");
		set.add("b");
		set.add("ee");
		Iterator iter = set.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
	}
	
}
