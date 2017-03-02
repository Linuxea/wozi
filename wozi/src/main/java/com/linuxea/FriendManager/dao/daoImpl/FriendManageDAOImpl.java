package com.linuxea.FriendManager.dao.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.linuxea.FriendManager.dao.FriendManageDAO;
import com.linuxea.FriendManager.po.FriendBean;

import utils.UUIDUtil;

/*
 *@author Linuxea
 *@date Mar 3, 2017  12:18:17 AM
 *@version 1.0
 *@desc 
 */

@Repository("friendManageDAO")
public class FriendManageDAOImpl implements FriendManageDAO{
	
	private Logger logger = Logger.getLogger(FriendManageDAOImpl.class);
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	@Resource(name="jdbcTemplate")
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public boolean sentAddNews(String addId, String selfId) throws Exception {
		String insertSql = "insert into tb_wozi_friends(id,addperson,addedperson,addtime) values(?,?,?,?)";
		String id = UUIDUtil.createId();
		int count = jdbcTemplate.update(insertSql, new PreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, id);
				ps.setString(2, selfId);
				ps.setString(3, addId);
				ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			}
		});
		return count == 1;
	}
	
	
	@Override
	public FriendBean isSent(String addId, String selfId) throws Exception {
		String findSql = "select * from tb_wozi_friends where addperson = ? and addedperson = ?  ";
		List<FriendBean> isFriends = null;
		isFriends = 
				jdbcTemplate.query(findSql, new PreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, selfId);
				ps.setString(2, addId);
			}
		}, new RowMapper<FriendBean>(){
			@Override
			public FriendBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				FriendBean fb = new FriendBean();
				fb.setId(rs.getString("id"));
				fb.setAddPerson(rs.getString("addperson"));
				fb.setAddedPerson(rs.getString("addedperson"));
				fb.setAddTime(rs.getDate("addtime"));
				fb.setAddedAgree(rs.getString("addedAgree"));
				return fb;
			}
		});
		return isFriends.size()>0?isFriends.get(0):null;//如果不为null则表示已经发送过请求了 
	}
	

}
