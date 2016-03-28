package com.clj.dao.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.aop.support.AopUtils;
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
				//super.addObject(subject);
			}
			super.addObject(article);
			for(Articlesubject subject : subjects)
			{
				super.addObject(subject);
			}

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
				//super.addObject(subject);
			}
			super.addObject(article);
			super.addObject(content);
			for(Articlesubject subject : subjects)
			{
				super.addObject(subject);
			}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> queryArticleBySubjectAndLastPublishDate(
			long lastPublishDate, ArrayList<String> subjects, String method, int size) {
		// TODO Auto-generated method stub
		List<Article> articleList = new ArrayList<Article>();
		List<Article> queryList = null;
		if("latest".equals(method))
		{
			//String hql = "from Article as a inner join fetch a.articlesubjects as s where s.subject.subjectname=? order by createtime desc";
			String hql = "from Article as a inner join fetch a.articlesubjects as s where s.subject.subjectname in (:subjectname) order by createtime desc";
			//queryList = (List<Article>)super.queryByPage(hql, 0, size, subjects);
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameterList("subjectname", subjects); 
			queryList  = (List<Article>)query.setFirstResult(0).setMaxResults(size).list();
		}
		else if("above".equals(method))
		{
			String hql = "from Article as a inner join fetch a.articlesubjects as s where a.createtime>:createtime and s.subject.subjectname in (:subjectname) order by createtime asc";
			//queryList = (List<Article>)super.queryByPage(hql, 0, size, lastPublishDate, subjects);
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("createtime", lastPublishDate);
			query.setParameterList("subjectname", subjects); 
			queryList  = (List<Article>)query.setFirstResult(0).setMaxResults(size).list();
		}
		else if("below".equals(method))
		{
			String hql = "from Article as a inner join fetch a.articlesubjects as s where a.createtime<:createtime and s.subject.subjectname in (:subjectname) order by createtime desc";
			//queryList = (List<Article>)super.queryByPage(hql, 0, size, lastPublishDate, subjects);
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("createtime", lastPublishDate);
			query.setParameterList("subjectname", subjects); 
			queryList  = (List<Article>)query.setFirstResult(0).setMaxResults(size).list();
		}
		
		if(queryList != null)
		{
			articleList.addAll(queryList);
		}
		
		return articleList;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED , readOnly=false)
	public void deleteArticle(String articleId) {
		// TODO Auto-generated method stub
		String hql = "from Article as a where a.articleid=?";
		Article article = (Article)(super.queryBySql(hql, articleId).get(0));
		
		super.deleteObject(article);
	}

	@Override
	public List<Article> queryArticleBySubjectAndLastFormatDate(
			long lastFormatDate, ArrayList<String> subjects, String method, int size) {
		// TODO Auto-generated method stub
		List<Article> articleList = new ArrayList<Article>();
		List<Article> queryList = null;
		if("latest".equals(method))
		{
			String hql = "from Article as a inner join fetch a.articlesubjects as s where s.subject.subjectname in (:subjectname) and a.formattime!=0 order by formattime desc";
			//queryList = (List<Article>)super.queryByPage(hql, 0, size, subjects);
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameterList("subjectname", subjects); 
			queryList  = (List<Article>)query.setFirstResult(0).setMaxResults(size).list();
		}
		else if("above".equals(method))
		{
			String hql = "from Article as a inner join fetch a.articlesubjects as s where a.formattime>:formattime and a.formattime!=0 and s.subject.subjectname in (:subjectname) order by formattime asc";
			//queryList = (List<Article>)super.queryByPage(hql, 0, size, lastFormatDate, subjects);
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("formattime", lastFormatDate);
			query.setParameterList("subjectname", subjects); 
			queryList  = (List<Article>)query.setFirstResult(0).setMaxResults(size).list();
		}
		else if("below".equals(method))
		{
			String hql = "from Article as a inner join fetch a.articlesubjects as s where a.formattime<:formattime and a.formattime!=0 and s.subject.subjectname in (:subjectname) order by formattime desc";
			//queryList = (List<Article>)super.queryByPage(hql, 0, size, lastFormatDate, subjects);
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("formattime", lastFormatDate);
			query.setParameterList("subjectname", subjects); 
			queryList  = (List<Article>)query.setFirstResult(0).setMaxResults(size).list();
		}
		
		if(queryList != null)
		{
			articleList.addAll(queryList);
		}
		
		return articleList;
	}
	
}
