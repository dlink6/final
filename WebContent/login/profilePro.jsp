<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

<!-- -1,1  -->
<c:set var="success" value="<script type='text/javascript'>alert('정상적으로 프로필 사진이 변경되었습니다..');</script>"/>
<c:set var="error" value="<script type='text/javascript'>alert('프로필 사진 변경중 오류가 발생했습니다.');</script>" />
<c:set var="check" value="${check}"/>
<c:choose>
	<c:when test="${check==1}">
	<c:out value="${success}" escapeXml="false" />
	  <meta http-equiv="refresh" content="0;URL='/semi/Main.ls'"> 
	</c:when>
	<c:when test="${check==-1}"><!-- 비밀번호 틀림  -->
	<c:out value="${error}" escapeXml="false" />
	  <meta http-equiv="refresh" content="0;URL='/semi/Main.ls'"> 
	</c:when>
</c:choose>  

</body></html>