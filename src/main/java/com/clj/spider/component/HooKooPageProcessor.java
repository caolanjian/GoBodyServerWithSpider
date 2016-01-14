package com.clj.spider.component;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clj.dao.interfaces.HistoryurlServiceInter;
import com.clj.domain.Historyurl;
import com.clj.resources.Resources;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

@Component
public class HooKooPageProcessor implements PageProcessor{

	private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
	@Resource HistoryurlServiceInter historyurlService;
	@Resource HaoKooFilter haoKooFilter;

	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}

	public void process(Page page) {
		// TODO Auto-generated method stub
		
		Html html = page.getHtml();
		
		page.addTargetRequests(html.links().regex("(http://www\\.haokoo\\.com/.+)").all());
		
		
		//page.addTargetRequests(html.links().regex("(http://www\\.haokoo\\.com/beauty/\\w+\\.html)").all());
		//page.addTargetRequests(html.links().all());
		
		try
		{
			String title = html.xpath("//title/text()").toString();
			if(page.getUrl().regex("(http://www\\.haokoo\\.com/\\w+/\\w+\\.html)").match())
			{
				if(checkWhetherSkipPage(page)==false)
				{
					ArticleCrawled article = parserArticle(page);
					page.putField("article", article);
				}
				else
				{
					page.setSkip(true);
				}
				
			}
			else
			{
				page.setSkip(true);
			}
		}
		catch(Exception e)
		{
			page.setSkip(true);
			e.printStackTrace();
		}
		
	}
	
	public boolean checkWhetherSkipPage(Page page)
	{
		Html html = page.getHtml();
		
		try
		{
			String url = page.getUrl().toString();
			String titleFull = html.xpath("//title/text()").toString(); 
			String author = titleFull.substring(titleFull.indexOf("_")+1, titleFull.indexOf("΢�Ź���"));
			String keywords = html.xpath("//meta[@name='keywords']/@content").toString();
			
			List<Historyurl> historyUrls = (List<Historyurl>)historyurlService.queryBySql("from Historyurl where url=?", url);
			if(historyUrls!=null && historyUrls.size() > 0)
			{
				return true;
			}
			
			for(String af : haoKooFilter.getAuthorsInitUrl().keySet())
			{
				if(author.indexOf(af)!=-1)
				{
					return false;
				}
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return true;
		}

		return true;
	}
	
	private ArticleCrawled parserArticle(Page page)
	{
		Html html = page.getHtml();
		String title = html.xpath("//title/text()").toString();
		title = title.substring(0, title.indexOf("_"));
		
		String url = page.getUrl().toString();
		String img1 = null;
		String img2 = null;
		String img3 = null;
		
		String author = html.xpath("//div[@class='haokuacl13']/p/span/a/text()").toString();
		
		String articleCreateTime = html.xpath("//div[@class='haokuacl13']//p/text()").toString();
		articleCreateTime = articleCreateTime.substring(0, articleCreateTime.indexOf(" "));
		
		String articleAbstract = html.xpath("//div[@id='article']/div[@class='description']/p/text()").toString();
		
		List<String> keywords = new ArrayList<String>();
		for(String keyword : html.xpath("//meta[@name='keywords']/@content").toString().split(","))
		{
			keywords.add(keyword);
		}
		
		List<String> articleContent = new ArrayList<String>();
		
		String nodeList = html.xpath("//div[@id='article']").nodes().toString();
		for(String node : nodeList.split("\n"))
		{
			//System.out.println("Nodes: " + node);
			Html lineHtml = new Html(node);
			
			List<String> imgList = new ArrayList<String>();
			imgList.addAll(lineHtml.xpath("//img[@data-src]/@data-src").all());
			imgList.addAll(lineHtml.xpath("//img[@src]/@src").all());
			imgList.addAll(lineHtml.xpath("//img[@data]/@data").all());
			imgList.addAll(lineHtml.xpath("//img[@data-]/@data-").all());
			if(imgList!=null && imgList.size() > 0)
			{
				for(String img : imgList)
				{
					String imgUrl = img;
					if(img.indexOf("url=http") != -1)
					{
						imgUrl = img.substring(img.indexOf("url=http") + "url=".length());
					}
					articleContent.add("<img>" + imgUrl + "</img>");
					if(img1 == null)
					{
						img1 = imgUrl;
					}
					else if(img2 == null)
					{
						img2 = imgUrl;
					}
					else if(img3 == null)
					{
						img3 = imgUrl;
					}
				}
			}
			
			List<String> frameList = new ArrayList<String>();
			frameList.addAll(lineHtml.xpath("//iframe[@data-src]/@data-src").all());
			frameList.addAll(lineHtml.xpath("//iframe[@src]/@src").all());
			if(frameList !=null && frameList.size() >0)
			{
				for(String frame : frameList)
				{
					articleContent.add("<iframe>" + frame + "</iframe>");
				}
			}
			
			if(node != null)	
			{
				StringBuffer text = new StringBuffer("");
				//ƥ�����б�><Ȧ�������ַ�
				List<String> textList = lineHtml.regex(">[^<]+<").all();
				for(String part : textList)
				{
					text.append(part.subSequence(1, part.length()-1));
				}
				if("".equals(text.toString().trim()) && node.indexOf("<br")!=-1)
				{
					articleContent.add("<br />");
				}
				else
				{
					articleContent.add(text.toString());
				}
			}
		}
		
		articleContent = cutOffContent(articleContent, author);

		return new ArticleCrawled(title, url, articleAbstract, 
				img1, img2, img3, 
				articleCreateTime,
				author,
				keywords,
				articleContent);
	}
	
	private List<String> cutOffContent(List<String> contentList, String author)
	{
		String frontIgnore = "阅读，只需一秒。精彩，尽在掌握";

		int frontIgnoreIndex = 0;
		for(int index=0; index<contentList.size(); index++)
		{
			String line = contentList.get(index);

			if(line.indexOf(frontIgnore) != -1)
			{
				frontIgnoreIndex = index;
			}
		}
		
		return contentList.subList(frontIgnoreIndex+1, contentList.size()-1);
	}
}
