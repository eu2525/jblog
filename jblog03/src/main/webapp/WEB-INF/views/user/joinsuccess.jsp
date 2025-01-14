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
	<div class="center-content">
		<h1 class="logo">JBlog</h1>
		<c:import url="/WEB-INF/views/includes/menu.jsp" />
		<p class="welcome-message">
			<span> 감사합니다. 회원 가입 및 블로그가 성공적으로 만들어 졌습니다.</span>
			<br><br>
			<a href="${pageContext.request.contextPath }/user/login">로그인 하기</a>
		</p>
	</div>
</body>
</html>
