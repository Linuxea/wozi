package com.linuxea.noteManager.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.linuxea.linuxea.action.BaseAction;
import com.linuxea.noteManager.po.TbWoziNoteMenuPO;
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
	
	public String handleNoteMenu() throws Exception{
		List<TbWoziNoteMenuPO> menuListJSON = this.noteManagerService.handleMenuInfo("466a37d9-935b-47b3-bdea-2250fe974a57");
		if(null!=menuListJSON && menuListJSON.size()>0){
			this.setActionResult("0", "查询用户目录信息成功", menuListJSON);
		}else{
			this.setActionResult("-1", "用户目录为空");
		}
		return this.SUCCESS;
	}
	
	public String test() {
		this.setActionResult("0", "success");
		return this.SUCCESS;
	}
	
	
}
