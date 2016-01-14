package com.clj.domain;

/**
 * Userfavorite entity. @author MyEclipse Persistence Tools
 */

public class Userfavorite implements java.io.Serializable {

	// Fields

	private Integer id;
	private Article article;
	private Users users;

	// Constructors

	/** default constructor */
	public Userfavorite() {
	}

	/** full constructor */
	public Userfavorite(Article article, Users users) {
		this.article = article;
		this.users = users;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Article getArticle() {
		return this.article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

}