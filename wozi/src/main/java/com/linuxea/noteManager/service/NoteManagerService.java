package com.linuxea.noteManager.service;

import java.util.List;

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
	public boolean createMenuNode(String directMenuParentId,String userId) throws Exception;
	
	/*
	 * 重命名目录节点名称
	 */
	public boolean reNameMenuNode(String currentMenuNodeId,String newTextName) throws Exception;
	
	/*
	 * 删除目录及其子目录
	 */
	public boolean deleteMenuNode(String curentMenuId) throws Exception;
}
