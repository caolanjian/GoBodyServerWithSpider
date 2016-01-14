package com.clj.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.clj.dao.interfaces.BaseServiceInter;
import com.clj.domain.Subject;

public class Test {

	public static void main(String[] args)
	{
		Properties prop = new Properties();
		InputStream in = Object.class.getResourceAsStream("/resources/webResource/resources.properties");
		try{
			prop.load(in);
			String APACHE_HOME_SERVER_IP = prop.getProperty("APACHE_HOME_SERVER_IP");
			String APACHE_IMG_SERVER_IP = prop.getProperty("APACHE_IMG_SERVER_IP");
			String MYSQL_CONNECTION_URL = prop.getProperty("MYSQL_CONNECTION_URL");
			String IMG_URI_HEADER = prop.getProperty("IMG_URI_HEADER");
			String APACHE_RESOURCE_PATH = prop.getProperty("APACHE_RESOURCE_PATH");
			String TOMCAT_SERVER1_IP = prop.getProperty("TOMCAT_SERVER1_IP");
			String TOMCAT_SERVER2_IP = prop.getProperty("TOMCAT_SERVER2_IP");
			String TOMCAT_SERVER_APP_NAME = prop.getProperty("TOMCAT_SERVER_APP_NAME");
			String IMG_DOWNLOAD_PATH = APACHE_RESOURCE_PATH + IMG_URI_HEADER;
			String IMG_SERVER_URL_HEADER = APACHE_IMG_SERVER_IP + IMG_URI_HEADER;
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:resources/spring/applicationContext.xml");
		TestBean testBean = (TestBean)ac.getBean("testBean");
		testBean.printName();
		
		BaseServiceInter bs = (BaseServiceInter)ac.getBean("baseServiceBean");
		bs.addObject(new Subject("asdfg"));
	}
}
