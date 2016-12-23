package com.linuxea.noteManager.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.linuxea.linuxea.action.BaseAction;
import com.linuxea.noteManager.po.TbWoZiNotePO;
import com.linuxea.noteManager.po.TbWoziNoteMenuPO;
import com.linuxea.noteManager.service.NoteManagerService;
import com.linuxea.userManager.vo.TbWoZiUser;

/*
 *@author Linuxea
 *@date Dec 17, 2016  4:57:01 PM
 *@version 1.0
 *@desc 
 */
@SuppressWarnings("all")
@Controller("noteManagerAction")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class NoteManagerAction extends BaseAction{
	private TbWoZiNotePO tbWoZiNotePO;
	private String tbWoZiNotePOStr;
	private String newNodeId;
	private NoteManagerService noteManagerService;
	private String directMenuParentId;
	private String currentMenuNodeId;
	private String newTextName;
	private String currentNoteId;
	
	public String getCurrentNoteId() {
		return currentNoteId;
	}

	public void setCurrentNoteId(String currentNoteId) {
		this.currentNoteId = currentNoteId;
	}

	public String ajaxAddMenuNode() throws Exception {
		TbWoZiUser tbWoZiUser = (TbWoZiUser) super.getSession().get("user");
		if(this.noteManagerService.createMenuNode(directMenuParentId,newNodeId, tbWoZiUser.getId())){//"466a37d9-935b-47b3-bdea-2250fe974a57"
			this.setActionResult("0", "创建新节点成功");
		}
		return this.SUCCESS;
	}

	public String ajaxAddNote() throws Exception{
		TbWoZiUser tbWoZiUser = (TbWoZiUser) super.getSession().get("user");
		JSONObject jo = JSON.parseObject(tbWoZiNotePOStr);
		tbWoZiNotePO = JSON.toJavaObject(jo, TbWoZiNotePO.class);
		tbWoZiNotePO.setRefUser(tbWoZiUser.getId());
		this.noteManagerService.ajaxAddNote(tbWoZiNotePO);
		this.setActionResult("0", "创建新笔记成功");		
		return this.SUCCESS;
	}

	public String ajaxGetNode() throws Exception{
		TbWoZiNotePO note = new TbWoZiNotePO();
		note = this.noteManagerService.getNoteById(currentNoteId);
		this.setActionResult("0", "获取具体笔记信息成功",note);
		return this.SUCCESS;
	}

	public String delMenuNode() throws Exception {
		TbWoZiUser tbWoZiUser = (TbWoZiUser) super.getSession().get("user");
		boolean isSuccessDel = this.noteManagerService.deleteMenuNode(currentMenuNodeId, tbWoZiUser.getId());
		if(isSuccessDel) {
			this.setActionResult("0", "成功删除目录");
		}else{
			this.setActionResult("-1", "删除目录失败(无法删除根目录及存在子目录的目录)");
		}
		return this.SUCCESS;
	}

	public String getCurrentMenuNodeId() {
		return currentMenuNodeId;
	}

	public String getDirectMenuParentId() {
		return directMenuParentId;
	}

	public String getNewNodeId() {
		return newNodeId;
	}
	public String getNewTextName() {
		return newTextName;
	}
	public NoteManagerService getNoteManagerService() {
		return noteManagerService;
	}
	public TbWoZiNotePO getTbWoZiNotePO() {
		return tbWoZiNotePO;
	}
	public String getTbWoZiNotePOStr() {
		return tbWoZiNotePOStr;
	}

	public String handleNoteMenu() throws Exception{
		TbWoZiUser tbWoZiUser = (TbWoZiUser) super.getSession().get("user");
		List<TbWoziNoteMenuPO> menuListJSON = this.noteManagerService.handleMenuInfo(tbWoZiUser.getId());
		if(null!=menuListJSON && menuListJSON.size()>0){
			this.setActionResult("0", "查询用户目录信息成功", menuListJSON);
		}else{
			this.setActionResult("-1", "用户目录为空");
		}
		return this.SUCCESS;
	}

	public String listNoteByDirectMenuId() throws Exception {
		TbWoZiUser tbWoZiUser = (TbWoZiUser) super.getSession().get("user");
		List<TbWoZiNotePO> noteList = this.noteManagerService.noteList(currentMenuNodeId,tbWoZiUser.getId());
		this.setActionResult("0", "获取该目录下笔记列表成功",noteList);
		return this.SUCCESS;
	}
	
	public String reNameMenuNode() throws Exception{
		TbWoZiUser tbWoZiUser = (TbWoZiUser) super.getSession().get("user");
		boolean isSuccess = false;
		isSuccess = this.noteManagerService.reNameMenuNode(currentMenuNodeId, newTextName, tbWoZiUser.getId());//tbWoZiUser.getRealId()
		if(isSuccess) {
			this.setActionResult("0", "更改节点名称成功");
		}else{
			this.setActionResult("-1", "更改节点名称失败");
		}
		return this.SUCCESS;
	}
	
	public void setCurrentMenuNodeId(String currentMenuNodeId) {
		this.currentMenuNodeId = currentMenuNodeId;
	}
	
	
	public void setDirectMenuParentId(String directMenuParentId) {
		this.directMenuParentId = directMenuParentId;
	}
	
	public void setNewNodeId(String newNodeId) {
		this.newNodeId = newNodeId;
	}
	
	public void setNewTextName(String newTextName) {
		this.newTextName = newTextName;
	}
	
	@Resource(name="noteManagerService")
	public void setNoteManagerService(NoteManagerService noteManagerService) {
		this.noteManagerService = noteManagerService;
	}
	
	public void setTbWoZiNotePO(TbWoZiNotePO tbWoZiNotePO) {
		this.tbWoZiNotePO = tbWoZiNotePO;
	}
	
	public void setTbWoZiNotePOStr(String tbWoZiNotePOStr) {
		this.tbWoZiNotePOStr = tbWoZiNotePOStr;
	}
}
