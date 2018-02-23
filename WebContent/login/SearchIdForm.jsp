<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="passflase" value="<script type='text/javascript'>alert('입력하신 사원번호가 없습니다..');</script>"/>
<c:set var="idfalse" value="<script type='text/javascript'>alert('주민등록 번호가 일치 하지 않습니다.');</script>" />
<c:set var="back" value="<script type='text/javascript'>history.go(-1);</script>" />
<c:set var="check" value="${check}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:choose>
	<c:when test="${check==1}">
	  <h1 align="center" style="color:#26a69a">아이디 찾기</h1>
	  <h4>당신의 사원번호는</h4><h2 style="color:#26a69a"> ${userId}</h2> <h4> 입니다.</h4>
	</c:when>
	<c:when test="${check==-1}"><!-- 아이디가 없음 -->
	<c:out value="${passflase}" escapeXml="false" />
	<c:out value="${back}" escapeXml="false" />
	</c:when>
	<c:when test="${check==0}"><!-- 비밀번호 틀림  -->
	<c:out value="${idfalse}" escapeXml="false" />
	<c:out value="${back}" escapeXml="false" />
	</c:when>
</c:choose>  





</body>
</html>






