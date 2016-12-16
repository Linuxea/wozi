package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.linuxea.linuxea.daoUtil.DaoUtil;
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
	
	
}
