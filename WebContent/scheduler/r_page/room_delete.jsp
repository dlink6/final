<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<head>
<title>회의실 예약 등록</title>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

<script>
	//현재 로그인된 사원과 이름과 같은지 확인
	function checkIt(){
		if((document.getElementById("name").value) == (document.getElementById("ename_").value)){
			if(confirm("정말 삭제하시겠습니까?")){
				return true;
		  	} else{
			 	return false;
		 	}
		} else{
			alert("다른 사람의 예약을 지울 수 없습니다.");
			return false;
		}
	}
</script>

<style>
.container {
	width: 400px;
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
	<form role="form" method="post" action="/semi/reservationDelPro.ls" onsubmit="return checkIt()">
		<input type="hidden" name="empno" value="${memId.getEmpno()}">
		
		<div class="form-group">
			<h3 align=center>회의실 예약</h3>
		</div>
		<div class="form-group">
			<label for="name">예약자 이름</label> <!-- 예약자명 자동 입력 -->
			<input type="text" class="form-control" id="name" name="member_name" value="${member_name}" readonly >
		</div>
		<div class="form-group">
			<label for="room">회의실</label><br>
			<input type="text" class="form-control" id="room" name="room" value="${room}" readonly>
		</div>
		<div class="form-group">
			<label for="count">참여 인원수</label>
			<input type="text" class="form-control" id="count" name="member_count" value="${member_count}" readonly>
		</div>
		<div class="form-group">
			<label for="start">시작 시간</label>
			<input type="datetime-local" class="form-control" id="start" name="r_start" value="${r_start}" readonly>
		</div>
		<div class="form-group">
			<label for="end">종료 시간</label>
			<input type="datetime-local" class="form-control" id="end" name="r_end" value="${r_end}" readonly>
		</div>
		<div class="form-group" id="button" align="center">
			<input type="hidden" name="ename_" id="ename_" value="${memId.ename}">
			<input type="submit" value="예약삭제" class="btn btn-success" onclick="return checkIt();">
			<input type="button" value="닫기" class="btn btn-success" onclick="window.close()">
		</div>
		
	</form>
	</div>
</body>
</html>