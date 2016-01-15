package com.clj.web.servlet;

import java.util.Timer;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.clj.spider.SpiderTask;
import com.clj.web.utils.FileLogHelper;

public class SpiderServlet extends HttpServlet{

	@Resource SpiderTask spiderTask;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		
		String runSpider = getServletConfig().getServletContext().getInitParameter("runSpider");
		FileLogHelper.writeLog("SpiderServlet Init");
		if(!"0".equals(runSpider))
		{
			//启动Spider
			Timer timer = new Timer();
			long period = 1000 * 60 * 60 * 12;
			timer.schedule(spiderTask, 0, period);
			
			FileLogHelper.writeLog("Spider Run");
		}
		super.init();
	}

	
}
