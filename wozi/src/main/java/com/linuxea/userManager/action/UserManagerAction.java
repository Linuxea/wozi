package com.linuxea.userManager.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.linuxea.linuxea.action.BaseAction;
import com.linuxea.userManager.service.UserManagerService;
import com.linuxea.userManager.vo.TbWoZiUser;

@Controller("userManagerAction")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserManagerAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(UserManagerAction.class);
	private UserManagerService userManagerService;
	private TbWoZiUser tbWoZiUser;
	public TbWoZiUser getTbWoZiUser() {
		return tbWoZiUser;
	}

	public void setTbWoZiUser(TbWoZiUser tbWoZiUser) {
		this.tbWoZiUser = tbWoZiUser;
	}

	public UserManagerService getUserManagerService() {
		return userManagerService;
	}
	
	@Resource(name="userManagerService")
	public void setUserManagerService(UserManagerService userManagerService) {
		this.userManagerService = userManagerService;
	}
	
	public String ajaxRegist() throws Exception {
		this.userManagerService.userRegist(tbWoZiUser);
		this.setActionResult("0", "新增用户"+tbWoZiUser.getUserName()+"成功");
		return this.SUCCESS;
	}
	
	public String ajaxLogin() throws Exception {
		boolean isExistUser = this.userManagerService.userLogin(tbWoZiUser);
		if(isExistUser){
			this.setActionResult("0", tbWoZiUser.getUserName()+"登录成功");
		}else{
			this.setActionResult("-1", tbWoZiUser.getUserName()+"登录失败");
		}
		return super.SUCCESS;
	}
	
	
	
}
