package wozi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.sun.istack.internal.logging.Logger;

public class Test {
	
	@org.junit.Test
	public void test() {
		System.out.println("abc");
	}
	
	@org.junit.Test
	public void testJDBC() throws ClassNotFoundException, SQLException {
		Connection connect = null;
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager
                .getConnection("jdbc:mysql://localhost/user","root","linuxea");
		System.out.println(connect);
		
	}
	
	@org.junit.Test
	public void testLog4j() {
		Logger logger = Logger.getLogger(Test.class);
		logger.warning("this is an warning message");
		logger.info("this is an info message");
		
	}
	@org.junit.Test
	public void testSqlDate() {
		java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
		System.out.println(date);
	}
	
	@org.junit.Test
	public void test3() {
		System.out.println("abc");
		System.out.println("用来测试提交是否能成功");
	}

}
