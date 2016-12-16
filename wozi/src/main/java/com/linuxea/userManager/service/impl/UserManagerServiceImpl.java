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
	public void userRegist(TbWoZiUser tbWoZiUser) throws Exception {
		this.userManagerDAO.userRegist(tbWoZiUser);
	}
	@Override
	public boolean userLogin(TbWoZiUser tbWoZiUser) throws Exception {
		return this.userManagerDAO.userLogin(tbWoZiUser);
	}
	
}
