<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>아이디 찾기</title>
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
<form action="/semi/SearchId.ls" method="post" accept-charset="utf-8" name="searchedId" onsubmit="return CheckIt()">
<h2 style="color:#26a69a">아이디 찾기</h2>
				<div class="row">
				<div class="col-md-4"></div>
					<div class="col-md-4">
						<label for="id">이름</label>
						<input type="text" name="ename" class="form-control" placeholder ="사원이름">
					</div>
					<div class="col-md-4"></div>
				</div>
				
				<div class="row form-group">
				<div class="col-md-4"></div>
					<div class="col-md-4">
						<label for="password">주민등록번호</label>
						<input type="text" name="jumins" class="form-control" placeholder="123456-1234567 와 같이 표기" pattern="[0-9]{6}-[0-9]{7}" required />
					</div>
					<div class="col-md-4"></div>
				</div>     
				<div class="row form-group">
				<div class="col-md-4"></div>
					<div class="col-md-4">
						<input type="submit" class="btn btn-primary btn-block" value="아이디찾기" >
					</div>
					<div class="col-md-4"></div>
				</div>
			</form>
			
			<script>
			 function CheckIt() {
			        var userinput = eval("document.searchedId");
			        if(!searchedId.ename.value ) {
			            alert("이름를 입력하세요");
			            return false;
			        }  
			        if(!searchedId.jumins.value){
			        	alert("주민등록번호를 입력하세요");
			        	return false;
			        }
			    }
		
				
			 
			</script>
</body>
</html>