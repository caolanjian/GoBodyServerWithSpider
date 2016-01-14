package com.clj.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.clj.dao.interfaces.ArticleServiceInter;
import com.clj.dao.services.ArticleServiceBean;
import com.clj.domain.Articlecontent;
import com.clj.domain.ContentLine;
import com.clj.resources.Resources;
import com.clj.web.utils.ActionContextService;
import com.opensymphony.xwork2.ActionContext;

@Controller
public class ResourceAction {

	@Resource private ArticleServiceInter articleService;
	@Resource private Resources resources;
	
	public String getArticleContent()
	{
		System.out.println("Handler request in getArticleContent");
		String returnedString = "success";
		
		ActionContextService actionContextService = new ActionContextService(ActionContext.getContext());
		String articleId = actionContextService.getStringParaFormContext("articleId");
		
		
		//Articlecontent articleContent = (Articlecontent)hibernateUtil.getSingleObject(Articlecontent.class, articleId);
		String hql = "from Articlecontent where article.articleid=?";
		Articlecontent articleContent =  (Articlecontent)articleService.queryBySql(hql, articleId).get(0);
		//Articlecontent articleContent = (Articlecontent)(hibernateUtil.getSingleObject(Articlecontent.class, articleId));
		
		if(articleContent != null)
		{
			String content = articleContent.getContent();
			String[] contentList = content.split("\n");
			System.out.println(contentList.length);
			List<ContentLine> article = new ArrayList<ContentLine>();
			for(String line : contentList)
			{
				if(line.indexOf("<img>")!=-1)
				{
					String url = resources.getAPACHE_HOME_SERVER_IP() + 
							line.substring(line.indexOf("<img>") + "<img>".length(), line.indexOf("</img>"));
					ContentLine cl = new ContentLine("img", 
													url.substring(url.lastIndexOf(".")+1),
													url);
					article.add(cl);
				}
				else if(line.indexOf("<iframe>")!=-1)
				{
					String url = line.substring(line.indexOf("<iframe>") + "<iframe>".length(), line.indexOf("</iframe>"));
					ContentLine cl = new ContentLine("iframe","", url);
					article.add(cl);
				}
				else
				{
					ContentLine cl = new ContentLine("text","", line);
					article.add(cl);
				}
				System.out.println(line);
			}
			
			returnedString  = "success";
			actionContextService.setParaToSession("articleContent", article);
			actionContextService.setParaToSession("test", "test");
			System.out.println(article.size());
		}
		else
		{
			returnedString  = "failed";
		}
		
		return returnedString;
	}
}
