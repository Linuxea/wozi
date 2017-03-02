package com.linuxea.FriendManager.dao;

import com.linuxea.FriendManager.po.FriendBean;

/*
 *@author Linuxea
 *@date Mar 3, 2017  12:17:53 AM
 *@version 1.0
 *@desc 
 */

public interface FriendManageDAO {
	
	/**向某位好友发送添加请求*/
	boolean sentAddNews(String addId, String selfId) throws Exception;
	
	/**发送之前检测是否已经发送过好友请求 以免重复发送*/
	FriendBean isSent(String addId, String selfId) throws Exception;

}
