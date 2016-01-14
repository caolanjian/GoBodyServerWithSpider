package com.clj.domain;

/**
 * Keywords entity. @author MyEclipse Persistence Tools
 */

public class Keywords implements java.io.Serializable {

	// Fields

	private Integer id;
	private String keyword;
	private String articleIds;

	// Constructors

	/** default constructor */
	public Keywords() {
	}

	/** minimal constructor */
	public Keywords(String keyword) {
		this.keyword = keyword;
	}

	/** full constructor */
	public Keywords(String keyword, String articleIds) {
		this.keyword = keyword;
		this.articleIds = articleIds;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getArticleIds() {
		return this.articleIds;
	}

	public void setArticleIds(String articleIds) {
		this.articleIds = articleIds;
	}

}