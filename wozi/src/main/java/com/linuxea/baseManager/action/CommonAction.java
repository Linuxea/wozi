package com.linuxea.baseManager.action;

import org.springframework.stereotype.Controller;

import utils.GetCurrentIPUtils;

/*
 *@author Linuxea
 *@date Mar 6, 2017  3:45:51 AM
 *@version 1.0
 *@desc 
 */
@Controller("baseAction")
public class CommonAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8315646883345582693L;

	public String getIp() {
		String ip = GetCurrentIPUtils.getCurrnetIP();
		ip = "http://"+ip+":8080/wozi/";
		this.setActionResult("0", "获取ip地址成功", ip);
		return this.SUCCESS;
	}

}
