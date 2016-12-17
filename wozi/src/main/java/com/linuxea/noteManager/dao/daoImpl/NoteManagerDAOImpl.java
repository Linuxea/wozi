package com.linuxea.noteManager.dao.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.linuxea.noteManager.dao.NoteManagerDAO;
import com.linuxea.noteManager.po.TbWoziNoteMenuPO;

/*
 *@author Linuxea
 *@date Dec 17, 2016  4:58:29 PM
 *@version 1.0
 *@desc 
 */
@Repository("noteManagerDAO")
public class NoteManagerDAOImpl implements NoteManagerDAO{
	
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
	public List<TbWoziNoteMenuPO> getMenuInfoByUserId(String id) throws Exception {
		String dataSql = "select * from tb_wozi_note_menu where id = ?";
		List<TbWoziNoteMenuPO> menuList = new ArrayList<>();
		menuList = (List<TbWoziNoteMenuPO>) jdbcTemplate.query(dataSql, new PreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, id);
			}
		}, new RowMapperResultSetExtractor(new NoteRowMapper()));
		return menuList;
	}
	
	//处理Note映射的内部类
	class NoteRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs,int index) throws SQLException{
			TbWoziNoteMenuPO menu = new TbWoziNoteMenuPO();
			menu.setId(rs.getString("ID"));
			menu.setFlag(rs.getString("flag"));
			menu.setParent(rs.getString("parent"));
			menu.setRefUser(rs.getString("ref_user"));
			menu.setText(rs.getString("text"));
			return menu;
			}
		}
}
