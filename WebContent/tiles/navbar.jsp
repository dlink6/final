<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="/semi/tiles/css/navbar.css" rel="stylesheet" type="text/css">
<script>
	$(function(){
		$("#bs-example-navbar-collapse-1 > ul:nth-child(1) > li> a").click(function(){
			
		})
	})
	
var sel_file;

$(function(){
	$("#input_img").on("change",handleImgFileSelect);
});

function handleImgFileSelect(e){
	var files = e.target.files;
	var filesArr = Array.prototype.slice.call(files);
	
	filesArr.forEach(function(f){
		if(!f.type.match("image.*")){
			alert("확장자는 이미지 확장자만 가능합니다.");
			return ;
		}
		sel_file = f;
		
		var reader = new FileReader();
		reader.onload = function(e){
			$("#img").attr("src",e.target.result);
		}
		reader.readAsDataURL(f);
	});
}
	
</script>
<nav class="navbar navbar-default navbar-inverse">
	<div class="container">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      	<a class="navbar-brand" href="/semi/Main.ls" style="line-height:1; font-size:26px; font-family:Godo; font-weight:700; color:#A4A4A4;">Link_6</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">게시판<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="/semi/list.ls?board_cat=공지사항">공지사항</a></li>
            <li><a href="/semi/list.ls?board_cat=부서별소식">부서별소식</a></li>
            <li><a href="/semi/list.ls?board_cat=자유게시판">자유게시판</a></li>
          </ul>
        </li>
         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">업무관리<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="/semi/projectList.ls">프로젝트 현황</a></li>
            <li><a href="/semi/pbList.ls">프로젝트 자료실</a></li>
          </ul>
        </li>
         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">일정/회의실<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="/semi/schedulePage.ls">일정 조회/등록</a></li>
            <li><a href="/semi/reservationPage.ls">회의실 예약/현황</a></li>
          </ul>
        </li>
         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">전자결재<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="/semi/List.ls">결재함</a></li>
            <li><a href="/semi/requestForVacation.ls">결재요청</a></li>
          </ul>
        </li>
        <li class="dropdown">
          <a href="/semi/photoAlbum.ls" >사진첩</a>
        </li>
        <c:if test="${memId.getDeptno() == '10' or memId.getEmpno()=='1000'}">
        	<li>
         		<a href="/semi/inputForm.ls">인사관리</a>
        	</li>
        </c:if>
      </ul>
       
      
      
      <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="font-family:Godo; font-size:1.3em; height:70px; line-height:1.5;"><span class="glyphicon glyphicon-user"></span>&nbsp;내정보 <span class="caret"></span></a>
			<ul id="login-dp" class="dropdown-menu">
				<li class="container board" style="width :280px; height:128px;">
					<div class=row>
						<div class="col-sm-4" style="margin-top:15px;">					
							<img style="border-radius: 50%;" width=70px height=70px src="${memId.profilePath}"/>
						</div>
						<div class="col-sm-8" style="margin-top:10px;">
							<div class="row" style="margin-top:10px; margin-left:auto;">
								<b> ${memId.ename}</b>님 안녕하세요!
							</div>
							<div class="row" style="margin-left:auto; margin-top:10px; margin-bottom:10px;">
								<a href="/semi/projectList.ls" class="btn btn-warning btn-sm">업무관리</a>
								<a href="/semi/schedulePage.ls" class="btn btn-danger btn-sm">일정관리</a>
           					</div>
						</div>
					</div>
					
           			<div class="row" style="margin-top:15px; margin-bottom:0;">
						<div class="btn-group btn-group-justified" role="group" aria-label="...">
 							<div class="btn-group" role="group">
    							<a href="/semi/UserModifyForm.ls" class="btn btn-default btn-sm">정보수정</a>
 							</div>
 							<div class="btn-group" role="group">
    							<button type="button" class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal">사진변경</button>
 							</div>
  							<div class="btn-group" role="group">
   								<a href="/semi/logout.ls" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-off"></span> 로그아웃</a>
 							</div>
						</div>
					</div>
				</li>
			</ul>
        </li>
      </ul>
      
      <!-- 프로필사진 변경 모달창 -->
      <form action="/semi/profileForm.ls" method ="post" enctype="multipart/form-data" name ="fileForm">
      <div class="modal fade" id="myModal" role="dialog">
      <div class="modal-dialog modal-sm">
      <div class="modal-content board">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">프로필 사진 변경</h4>
        </div>	
      	<div class="modal-body">
      		<div>
				<h3><b>이미지 미리보기</b></h3>
				<div class ="img_wrap">
					<img id ="img" class="img-responsive"/>
				</div>
				<p class ="title">최대 업로드 파일 크기 5mb</p>
				<input type ="file"id="input_img"name ="uploadFile"/>
			</div>
			
        </div>
        <div class="modal-footer">
        	<input type = "submit" class ="btn btn-warning" value ="확인">
       		<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
        </div>
      </div>
      </div>
 	  </div>
 	  </form>
 	  
 	 </div>
    </div>
  </nav>
<div class="col-xs-12">
<!-- <img src="/semi/login/images/13.png" class="img-responsive">
 --></div>