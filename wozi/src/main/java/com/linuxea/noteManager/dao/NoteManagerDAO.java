package com.linuxea.noteManager.dao;


import java.util.List;

import com.linuxea.noteManager.po.TbWoZiNotePO;
import com.linuxea.noteManager.po.TbWoziNoteMenuPO;

/*
 *@author Linuxea
 *@date Dec 17, 2016  4:58:17 PM
 *@version 1.0
 *@desc 
 */

public interface NoteManagerDAO {
	
	/*
	 * 获取某用户的笔记目录信息
	 */
	public List<TbWoziNoteMenuPO> getMenuInfoByUserId(String id)throws Exception;
	
	/*
	 * 创建新目录节点
	 */
	public boolean createMenuNode(String directMenuParentId,String newNodeId, String userId) throws Exception;
	
	/*
	 * 重命名目录节点名称
	 */
	public boolean reNameMenuNode(String currentMenuNodeId,String newTextName, String refUserId) throws Exception;
	
	/*
	 * 删除目录及其子目录的两个方法
	 */
	public boolean deleteMenuNode(String currentMenuNodeId, String userId) throws Exception ;
	
	/*
	 * 根据目录id来取得目录下的笔记列表
	 */
	public List<TbWoZiNotePO> listNoteByMenuId(String menuId) throws Exception ;
	
	/*
	 * 创建笔记
	 */
	public boolean ajaxAddNote(TbWoZiNotePO tbWoZiNotePo) throws Exception;
}
