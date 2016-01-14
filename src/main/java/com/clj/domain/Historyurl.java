package com.clj.domain;

/**
 * Historyurl entity. @author MyEclipse Persistence Tools
 */

public class Historyurl implements java.io.Serializable {

	// Fields

	private Integer id;
	private String url;

	// Constructors

	/** default constructor */
	public Historyurl() {
	}

	/** full constructor */
	public Historyurl(String url) {
		this.url = url;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}