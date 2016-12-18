package com.linuxea.noteManager.dao.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
		String dataSql = "select * from tb_wozi_note_menu where ref_user = ?";
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

	@Override
	public boolean createMenuNode(String directMenuParentId, String userId) throws Exception {
		String dataSql = "insert into tb_wozi_note_menu (id,parent,text,flag,ref_user,isDelete) "
				+ "values (?,?,?,?,?,?);";
		jdbcTemplate.update(dataSql, new PreparedStatementSetter(){

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, UUID.randomUUID().toString());
				ps.setString(2, directMenuParentId);
				ps.setString(3, "New node");
				ps.setString(4, "0");
				ps.setString(5, userId);
				ps.setString(6, "0");
			}
		});
		return true;
	}
	@Override
	public boolean reNameMenuNode(String currentMenuNodeId, String newTextName) throws Exception {
		String dataSql = "update tb_wozi_note_menu set text = ? where id=?";
		jdbcTemplate.update(dataSql, new PreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, newTextName);
				ps.setString(2, currentMenuNodeId );
			}
		});
		return true;
	}
	
	public boolean deleteMenuNode(String currentMenuNodeId) throws Exception {
		//删除当前目录
		String dataSql = "delete from tb_wozi_note_menu where id = ?";
		jdbcTemplate.update(dataSql, new PreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, currentMenuNodeId);
			}
		});
		return true;
	}
	
	public boolean deleteMenuNodeandNode(String currentMenuNodeId) throws Exception {
		//删除该目录下的子目录
		String dataSql2 = "delete from tb_wozi_note_menu where parent = ?";
		jdbcTemplate.update(dataSql2, new PreparedStatementSetter(){

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, currentMenuNodeId);
			}
		});
		return true;		
	}
}
