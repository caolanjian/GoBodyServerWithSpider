package com.clj.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.clj.dao.interfaces.ArticleServiceInter;
import com.clj.dao.interfaces.ArticlecontentServiceInter;
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
	@Resource private ArticlecontentServiceInter articleContentService;
	
	public String getArticleContent()
	{
		System.out.println("Handler request in getArticleContent");
		String returnedString = "success";
		
		ActionContextService actionContextService = new ActionContextService(ActionContext.getContext());
		String articleId = actionContextService.getStringParaFormContext("articleId");
		String edit = actionContextService.getStringParaFormContext("edit");
		
		//Articlecontent articleContent = (Articlecontent)hibernateUtil.getSingleObject(Articlecontent.class, articleId);
		String hql = "from Articlecontent where article.articleid=?";
		Articlecontent articleContent =  (Articlecontent)articleService.queryBySql(hql, articleId).get(0);
		//Articlecontent articleContent = (Articlecontent)(hibernateUtil.getSingleObject(Articlecontent.class, articleId));
		
		if(articleContent != null)
		{
			String content = articleContent.getContent();
			StringBuffer formatContent = new StringBuffer("");
			String[] contentList = content.split("\n");
			System.out.println(contentList.length);
			List<ContentLine> article = new ArrayList<ContentLine>();
			for(String line : contentList)
			{
				if(line.indexOf("<img>")!=-1)
				{
					String url = resources.getAPACHE_IMG_SERVER_IP() + 
							line.substring(line.indexOf("<img>") + "<img>".length(), line.indexOf("</img>"));
					ContentLine cl = new ContentLine("img", 
													url.substring(url.lastIndexOf(".")+1),
													url);
					//System.out.println(line);
					formatContent.append(line).append("\n").append("\n");
					article.add(cl);
				}
				else if(line.indexOf("<iframe>")!=-1)
				{
					String url = line.substring(line.indexOf("<iframe>") + "<iframe>".length(), line.indexOf("</iframe>"));
					ContentLine cl = new ContentLine("iframe","", url);
					//System.out.println(line);
					formatContent.append(line).append("\n").append("\n");
					article.add(cl);
				}
				else
				{
					if(!"".equals(line.trim()))
					{
						ContentLine cl = new ContentLine("text","", line);
						//System.out.println(line);
						formatContent.append(line).append("\n").append("\n");
						article.add(cl);
					}
				}
				//System.out.println(line);
			}
			
			if(edit!=null && "true".equals(edit))
			{
				returnedString  = "successWithEdit";
			}
			else
			{
				returnedString  = "success";
			}
			

			actionContextService.setParaToSession("articleContent", article);
			actionContextService.setParaToSession("contentStr", formatContent.toString());
			actionContextService.setParaToSession("articleid", articleId);
			System.out.println(article.size());
		}
		else
		{
			returnedString  = "failed";
		}
		
		return returnedString;
	}
	
	public String updateArticleContent()
	{
		String returnedString = "editSuccess";
		
		ActionContextService actionContextService = new ActionContextService(ActionContext.getContext());
		String articleid = actionContextService.getStringParaFormContext("articleid");
		String formatContent = actionContextService.getStringParaFormContext("content");
		
		System.out.println("update " + articleid);
		System.out.println(formatContent);
		
		articleContentService.updateContentandArticleCascade(articleid, formatContent);
		
		return returnedString;
	}
	
	public String deleteArticle()
	{
		ActionContextService actionContextService = new ActionContextService(ActionContext.getContext());
		String articleid = actionContextService.getStringParaFormContext("articleid");
		
		System.out.println("delete " + articleid);
		
		if(articleid!=null && !articleid.equals(""))
		{
			articleService.deleteArticle(articleid);
		}
		return "editSuccess";
	}
}
