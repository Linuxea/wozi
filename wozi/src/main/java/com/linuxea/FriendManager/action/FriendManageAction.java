package com.linuxea.FriendManager.action;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	public String listAllPeoInfo() throws SQLException {
		List<People> peos = new ListAllPersonUtils().listPersons();
		super.setActionResult("0", "获取所有用户信息成功", peos);
		return this.SUCCESS;
	}
	

}
