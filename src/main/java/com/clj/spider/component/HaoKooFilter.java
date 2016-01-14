package com.clj.spider.component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


public class HaoKooFilter {

	public Map<String, String[]> subjectFilter;
	public Map<String, String> authorsInitUrl;
	
	public Map<String, String[]> getSubjectFilter() {
		return subjectFilter;
	}
	public void setSubjectFilter(Map<String, String[]> subjectFilter) {
		this.subjectFilter = subjectFilter;
	}
	public Map<String, String> getAuthorsInitUrl() {
		return authorsInitUrl;
	}
	public void setAuthorsInitUrl(Map<String, String> authorsInitUrl) {
		this.authorsInitUrl = authorsInitUrl;
	}
	
	
}