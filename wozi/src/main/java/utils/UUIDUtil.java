package utils;

import java.util.UUID;

/*
 *@author Linuxea
 *@date Mar 3, 2017  2:36:58 AM
 *@version 1.0
 *@desc 
 */
/**用来生成没有连字符-的Id总接口*/
public class UUIDUtil {
	
	public static String createId() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
