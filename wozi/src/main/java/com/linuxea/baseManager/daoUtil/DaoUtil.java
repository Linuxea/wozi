package com.linuxea.baseManager.daoUtil;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/*
 *@author Linuxea
 *@date Dec 14, 2016-9:13:50 PM
 *@version 1.0
 *@desc 
 */

public class DaoUtil {
	
	private static ComboPooledDataSource cbpd = new ComboPooledDataSource();
	private static Connection conn = null;
	
	public static Connection getConn() {
		try {
			cbpd.setDriverClass("com.mysql.jdbc.Driver");
			cbpd.setJdbcUrl("jdbc:mysql://localhost:3306/wozi");
			cbpd.setUser("root");
			cbpd.setPassword("linuxea");
			cbpd.setMaxPoolSize(2000);
			cbpd.setMaxStatements(1000);
			conn = cbpd.getConnection();
			conn.setAutoCommit(true);
		} catch (PropertyVetoException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
		
	}
	
	
	
}
