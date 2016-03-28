package com.clj.dao.interfaces;

public interface ArticlecontentServiceInter extends BaseServiceInter {
	
	public void updateContentandArticleCascade(String articleId, String content);
}
