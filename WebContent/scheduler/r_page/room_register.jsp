<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<head>
<title>회의실 예약 등록</title>

<!-- <link rel="stylesheet" href="css/bootstrap.css" type="text/css">
<link rel="stylesheet" href="css/bootstrap-theme.css" type="text/css"> -->

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

<script>
function checkIt(){
	if(document.getElementById("room").value==""){
		alert("회의실을 선택하세요.");
		return false;
	}
	
	if(document.getElementById("count").value==""){
		alert("참여 인원 수를 선택하세요.");
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
	<form role="form" method="post" action="/semi/reservationRegPro.ls" onsubmit="return checkIt()">
		<input type="hidden" name="empno" value="${memId.getEmpno()}">
		
		<div class="form-group">
			<h3 align=center>회의실 예약</h3>
		</div>
		<div class="form-group">
			<label for="name">예약자 이름</label> <!-- 로그인된 사원의 이름 자동 입력 -->
			<input type="text" class="form-control" id="name" name="member_name" value="${memId.getEname()}" readonly>
		</div>
		<div class="form-group">
			<label for="room">회의실</label><br>
			<select id="room" name="room" class="form-control">
				<option value="" selected align="center">--- 선택 ---</option>
				<option value="A">A 실</option>
				<option value="B">B 실</option>
			</select>
		</div>
		<div class="form-group">
			<label for="count">참여 인원수</label>
			<select id="count" name="member_count" class="form-control">
				<option value="" selected align="center">--- 선택 ---</option>
				<option value="2">2 명</option>
				<option value="3">3 명</option>
				<option value="4">4 명</option>
				<option value="5">5 명</option>
				<option value="6">6 명</option>
			</select>
		</div>
		<div class="form-group">
			<label for="start">시작 시간</label>
			<input type="datetime-local" class="form-control" id="start" name="r_start">
		</div>
		<div class="form-group">
			<label for="end">종료 시간</label> <!-- 제한시간은 어디서 주는거죠? -->
			<input type="datetime-local" class="form-control" id="end" name="r_end" value="" >
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