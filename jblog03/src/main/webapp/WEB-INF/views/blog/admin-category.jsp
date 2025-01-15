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
				<li><a href="${pageContext.request.contextPath }/user/logout">로그아웃</a></li>
				<li><a href="${pageContext.request.contextPath }/${blogVo.blogId  }/admin">블로그 관리</a></li>
			</ul>
		</div>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="${pageContext.request.contextPath }/${blogVo.blogId  }/admin">기본설정</a></li>
					<li class="selected">카테고리</li>
					<li><a href="${pageContext.request.contextPath }/${blogVo.blogId  }/admin/write">글작성</a></li>
				</ul>
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
		      		
		      		<c:forEach var="category" items="${categoryList }" >
		      			<tr>
			      			<td>${category.id }</td>
							<td>${category.name }</td>
							<td>${category.totalPost }</td>
							<td>${category.description }</td>
							<td>
							    <a href="${pageContext.request.contextPath}/${blogVo.blogId}/admin/category/delete/${category.id}">
					                <img src="${pageContext.request.contextPath}/assets/images/delete.jpg" alt="삭제" style="cursor: pointer;">
					            </a>
							</td>
						</tr>
					</c:forEach>			  
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
      			<form id="admin-cat-form" action="${pageContext.request.contextPath }/${blogVo.blogId  }/admin/category" method="POST">
			      	<table id="admin-cat-add">
			      		<tr>
			      			<td class="t">카테고리명</td>
			      			<td><input type="text" name="name"></td>
			      		</tr>
			      		<tr>
			      			<td class="t">설명</td>
			      			<td><input type="text" name="description"></td>
			      		</tr>
			      		<tr>
			      			<td class="s">&nbsp;</td>
			      			<td><input type="submit" value="카테고리 추가"></td>
			      		</tr>      		      		
			      	</table> 
				</form>
			</div>
		</div>
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>