package com.clj.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import resources.webResource.Resources;

import com.clj.dao.interfaces.ArticleServiceInter;
import com.clj.dao.interfaces.SubjectServiceInter;
import com.clj.dao.services.ArticleServiceBean;
import com.clj.dao.services.SubjectServiceBean;
import com.clj.domain.Article;
import com.clj.web.utils.ActionContextService;
import com.opensymphony.xwork2.ActionContext;

@Controller
public class ListAction {


	@Resource private ArticleServiceInter articleService;
	@Resource private SubjectServiceInter subjectService;
	
	

	public String getList()
	{
		List<Article> articleList = new ArrayList<Article>();
		int itemCount = articleService.queryBySql("from Historyurl").size();
		int pageSize = 10;
		int pageCount = itemCount/pageSize+1;
		
		ActionContextService actionContextService = new ActionContextService(ActionContext.getContext());
		String pageNumStr = actionContextService.getStringParaFormContext("pageNum");
		System.out.println("Try to get pageNum");
		int pageNum = 1;
		if(pageNumStr != null)
		{
			System.out.println("pageNum: " + pageNumStr);
			pageNum = Integer.parseInt(pageNumStr);
		}
		
		articleList = (List<Article>)articleService.queryByPage("from Article order by createtime desc", pageSize*(pageNum-1), pageSize);
		
		
		
		for(Article art : articleList)
		{
			//reset url
			String url = Resources.SERVER_IP + Resources.SERVER_NAME + "/Resource!getArticleContent.action?articleId=" + art.getArticleid();
			art.setUrl(url);

		}
		
		actionContextService.setParaToSession("articleList", articleList);
		actionContextService.setParaToSession("pageCount", pageCount);
		actionContextService.setParaToSession("itemCount", itemCount);
		return "success";
	}
	
	public String getXmlListInfo()
	{
		List<Article> articleList = null;
		int querySize = 6;

		ActionContextService actionContextService = new ActionContextService(ActionContext.getContext());
		String lastPublishDateStr = actionContextService.getStringParaFormContext("lastPublishDate");
		String subject = actionContextService.getStringParaFormContext("subject");
		long lastPublishDate = 0;
		System.out.println("Try to get lastPublishDate");
		if(lastPublishDateStr != null)
		{
			System.out.println("lastPublishDate: " + lastPublishDateStr);
			lastPublishDate = Long.parseLong(lastPublishDateStr);
		}
		if(subject == null)
		{
			subject = "fitness";
		}
		System.out.println(subject);
		String subjectInDB = Resources.channelIndexMap.get(subject);
		System.out.println(subjectInDB);
		articleList = articleService.queryArticleBySubjectAndLastPublishDate(lastPublishDate, subjectInDB);
		
		for(Article art : articleList)
		{
			//reset url
			String url = Resources.SERVER_IP + Resources.SERVER_NAME + "/Resource!getArticleContent.action?articleId=" + art.getArticleid();
			art.setUrl(url);
		}
		actionContextService.setParaToSession("items", articleList.size());
		actionContextService.setParaToSession("articleList", articleList);
		return "successXml";
	}
}
