package com.linuxea.userManager.vo;

/*
 *@author Linuxea
 *@date Dec 13, 2016-11:04:38 PM
 *@version 1.0
 *@desc 会员积分
 */

public class TbWoZiIntegration {
	private String id;
	private String userId;//ref userid
	private int integration;//积分
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getIntegration() {
		return integration;
	}
	public void setIntegration(int integration) {
		this.integration = integration;
	}
	
	
}
