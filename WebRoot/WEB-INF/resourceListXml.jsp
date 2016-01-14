<%@ page language="java" isELIgnored="false" contentType="text/xml; charset=utf-8" import="com.clj.domain.*" pageEncoding="utf-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><?xml version="1.0" encoding="utf-8"?>

<ArticlesList items="${session.items}">
  			<c:forEach var="articleItem" items="${session.articleList}">
  				<id value="${articleItem.id }">
  					<articleid>${articleItem.articleid }</articleid>
  					<title>${articleItem.title }</title>
  					<url>${articleItem.url }</url>
					<abstract>${articleItem.abstract_ }</abstract>
					<author>${articleItem.author }</author>
					<img1>${articleItem.img1 }</img1>
					<img2>${articleItem.img2 }</img2>
					<img3>${articleItem.img3 }</img3>
					<createtime>${articleItem.createtime }</createtime>
  				</id>
  			</c:forEach>
  		
</ArticlesList>