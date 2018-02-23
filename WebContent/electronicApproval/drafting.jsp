<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="/semi/electronicApproval/css/drafting.css" rel="stylesheet">
<link href="/semi/electronicApproval/calendar/calendar1.css" rel="stylesheet">
<script src="/semi/electronicApproval/calendar/calendar2.js"></script>
<script src="/semi/electronicApproval/calendar/calendar3.js"></script>
<script src="/semi/electronicApproval/calendar/calendar4.js"></script>  
<script>

//상단 네비바
$(document).ready(function() {
  var pathNull = document.location.href;
  pathNull = pathNull.substring(pathNull.lastIndexOf("/"));
   if (pathNull.indexOf("drafting.ls") != -1) {
    $( "#결재요청" ).addClass( "active" );
  }
});


	$(function(){
		$(":radio").change(function(){
			var result = $(this).next().attr("for");
			
			$("#check1").attr("style","margin-top:5px; position:absolute; display:none;")
			$("#check2").attr("style","margin-top:5px; position:absolute; display:none;")
			$("#check3").attr("style","margin-top:5px; position:absolute; display:none;")
				
			if( result == "a" ){
				$("#check1").attr("style","margin-top:5px; position:absolute;")
			}
			if(result =="b"){
				$("#check2").attr("style","margin-top:5px; position:absolute;")
			}
			if(result == "c"){
				$("#check3").attr("style","margin-top:5px; position:absolute;")
			}
		})
		$("#list").click(function(){
			$(this).next().toggleClass("listShow")
		})
		$('.input-group.date').datepicker({
		    calendarWeeks: true,	
		    todayHighlight: true,
		    autoclose: true
		});
		
		
	})
		
		function check(){
			if($(":radio").val()==""){
				alert("기안서종류를 선택해주세요")
				return false;
			}
			if($("#d_date").val() ==""){
				alert("기안일을 입력해주세요")
				return false;
			}
			if($("#d_subject").val() ==""){
				alert("기안서제목을 입력해주세요")
				return false;
			}
			if($("d_content").val() ==""){
				alert("내용을 입력해주세요")
				return false;
			}
			
		}
	
	
</script> 


<div class="container" style="margin-top:10px; position:relative;" >
	<div class="row col-xs-12" > 
	<div class="container">
<!-- 		<div class="row">
			<div ><h1 class="col-xs-12" style="font-size:50px;">결재요청</h1></div>
		</div>
		<div class="row">
			<div class="col-xs-12" id="approvalTitleUnderline" style="border:1px solid black;width:350px;height:5px"></div>
		</div>
 -->	</div> 
	
	<div style="height:30px;"></div>
	<div class="row col-xs-12">
		<ul class="nav nav-pills board">
 		<li role="presentation"><a href="/semi/List.ls">결재함</a></li>
  		<li role="presentation" class="dropdown" id="결재요청">
  			<a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false" >
     		 결재요청<span class="caret"></span>
   			</a>
   			 <ul class="dropdown-menu" style="width:20px;">
      			<li><a href="/semi/requestForVacation.ls">휴가신청서</a></li>
      			<li><a href="/semi/drafting.ls">기안서</a></li>
      			<li><a href="/semi/spendingResolution.ls">지출결의서</a></li>
      			<li><a href="/semi/resignation.ls">사직서</a></li>
   			 </ul>
	</ul>
	</div>
	
	<div class="row col-xs-12"  style="padding-bottom:30px; ">
		<h2 class="col-xs-12 text-center"><span class="glyphicon glyphicon-chevron-left"></span>기안서<span class="glyphicon glyphicon-chevron-right"></span></h2>
		<div class="col-xs-12" style="height:20px"></div>
		<form action="/semi/draftingPro.ls" method="post" onsubmit="return check();">
		<table class="table col-xs-offset-1 drafting">
			<tr class="row">
				<th class="	col-xs-2 active" >부서</th>
				<td class="col-xs-4" colspan="2"><input type="text" class="col-xs-12" name="dname" readonly="readonly" value="${memId.dname}"></td>
				<th class="	col-xs-4 active" colspan="2" rowspan="2" style="line-height:30px;">구  분<span class=col-xs-12>(선택해주세요)</span></th>
			</tr>
			<tr class="row"> 
				<th class="active">성 명</th>
				<td colspan="2"><input type="text" class="col-xs-12" name="ename" readonly="readonly" value="${memId.ename}"></td>
			
			</tr>
			<tr class="row">
				<th class="active">기 안 일</th>
				<td colspan="2"><div class="input-group date col-xs-12"><input type="text" class="col-xs-12 from-control" name="d_date" id="d_date"style="padding-left:15px;color:black;font-size:16px;"><span class="btn" style="display:none;"></span></div></td>
					<td rowspan="2">
				<input type="radio" name="d_category" value="기획" id="a" style="display:none;">
				<label for="a" class="col-xs-4 btn" style="padding:10px;"><span class="glyphicon glyphicon-ok" id="check1" style="margin-top:5px; position:absolute; display:none;" ></span>ㅁ기획</label>
				<input type="radio" name="d_category" value="제안" id="b" style="display:none;">
				<label for="b" class="col-xs-4 btn" style="padding:10px;"><span class="glyphicon glyphicon-ok" id="check2" style="margin-top:5px; position:absolute; display:none;" ></span>ㅁ제안</label>
				<input type="radio" name="d_category" value="기타" id="c" style="display:none;">
				<label for="c" class="col-xs-4 btn" style="padding:10px;"><span class="glyphicon glyphicon-ok" id="check3" style="margin-top:5px; position:absolute; display:none;" ></span>ㅁ기타</label>
				</td>
			</tr>
			<tr class="row">
				<th class=" active">제 목</th>
				<td colspan="4"><input type="text" class="col-xs-12" name="d_subject" id="d_subject"></td>
			</tr>
			<tr class="row">
				<td colspan="5"><textarea class="col-xs-12" style="height:400px; resize:none;" name="d_content"  id="d_content">
				1.목적

2.일정


3.기간


4.참여인원


5.예산
				</textarea></td>
			</tr>
		</table> 
		<input class="col-xs-4 col-xs-offset-4" type="submit" class="btn" value="제출하기" style="height:50px;">
		</form>
	</div>
</div>


</div>