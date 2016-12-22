package com.linuxea.userManager.service.impl;


import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.linuxea.userManager.dao.UserManagerDAO;
import com.linuxea.userManager.service.UserManagerService;
import com.linuxea.userManager.vo.TbWoZiUser;


/*
 *@author Linuxea
 *@date Dec 15, 2016  1:16:54 AM
 *@version 1.0
 *@desc 
 */
@Service("userManagerService")
public class UserManagerServiceImpl implements UserManagerService {
	private Logger logger = Logger.getLogger(UserManagerServiceImpl.class);
	
	private UserManagerDAO userManagerDAO;

	public UserManagerDAO getUserManagerDAO() {
		return userManagerDAO;
	}
	@Resource(name="userManagerDAO")
	public void setUserManagerDAO(UserManagerDAO userManagerDAO) {
		this.userManagerDAO = userManagerDAO;
	}
	@Override
	public String userRegist(TbWoZiUser tbWoZiUser) throws Exception {
		String id = this.userManagerDAO.userRegist(tbWoZiUser);
		this.userManagerDAO.insertRootMenuForNewUser(id);
		return id;
		
	}
	@Override
	public boolean ajaxLogin(TbWoZiUser tbWoZiUser) throws Exception {
		return this.userManagerDAO.ajaxLogin(tbWoZiUser);
	}
	@Override
	public boolean isUserExist(TbWoZiUser tbWoZiUser) throws Exception {
		return this.userManagerDAO.isUserExist(tbWoZiUser);
	}
	@Override
	public String getUserIdByName(String name) throws Exception {
		return this.userManagerDAO.getUserIdByName(name);
	}
	
}
