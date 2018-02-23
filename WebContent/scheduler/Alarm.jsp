<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<head>
<title>일정 알림</title>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<style>
.container {
	width: 500px;
	/* border: 1px solid; */
	padding: 60px;
}
.h {
	text-align: center;
}
#str {
	text-align: center;
}

</style>

</head>
<body>
	<div class="container">
		<h2 id="str">${memId.ename}님, 안녕하세요!<br></h2>
		<h3 id="str">${str}의 일정입니다.</h3>
		
		<c:if test="${emptyData == false}">
		<ul class="list-group">
		<c:forEach var="i" items="${data}">
	  		<li class="list-group-item">${i.s_title}</li>
	  	</c:forEach>
		</ul>
		</c:if>
		
		<c:if test="${emptyData == true}">
		<ul class="list-group">
			<li class="list-group-item">일정이 없습니다.</li>
		</ul>
		</c:if>
		
		<div align="center">
		<button type="button" class="btn btn-info" onclick="window.close()" style="align:center">확인</button>
		</div>
	</div>
</body>
</html>