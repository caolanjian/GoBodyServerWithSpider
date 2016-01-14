package com.clj.dao.services;

import java.io.Serializable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.clj.dao.interfaces.SubjectServiceInter;
import com.clj.domain.Subject;

@Service
@Transactional(propagation=Propagation.REQUIRED , readOnly=true)
public class SubjectServiceBean extends BaseServiceBean implements SubjectServiceInter{

	
	public Subject getSubjectByName(String name) {
		// TODO Auto-generated method stub
		String hql = "from Subject where subjectname=?";
		return (Subject)super.queryBySql(hql, name).get(0);
	}

	public Subject getSubjectById(Serializable id) {
		// TODO Auto-generated method stub
		return (Subject)super.getSingleObject(Subject.class, id);
	}

}
