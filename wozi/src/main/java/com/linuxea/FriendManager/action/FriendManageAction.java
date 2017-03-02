package com.linuxea.FriendManager.action;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxea.FriendManager.po.FriendBean;
import com.linuxea.FriendManager.service.FriendManageService;
import com.linuxea.linuxea.action.BaseAction;

import beans.People;
import utils.ListAllPersonUtils;

/*
 *@author Linuxea
 *@date Mar 3, 2017  12:22:17 AM
 *@version 1.0
 *@desc 
 */

public class FriendManageAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4146384624875065925L;
	private Logger log = LoggerFactory.getLogger(FriendManageAction.class);
	
	private FriendManageService friendManageService;
	private String addId;
	private String selfId;
	public FriendManageService getFriendManageService() {
		return friendManageService;
	}
	@Resource(name="friendManageService")
	public void setFriendManageService(FriendManageService friendManageService) {
		this.friendManageService = friendManageService;
	}

	public String listAllPeoInfo() throws SQLException {
		List<People> peos = ListAllPersonUtils.listPersons();
		super.setActionResult("0", "获取所有用户信息成功", peos);
		return SUCCESS;
	}
	
	public String addFriend() throws Exception {
		FriendBean fb = this.friendManageService.isSent(addId, selfId);
		if(null!=fb) {//表示已经发送过好友请求了
			switch (fb.getAddedAgree()){
				case "0"://对方还没同意
					this.setActionResult("0", "对方不想和你说话并向你扔了一只柴犬!");break;
				case "1"://已经是好友啦
					this.setActionResult("0", "你们已经是好友啦!");break;
				default:
					break;
			}
			return SUCCESS;
		}
		if(StringUtils.isEmpty(addId) || StringUtils.isEmpty(selfId)) {
			super.setActionResult("-9999", "系统异常!请联系管理员");
			return SUCCESS;
		}
		boolean rs = friendManageService.addFriend(addId, selfId);
		if(rs) {
			super.setActionResult("0", "发送好友添加请求成功");
		}else{
			super.setActionResult("-1", "发送好友添加请求失败");
		}
		return SUCCESS;
	}
	
	
	
	
	
	
	public String getAddId() {
		return addId;
	}
	public void setAddId(String addId) {
		this.addId = addId;
	}
	public String getSelfId() {
		return selfId;
	}
	public void setSelfId(String selfId) {
		this.selfId = selfId;
	}
	

}
