<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" import="com.clj.domain.*"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<div id="article_body">
		<c:forEach var="line" items="${session.articleContent}" varStatus="status">
			<c:choose>
				<c:when test="${fn:contains(line.type, 'img') }">
					<p>
						<img data-s="300,640" alt="img" data-type="${line.suffix}" src="${line.lineContent}" data-ratio="1" data-w=""  />

					</p>
				</c:when>
				<c:when test="${fn:contains(line.type, 'iframe') }">
					<p>
					<video src="${line.lineContent}" height="375" width="500" controls="controls">
						</video>
					</p>
				</c:when>
				<c:otherwise>
					<p>${line.lineContent}</p>
				</c:otherwise>
			</c:choose>
			
    	</c:forEach>
    	
    </div>
</body>
</html>