<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% request.setCharacterEncoding("UTF-8");%>
 
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <link rel="stylesheet" href="/semi/INPUT/calendar/calendar1.css">
  <script src="/semi/INPUT/calendar/calendar2.js"></script>
  <script src="/semi/INPUT/calendar/calendar3.js"></script>
  <script src="/semi/INPUT/calendar/calendar4.js"></script>
  <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
  <style>
  body {position: relative;}
  #section1 {padding-top:50px;height:1200px;color: #000; background-color: #ffffff;}
  #section2 {padding-top:50px;height:1200px;color: #000; background-color: #ffffff;}
  </style>
<script>

$(document).ready(function(){
	$("#job").val("${dto.job}").attr("selected","selected");
	
	
	
})
	
	$(function(){
        $('.input-group.date').datepicker({
            calendarWeeks: true,
            todayHighlight: true,
            autoclose: true
        }); 
	})

    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = ''; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    fullAddr = data.roadAddress;

                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    fullAddr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
                if(data.userSelectedType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('sample6_address').value = fullAddr;

                // 커서를 상세주소 필드로 이동한다.
                document.getElementById('sample6_address2').focus();
            }
        }).open();
        

    }
</script>
  
<!-- 	<nav class="navbar navbar-inverse navbar-fixed-top">
 		<div class="container-fluid">
    		<div class="navbar-header">
        			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
          				<span class="icon-bar"></span>
          				<span class="icon-bar"></span>
         			 	<span class="icon-bar"></span>                        
     				</button>
      			<a class="navbar-brand" href="/semi/login/main.ls">Link_6</a>
    		</div>
    		<div>
    			 </div>
  		</div>
	</nav>
 -->	<form name="inputForm" action="/semi/ModifyUserinfoPro.ls"
		method="post">
		<div id="section1" class="container-fluid">

			<div class="container">
				<div class="row col-xs-8 col-xs-offset-2">
					<h1 class="text-center">사원 정보 수정</h1>
					<hr width="100%" color="black">
					<div class="row">
						<div class="col-xs-6 col-md-6">
							<label class="text-right">부서</label>
							<!-- 부서번호 -->
							<select class="form-control" name="deptno">
								<option value="00">선택해주세요</option>
								<option value="10">인사 팀</option>
								<option value="20">영업 팀</option>
								<option value="30">개발 팀</option>
								<option value="40">홍보 팀</option>
								<option value="50">기획 팀</option>
							</select>
							<input type ="hidden" name ="deptno1" value ="${dto.deptno}">
						</div>
						<div class="col-xs-6 col-md-6">
							<label class="text-right">사원번호</label> <input type="text" name="empno" class="form-control input-lg" readonly placeholder="${dto.empno}" />
							<input type ="hidden" name ="empno1" value ="${dto.empno}">
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-xs-6 col-md-6">
							<label class="text-right">직급</label> <select class="form-control"
								id="job" name="job">
								<option value="00">선택해주세요</option>
								<option value="인턴">인턴</option>
								<option value="사원">사원</option>
								<option value="대리">대리</option>
							</select>
							<input type ="hidden" name="job1" value="${dto.job}">
						</div>

						<div class="col-xs-6 col-md-6">
							<label class="text-right">재직상태</label> <select
								class="form-control" name="status">
								<option value="00">선택해주세요</option>
								<option value=0>입사</option>
								<option value=1>퇴사</option>
							</select>
						</div>
					</div>
					<hr width="100%">
					<div class="row">
						<div class="col-xs-6 col-md-6">
							<label class="text-right">이름</label> 
							<input type="text" name="ename" class="form-control input-lg" value="${dto.ename}"readonly />
							<input type ="hidden" name="ename1" value="${dto.ename}">
						</div>
						<div class="col-xs-6 col-md-6">
							<label class="text-right">주민등록번호</label>
							<input type="text" name="jumins" class="form-control input-lg" value="${dto.jumin1}-${dto.jumin2}" readonly />
							<input type ="hidden" name="jumins1" value="${dto.jumin1}-${dto.jumin2}">
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-xs-6 col-md-6">
							<label class="text-right">연락처</label> <input
								type="text" name="phone1" class="form-control input-lg"
								value="${dto.phone1}" />
								<input type ="hidden" name="phone2" value="${dto.phone1}">
						</div>
						<div class="col-xs-6 col-md-6">
							<label class="text-right">입사일</label>
							<div class="input-group date col-xs-12">
							<input style="border-radius: 6px; height: 46px;" type="text" name="hiredate" class="form-control col-xs-12"	value="${dto.hiredate}" />
							<input type ="hidden" name="hiredate1" value="${dto.hiredate}">
							<span class="btn" style="display: none;"></span>
							</div>
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-xs-9 col-md-9">
							<label class="text-right">E-mail</label> 
							<input type="text"	name="email" class="form-control input-lg" value="${dto.email}" />
							<input type="hidden" name ="email1" value ="${dto.email}">
						</div>
					</div>
					<br>
				</div>
				<div class="row col-xs-8 col-xs-offset-2">
					<label class="text-right">주소</label>
					<div class="row">
						<div class="col-xs-5 col-md-5">
							<input type="text" class="form-control input-lg"id="sample6_postcode" placeholder="우편번호" value="${dto.zipcode}"name="zipcode" readonly>
							<input type="hidden" name ="zipcode1" value ="${dto.zipcode}">
						</div>
						<div class="col-xs-3 col-md-3">
							<input type="button"
								class="btn btn-lg btn-primary btn-block signup-btn"
								onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
						</div>
					</div>


					<input type="text" id="sample6_address"	class="form-control input-lg" placeholder="주소" value="${dto.address}" name="address1"><br>
					<input type="hidden" name ="address2" value ="${dto.address}">
					<!-- <input type="text" id="sample6_address2"  class="form-control input-lg" placeholder="상세주소" name="address2"> -->
				</div>
			</div>
			<br>
			<hr width="100%" color="black">
			<br>
			<h2 align="center">정보를 수정하시겠습니까?</h2>
			<br>
			<div class="row">
				<div class="col-xs-4 col-md-4" align="center"></div>
				<div class="col-xs-4 col-md-4" align="center">
					<button class="btn btn-lg btn-primary btn-block signup-btn"
						type="submit">수정완료</button>
				</div>
			</div>
		</div>
	</form>