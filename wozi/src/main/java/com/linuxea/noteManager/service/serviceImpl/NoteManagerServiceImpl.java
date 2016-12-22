package com.linuxea.noteManager.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.linuxea.noteManager.dao.NoteManagerDAO;
import com.linuxea.noteManager.po.TbWoZiNotePO;
import com.linuxea.noteManager.po.TbWoziNoteMenuPO;
import com.linuxea.noteManager.service.NoteManagerService;

/*
 *@author Linuxea
 *@date Dec 17, 2016  4:59:41 PM
 *@version 1.0
 *@desc 
 */
@Repository("noteManagerService")
public class NoteManagerServiceImpl implements NoteManagerService {
	private NoteManagerDAO noteManagerDAO;

	public NoteManagerDAO getNoteManagerDAO() {
		return noteManagerDAO;
	}
	@Resource(name="noteManagerDAO")
	public void setNoteManagerDAO(NoteManagerDAO noteManagerDAO) {
		this.noteManagerDAO = noteManagerDAO;
	}
	
	/*
	 * 处理并封装用户目录信息
	 */
	public List<TbWoziNoteMenuPO> handleMenuInfo(String id)throws Exception {
		return this.noteManagerDAO.getMenuInfoByUserId(id);
	}
	@Override
	public boolean createMenuNode(String directMenuParentId,String newNodeId, String userId) throws Exception {
		return this.noteManagerDAO.createMenuNode(directMenuParentId,newNodeId, userId);
	}
	@Override
	public boolean reNameMenuNode(String currentMenuNodeId, String newTextName, String refUserId) throws Exception {
		return this.noteManagerDAO.reNameMenuNode(currentMenuNodeId, newTextName, refUserId);
	}
	@Override
	public boolean deleteMenuNode(String curentMenuId,String userId) throws Exception {
		boolean isSuccessDelNode = false;
		isSuccessDelNode = this.noteManagerDAO.deleteMenuNode(curentMenuId ,userId);
		return isSuccessDelNode;
	}
	@Override
	public List<TbWoZiNotePO> noteList(String directMenuId,  String userId) throws Exception {
		return this.noteManagerDAO.noteList(directMenuId,userId);
	}
	
}
