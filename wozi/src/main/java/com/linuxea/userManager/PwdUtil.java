package com.linuxea.userManager;

/*
 *@author Linuxea
 *@date Dec 30, 2016  3:37:21 PM
 *@version 1.0
 *@desc  明文密码 <=> ascii表
 */

public class PwdUtil {
	public static String changeToAnscii(String pwd){
		int num ;
		String newPwd = "";
		for(int i = 0,j=pwd.length();i<j;i++) {
			num = pwd.charAt(i);
			newPwd += num+" ";
		}
		return newPwd;
		
	}
	
	public static String changeToString(String ansciiPwd){
		String[] strArr = ansciiPwd.split(" ");
		for(int i = 0,j = strArr.length;i<j;i++){
			//目前用不到 不写了
		}
		return "";
	}
}
