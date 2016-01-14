package com.clj.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Subject entity. @author MyEclipse Persistence Tools
 */

public class Subject implements java.io.Serializable {

	// Fields

	private Integer subjectid;
	private String subjectname;
	private Set articlesubjects = new HashSet(0);

	// Constructors

	/** default constructor */
	public Subject() {
	}

	/** minimal constructor */
	public Subject(String subjectname) {
		this.subjectname = subjectname;
	}

	/** full constructor */
	public Subject(String subjectname, Set articlesubjects) {
		this.subjectname = subjectname;
		this.articlesubjects = articlesubjects;
	}

	// Property accessors

	public Integer getSubjectid() {
		return this.subjectid;
	}

	public void setSubjectid(Integer subjectid) {
		this.subjectid = subjectid;
	}

	public String getSubjectname() {
		return this.subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public Set getArticlesubjects() {
		return this.articlesubjects;
	}

	public void setArticlesubjects(Set articlesubjects) {
		this.articlesubjects = articlesubjects;
	}

}