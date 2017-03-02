package utils;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import beans.People;

/*
 *@author Linuxea
 *@date Mar 3, 2017  12:24:37 AM
 *@version 1.0
 *@desc 
 */

/**
 * 列出除了自身之外的所有用户信息
 * @author linuxea
 *
 */
public class ListAllPersonUtils {
	private Logger log = LoggerFactory.getLogger(ListAllPersonUtils.class);
	
	/**
	 * 返回所有的用户信息
	 * @return
	 * @throws SQLException 
	 */
	public List<People> listPersons() throws SQLException {
		List<People> peopleList = new ArrayList<>();
		String querySql = "select * from tbwoziuser";
		Connection conn = ConnectionUtils.getConnection();
		PreparedStatement pstm = conn.prepareStatement(querySql);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			People people = new People();
			people.setUserName(rs.getString("username"));
			people.setId(rs.getString("id"));
			people.setEmail(rs.getString("email"));
			people.setRegistdate(rs.getDate("registdate"));
			peopleList.add(people);
		}
		return peopleList;
	}
	
	
	
	
	

}











