package com.clj.spider;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Spider;

import com.clj.resources.Resources;
import com.clj.spider.component.ArticleStorePipeline;
import com.clj.spider.component.HaoKooFilter;
import com.clj.spider.component.HooKooPageProcessor;

@Component
public class SpiderHolder {

	@Resource HooKooPageProcessor hooKooPageProcessor;
	@Resource ArticleStorePipeline articleStorePipeline;
	@Resource HaoKooFilter haoKooFilter;
	private Spider spider;
	
	public HooKooPageProcessor getHooKooPageProcessor() {
		return hooKooPageProcessor;
	}
	public void setHooKooPageProcessor(HooKooPageProcessor hooKooPageProcessor) {
		this.hooKooPageProcessor = hooKooPageProcessor;
	}
	public ArticleStorePipeline getArticleStorePipeline() {
		return articleStorePipeline;
	}
	public void setArticleStorePipeline(ArticleStorePipeline articleStorePipeline) {
		this.articleStorePipeline = articleStorePipeline;
	}
	
	public Spider initSpider()
	{
		int threadNum = 0;
		spider = Spider.create(this.getHooKooPageProcessor())
				  .addPipeline(this.getArticleStorePipeline());
		
		Set<String> keySet = haoKooFilter.getAuthorsInitUrl().keySet();
		for(String author : keySet)
		{
			String url = haoKooFilter.getAuthorsInitUrl().get(author);
			if(url!=null && !"".equals(url))
			{
				spider = spider.addUrl(url);
				threadNum++;
			}
		}
		spider.thread(threadNum);
		return spider;
	}
	
	public void runSpider()
	{
		if(spider == null)
		{
			initSpider().run();
		}
		else
		{
			spider.run();
		}
	}
	
	public void stopSpider()
	{
		if(spider != null)
		{
			spider.stop();
			spider = null;
		}
	}
	
	
}
