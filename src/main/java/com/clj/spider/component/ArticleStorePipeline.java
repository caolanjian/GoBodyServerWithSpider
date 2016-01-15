package com.clj.spider.component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.clj.dao.interfaces.ArticleServiceInter;
import com.clj.dao.interfaces.HistoryurlServiceInter;
import com.clj.dao.interfaces.SubjectServiceInter;
import com.clj.domain.Article;
import com.clj.domain.Articlecontent;
import com.clj.domain.Articlesubject;
import com.clj.domain.Historyurl;
import com.clj.domain.Keywords;
import com.clj.domain.Subject;
import com.clj.spider.exception.IsNotDirectoryException;
import com.clj.web.utils.DownloaderUtil;
import com.clj.web.utils.FileLogHelper;
import com.clj.web.utils.ImageHelper;
import com.clj.resources.Resources;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Component
public class ArticleStorePipeline implements Pipeline{

	@Value("${IMG_DOWNLOAD_PATH}") String path;
	@Resource ArticleServiceInter articleService;
	@Value("${IMG_URI_HEADER}") String imgUrlHeader;
	@Resource SubjectServiceInter subjectService;
	@Resource HistoryurlServiceInter historyurlService;
	@Resource HaoKooFilter haoKooFilter;
	
	public String getSuffixFromUrl(String url)
	{
		String suffix = null;
		if(url.indexOf("?")!=-1)
		{
			String subParas = url.substring(url.indexOf("?") + 1);
			String[] paras = subParas.split("&");
			for(String para : paras)
			{
				if(para.indexOf("wx_fmt=")!=-1)
				{
					suffix = para.substring(para.indexOf("=")+1);
					break;
				}
			}
		}
		else
		{
			String lastSubUrl = url.substring(url.lastIndexOf("/") + 1);
			if(lastSubUrl.indexOf(".") != -1)
			{
				suffix = lastSubUrl.substring(lastSubUrl.lastIndexOf(".") + 1);
			}
		}

		if(suffix == null || suffix.equals(""))
		{
			suffix = "jpg";
		}
		
		return suffix;
	}
	
	public boolean checkImageValid(String imagePath)
	{
		File file = new File(imagePath);
		try
		{
			FileInputStream fis = new FileInputStream(file);
			BufferedImage bufferedImg = ImageIO.read(fis);
			int imgWidth = bufferedImg.getWidth();
			int imgHeight = bufferedImg.getHeight();
			if(imgWidth < 200 || imgHeight < 200 || imgWidth/imgHeight>2 || imgHeight/imgWidth>2)
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		catch(Exception e)
		{
			return true;
		}
		
	}
	
	public void process(ResultItems resultItems, Task task) {
		// TODO Auto-generated method stub
		File pictureStorePath = new File(path);
		if(pictureStorePath.isDirectory())
		{
			if(!pictureStorePath.exists())
			{
				pictureStorePath.mkdir();
			}
			else
			{
				//keep this folder
			}
		}
		
		ArticleCrawled articleCrawled = (ArticleCrawled)resultItems.get("article");
		if(articleCrawled == null)
		{
			return;
		}
		
		String title = articleCrawled.getTitle();
		String url = articleCrawled.getUrl();
		String articleAbstract = articleCrawled.getArticleAbstract();
		String img1 = "";
		String img2 = "";
		String img3 = "";
		String articleCreateTime = articleCrawled.getArticleCreateTime();
		String author = articleCrawled.getAuthor();
		List<String> keywords = articleCrawled.getKeywords();
		List<String> articleContent = articleCrawled.getArticleContent();
		
		//�޳��ظ�����
		List<Article> temp = (List<Article>)articleService.queryBySql("from Article where title=?", title);
		if(temp != null && temp.size() > 0)
		{
			return;
		}

		//�޳�����Ϊ�յ�����
		if(articleContent == null)
		{
			return;
		}
		if(articleContent.size() <= 0)
		{
			return;
		}
		
		//1. create a Article Domain
		Article articleInDB = new Article();
		
		//2. create a id, create a folder to store img for article
		String id = UUID.randomUUID().toString();
		articleInDB.setArticleid(id);
				
		//4. generate articleCreateTime
		if(articleCreateTime != null)
		{
			Long date = (new Date()).getTime();

			articleInDB.setCreatetime(date);
		}
		
		//5. set title, url, abstract_, author, subject
		articleInDB.setTitle(title);
		articleInDB.setUrl(url);
		articleInDB.setAbstract_(articleAbstract);
		articleInDB.setAuthor(author);
		articleInDB.setSupportnum(0);
		
		//6. generate content

		Articlecontent content = new Articlecontent(articleInDB,  "");
		if(articleContent != null)
		{
			StringBuffer contentString = new StringBuffer("");
			for(String contentLine : articleContent)
			{
				if(contentLine.startsWith("<br"))
				{
					contentString.append("\n");
				}
				else if(contentLine.startsWith("<img>"))
				{
					//1. ��ȡͼƬurl��ַ
					String imgUrl = contentLine.substring(contentLine.indexOf("<img>") + "<img>".length(), 
																		contentLine.indexOf("</img>"));
					
					int downloadResult = -1;
					//2. ��ʼ�������ļ����resize�ļ���
					String imageName = UUID.randomUUID().toString() + "." + getSuffixFromUrl(imgUrl);
					String imagePath = path + "/" + imageName; 
					
					//3. ����ͼƬ
					downloadResult = DownloaderUtil.downloadFile(imgUrl, 
														imagePath);
					if(downloadResult != -1 && checkImageValid(imagePath))
					{
						/*set img sever url*/
						String urlPath = imgUrlHeader + "/" + imageName;
						contentString.append("<img>" + urlPath + "</img>" + "\n");
						if("".equals(img1))
						{
							img1 = urlPath;
						}
						else if("".equals(img2))
						{
							img2 = urlPath;
						}
						else if("".equals(img3))
						{
							img3 = urlPath;
						}
					}
				}
				else if(contentLine.startsWith("<iframe>"))
				{
					contentString.append(contentLine);
					contentString.append("\n");
				}
				else
				{
					contentString.append(contentLine);
					contentString.append("\n");
				}
			}
			content.setContent(contentString.toString());
		}
		
		//7. add Articlecontent into DB, Article will be add into DB auto
		articleInDB.setImg1(img1);
		articleInDB.setImg2(img2);
		articleInDB.setImg3(img3);
		
		//8. add Subject
		String subject = "健身";
		HashSet<Articlesubject> subjects = new HashSet<Articlesubject>();
		for(String subjectKey : haoKooFilter.getSubjectFilter().keySet())
		{
			String[] autherFilter = haoKooFilter.getSubjectFilter().get(subjectKey);
			for(String autherStr : autherFilter)
			{
				if(autherStr.indexOf(author) != -1)
				{
					subject = subjectKey;
					Subject subjectInDB = subjectService.getSubjectByName(subject);
					Articlesubject articleSubject = new Articlesubject();
					articleSubject.setArticle(articleInDB);
					articleSubject.setSubject(subjectInDB);
					subjects.add(articleSubject);
					break;
				}
			}
		}
		
		articleService.saveArticleAndContentAndSubjectCascade(articleInDB, content, subjects);
		
		//9. save the url into the history Url
		Historyurl historyUrl = new Historyurl(url);
		historyurlService.addObject(historyUrl);
	}

}
