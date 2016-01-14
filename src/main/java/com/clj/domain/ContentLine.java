package com.clj.domain;

import java.io.Serializable;

public class ContentLine implements Serializable{

	/**
	 * 
	 */
	private String type;
	private String suffix;
	private String lineContent;
	
	public ContentLine()
	{
		
	}
	
	public ContentLine(String type, String suffix, String lineContent)
	{
		this.lineContent = lineContent;
		this.type = type;
		this.suffix = suffix;
	}
	
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public String getLineContent() {
		return lineContent;
	}
	public void setLineContent(String lineContent) {
		this.lineContent = lineContent;
	}
	
	
	
}
