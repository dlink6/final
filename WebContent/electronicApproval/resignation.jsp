<%@ page contentType="text/html; charset=UTF-8"%>
<link href="/semi/boot/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="/semi/electronicApproval/calendar/calendar1.css" rel="stylesheet">
<script src="/semi/electronicApproval/calendar/calendar2.js"></script>
<script src="/semi/electronicApproval/calendar/calendar3.js"></script>
<script src="/semi/electronicApproval/calendar/calendar4.js"></script>   -->
<link href="/semi/electronicApproval/css/resignation.css" rel="stylesheet">
<script>
$(document).ready(function() {
	  var pathNull = document.location.href;
	  pathNull = pathNull.substring(pathNull.lastIndexOf("/"));
	   if (pathNull.indexOf("resignation.ls") != -1) {
	    $( "#결재요청" ).addClass( "active" );
	  }
	});



$(function(){
		$("#insert").click(function(){
			$("#detail").before("<tr class='row'><td class='col-xs-1'><input class='col-xs-12' ></td><td class='col-xs-2'><input class='col-xs-12'> </td><td class='col-xs-2'><input class='col-xs-12'></td><td class='col-xs-1'><input class='col-xs-12'></td><td class='col-xs-3'><input class='col-xs-12'></td></tr>");
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
		if($("#enddate").val() ==""){
			alert("희망사직일을 입력해주세요")
			return false;
		}
		if($("#r_cause").val() ==""){
			alert("사직하는 사유를 입력해주세요")
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
	<div class="row col-xs-12" style=" padding-bottom:30px;"  >
		<h2 class="col-xs-12 text-center"><span class="glyphicon glyphicon-chevron-left"></span>사직서<span class="glyphicon glyphicon-chevron-right"></span></h2>
		<div class="col-xs-12" style="height:20px"></div>
		<form action="/semi/resignationPro.ls" method="post" onsubmit="return check();">
		<table class="table col-xs-offset-1 resignation">
			<tr class="row">
				<th class="	col-xs-2 active">부 서</th>
				<td class="col-xs-3"><input type="text" class="col-xs-12" name="dname"  readonly="readonly" value="${memId.dname}"></td>
				<th class="	col-xs-2 active">직 위</th>
				<td class="col-xs-3"><input type="text" class="col-xs-12"  readonly="readonly" value="${memId.job}"></td>
			</tr>
			<tr class="row"> 
				<th class="active">성 명</th>
				<td colspan="3" ><input class="col-xs-12" name="ename" readonly="readonly" value="${memId.ename}"></td>
			</tr>
			<tr class="row"> 
				<th class="active">주 소</th>
				<td colspan="3"><input class="col-xs-12"  readonly="readonly" value="${memId.address}"></td>
			</tr>
			<tr class="row"> 	
				<th class="active">주민등록번호</th>
				<td colspan="3" ><input class="col-xs-12"  readonly="readonly" value="${memId.jumin1} - ${memId.jumin2}"></td>
			</tr>
			<tr class="row"> 
				<th class="active">입사년월일</th>
				<td colspan="3" ><input class="col-xs-12" readonly="readonly" value="${memId.hiredate}"></td>
			</tr>
			<tr class="row"> 
				<th class="active">희망사직일</th>
				<td colspan="3" ><div class="input-group date col-xs-12"><input  type="text" class="col-xs-12 form-control" name="enddate" id="enddate" style="padding-left:15px;color:black;font-size:16px;"><span class="btn" style="display:none;"></span></div></td>
			</tr>
			<tr class="row">
				<td colspan="4"><textarea class="col-xs-12" style="height:200px; resize:none;" name="r_cause" id="r_cause"></textarea></td>
			</tr>
			
		</table>
			<input class="col-xs-4 col-xs-offset-4" type="submit" class="btn" value="제출하기" style="height:50px;">
		</form>
	</div>
	
</div>
</div>
