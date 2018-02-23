<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
  
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
</script>

<form name="modifyForm" action="/semi/ModifyPro.ls" method="post">
	<div class="container">
		<div class="row col-xs-8 col-xs-offset-2">	
  			<h2 class="text-center">내 정보 수정</h2>
 			<hr width="100%" color="black">
  		<div style="height:30px;"></div>
  				
  		<div class="row">
    		<div class="col-xs-6 col-md-6">
   				<label class="text-right">이름</label>
     				 <input type="text" name="ename" class="form-control input-lg" placeholder="${memId.ename}" readonly/>
     				 
    		</div>
   			<div class="col-xs-6 col-md-6">
    			<label class="text-right">주민등록번호</label>
      				 <input type="text" name="jumins" class="form-control input-lg" placeholder="${memId.jumin1}-${memId.jumin2}"readonly/>
    		</div>
  		</div>
  <br>
		<div class="row">
			<div class="col-xs-6 col-md-6">
				<label class="text-right">연락처</label>
					<input type="text" name="phone" class="form-control input-lg" placeholder="${memId.phone1}" />
					<input type ="hidden" name ="phone1" value="${memId.phone1}"> 
			</div>
			<div class="col-xs-6 col-md-6">
				<label class="text-right">입사일</label>
					<input type="text" name="hiredate" class="form-control input-lg" placeholder="${memId.hiredate}" readonly/>
			</div>
		</div>
  <br>
  <div class="row">
			<div class="col-xs-6 col-md-6">
				<label class="text-right">사원번호</label>
					<input type="text" name="empno2" class="form-control input-lg" placeholder="${memId.empno}" readonly/>
					<input type ="hidden" name ="empno" value ="${memId.empno}">
			</div>
			<div class="col-xs-6 col-md-6">
    			<label class="text-right">비밀번호</label>
      				 <input type="text" name="passwd" class="form-control input-lg" />
      				 <input type ="hidden" name ="passwd1" value="${memId.passwd}">
      				 
    		</div>
		</div>
  <br>
  	
  		<div class="row">
  			<div class="col-xs-9 col-md-9">
  				<label class="text-right">E-mail</label>
  				<input type="text" name="email" class="form-control input-lg" value="${memId.email}" />
  				<input type ="hidden" name ="email1" value ="${memId.email}">
  			</div>
  		</div>
  <br>		
  	</div>
  		<div class="row col-xs-8 col-xs-offset-2">  
			<label class="text-right">주소 *</label>  
   			<div class="row">
   				<div class="col-xs-5 col-md-5">
      				<input type="text"  class="form-control input-lg" id="sample6_postcode" value="${memId.zipcode}" name="zipcode" readonly></div>
      				<div class="col-xs-3 col-md-3">
					<input type="button" class="btn btn-lg btn-primary btn-block signup-btn" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br></div></div>
					<input type="text"  id="sample6_address"  class="form-control input-lg" placeholder="주소" name="address1"  value="${memId.address}" readonly><br>
					<input type="text" id="sample6_address2"  class="form-control input-lg" placeholder="상세주소" name="address2">
					<input type ="hidden" name="address" value ="${memId.address}">
					
    		 	</div>
			</div>
     <br>
     	<hr width="60%" color="black">
     <br>     

			<div class="row">
  				<div class="col-xs-4 col-md-4" align="center"></div>
  				<div class="col-xs-4 col-md-4" align="center">
  					<button class="btn btn-lg btn-primary btn-block signup-btn" type="submit">저장</button>
  					
 				</div>
 			</div>
</form>

<div style="height:150px;"></div>
 		
