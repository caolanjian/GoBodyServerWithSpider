package com.clj.dao.services;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.clj.dao.interfaces.ArticlecontentServiceInter;
import com.clj.domain.Article;
import com.clj.domain.Articlecontent;

@Service
@Transactional(propagation=Propagation.REQUIRED , readOnly=true)
public class ArticlecontentServiceBean extends BaseServiceBean implements ArticlecontentServiceInter{

	@Override
	@Transactional(propagation=Propagation.REQUIRED , readOnly=false)
	public void updateContentandArticleCascade(String articleId, String content) {
		// TODO Auto-generated method stub
		if(articleId==null || content==null)
		{
			return;
		}
		String hql = "from Articlecontent as ac inner join fetch ac.article as a where a.articleid=?";
		Articlecontent articleContent = (Articlecontent)super.queryBySql(hql, articleId).get(0);
		Article article = articleContent.getArticle();
		
		if(articleContent == null || article == null)
		{
			return;
		}
		
		article.setFormattime((new Date()).getTime());
		articleContent.setContent(content);
		
		super.updateObject(articleContent);
		super.updateObject(article);
		
	}

}
