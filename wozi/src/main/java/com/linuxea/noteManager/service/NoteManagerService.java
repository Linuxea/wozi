package com.linuxea.noteManager.service;

import java.util.List;

import com.linuxea.noteManager.po.TbWoZiNotePO;
import com.linuxea.noteManager.po.TbWoziNoteMenuPO;

/*
 *@author Linuxea
 *@date Dec 17, 2016  4:59:23 PM
 *@version 1.0
 *@desc 
 */

public interface NoteManagerService {
	public List<TbWoziNoteMenuPO> handleMenuInfo(String id)throws Exception ;
	
	/*
	 * 创建新目录节点
	 */
	public boolean createMenuNode(String directMenuParentId,String newNodeId,String userId) throws Exception;
	
	/*
	 * 重命名目录节点名称
	 */
	public boolean reNameMenuNode(String currentMenuNodeId,String newTextName, String refUserId) throws Exception;
	
	/*
	 * 删除目录及其子目录
	 */
	public boolean deleteMenuNode(String curentMenuId ,String userId) throws Exception;
	
	/*
	 * 通过目录Id来获取其下的笔记列表
	 */
	public List<TbWoZiNotePO> noteList(String directMenuId,  String userId) throws Exception;
	
	/*
	 * 创建笔记
	 */
	public boolean ajaxAddNote(TbWoZiNotePO tbWoZiNotePo) throws Exception;
	
	/*
	 * 通过笔记id来获取笔记
	 */
	public TbWoZiNotePO getNoteById(String noteId) throws Exception;
	
	/*
	 * 更新笔记
	 */
	public void updateNote(TbWoZiNotePO tbWoZiNotePO) throws Exception;
	
	/*
	 * 删除笔记
	 */
	public boolean delNodeById(String currentNoteId) throws Exception;
	
}
