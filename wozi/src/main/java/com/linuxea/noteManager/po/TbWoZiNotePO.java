package com.linuxea.noteManager.po;

import java.util.Date;

/*
 *@author Linuxea
 *@date Dec 19, 2016  7:43:53 PM
 *@version 1.0
 *@desc 
 */

public class TbWoZiNotePO {
	private String id;
	private String refMenu;
	private String title;
	private String content;
	private Date uploadTime;
	private String status;
	private String refUser;
	public String getRefUser() {
		return refUser;
	}
	public void setRefUser(String refUser) {
		this.refUser = refUser;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRefMenu() {
		return refMenu;
	}
	public void setRefMenu(String refMenu) {
		this.refMenu = refMenu;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
