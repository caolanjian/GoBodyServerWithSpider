package com.clj.domain;

/**
 * Usercomment entity. @author MyEclipse Persistence Tools
 */

public class Usercomment implements java.io.Serializable {

	// Fields

	private Integer id;
	private Article article;
	private Users users;
	private String comment;

	// Constructors

	/** default constructor */
	public Usercomment() {
	}

	/** full constructor */
	public Usercomment(Article article, Users users, String comment) {
		this.article = article;
		this.users = users;
		this.comment = comment;
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

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}