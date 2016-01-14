package com.clj.spider.component;

public abstract class FilterAbstractClass implements AutherFilterInterface
{
	public static String auther;

	public abstract String lastIgnoreLine();

}

interface AutherFilterInterface
{
	public String lastIgnoreLine();

}

