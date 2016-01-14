package com.clj.dao.interfaces;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseServiceInter {

	public void addObject(Object obj);
	public void deleteObject(Object obj);
	public void updateObject(Object obj);
	public Object getSingleObject(Class<?> arg, Serializable id);
	public List<?> queryByPage(final String hql, final int offset, final int pagesize, Object... values);
	public List<?> queryBySql(final String sql, Map parasMap);
	public List<?> queryBySql(final String sql, final Object... parameters);
}
