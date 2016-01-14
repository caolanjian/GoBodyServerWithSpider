package com.clj.dao.services;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.clj.dao.interfaces.BaseServiceInter;

/**
 * 
 * @author a
 * <tx:annoation-driven />只会查找和它在相同的应用上下文件中定义的bean上面的@Transactional注解
 * 如果不先将BaseServiceBean交给Spring管理，则BaseServiceBean无法使用事物，也无法被Spring注入Session
 */

@Service
@Transactional(propagation=Propagation.REQUIRED , readOnly=true)
public class BaseServiceBean implements BaseServiceInter {

	@Resource SessionFactory sessionFactory;
	
/*	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}*/
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS , readOnly=false)
	public void addObject(Object obj) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().persist(obj);;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS , readOnly=false)
	public void deleteObject(Object obj) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(obj);;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS , readOnly=false)
	public void updateObject(Object obj) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().merge(obj);
	}

	@Override
	public Object getSingleObject(Class<?> arg, Serializable id) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().load(arg, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> queryByPage(String hql, int offset, int pagesize,
			Object... values) {
		// TODO Auto-generated method stub
		List<Object> list = null;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		for (int i = 0; i < values.length; i++) {  
			 query.setParameter(i, values[i]);
		}
		if(0 == pagesize)
		{
			list = query.list();
		}
		else
		{
			list  = query.setFirstResult(offset).setMaxResults(pagesize).list();
		}
		return list;
	}

	@Override
	public List<?> queryBySql(String sql, Map parasMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> queryBySql(String sql, Object... parameters) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		for (int i = 0; i < parameters.length; i++) {  
			 query.setParameter(i, parameters[i]);
		}
		
		return query.list();
	}

}
