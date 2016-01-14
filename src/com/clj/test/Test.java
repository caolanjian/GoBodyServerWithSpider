package com.clj.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.clj.dao.interfaces.BaseServiceInter;
import com.clj.domain.Subject;

public class Test {

	public static void main(String[] args)
	{
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:resources/spring/applicationContext.xml");
		TestBean testBean = (TestBean)ac.getBean("testBean");
		testBean.printName();
		
		BaseServiceInter bs = (BaseServiceInter)ac.getBean("baseServiceBean");
		bs.addObject(new Subject("asdfg"));
	}
}
