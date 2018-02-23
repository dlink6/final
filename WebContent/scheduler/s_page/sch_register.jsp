<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>

<!DOCTYPE html>
<html>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<head>
<title>일정 등록</title>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script>
function checkIt(){
	if(document.getElementById("sortation").value==""){
		alert("일정 구분을 선택하세요.");
		return false;
	}
	
	if(!document.getElementById("title").value){
		alert("일정의 제목을 입력하세요.");
		return false;
	}
	
	if(!document.getElementById("start").value){
		alert("시작일을 입력하세요.");
		return false;
	}
	
	if(!document.getElementById("end").value){
		alert("종료일을 입력하세요.");
		return false;
	}
	
	//시작시간 날짜 변환
	var a =document.getElementById("start").value.split('T');
	var b = a[0].split('-')
	var c = b[0]+b[1]+b[2];
	
	//종료시간 날짜 변환
	var a1 =document.getElementById("end").value.split('T');
	var b1 = a1[0].split('-')
	var c1 = b1[0]+b1[1]+b1[2];
	
	//종료시간이 시작시간보다 작으면 등록 불가
	if(c1<c){
		alert("정확한 기간을 입력하세요.");
		return false;
	}

	return true;
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
	<form role="form" method="post" action="/semi/scheduleRegPro.ls" onsubmit="return checkIt()">
		<input type="hidden" name="empno" value="${sessionScope.memId.getEmpno()}">
		<input type="hidden" name="deptno" value="${sessionScope.memId.getDeptno()}">
		
		<select id="sortation" name="sortation" class="form-control" style="width:111px">
			<option value="" selected>--- 선택 ---</option>
			<c:if test="${memId.getEmpno() == '1001'}"> <!-- 관리자의 사원번호로 로그인 했을때만 보여짐 (회사일정 등록이 가능) -->
				<option value="company">회사 일정</option>
			</c:if>
			<option value="team">부서 일정</option>
			<option value="mine">나의 일정</option>
		</select>
		<div class="form-group">
			<h3 align=center>일정 등록</h3>
		</div>
		<div class="form-group">
			<label for="title">제목</label>
			<input type="text" class="form-control" id="title" name="s_title">
		</div>
		<div class="form-group">
			<label for="start">시작 시간</label>
			<input type="datetime-local" class="form-control" id="start" name="s_start" >
		</div>
		<div class="form-group">
			<label for="end">종료 시간</label>
			<input type="datetime-local" class="form-control" id="end" name="s_end">
		</div>
		<div class="form-group">
			<label for="content">내용</label>
			<textarea class="form-control" id="content" name="s_content"></textarea>
		</div>
		<div class="form-group" id="button" align="center">
			<input type="submit" value="등록" class="btn btn-success">
			<input type="reset" value="다시 쓰기" class="btn btn-success">
			<input type="button" value="닫기" class="btn btn-success" onclick="window.close()">
		</div>
		
	</form>
	</div>
</body>
</html>