package utils;

import java.net.InetAddress;

/*
 *@author Linuxea
 *@date Mar 6, 2017  12:42:35 AM
 *@version 1.0
 *@desc 
 */
/**提供当前ip地址的一个接口*/
public class GetCurrentIPUtils {
	
	public static String getCurrnetIP() {
		InetAddress addr = null;
		String ip = null;
		  try {
	            addr = InetAddress.getLocalHost();
	            ip = addr.getHostAddress();
	        } catch ( java.net.UnknownHostException e) {
	            e.printStackTrace();
	        }
		return ip;
		
	}
}
