package com.clj.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Timer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.clj.dao.interfaces.BaseServiceInter;
import com.clj.domain.Subject;
import com.clj.spider.SpiderTask;

public class Test {

	public static void main(String[] args)
	{

		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:resources/spring/applicationContext.xml");
		TestBean testBean = (TestBean)ac.getBean("testBean");
		testBean.printName();
		
		SpiderTask spiderTask = (SpiderTask)ac.getBean("spiderTask");
		Timer timer = new Timer();
		long period = 1000 * 60 * 60 * 12;
		timer.schedule(spiderTask, 0, 1000 * 60 * 10);
	}
}
