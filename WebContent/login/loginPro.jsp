<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="passflase" value="<script type='text/javascript'>alert('비밀번호가 맞지않습니다.');</script>"/>
<c:set var="idfalse" value="<script type='text/javascript'>alert('아이디가 맞지않습니다');</script>" />
<c:set var="execute" value="<script type='text/javascript'>window.open('/semi/alarm.ls','일정 알림','top=50px, left=520px, width=500px, height=440px, menubar=no, toolbar=no');</script>" />

<c:set var="x" value="${x}"/>

<c:choose>
	<c:when test="${x==1}">
	  <c:out value="${execute}" escapeXml="false" />
	  <meta http-equiv="refresh" content="0;URL='/semi/Main.ls'"> 
	</c:when>
	<c:when test="${x==0}"><!-- 아이디가 없음 -->
	<c:out value="${idfalse}" escapeXml="false" />
		<meta http-equiv="refresh" content="0;URL='/semi/Loginpage.ls'">
	</c:when>
	<c:when test="${x==-1}"><!-- 비밀번호 틀림  -->
	<c:out value="${passflase}" escapeXml="false" />
		<meta http-equiv="refresh" content="0;URL='/semi/Loginpage.ls'">
	</c:when>
</c:choose>  
