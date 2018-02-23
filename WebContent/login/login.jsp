<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>환영합니다</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Free HTML5 Website Template by GetTemplates.co" />
<meta name="keywords"
	content="free website templates, free html5, free template, free bootstrap, free website template, html5, css3, mobile first, responsive" />
<meta name="author" content="GetTemplates.co" />
<link href="https://fonts.googleapis.com/css?family=Lato:300,400,700"
	rel="stylesheet">
<link rel="stylesheet" href="/semi/login/css/animate.css">
<link rel="stylesheet" href="/semi/login/css/bootstrap.css">
<link rel="stylesheet"
	href="/semi/login/css/bootstrap-datepicker.min.css">
<link rel="stylesheet" href="/semi/login/css/style.css">
<!-- song -->
<link rel="stylesheet" href="/semi/login/css/mask.css">
<!-- song -->
<link rel="stylesheet" href="/semi/login/css/searchid1.css">
<!-- song -->
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script src="/semi/login/js/modernizr-2.6.2.min.js"></script>

<script>
	$(function() {
		$("#id").blur(function() {
			var id = $(this).val();
			if ($("#id").val() != "") {
				if (!($.isNumeric(id))) {
					alert("아이디에 숫자만입력해주세요")
					$("#id").val("");
					$("#id").focus()
					return false;
				}
			}
		})
	})
</script>
</head>
<body>

	<div class="gtco-loader"></div>
	<!--로딩중 동그라미 표시 되는태그 -->

	<div id="page">


		<header id="gtco-header" class="gtco-cover gtco-cover-md"
			role="banner" style="background-image: url(/semi/login/images/img_bg_2.jpg)">
			<div class="overlay"></div>
			<div class="gtco-container">
				<div class="row">
					<div class="col-md-12 col-md-offset-0 text-left">
						<div class="row row-mt-15em">
							<div class="col-md-7 mt-text animate-box"
								data-animate-effect="fadeInUp">
								<h1>
									로그인 페이지 입니다<BR> 환영합니다
								</h1>
							</div>
							<div class="col-md-4 col-md-push-1 animate-box"
								data-animate-effect="fadeInRight">
								<div class="form-wrap">
									<div class="tab">


										<div class="tab-content">
											<div class="tab-content-inner active" data-content="signup">
												<h3>로그인</h3>
												<!--여기 폼태그가 졵재한다   -->
												<form name="Log" action="/semi/Login.ls" method="post"
													onSubmit="return Check()">
													<div class="row form-group">
														<div class="col-md-12">
															<label for="id">사원번호 또는 휴대폰번호</label> <input type="text"
																name="id" id="id" class="form-control" autofocus>
														</div>

													</div>
													<div class="row form-group">
														<div class="col-md-12">
															<label for="passwd">비밀번호</label> <input type="password"
																name="passwd" class="form-control">
														</div>
													</div>
													<div class="row form-group">
														<div class="col-md-12">
															<input type="submit" class="btn btn-primary btn-block"
																value="로그인">
														</div>
														<div class="col-md-offset-1 col-md-4 btn" onClick="return openId()">아이디찾기</div>
														<div class="col-md-5 btn" onClick="return openPw()">비밀번호찾기</div>
													</div>
												</form>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</header>
	</div>

	<div class="gototop js-top">
		<!-- 아이디 비밀번호 찾는 페이지 중 아이디 찾는 페이지 알럿창으로 보내주기로 -->
		<a href="#" class="js-gotop"><i class="icon-arrow-up"></i></a>
	</div>
	<!-- 위로 올라가는 버튼 -->

	<script src="/semi/login/js/jquery.min.js"></script>
	<script src="/semi/login/js/jquery.easing.1.3.js"></script>
	<script src="/semi/login/js/bootstrap.min.js"></script>
	<script src="/semi/login/js/jquery.waypoints.min.js"></script>
	<script src="/semi/login/js/owl.carousel.min.js"></script>
	<script src="/semi/login/js/jquery.countTo.js"></script>
	<script src="/semi/login/js/jquery.stellar.min.js"></script>
	<script src="/semi/login/js/jquery.magnific-popup.min.js"></script>
	<script src="/semi/login/js/magnific-popup-options.js"></script>
	<script src="/semi/login/js/bootstrap-datepicker.min.js"></script>
	<script src="/semi/login/js/main.js"></script>

	<script>
		function Check() {

			var login = eval("document.Log");

			if (!document.Log.id.value) {
				alert("아이디를 입력하지 않으셨습니다.");
				document.Log.id.focus();
				return false;
			}
			if (!document.Log.passwd.value) {
				alert("비밀번호가 입력되지 않았습니다");
				document.Log.passwd.focus();
				return false;
			}
			return true;

		}
	</script>

	<script>
		function openId() {
			window.open(
							"/semi/openID.ls",
							"아이디찾기",
							"width=600,height=300 ,toolbar=no, menubar=no,scrollbar=no,resizable=yes,x=300px");
		}
	</script>

	<script>
		function openPw() {
			window.open(
							"/semi/openPW.ls",
							"비밀번호찾기",
							"width=600,height=300 ,toolbar=no, menubar=no,scrollbar=no,resizable=yes,x=300px");
		}
	</script>
</body>
</html>

