<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="suc" value ="<script type='text/javascript'>alert('정보가 수정되었습니다.');</script>"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>인사팀 정보수정</title>
</head>
<c:out value="${suc}" escapeXml="false"/>
<meta http-equiv ="refresh" content ="0;URL='/semi/inputForm.ls#section2'" >
</html>

