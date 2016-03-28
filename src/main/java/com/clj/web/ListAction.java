package com.clj.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.clj.dao.interfaces.ArticleServiceInter;
import com.clj.dao.interfaces.SubjectServiceInter;
import com.clj.domain.Article;
import com.clj.resources.Resources;
import com.clj.web.utils.ActionContextService;
import com.opensymphony.xwork2.ActionContext;

@Controller
public class ListAction {


	@Resource private ArticleServiceInter articleService;
	@Resource private SubjectServiceInter subjectService;
	@Resource private Resources resources;
	

	public String getList()
	{
		List<Article> articleList = new ArrayList<Article>();
		int itemCount = articleService.queryBySql("from Historyurl").size();
		int pageSize = 10;
		int pageCount = itemCount/pageSize+1;
		
		ActionContextService actionContextService = new ActionContextService(ActionContext.getContext());
		String pageNumStr = actionContextService.getStringParaFormContext("pageNum");
		String edit = actionContextService.getStringParaFormContext("edit");
		if(edit==null)
		{
			edit="true";
		}
		String subject = actionContextService.getStringParaFormContext("subject");
		System.out.println("Try to get pageNum");
		int pageNum = 1;
		if(pageNumStr != null)
		{
			System.out.println("pageNum: " + pageNumStr);
			pageNum = Integer.parseInt(pageNumStr);
		}
		
		if(subject!=null)
		{
			subject = resources.getChannelIndexMap().get(subject);
		}
		
		if(edit!=null && edit.equals("true"))
		{
			if(subject == null)
			{
				articleList = (List<Article>)articleService.queryByPage("from Article where formattime=0 order by createtime desc", pageSize*(pageNum-1), pageSize);
			}
			else
			{
				articleList = (List<Article>)articleService.queryByPage("from Article as a inner join fetch a.articlesubjects as s where a.formattime=0 and s.subject.subjectname=" + subject + " order by createtime desc", pageSize*(pageNum-1), pageSize);
			}
		}
		else
		{
			if(subject == null)
			{
				articleList = (List<Article>)articleService.queryByPage("from Article order by createtime desc", pageSize*(pageNum-1), pageSize);
			}
			else
			{
				articleList = (List<Article>)articleService.queryByPage("from Article as a inner join fetch a.articlesubjects as s where s.subject.subjectname=" + subject + " order by createtime desc", pageSize*(pageNum-1), pageSize);
			}
		}
		
		for(Article art : articleList)
		{
			//reset url
			String url = resources.getAPACHE_HOME_SERVER_IP() + resources.getTOMCAT_SERVER_APP_NAME() + "/Resource!getArticleContent.action?articleId=" + art.getArticleid();
			art.setUrl(url);
			
			if(art.getImg1() != null && !art.getImg1().equals(""))
			{
				String imgUrl1 = resources.getAPACHE_IMG_SERVER_IP() + art.getImg1();
				art.setImg1(imgUrl1);
			}
			
			if(art.getImg2() != null && !art.getImg2().equals(""))
			{
				String imgUrl2 = resources.getAPACHE_IMG_SERVER_IP() + art.getImg2();
				art.setImg2(imgUrl2);
			}
			
			if(art.getImg3() != null && !art.getImg3().equals(""))
			{
				String imgUrl3 = resources.getAPACHE_IMG_SERVER_IP() + art.getImg3();
				art.setImg3(imgUrl3);
			}
		}

		actionContextService.setParaToSession("articleList", articleList);
		actionContextService.setParaToSession("pageCount", pageCount);
		actionContextService.setParaToSession("itemCount", itemCount);
		actionContextService.setParaToSession("pageNum", pageNum);
		return "success";
	}
	
	public String getXmlListInfo()
	{
		List<Article> articleList = null;
		int querySize = 6;

		ActionContextService actionContextService = new ActionContextService(ActionContext.getContext());
		String lastPublishDateStr = actionContextService.getStringParaFormContext("lastPublishDate");
		String subject = actionContextService.getStringParaFormContext("subject");
		String method = actionContextService.getStringParaFormContext("method");
		String sizeStr = actionContextService.getStringParaFormContext("size");
		long lastPublishDate = 0;
		int size = 6;
		System.out.println("Try to get lastPublishDate");
		if(lastPublishDateStr != null)
		{
			System.out.println("lastPublishDate: " + lastPublishDateStr);
			lastPublishDate = Long.parseLong(lastPublishDateStr);
		}
		
		ArrayList<String> subjects = new ArrayList<String>();
		if(subject == null)
		{
			Set<String> subjectSet = resources.getChannelIndexMap().keySet();
			for(String s : subjectSet)
			{
				subjects.add(resources.getChannelIndexMap().get(s));
			}
		}
		else
		{
			String[] subjectSet = subject.split("\\|");
			for(String s : subjectSet)
			{
				subjects.add(resources.getChannelIndexMap().get(s));
			}
		}
		if(method == null)
		{
			method = "above";
		}
		System.out.println(method);
		if(sizeStr != null)
		{
			size = Integer.parseInt(sizeStr);
		}
		System.out.println(size);
		System.out.println(subject);
		//String subjectInDB = resources.getChannelIndexMap().get(subject);
		//System.out.println(subjectInDB);
		articleList = articleService.queryArticleBySubjectAndLastFormatDate(lastPublishDate, subjects, method, size);
		
		for(Article art : articleList)
		{
			//reset url
			String url = resources.getAPACHE_HOME_SERVER_IP() + resources.getTOMCAT_SERVER_APP_NAME() + "/Resource!getArticleContent.action?articleId=" + art.getArticleid();
			art.setUrl(url);
			
			if(art.getImg1() != null && !art.getImg1().equals(""))
			{
				String imgUrl1 = resources.getAPACHE_IMG_SERVER_IP() + art.getImg1();
				art.setImg1(imgUrl1);
			}
			
			if(art.getImg2() != null && !art.getImg2().equals(""))
			{
				String imgUrl2 = resources.getAPACHE_IMG_SERVER_IP() + art.getImg2();
				art.setImg2(imgUrl2);
			}
			
			if(art.getImg3() != null && !art.getImg3().equals(""))
			{
				String imgUrl3 = resources.getAPACHE_IMG_SERVER_IP() + art.getImg3();
				art.setImg3(imgUrl3);
			}

		}

		actionContextService.setParaToSession("items", articleList.size());
		actionContextService.setParaToSession("articleList", articleList);
		
		return "successXml";
	}
}
