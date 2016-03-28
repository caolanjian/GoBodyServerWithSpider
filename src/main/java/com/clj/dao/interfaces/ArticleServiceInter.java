package com.clj.dao.interfaces;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.clj.domain.Article;
import com.clj.domain.Articlecontent;
import com.clj.domain.Articlesubject;
import com.clj.dao.interfaces.BaseServiceInter;

public interface ArticleServiceInter extends BaseServiceInter {

	public void saveArticle(Article article);
	
	public void deleteArticle(String articleId);
	
	public void saveArticleAndContentCascade(Article article, Articlecontent content);
	
	public void saveArticleAndSubjectCascade(Article article, HashSet<Articlesubject> subjects);
	
	public void saveArticleAndContentAndSubjectCascade(Article article, Articlecontent content, HashSet<Articlesubject> subjects);
	
	public List<Article> queryArticleBySubjectAndLastPublishDate(long lastPublishDate, ArrayList<String> subjects, String method, int size);
	
	public List<Article> queryArticleBySubjectAndLastFormatDate(long lastPublishDate, ArrayList<String> subjects, String method, int size);
}
