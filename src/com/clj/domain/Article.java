package com.clj.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Article entity. @author MyEclipse Persistence Tools
 */

public class Article implements java.io.Serializable {

	// Fields

	private Integer id;
	private String articleid;
	private String title;
	private String url;
	private String abstract_;
	private String author;
	private String img1;
	private String img2;
	private String img3;
	private Integer supportnum;
	private Long createtime;
	private Set userfavorites = new HashSet(0);
	private Set usercomments = new HashSet(0);
	private Articlecontent articlecontent;
	private Set articlesubjects = new HashSet(0);

	// Constructors

	/** default constructor */
	public Article() {
	}

	/** minimal constructor */
	public Article(String articleid, String title, String url,
			String abstract_, String author, Integer supportnum, Long createtime) {
		this.articleid = articleid;
		this.title = title;
		this.url = url;
		this.abstract_ = abstract_;
		this.author = author;
		this.supportnum = supportnum;
		this.createtime = createtime;
	}

	/** full constructor */
	public Article(String articleid, String title, String url,
			String abstract_, String author, String img1, String img2,
			String img3, Integer supportnum, Long createtime,
			Set userfavorites, Set usercomments, Articlecontent articlecontent,
			Set articlesubjects) {
		this.articleid = articleid;
		this.title = title;
		this.url = url;
		this.abstract_ = abstract_;
		this.author = author;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.supportnum = supportnum;
		this.createtime = createtime;
		this.userfavorites = userfavorites;
		this.usercomments = usercomments;
		this.articlecontent = articlecontent;
		this.articlesubjects = articlesubjects;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getArticleid() {
		return this.articleid;
	}

	public void setArticleid(String articleid) {
		this.articleid = articleid;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAbstract_() {
		return this.abstract_;
	}

	public void setAbstract_(String abstract_) {
		this.abstract_ = abstract_;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getImg1() {
		return this.img1;
	}

	public void setImg1(String img1) {
		this.img1 = img1;
	}

	public String getImg2() {
		return this.img2;
	}

	public void setImg2(String img2) {
		this.img2 = img2;
	}

	public String getImg3() {
		return this.img3;
	}

	public void setImg3(String img3) {
		this.img3 = img3;
	}

	public Integer getSupportnum() {
		return this.supportnum;
	}

	public void setSupportnum(Integer supportnum) {
		this.supportnum = supportnum;
	}

	public Long getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}

	public Set getUserfavorites() {
		return this.userfavorites;
	}

	public void setUserfavorites(Set userfavorites) {
		this.userfavorites = userfavorites;
	}

	public Set getUsercomments() {
		return this.usercomments;
	}

	public void setUsercomments(Set usercomments) {
		this.usercomments = usercomments;
	}

	public Articlecontent getArticlecontent() {
		return this.articlecontent;
	}

	public void setArticlecontent(Articlecontent articlecontent) {
		this.articlecontent = articlecontent;
	}

	public Set getArticlesubjects() {
		return this.articlesubjects;
	}

	public void setArticlesubjects(Set articlesubjects) {
		this.articlesubjects = articlesubjects;
	}

}