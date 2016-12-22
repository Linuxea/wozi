package com.linuxea.noteManager.dao.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.linuxea.noteManager.dao.NoteManagerDAO;
import com.linuxea.noteManager.po.TbWoZiNotePO;
import com.linuxea.noteManager.po.TbWoziNoteMenuPO;

/*
 *@author Linuxea
 *@date Dec 17, 2016  4:58:29 PM
 *@version 1.0
 *@desc 
 */
@Repository("noteManagerDAO")
public class NoteManagerDAOImpl implements NoteManagerDAO{
	
	private Logger logger = Logger.getLogger(NoteManagerDAOImpl.class);
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
			menu.setRealId(rs.getString("real_id"));
			menu.setId(rs.getString("ID"));
			menu.setFlag(rs.getString("flag"));
			menu.setParent(rs.getString("parent"));
			menu.setRefUser(rs.getString("ref_user"));
			menu.setText(rs.getString("text"));
			return menu;
			}
		}

	@Override
	public boolean createMenuNode(String directMenuParentId,String newNodeId, String userId) throws Exception {
		String dataSql = "insert into tb_wozi_note_menu (id,parent,text,flag,ref_user,isDelete,real_id) "
				+ "values (?,?,?,?,?,?,?);";
		jdbcTemplate.update(dataSql, new PreparedStatementSetter(){

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, newNodeId);
				ps.setString(2, directMenuParentId);
				ps.setString(3, "New node");
				ps.setString(4, "0");
				ps.setString(5, userId);
				ps.setString(6, "0");
				ps.setString(7, UUID.randomUUID().toString());
			}
		});
		return true;
	}
	@Override
	public boolean reNameMenuNode(String currentMenuNodeId, String newTextName, String refUserId) throws Exception {
		String dataSql = "update tb_wozi_note_menu set text = ? where id=? and ref_user=?";
		jdbcTemplate.update(dataSql, new PreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, newTextName);
				ps.setString(2, currentMenuNodeId );
				ps.setString(3,refUserId);
			}
		});
		return true;
	}
	
	public boolean deleteMenuNode(String currentMenuNodeId, String userId) throws Exception {
		//检测目录是否是根目录  parent "#" 是则不能删除
		TbWoziNoteMenuPO menuList = isRootMenu(currentMenuNodeId,userId).get(0);
		if(menuList.getParent().equals("#")){
			return false;
		}
		//检测该目录是否有子目录  有则不能删除
		boolean isExistSubMenu = isExistSubMenu(currentMenuNodeId);
		if(!isExistSubMenu) {
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
			return false;
		
	}
	
	/*public boolean deleteMenuNodeandNode(String currentMenuNodeId) throws Exception {
		//删除该目录下的子目录
		String dataSql2 = "delete from tb_wozi_note_menu where parent = ?";
		jdbcTemplate.update(dataSql2, new PreparedStatementSetter(){

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, currentMenuNodeId);
			}
		});
		return true;		
	}*/
	
	@SuppressWarnings("all")
	public List<TbWoziNoteMenuPO> isRootMenu(String currentMenuId, String userId) throws Exception {
		String dataSql = "select * from tb_wozi_note_menu where id = ? and ref_user = ?";
		List<TbWoziNoteMenuPO> menuList = new ArrayList<>();
		return menuList = (List<TbWoziNoteMenuPO>)jdbcTemplate.query(dataSql, new PreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, currentMenuId);
				ps.setString(2, userId);
			}
		}, new RowMapperResultSetExtractor(new NoteRowMapper()));
	}
	
	public boolean isExistSubMenu(String parentId) throws Exception {
		Map<String,Integer> count = new HashMap<>();
		count.put("count", 0);
		String countSql = "select count(*) from tb_wozi_note_menu user where parent = ?";
		jdbcTemplate.query(countSql, new PreparedStatementSetter(){

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, parentId);
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
	
	class NoteListRowMapper  implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			TbWoZiNotePO note = new TbWoZiNotePO();
			note.setId(rs.getString("id"));
			note.setContent(rs.getString("content"));
			note.setRefMenu(rs.getString("ref_menu"));
			note.setStatus(rs.getString("status"));
			note.setTitle(rs.getString("title"));
			note.setUploadTime(rs.getTimestamp("upload_time"));
			return note;
		}
		
	}

	@Override
	public boolean ajaxAddNote(TbWoZiNotePO tbWoZiNotePo) throws Exception {
		String dataSql = "insert into tb_wozi_note (id,ref_menu,title,content,upload_time) "
				+ " values(?,?,?,?,?)";
		jdbcTemplate.update(dataSql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, UUID.randomUUID().toString());
				ps.setString(2, tbWoZiNotePo.getRefMenu());
				ps.setString(3, tbWoZiNotePo.getTitle());
				ps.setString(4, tbWoZiNotePo.getContent());
				ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			}
		});
		return true;
	}
	@SuppressWarnings("all")
	@Override
	public List<TbWoZiNotePO> noteList(String directMenuId,  String userId) throws Exception {
		List<TbWoZiNotePO> noteList = new ArrayList<>();
		String dataSql = "select * from tb_wozi_note where ref_menu = ? and ref_user=? order by upload_time desc";
		noteList = jdbcTemplate.query(dataSql, new PreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, directMenuId);
				ps.setString(2, userId);
			}
		}, new NoteListRowMapper());
		return noteList;
	}
}
