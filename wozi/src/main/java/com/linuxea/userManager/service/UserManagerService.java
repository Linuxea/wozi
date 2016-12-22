package com.linuxea.userManager.service;

import com.linuxea.userManager.vo.TbWoZiUser;

/*
 *@author Linuxea
 *@date Dec 15, 2016  1:16:37 AM
 *@version 1.0
 *@desc 
 */

public interface UserManagerService {

	String userRegist(TbWoZiUser tbWoZiUser) throws Exception;

	boolean ajaxLogin(TbWoZiUser tbWoZiUser) throws Exception;

	boolean isUserExist(TbWoZiUser tbWoZiUser) throws Exception;
	
	public String getUserIdByName(String name) throws Exception;

}
