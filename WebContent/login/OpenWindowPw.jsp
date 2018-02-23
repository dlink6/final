<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비밀번호 찾기</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>환영합니다</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Free HTML5 Website Template by GetTemplates.co" />
	<meta name="keywords" content="free website templates, free html5, free template, free bootstrap, free website template, html5, css3, mobile first, responsive" />
	<meta name="author" content="GetTemplates.co" />
	<link href="https://fonts.googleapis.com/css?family=Lato:300,400,700" rel="stylesheet">
	<link rel="stylesheet" href="/semi/login/css/animate.css">
	<link rel="stylesheet" href="/semi/login/css/bootstrap.css">
	<link rel="stylesheet" href="/semi/login/css/bootstrap-datepicker.min.css">
	<link rel="stylesheet" href="/semi/login/css/style.css"><!-- song -->
	<link rel="stylesheet" href="/semi/login/css/mask.css"><!-- song -->
	<link rel="stylesheet" href="/semi/login/css/searchid1.css"><!-- song -->
	<script src="https://code.jquery.com/jquery-latest.js"></script> 
	<script src="/semi/login/js/modernizr-2.6.2.min.js"></script>
		
</head>
<body>
<% request.setCharacterEncoding("utf-8");%>
<h2 style="color:#26a69a">비밀번호 찾기</h2>
<form action="/semi/SearchPw.ls" method="post" name="passwdsearch" onsubmit="return CheckIt()">
				<div class="row">
					<div class="col-md-4"></div>
					<div class="col-md-4">
						<label for="id">사원번호 및 핸드폰번호</label>
						<input type="text" name="empno" class="form-control" maxlength="11" placeholder = "01012345678"/>
					</div>
					<div class="col-md-4"></div>
				</div>
				<div class="row form-group">
				<div class="col-md-4"></div>
					<div class="col-md-4">
						<label for="passwd">주민등록번호</label>
						<input type="text" name="jumins" class="form-control" placeholder="123456-1234567 와 같이 표기" pattern="[0-9]{6}-[0-9]{7}" required>
					</div>
					<div class="col-md-4"></div>
				</div>
				<div class="row form-group">
				<div class="col-md-4"></div>
					<div class="col-md-4">
						<input type="submit" class="btn btn-primary btn-block" value="비밀번호 찾기" >
					</div>
					<div class="col-md-4"></div>
				</div>
			</form>
			<script>
			function CheckIt(){ 
				if(!passwdsearch.empno.value) {
		         alert("사원번호 및 핸드폰번호를 입력해주세요");
		         return false;
		     }
				if(!passwdsearch.jumins.value) {
			         alert("주민등록번호를 입력해주세요");
			         return false;
			     }
				if(!($.isNumeric(passwdsearch.empno.value))){
					alert("사원번호나 핸드폰번호를 입력해주세요.");
					passwdsearch.empno.value="";
					return false;
				}
				
				
		}
			</script>
</body>
</html>