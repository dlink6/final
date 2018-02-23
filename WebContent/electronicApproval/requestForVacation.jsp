<%@ page contentType="text/html; charset=UTF-8"%>
<link href="/semi/electronicApproval/css/requestForVacation.css" rel="stylesheet">
<link href="/semi/electronicApproval/calendar/calendar1.css" rel="stylesheet">
<script src="/semi/electronicApproval/calendar/calendar2.js"></script>
<script src="/semi/electronicApproval/calendar/calendar3.js"></script>
<script src="/semi/electronicApproval/calendar/calendar4.js"></script>  
<script>

//상단 네비바
$(document).ready(function() {
  var pathNull = document.location.href;
  pathNull = pathNull.substring(pathNull.lastIndexOf("/"));
   if (pathNull.indexOf("requestForVacation.ls") != -1) {
    $( "#결재요청" ).addClass( "active" );
  }
});


	$(function(){
		$("#list").click(function(){
			$(this).next().toggleClass("listShow")
		})
		$('.input-group.date').datepicker({
		    calendarWeeks: true,
		    todayHighlight: true,
		    autoclose: true
		}); 
		$(":radio").change(function(){
			var result = $(this).next().attr("for");
			
			$("#check1").attr("style","margin-top:5px; position:absolute; display:none;")
			$("#check2").attr("style","margin-top:5px; position:absolute; display:none;")
			$("#check3").attr("style","margin-top:5px; position:absolute; display:none;")
			$("#check4").attr("style","margin-top:5px; position:absolute; display:none;")
				
			if( result == "a" ){
				$("#check1").attr("style","margin-top:5px; position:absolute;")
			}
			if(result =="b"){
				$("#check2").attr("style","margin-top:5px; position:absolute;")
			}
			if(result == "c"){
				$("#check3").attr("style","margin-top:5px; position:absolute;")
			}
			if(result == "d"){
				$("#check4").attr("style","margin-top:5px; position:absolute;")
			}
		})
		
	})
	
	function check(){
		
		if($(":radio").val()==""){
			alert("휴가종류를 선택해주세요")
			return false;
		}
		
		if($(":radio").val() == "" || $(":radio").val() == null){
			alert("휴가종류를 선택해주세요")
			return false;
		}
		
		if($("#v_cause").val() == "" ){
			alert("휴가사유를 입력해주세요")
			return false;
		}
		if($("#v_start").val() =="" ){
			alert("휴가기간을 선택해주세요")
			return false;
		}
		if($("#v_end").val() ==""){
			alert("휴가기간을 선택해주세요")
			return false;
		}
		if($("#v_emergencyNumber").val() ==""){
			alert("비상연락번호를 입력해주세요");
			return false;
		}
		var v_start =$("#v_start").val().split('-');
	      var v_end =$("#v_end").val().split('-');
	      var start = v_start[0]+v_start[1]+v_start[2];
	      var end = v_end[0]+v_end[1]+v_end[2];
	      
	      if(start> end){
	         alert("정확한 기간을 입력 해주세요")
	         return false;
	      }
		
	}
</script> 
<div class="container" style="margin-top:10px; position:relative;" >
	<div class="row col-xs-12" > 
	<div class="container">
		<!-- <div class="row">
			<div ><h1 class="col-xs-12" style="font-size:50px;">결재요청</h1></div>
		</div>
		<div class="row">
			<div class="col-xs-12" id="approvalTitleUnderline" style="border:1px solid black;width:350px;height:5px"></div>
		</div> -->
	</div> 
	
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
	<div class="row col-xs-12" style=" padding-bottom:30px;" >
		<h2 class="col-xs-12 text-center"><span class="glyphicon glyphicon-chevron-left"></span>휴가신청서<span class="glyphicon glyphicon-chevron-right"></span></h2>
		<div class="col-xs-12" style="height:20px"></div> 
		<form action="/semi/requestForVacationPro.ls" method="post" onSubmit="return check();"  >
		<table class="table col-xs-offset-1 requestForVacation">
			<tr class="row">
				<th class="active col-xs-2">성 명</th>
				<td class="col-xs-3"><input type="text" class="col-xs-12" name="ename" readonly="readonly" value="${memId.ename}"></td>
				<th class="active col-xs-2">소 속</th>
				<td class="col-xs-3"><input type="text" class="col-xs-12" name="dname" readonly="readonly" value="${memId.dname}" ></td>
			</tr>
			<tr class="row">
				<th class="active">직 위</th>
				<td colspan="3"><input type="text" class="col-xs-12"  readonly="readonly" value="${memId.job}"></td>
			</tr>
			<tr class="row">
				<th class="active">휴가종류</th>
				<td colspan="3">
					<input type="radio" name="v_type" value="월차" id="a" style="display:none;">
					<label for="a" class="col-xs-2 btn" style="padding:10px;"><span class="glyphicon glyphicon-ok" id="check1" style="margin-top:5px; position:absolute; display:none;" ></span>ㅁ월차</label>
					<input type="radio" name="v_type" value="연차" id="b" style="display:none;">
					<label for="b" class="col-xs-2 btn" style="padding:10px;"><span class="glyphicon glyphicon-ok" id="check2" style="margin-top:5px; position:absolute; display:none;" ></span>ㅁ연차</label>
					<input type="radio" name="v_type" value="특별휴가" id="c" style="display:none;">
					<label for="c" class="col-xs-2 btn" style="padding:10px;"><span class="glyphicon glyphicon-ok" id="check3" style="margin-top:5px; position:absolute; display:none;" ></span>ㅁ특별휴가</label>
					<input type="radio" name="v_type" value="기타" id="d" style="display:none;">
					<label for="d" class="col-xs-2 btn" style="padding:10px;"><span class="glyphicon glyphicon-ok" id="check4" style="margin-top:5px; position:absolute; display:none;" ></span>ㅁ기타</label>
				</td>
			</tr>
			<tr class="row">
				<th class="active">휴가사유</th>
				<td colspan="3"><input class="col-xs-12" name="v_cause" id="v_cause"></td>
			</tr>
			<tr class="row" >
				<th class="active" style="text-align:center;line-height: 60px;" rowspan="2"  >휴가기간</th>
				<td colspan="3"><div class="input-group date col-xs-12"><input type="text" class="form-control col-xs-12" id="v_start" name="v_start"style="padding-left:15px;color:black;font-size:16px;"><span class="btn" style="display:none;"></span></div></td>
			</tr>
			<tr class="row">
				<td colspan="3"><div class="input-group date col-xs-12"><input type="text" class="form-control col-xs-12" id="v_end" name="v_end"style="padding-left:15px;color:black;font-size:16px;" ><span class="btn" style="display:none;" ></span></div></td>
			</tr>
			<tr class="row">
				<th class="active">비상연락망</th>
				<td colspan="3"><input type="text" class="col-xs-12" name="v_emergencyNumber" id="v_emergencyNumber"></td>
			</tr>
			<tr class="row">
				<th class="active"style="line-height:84px;">기타사항</th>
				<td colspan="3"><textarea class="col-xs-12" style="resize:none; height:200px;" name="v_otherDetail" ></textarea></td>
			</tr>
		</table>
		<input class="col-xs-4 col-xs-offset-4" type="submit" class="btn" value="제출하기" style="height:50px;">
		</form>
	</div>
	
</div>

</div>