package com.clj.domain;

/**
 * Articlecontent entity. @author MyEclipse Persistence Tools
 */

public class Articlecontent implements java.io.Serializable {

	// Fields

	private Integer id;
	private Article article;
	private String content;

	// Constructors

	/** default constructor */
	public Articlecontent() {
	}

	/** full constructor */
	public Articlecontent(Article article, String content) {
		this.article = article;
		this.content = content;
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

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}