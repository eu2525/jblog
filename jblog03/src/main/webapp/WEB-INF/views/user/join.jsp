<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
$(function(){
	
	$("#btn-checkemail").click(function() {
		var id = $("#id").val();
		if(id == "") {
			return;
		}
		
		$.ajax({
			url: "${pageContext.request.contextPath}/api/user/checkid?id=" + id,
			type: "get",
			dataType: "json",
			success: function(response) {
				console.log(response);
				
				if(response.exist) {
					alert("아이디가 존재합니다. 다른 아이디를 사용해 주세요.");
					$("#id").val("");
					$("#id").focus();
					
					return;
				}
				
				$("#img-checkemail").show();
				$("#btn-checkemail").hide();
				$("input[type='submit']").prop("disabled", false);
			},
			error: function(xhr, status, err) {
				console.error(err);
			}
		});
	});
	
	
    // id값 변경 시 이벤트
    $("#id").on("input", function() {
    	// 초기화
        $("#btn-checkemail").show();
        $("#img-checkemail").hide();
        $("input[type='submit']").prop("disabled", true);
    });
    
});
</script>
</head>
<body>
	<div class="center-content">
		<h1 class="logo">JBlog</h1>
		<c:import url="/WEB-INF/views/includes/menu.jsp" />
		<form:form modelAttribute="userVo" class="join-form" id="join-form" method="post" action="${pageContext.request.contextPath }/user/join">
			<label class="block-label" for="name">이름</label>
			<form:input path="name" />
			<p style="color:#f00; text-align:left; padding:0">
				<form:errors path="name" />
			</p>	
			
			<label class="block-label" for="blog-id">아이디</label>
			<form:input path="id" /> 
			<input id="btn-checkemail" type="button" value="id 중복체크">
			<img id="img-checkemail" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">
			<p style="color:#f00; text-align:left; padding:0">
				<form:errors path="id" />
			</p>				


			<label class="block-label" for="password">패스워드</label>
			<form:input path="password" />
			<p style="color:#f00; text-align:left; padding:0">
				<form:errors path="password" />
			</p>	

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기" disabled>

		</form:form>
	</div>
</body>
</html>
