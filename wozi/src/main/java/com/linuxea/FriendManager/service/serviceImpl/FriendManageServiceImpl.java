package com.linuxea.FriendManager.service.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.linuxea.FriendManager.dao.FriendManageDAO;
import com.linuxea.FriendManager.po.FriendBean;
import com.linuxea.FriendManager.service.FriendManageService;

/*
 *@author Linuxea
 *@date Mar 3, 2017  12:19:14 AM
 *@version 1.0
 *@desc 
 */
@Service("friendManageService")
public class FriendManageServiceImpl implements FriendManageService {
	
	private FriendManageDAO friendManageDAO;

	public FriendManageDAO getFriendManageDAO() {
		return friendManageDAO;
	}
	
	@Resource(name = "friendManageDAO")
	public void setFriendManageDAO(FriendManageDAO friendManageDAO) {
		this.friendManageDAO = friendManageDAO;
	}
	
	public boolean addFriend(String addId, String selfId)throws Exception {
		return friendManageDAO.sentAddNews(addId, selfId);
	}

	@Override
	public FriendBean isSent(String addId, String selfId) throws Exception {
		return friendManageDAO.isSent(addId, selfId);
	}
}
