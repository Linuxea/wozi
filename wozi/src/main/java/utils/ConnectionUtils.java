package utils;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/*
 *@author Linuxea
 *@date Mar 3, 2017  1:00:59 AM
 *@version 1.0
 *@desc 
 */
/**connection  工具类*/
public class ConnectionUtils {
	
	
	private static ComboPooledDataSource cp = null;
	static {
			cp = new ComboPooledDataSource();
		try {
			cp.setDriverClass("com.mysql.jdbc.Driver");
			cp.setJdbcUrl("jdbc:mysql://localhost:3306/wozi?useUnicode=true&characterEncoding=utf-8");
			cp.setUser("root");
			cp.setPassword("linuxea");
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException{
		return cp.getConnection();
	}
	
}
