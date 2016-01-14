package com.clj.dao.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.clj.dao.interfaces.ArticleServiceInter;
import com.clj.domain.Article;
import com.clj.domain.Articlecontent;
import com.clj.domain.Articlesubject;



@Service
@Transactional(propagation=Propagation.REQUIRED , readOnly=true)
public class ArticleServiceBean extends BaseServiceBean implements ArticleServiceInter{

	@Transactional(propagation=Propagation.REQUIRED , readOnly=false)
	public void saveArticle(Article article) {
		// TODO Auto-generated method stub
		super.addObject(article);
	}

	@Transactional(propagation=Propagation.REQUIRED , readOnly=false)
	public void saveArticleAndContentCascade(Article article,
			Articlecontent content) {
		// TODO Auto-generated method stub
		article.setArticlecontent(content);
		content.setArticle(article);
		super.addObject(article);
		super.addObject(content);
	}

	@Transactional(propagation=Propagation.REQUIRED , readOnly=false)
	public void saveArticleAndSubjectCascade(Article article,
			HashSet<Articlesubject> subjects) {
		// TODO Auto-generated method stub

			article.setArticlesubjects(subjects);
			for(Articlesubject subject : subjects)
			{
				subject.setArticle(article);
				super.addObject(subject);
			}
			super.addObject(article);

	}

	@Transactional(propagation=Propagation.REQUIRED , readOnly=false)
	public void saveArticleAndContentAndSubjectCascade(Article article,
			Articlecontent content, HashSet<Articlesubject> subjects) {
		// TODO Auto-generated method stub

			article.setArticlesubjects(subjects);
			article.setArticlecontent(content);
			content.setArticle(article);
			for(Articlesubject subject : subjects)
			{
				subject.setArticle(article);
				super.addObject(subject);
			}
			super.addObject(article);
			super.addObject(content);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> queryArticleBySubjectAndLastPublishDate(
			long lastPublishDate, String subject) {
		// TODO Auto-generated method stub
		List<Article> articleList = new ArrayList<Article>();
		List<Article> queryList = null;
		String hql = "from Article as a inner join fetch a.articlesubjects as s where a.createtime>? and s.subject.subjectname=? order by createtime asc";
		queryList = (List<Article>)super.queryByPage(hql, 0, 6, lastPublishDate, subject);
		if(queryList != null)
		{
			articleList.addAll(queryList);
		}
		
		return articleList;
	}

}
