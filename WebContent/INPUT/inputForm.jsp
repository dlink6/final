<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="/semi/INPUT/calendar/calendar1.css">
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="/semi/login/js/bootstrap.js"></script>
<script src="/semi/INPUT/calendar/calendar2.js"></script>
<script src="/semi/INPUT/calendar/calendar3.js"></script>
<script src="/semi/INPUT/calendar/calendar4.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<style>
body {
	position: relative;
}

#section1 {
	padding-top: 50px;
	height: 1200px;
	color: #000;
	background-color: #ffffff;
}

#section2 {
	padding-top: 50px;
	height: 1200px;
	color: #000;
	background-color: #ffffff;
}
</style>
<script type="text/javascript">
window.onload = function() {
	empSearchFunction("");
}
</script>
<script type="text/javascript">
	$(function() {
		$(document).on("click", ".page-link", function() {
			empSearchFunction($(this).attr("id"));
		});
	});

	var EmpsearchRequest = new XMLHttpRequest();
	function empSearchFunction(pageNum) {
		EmpsearchRequest.open("Post", "./EmpSearchSevlet?userName="
				+ encodeURIComponent(document.getElementById("search").value)
				+ "&pageNum=" + pageNum, true);
		EmpsearchRequest.onreadystatechange = empSearchProcess;
		EmpsearchRequest.send(null);
	}
	function empSearchProcess() {
		var table = document.getElementById("empTable");
		var pagination = document.getElementById("pagination");
		table.innerHTML = "";
		if (EmpsearchRequest.readyState == 4 && EmpsearchRequest.status == 200) {
			var object = eval('(' + EmpsearchRequest.responseText + ')');
			var result = object.result;
			var count = object.count;
			if(count == 0){
			pagination.innerHTML = "";
				return false;
			}
			for (var i = 0; i < result.length; i++) {
				var row = table.insertRow(0);
				for (var j = 0; j < result[i].length; j++) {
					var cell = row.insertCell(j);
					if(j==1){
						cell.innerHTML ="<a href =/semi/ModifyUserinfoForm.ls?empno="+result[i][j-1].value+">"+result[i][j].value+"</a>"
					}else{ 
					cell.innerHTML = result[i][j].value;
					}
				}
			}
			
			var endPage = object.endPage;
			var startPage = object.startPage;
			var pageBlock = object.pageBlock;
			var pageCount = object.pageCount;
			var endRow = object.endRow;
			var startRow = object.startRow;
			var pageSize = object.pageSize;
			var nextPage = object.nextPage;
			var previous = object.previous;
			var currentPage = object.currentPage;
			var pageNum = object.pageNum;
			pagination.innerHTML = "";
		
				if (previous != 0) {
					$('#pagination')
							.append(
									'<li class ="page-item"><a class ="page-link" id ='+previous+'>이전</a></li>');
				}
				for (var i = startPage; i <= endPage; i++) {
					if (i == currentPage) {
						$('#pagination').append(
								'<li class ="page-item active"><a class ="page-link" id ='+i+'>'
										+ i + '</a></li>');
					} else {
						$('#pagination').append(
								'<li class ="page-item"><a class ="page-link" id ='+i+'>'
										+ i + '</a></li>');  
					}
				}
				if (nextPage != 0) {
					$('#pagination')
							.append(
									'<li class ="page-item"><a class ="page-link" id ='+nextPage+'>다음</a></li>');
			}
		}
	}
</script>
<script>
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

 
 var initPosition = 0;
 function scroll_floating() {
 	var target_top = $(document).scrollTop() - initPosition;
	if(target_top < 0) target_top = 0;
 		$('#floating').stop().animate({
		 top: target_top
 		}, 700);
 }
 $(window).load(function() {
 initPosition = $('#floating').offset().top;
 scroll_floating();
 }).scroll(function () {
 scroll_floating();
 });

</script>

