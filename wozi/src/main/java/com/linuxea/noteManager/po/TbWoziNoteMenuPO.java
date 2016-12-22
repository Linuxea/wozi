package com.linuxea.noteManager.po;

import java.io.Serializable;

/*
 *@author Linuxea
 *@date Dec 17, 2016  5:00:39 PM
 *@version 1.0
 *@desc 
 */

public class TbWoziNoteMenuPO implements Serializable{
	private String realId;
	private String id;
	private String parent;//是否存在父目录？父目录id:“#”
	private String text;//目录或者文件的名称
	private String flag;//0表示目录  1表示文件
	private String refUser;//关联用户的id
	public String getFlag() {
		return flag;
	}
	public String getId() {
		return id;
	}
	public String getParent() {
		return parent;
	}
	public String getRealId() {
		return realId;
	}
	public String getRefUser() {
		return refUser;
	}
	public String getText() {
		return text;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public void setRealId(String realId) {
		this.realId = realId;
	}
	public void setRefUser(String refUser) {
		this.refUser = refUser;
	}
	public void setText(String text) {
		this.text = text;
	}
}
