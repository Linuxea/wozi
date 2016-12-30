package com.linuxea.userManager.dao;

import com.linuxea.userManager.vo.TbWoZiUser;

/*
 *@author Linuxea
 *@date Dec 15, 2016  1:18:55 AM
 *@version 1.0
 *@desc 
 */

public interface UserManagerDAO {

	String userRegist(TbWoZiUser tbWoZiUser) throws Exception;

	boolean ajaxLogin(TbWoZiUser tbWoZiUser) throws Exception;

	boolean isUserExist(TbWoZiUser tbWoZiUser) throws Exception;
	
	public String getUserIdByName(String name) throws Exception;
	
	public void insertRootMenuForNewUser(String userId) throws Exception;

	boolean updateLastLoginTime(String userName) throws Exception;

}