<div class="container floating" style="position:relavent">
	<div id='floating' style='position:absolute;top:0;left:0;'>
		<ol class="breadcrumb">
			<li id="#section1"><a href="#section1">사원등록</a></li>
			<li id="#section2"><a href="#section2">사원관리</a></li>
		</ol>
	</div>
</div>

<form name="inputForm" action="/semi/inputPro.ls" method="post">
	<div id="section1" class="container-fluid">
	<div class="container">
		<div class="row col-xs-8 col-xs-offset-2">	
  			<h1 class="text-center">사원등록</h1>
 			<hr width="100%" color="black">
  		<div style="height:30px;"></div>
  				<label class="text-right">부서 *</label><!-- 부서번호 -->
  	  	 			<select class="form-control" name="deptno">
  	  	 				<option value="00">선택해주세요</option>
  	  	 				<option value="10">인사 팀</option>
  	  	 				<option value="20">영업 팀</option>
  	  	 				<option value="30">개발 팀</option>
  	  	 				<option value="40">홍보 팀</option>
  	  	 				<option value="50">기획 팀</option>
  	  	 			</select>
  	  <br>
  	  
  	  	 			<br>
  		<div class="row">
  			<div class="col-xs-6 col-md-6">
  				<label class="text-right">직급 *</label>
  	  	 			<select class="form-control" name="job">
  	  	 				<option value="00">선택해주세요</option>
  	  	 				<option value="인턴">인턴</option>
  	  	 				<option value="사원">사원</option>
  	  	 				<option value="대리">대리</option>
  	  	 			</select>
  	  	 	</div>
  	  	 	
  			<div class="col-xs-6 col-md-6">
  				<label class="text-right">재직상태 *</label>
  	  	 			<select class="form-control" name="status">
  	  	 				<option value="00">선택해주세요</option>
  	  	 				<option value=0>입사</option>
  	  	 				<option value=1>퇴사</option>
  	  	 			</select>
  	  	 	</div>
     	</div>
  <hr width="100%">
  		<div class="row">
    		<div class="col-xs-6 col-md-6">
   				<label class="text-right">이름 *</label>
     				 <input type="text" name="ename" class="form-control input-lg" placeholder="사원의 이름을 입력해주세요." />
    		</div>
   			<div class="col-xs-6 col-md-6">
    			<label class="text-right">주민등록번호 *</label>
      				 <input type="text" name="jumins" class="form-control input-lg" />
    		</div>
  		</div>
  <br>
		<div class="row">
			<div class="col-xs-6 col-md-6">
				<label class="text-right">연락처 *</label>
					<input type="text" name="phone1" class="form-control input-lg" placeholder="필수 기재사항" />
			</div>
			<div class="col-xs-6 col-md-6">
				<label class="text-right">입사일</label>
				<div class="input-group date col-xs-12" ><input style="border-radius:6px; height:46px;" type="text" name="hiredate" class="form-control col-xs-12" /><span class="btn" style="display:none;"></span></div>
			</div>
		</div>
  <br>
  		<div class="row">
  			<div class="col-xs-9 col-md-9">
  				<label class="text-right">E-mail</label>
  				<input type="text" name="email" class="form-control input-lg" />
  			</div>
  		</div>
  <br>		
  	</div>
  		<div class="row col-xs-8 col-xs-offset-2">  
			<label class="text-right">주소 *</label>  
   			<div class="row">
   				<div class="col-xs-5 col-md-5">
      				<input type="text"  class="form-control input-lg" id="sample6_postcode" placeholder="우편번호" name="zipcode" readonly></div>
      				<div class="col-xs-3 col-md-3">
					<input type="button" class="btn btn-lg btn-primary btn-block signup-btn" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br></div></div>
					
										
					<input type="text"  id="sample6_address"  class="form-control input-lg" placeholder="주소" name="address1" readonly><br>
					<input type="text" id="sample6_address2"  class="form-control input-lg" placeholder="상세주소" name="address2">
    		 	</div>
			</div>
     <br>
     	<hr width="100%" color="black">
     <br>     
    	<h2 align="center">위의 내용으로 사원의 정보가 저장됩니다.</h2>
     <br>
			<div class="row">
  				<div class="col-xs-4 col-md-4" align="center"></div>
  				<div class="col-xs-4 col-md-4" align="center">
  					<button class="btn btn-lg btn-primary btn-block signup-btn" type="submit" onClick="return chackIt()">저장</button>
 				</div>
 			</div>
		</div><!-- 세션1끝 -->
