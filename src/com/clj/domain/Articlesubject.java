package com.clj.domain;

/**
 * Articlesubject entity. @author MyEclipse Persistence Tools
 */

public class Articlesubject implements java.io.Serializable {

	// Fields

	private Integer id;
	private Subject subject;
	private Article article;

	// Constructors

	/** default constructor */
	public Articlesubject() {
	}

	/** full constructor */
	public Articlesubject(Subject subject, Article article) {
		this.subject = subject;
		this.article = article;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Article getArticle() {
		return this.article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

}