package com.linuxea.noteManager.action;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.linuxea.linuxea.action.BaseAction;
import com.linuxea.noteManager.service.NoteManagerService;

/*
 *@author Linuxea
 *@date Dec 17, 2016  4:57:01 PM
 *@version 1.0
 *@desc 
 */
@Controller("noteManagerAction")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class NoteManagerAction extends BaseAction{
	
	private NoteManagerService noteManagerService;

	public NoteManagerService getNoteManagerService() {
		return noteManagerService;
	}
	
	@Resource(name="noteManagerService")
	public void setNoteManagerService(NoteManagerService noteManagerService) {
		this.noteManagerService = noteManagerService;
	}
	
	
}
