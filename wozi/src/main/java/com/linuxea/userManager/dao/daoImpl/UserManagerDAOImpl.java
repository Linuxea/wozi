package com.linuxea.userManager.dao.daoImpl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.linuxea.userManager.dao.UserManagerDAO;
import com.linuxea.userManager.vo.TbWoZiUser;


/*
 *@author Linuxea
 *@date Dec 15, 2016  1:19:08 AM
 *@version 1.0
 *@desc 
 */
@Repository("userManagerDAO")
public class UserManagerDAOImpl implements UserManagerDAO {
	private Logger logger = Logger.getLogger(UserManagerDAOImpl.class);
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	@Resource(name="jdbcTemplate")
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@SuppressWarnings("all")
	@Override
	public void userRegist(TbWoZiUser tbWoZiUser) throws Exception {
		String dataSql = "insert into tbwoziuser (id,username,email,password,registdate) "
				+ "  values (?,?,?,?,?)";
		jdbcTemplate.update(dataSql, new PreparedStatementSetter(){

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, UUID.randomUUID().toString());
				pstmt.setString(2, tbWoZiUser.getUserName());
				pstmt.setString(3, tbWoZiUser.getEmail());
				pstmt.setString(4, tbWoZiUser.getPassword());
				pstmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			}
			
		});
	}
	@Override
	public boolean ajaxLogin(TbWoZiUser tbWoZiUser) throws Exception {
		Map<String,Integer> count = new HashMap<>();
		count.put("count", 0);
		String countSql = "select count(*) from tbwoziuser user where user.username = ? and user.password = ?";
		jdbcTemplate.query(countSql, new PreparedStatementSetter(){

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, tbWoZiUser.getUserName());
				ps.setString(2, tbWoZiUser.getPassword());
			}
		}, new  RowCallbackHandler(){
			@Override
			public void processRow(ResultSet rs) throws SQLException {
					if(!rs.wasNull()){
						int a = rs.getInt(1);
						count.put("count", a);
					}
			}
		});
		return count.get("count")>0;
	}
	@Override
	public boolean isUserExist(TbWoZiUser tbWoZiUser) throws Exception {
		Map<String,Integer> count = new HashMap<>();
		count.put("count", 0);
		String countSql = "select count(*) from tbwoziuser user where user.username = ?";
		jdbcTemplate.query(countSql, new PreparedStatementSetter(){

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, tbWoZiUser.getUserName());
			}
		}, new  RowCallbackHandler(){
			@Override
			public void processRow(ResultSet rs) throws SQLException {
					if(!rs.wasNull()){
						int a = rs.getInt(1);
						count.put("count", a);
					}
			}
		});
		return count.get("count")>0;
	}
}
