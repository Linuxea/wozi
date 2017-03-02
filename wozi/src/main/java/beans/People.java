package beans;

import java.util.Date;

/*
 *@author Linuxea
 *@date Mar 3, 2017  12:27:08 AM
 *@version 1.0
 *@desc 
 */

/**用来封装获取所有的用户信息*/
public class People {
	private String id;
	private String userName;
	private Date registdate;
	private String email;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getRegistdate() {
		return registdate;
	}
	public void setRegistdate(Date registdate) {
		this.registdate = registdate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
