<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<ul class="menu">
	<c:choose>
		<c:when test ="${empty authUser }">
			<li><a href="${pageContext.request.contextPath }/user/login">로그인</a></li>
			<li><a href="${pageContext.request.contextPath }/user/join">회원가입</a></li>
		</c:when>
		<c:otherwise>
			<li><a href="${pageContext.request.contextPath }/user/logout">로그아웃</a></li>		
			<li><a href="${pageContext.request.contextPath }/jblog/${authUser.id }">내블로그</a></li>
		</c:otherwise>
	</c:choose>
</ul>