</form>


	<div style="height: 150px;"></div>


	<div class="container">
		<!-- 컨테이너 -->
		<div id="section2" class="container-fluid" style="width:1200px">
			<!-- 세션2 정보검색-->
			<h1 align="center">사원 정보 검색</h1>
			<hr width="80%">
			<div class="row" style="width: 1200px">
				<div class="col-lg-4 col-lg-offset-4">
					<input type="search" id="search" value="" class="form-control"
						placeholder="찾으시는 사원의 정보를 입력하세요" onkeyup="empSearchFunction()">
				</div>
				<br> <br> <br>
			</div>
			<div class="col-lg-12">
				<table class="table board" id="table">
					<thead>
						<tr>
							<th style="background-color: #fafafa; text-align: center;" class="col-xs-1">사원번호</th>
							<th style="background-color: #fafafa; text-align: center;" class="col-xs-1">사원이름</th>
							<th style="background-color: #fafafa; text-align: center;" class="col-xs-1">연락처1</th>
							<th style="background-color: #fafafa; text-align: center;" class="col-xs-2">주소</th>
							<th style="background-color: #fafafa; text-align: center;" class="col-xs-1">직책</th>
							<th style="background-color: #fafafa; text-align: center;" class="col-xs-1">이메일</th>
							<th style="background-color: #fafafa; text-align: center;" class="col-xs-2">입사일</th>
							<th style="background-color: #fafafa; text-align: center;" class="col-xs-1">부서번호</th>
							<th style="background-color: #fafafa; text-align: center;" class="col-xs-2">현재프로젝트번호</th>
						</tr>
					</thead>
					<tbody id="empTable" style="text-align:center;">
					</tbody>
				</table>
				<hr>
				<nav aria-label="Page navigation example" class="text-center">
					<ul class="pagination" id="pagination">
					</ul>
				</nav>
			</div>
		</div>
	</div>
	<script>
$(document).ready(function(){

    $("select option[value='기본값']").attr("selected", true);
});

$(document).ready(function(){
  // Add scrollspy to <body>
  $('body').scrollspy({target: ".navbar", offset: 50});   

  // Add smooth scrolling on all links inside the navbar
  $("#myNavbar a").on('click', function(event) {
    // Make sure this.hash has a value before overriding default behavior
    if (this.hash !== "") {
      // Prevent default anchor click behavior
      event.preventDefault();

      // Store hash
      var hash = this.hash;

      // Using jQuery's animate() method to add smooth page scroll
      // The optional number (800) specifies the number of milliseconds it takes to scroll to the specified area
      $('html, body').animate({
        scrollTop: $(hash).offset().top
      }, 800, function(){
   
        // Add hash (#) to URL when done scrolling (default click behavior)
        window.location.hash = hash;
      });
    }  // End if
  });
});
	function zipCheck(){ 
		
		url="ZipCheck.jsp?check=y";
    	
    	window.open(url,"post","toolbar=no ,width=520 ,height=300,directories=no,status=yes,scrollbars=yes,menubar=no");
    }
 
 function chackIt(){
	
	 var inputForm = eval("document.inputForm");
	 
	 if(inputForm.deptno.value=="00") {
         alert("부서를 선택해주세요");
         return false;
     }
	 if(inputForm.job.value=="00") {
         alert("직급을 선택해주세요");
         return false;
     }
	 if(inputForm.status.value=="00") {
         alert("재직상태를 선택해주세요");
         return false;
	 }
	 return true;
 }
</script>
