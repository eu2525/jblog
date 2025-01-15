<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
<div id="container">
		<div id="header">
			<h1>${blogVo.title } </h1>
			<ul>
				<c:choose>
					<c:when test ="${empty authUser }">
						<li><a href="${pageContext.request.contextPath }/user/login">로그인</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${pageContext.request.contextPath }/user/logout">로그아웃</a></li>
						<c:if test="${authUser.id == blogVo.blogId }">
							<li><a href="${pageContext.request.contextPath }/${blogVo.blogId }/admin">블로그 관리</a></li>
						</c:if>	
					</c:otherwise>		
				</c:choose>
			</ul>
		</div>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<c:choose>
						<c:when test="${not empty mainPost }">
							<h4>${mainPost.title }</h4>
							<p>
								${mainPost.contents }
							<p>
						</c:when>
						<c:otherwise>
							<h4>읽고 싶은 글을 선택해주세요.</h4>
							<p>
								아래의 글 목록에서 읽고 싶은 글을 선택해주세요.
							<p>
						</c:otherwise>	
					</c:choose>
				</div>
				<ul class="blog-list">
					<c:forEach var="post" items="${postList }" >
						<c:choose>
							<c:when test="${post.title == mainPost.title }">
								 <li><a href="${pageContext.request.contextPath }/${blogVo.blogId }/${post.categoryId }/${post.id}" style="color: red; font-weight: bold;">${post.title }</a> <span>${post.regDate}</span></li>
							</c:when>
							<c:otherwise>
								<li><a href="${pageContext.request.contextPath }/${blogVo.blogId }/${post.categoryId }/${post.id}">${post.title }</a> <span>${post.regDate}</span>	</li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blogVo.profile }" >
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach var="category" items="${categoryList }" >
					<li><a href="${pageContext.request.contextPath }/${category.blogId }/${category.id }">${category.name }</a></li>
				</c:forEach>
			</ul>
		</div>
		
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>

</html>