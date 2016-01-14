package com.clj.spider.component;

import java.util.List;

public class ArticleCrawled {

	private String title;
	private String url;
	private String articleAbstract;
	private String img1;
	private String img2;
	private String img3;
	private String articleCreateTime;
	private String author;
	private List<String> keywords;
	private List<String> articleContent;
	
	public ArticleCrawled(String title, 
			String url, 
			String articleAbstract, 
			String img1, 
			String img2,
			String img3,
			String articleCreateTime,
			String author,
			List<String> keywords,
			List<String> articleContent)
	{
		this.title = title;
		this.url = url;
		this.articleAbstract = articleAbstract;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.articleCreateTime = articleCreateTime;
		this.author = author;
		this.keywords = keywords;
		this.articleContent = articleContent;
	}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getArticleAbstract() {
		return articleAbstract;
	}
	public void setArticleAbstract(String articleAbstract) {
		this.articleAbstract = articleAbstract;
	}


	public String getImg1() {
		return img1;
	}

	public void setImg1(String img1) {
		this.img1 = img1;
	}

	public String getImg2() {
		return img2;
	}

	public void setImg2(String img2) {
		this.img2 = img2;
	}

	public String getImg3() {
		return img3;
	}

	public void setImg3(String img3) {
		this.img3 = img3;
	}

	public String getArticleCreateTime() {
		return articleCreateTime;
	}

	public void setArticleCreateTime(String articleCreateTime) {
		this.articleCreateTime = articleCreateTime;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public List<String> getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(List<String> articleContent) {
		this.articleContent = articleContent;
	}
	
	
	
}
