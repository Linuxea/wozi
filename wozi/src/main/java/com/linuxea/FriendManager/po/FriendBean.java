package com.linuxea.FriendManager.po;

import java.util.Date;

/*
 *@author Linuxea
 *@date Mar 3, 2017  1:49:30 AM
 *@version 1.0
 *@desc 
 */

public class FriendBean {
	
	private String id;
	private String addPerson;
	private String addedPerson;
	private String addedAgree;
	private Date addTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAddPerson() {
		return addPerson;
	}
	public void setAddPerson(String addPerson) {
		this.addPerson = addPerson;
	}
	public String getAddedPerson() {
		return addedPerson;
	}
	public void setAddedPerson(String addedPerson) {
		this.addedPerson = addedPerson;
	}
	public String getAddedAgree() {
		return addedAgree;
	}
	public void setAddedAgree(String addedAgree) {
		this.addedAgree = addedAgree;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
	

}
