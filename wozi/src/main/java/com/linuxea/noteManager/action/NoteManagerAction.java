package com.linuxea.noteManager.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.linuxea.linuxea.action.BaseAction;
import com.linuxea.noteManager.po.TbWoziNoteMenuPO;
import com.linuxea.noteManager.service.NoteManagerService;
import com.linuxea.userManager.vo.TbWoZiUser;

/*
 *@author Linuxea
 *@date Dec 17, 2016  4:57:01 PM
 *@version 1.0
 *@desc 
 */
@Controller("noteManagerAction")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class NoteManagerAction extends BaseAction{
	private String newNodeId;
	
	public String getNewNodeId() {
		return newNodeId;
	}

	public void setNewNodeId(String newNodeId) {
		this.newNodeId = newNodeId;
	}

	public String getCurrentMenuNodeId() {
		return currentMenuNodeId;
	}

	public void setCurrentMenuNodeId(String currentMenuNodeId) {
		this.currentMenuNodeId = currentMenuNodeId;
	}

	public String getNewTextName() {
		return newTextName;
	}

	public void setNewTextName(String newTextName) {
		this.newTextName = newTextName;
	}

	private NoteManagerService noteManagerService;
	private String directMenuParentId;
	private String currentMenuNodeId;
	private String newTextName;
	public String getDirectMenuParentId() {
		return directMenuParentId;
	}

	public void setDirectMenuParentId(String directMenuParentId) {
		this.directMenuParentId = directMenuParentId;
	}

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
	
	
	public String ajaxAddMenuNode() throws Exception {
		TbWoZiUser tbWoZiUser = (TbWoZiUser) super.getSession().get("user");
		if(this.noteManagerService.createMenuNode(directMenuParentId,newNodeId, "466a37d9-935b-47b3-bdea-2250fe974a57")){
			this.setActionResult("0", "创建新节点成功");
		}
		return this.SUCCESS;
	}
	
	public String reNameMenuNode() throws Exception{
		TbWoZiUser tbWoZiUser = (TbWoZiUser) super.getSession().get("user");
		boolean isSuccess = false;
		isSuccess = this.noteManagerService.reNameMenuNode(currentMenuNodeId, newTextName, "466a37d9-935b-47b3-bdea-2250fe974a57");//tbWoZiUser.getRealId()
		if(isSuccess) {
			this.setActionResult("0", "更改节点名称成功");
		}else{
			this.setActionResult("-1", "更改节点名称失败");
		}
		return this.SUCCESS;
	}
	
	public String delMenuNode() throws Exception {
		boolean isSuccessDel = this.noteManagerService.deleteMenuNode(currentMenuNodeId, "466a37d9-935b-47b3-bdea-2250fe974a57");
		if(isSuccessDel) {
			this.setActionResult("0", "成功删除目录");
		}else{
			this.setActionResult("-1", "删除目录失败(无法删除根目录及存在子目录的目录)");
		}
		return this.SUCCESS;
	}
}
