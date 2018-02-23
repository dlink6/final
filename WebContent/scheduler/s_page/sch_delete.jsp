<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "schedule.ScheduleDBBean" %>
<%@ page import = "schedule.ScheduleDataBean" %>

<!DOCTYPE html>
<html>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<head>
<title>일정 상세보기</title>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script>
	function del(){
		if(confirm("정말 삭제하시겠습니까?")){
			return true;
	  	} else{
		 	return false;
	 	}
	}
</script>

<style>
.container {
	width: 500px;
	border: 1px solid;
	padding: 60px;
}
.h {
	text-align: center;
}
</style>

</head>

<body>
	<div class="container">
	<form method="post" role="form" action="/semi/scheduleDelPro.ls" >
		<div class="form-group">
			<h3 align=center><b>일정 상세보기</b></h3>
		</div>
		<div class="form-group">
			<label for="title">제목</label> <!-- 입력되어진 값이 표시되게 해야함. -->
			<input type="text" class="form-control" id="title" name="s_title" value="${s_title}" readonly>
		</div>
		<div class="form-group">
			<label for="start">시작 시간</label>
			<input type="datetime-local" class="form-control" id="start" name="s_start" value="${s_start}" readonly>
		</div>
		<div class="form-group">
			<label for="end">종료 시간</label>
			<input type="datetime-local" class="form-control" id="end" name="s_end" value="${s_end}" readonly>
		</div>
		<div class="form-group">
			<label for="content">내용</label>
			<textarea class="form-control" id="content" name="s_content" readonly style="resize:none;">${s_content}</textarea>
		</div>
		<div class="form-group" id="button" align="center">
			<c:if test="${sortation == 'company'}">		
				<c:if test="${memId.empno =='1000'}">		
					<input type="submit" name="delete" value="일정삭제" class="btn btn-success" onclick="return del();">
				</c:if>
					<input type="button" value="닫기" class="btn btn-success" onclick="window.close()">
			</c:if>
			<c:if test="${sortation != 'company'}">
				<input type="submit" name="delete" value="일정삭제" class="btn btn-success" onclick="return del();">
				<input type="button" value="닫기" class="btn btn-success" onclick="window.close()">
			</c:if>
		
		</div>
		
	</form>
	</div>
</body>
</html>