<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" import="com.clj.domain.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<html>
  <head>
    <title>My JSP 'resourceList.jsp' starting page</title>
  </head>
  
  <body>
  		<div class="itemList">
  			<c:forEach var="articleItem" items="${session.articleList}">
  			
  				<div class="desc">
					  <div class="desc_pic">
					  		<a href="${articleItem.url }">
					  			<img src="${articleItem.img1 }" width="175" height="116" alt="${articleItem.title }"/>
					  		</a>
					  </div>
					  
				      <div class="desc_title">
				    		<span>
				    			<a href="${articleItem.url }">${articleItem.title }</a>
				    		</span>
				      </div>	   
				           
					  <div class="desc_time">
					  		<span>TimeStamp: </span>${articleItem.createtime }
					  </div>
					  
					  <div class="desc_info">		     
					     	<div class="desc_info_abstract">
					     			<p>${articleItem.abstract_ }</p>
					    	 </div>
					     
						 	 <div class="desc_info_addi">
						   			<div class="com_num">
						   				<a href="${articleItem.url }">阅读(94)</a>
						   					┆作者：${articleItem.author }
						   			</div>			   
						   	
						  	 <div class="detail">
						  			<a href="${articleItem.url }">查看全文>></a>
						  	 </div>
						 </div>
					</div>
				</div>
  			</c:forEach>
  		
  		</div>
    	
    	<div class="pageList" style="margin-top:20px;">
			<div class="pagelistbox">
				<span>共 ${session.pageCount } 页/${session.itemCount }条记录 </span>
				<span class='indexPage'>首页 </span><strong>1</strong>
				<a href='http://caolanjian.6655.la:46664/GoBodyServerTest/List!getList.action?pageNum=2'>2</a>
				<a href='http://caolanjian.6655.la:46664/GoBodyServerTest/List!getList.action?pageNum=3'>3</a>
				<a href='http://caolanjian.6655.la:46664/GoBodyServerTest/List!getList.action?pageNum=4'>4</a>
				<a href='http://caolanjian.6655.la:46664/GoBodyServerTest/List!getList.action?pageNum=5'>5</a>
				<a href='http://caolanjian.6655.la:46664/GoBodyServerTest/List!getList.action?pageNum=6'>6</a>
				<a href='http://caolanjian.6655.la:46664/GoBodyServerTest/List!getList.action?pageNum=7'>7</a>
				<a class='nextPage' href='http://caolanjian.6655.la:46664/GoBodyServerTest/List!getList.action?pageNum=2'>下页</a> 
				<a class='endPage' href='http://caolanjian.6655.la:46664/GoBodyServerTest/List!getList.action?pageNum=${session.pageCount } '>末页</a> 
		</div>
</div>
  </body>
</html>
