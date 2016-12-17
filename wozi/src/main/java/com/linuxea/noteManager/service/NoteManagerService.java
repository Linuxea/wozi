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
}
