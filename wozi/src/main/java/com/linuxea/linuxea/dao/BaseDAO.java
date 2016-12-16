package com.linuxea.linuxea.dao;

import java.util.List;

/*
 *@author Linuxea
 *@date Dec 15, 2016  11:47:51 AM
 *@version 1.0
 *@desc 
 */

public interface  BaseDAO<T> {
	/*
	 * save single 
	 */
	void saveSingle(T t);
	
	/*
	 * save list
	 */
	void saveList(List<T> tList);
	
	/*
	 * get single by id
	 */
	T getSingle(String tId);
	
	/*
	 * get list
	 */
	List<T> getList(T t);
	
}
