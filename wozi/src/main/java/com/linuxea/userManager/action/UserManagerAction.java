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
	private String confirmPassword;
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

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
		boolean isUserExist = this.userManagerService.isUserExist(tbWoZiUser);
		if(isUserExist){
			this.setActionResult("-1", "用户名称已经存在");
		}else if(null==tbWoZiUser || tbWoZiUser.getUserName().equals("")
				|| tbWoZiUser.getPassword().equals("")){
			this.setActionResult("-1", "请确认信息完整");
		}else if(!tbWoZiUser.getPassword().equals(confirmPassword)){
			this.setActionResult("-1", "密码不一致");
		}else{
			this.userManagerService.userRegist(tbWoZiUser);
			super.getSession().put("user", tbWoZiUser);//存放用户信息
			this.setActionResult("0", "新增用户"+tbWoZiUser.getUserName()+"成功");
		}
		return this.SUCCESS;
	}
	
	public String ajaxLogin() throws Exception {
		boolean isUserExist = this.userManagerService.ajaxLogin(tbWoZiUser);
		if(isUserExist){
			super.getSession().put("user", tbWoZiUser);//存放用户信息
			this.setActionResult("0", tbWoZiUser.getUserName()+"登录成功",tbWoZiUser.getUserName());
		}else{
			this.setActionResult("-1", tbWoZiUser.getUserName()+"登录失败");
		}
		return super.SUCCESS;
	}
}
