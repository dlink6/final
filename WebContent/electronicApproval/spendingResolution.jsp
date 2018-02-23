<%@ page contentType="text/html; charset=UTF-8"%>
<link href="/semi/electronicApproval/css/spendingResolution.css" rel="stylesheet">
<link href="/semi/electronicApproval/calendar/calendar1.css" rel="stylesheet">
<script src="/semi/electronicApproval/calendar/calendar2.js"></script>
<script src="/semi/electronicApproval/calendar/calendar3.js"></script>
<script src="/semi/electronicApproval/calendar/calendar4.js"></script>  
<script>

//상단 네비바
$(document).ready(function() {
	  var pathNull = document.location.href;
	  pathNull = pathNull.substring(pathNull.lastIndexOf("/"));
	   if (pathNull.indexOf("spendingResolution.ls") != -1) {
	    $( "#결재요청" ).addClass( "active" );
	  }
	});

	$(function(){
		$("#insert").click(function(){
			$("#detail").prev().before("<tr class='row'><td><input class='col-xs-12 s_day' name='s_day' ></td><td><input class='col-xs-12 s_goods' name='s_goods' > </td><td><input class='col-xs-12 s_money' name='s_money'></td><td><input class='col-xs-12 s_amount' name='s_amount' ></td><td><input class='col-xs-12 s_content' name='s_content' ></td><td class='col-xs-1' style='border:1px solid white;' ><div class='col-xs-12 btn' id='minus' style='padding:10px;width:35px;'><span class='glyphicon glyphicon-minus' ></span></div></td></tr>");
		})
		$(document).on("click","#minus",function(){
			$(this).closest("tr").remove();
		})
		$("#list").click(function(){
			$(this).next().toggleClass("listShow")
		})
		
		//총 합계금액 구해주는 함수
		$(document).on("blur",".s_money",function(){
			var sum =0;
			$(".s_money").each(function(){
				sum += Number($(this).val());
			})
			$("#s_moneySum").attr("value",sum)
		})
		
		//총 합계수량 구해주는 함수
		$(document).on("blur",".s_amount",function(){
			var sum =0;
			$(".s_amount").each(function(){
				sum += Number($(this).val());
			})
			$("#s_amountSum").attr("value",sum)
		})
		
		//캘린더
		$('.input-group.date').datepicker({
		    calendarWeeks: true,
		    todayHighlight: true,
		    autoclose: true
		});
		
		//금액에 숫자만 입력가능하게
		$(".s_money").blur(function(){
			var s_money = $(this).val();
			
			if(!($.isNumeric(s_money))){
				alert("금액에 숫자를 입력해주세요")
				$(this).val(0);
				$(this).fucus();
			}			
		})
		//수량에 숫자만 입력가능하게
		$(".s_amount").blur(function(){
			var szuty_amount = $(this).val();
			
			if(!($.isNumeric(s_amount))){
				alert("수량에 숫자를 입력해주세요")
				$(this).val(0);
				$(this).fucus();
			}			
		})
	})
	
	function check(){
		
		if($("#s_spendingDate").val() ==""){
			alert("지출일을 입력해주세요")
			return false;
		}
		if($("#s_category").val() == ""){
			alert("구분을 입력해주세요")
			return false;
		}
		
		if($(".s_day").val() =="" || $(".s_goods").val()=="" || $(".s_amount").val() =="" || $(".s_money").val() ==""){
			alert("내역을  입력해주세요")
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
	
	
	<div class="row col-xs-12" style=" padding-bottom:30px;" >
		<h2 class="col-xs-12 text-center"><span class="glyphicon glyphicon-chevron-left"></span>지출결의서<span class="glyphicon glyphicon-chevron-right"></span></h2>
		<div class="col-xs-12" style="height:20px"></div>
		<form action="/semi/spendingResolutionPro.ls" method="post" onsubmit="return check();">
		<table class="table col-xs-offset-1 spendingResolution">
			<tr class="row">
				<th class="	col-xs-1 active">영수자</th>
				<td class="col-xs-4" colspan="2"><input type="text" class="col-xs-12" name="ename"  readonly="readonly" value="${memId.ename}"></td>
				<th class="	col-xs-1 active">부서</th>
				<td class="col-xs-4"><input type="text" class="col-xs-12" name="dname"  readonly="readonly" value="${memId.dname}"></td>
				<td class="col-xs-1" style="display:none;" ></td>
			</tr>
			<tr class="row">
				<th class="active">발의</th>
				<td colspan="2"><input type="text" class="col-xs-12" readonly="readonly" value="${time }"></td>
				<th class=" active">구분</th>
				<td><input type="text" class="col-xs-12" name="s_category" id="s_category"></td>
			</tr>
			<tr class="row">
				<th class="active">지출</th>
				<td colspan="2"><div class="input-group date col-xs-12"><input type="text" class="form-control col-xs-12" name="s_spendingDate" id="s_spendingDate"  style="padding-left:15px;color:black;font-size:16px;"><span class="btn" style="display:none;"></span></div></td>
				<th class="active">결재</th>
				<td><input type="text" class="col-xs-12" readonly="readonly" placeholder="결재승인시 입력됩니다."></td>
			</tr>
			<tr class="row">
				<th colspan="5" class="active">내 역</th>
			</tr>
			<tr class="row">
				<th class="active">월/일</th>
				<th class="active">적요</th>
				<th class="active">금액</th>
				<th class="active">수량</th>
				<th class="active">비고</th>
			</tr>
			<tr class='row'>
				<td><input class='col-xs-12 s_day' name="s_day" ></td>
				<td><input class='col-xs-12 s_goods' name="s_goods"></td>
				<td><input class='col-xs-12 s_money' name="s_money"></td>
				<td><input class='col-xs-12 s_amount' name="s_amount"></td>
				<td><input class='col-xs-12 s_content' name="s_content"></td>
				<td class='col-xs-1' style='border:1px solid white;' >
					<div class='col-xs-12 btn' id='minus' style='padding:10px;width:35px;'>
					<span class='glyphicon glyphicon-minus' ></span>
					</div>
				</td>
			</tr>
			<tr class='row'>
				<td><input class='col-xs-12 s_day' name="s_day"></td>
				<td><input class='col-xs-12 s_goods' name="s_goods"></td>
				<td><input class='col-xs-12 s_money' name="s_money"></td>
				<td><input class='col-xs-12 s_amount' name="s_amount"></td>
				<td><input class='col-xs-12 s_content' name="s_content"></td>
				<td class='col-xs-1' style='border:1px solid white;' >
					<div class='col-xs-12 btn' id='minus' style='padding:10px;width:35px;'>
					<span class='glyphicon glyphicon-minus' ></span>
					</div>
				</td>
			</tr>
			<tr class='row'>
				<td><input class='col-xs-12 s_day' name="s_day"></td>
				<td><input class='col-xs-12 s_goods' name="s_goods"></td>
				<td><input class='col-xs-12 s_money' name="s_money"></td>
				<td><input class='col-xs-12 s_amount' name="s_amount"></td>
				<td><input class='col-xs-12 s_content' name="s_content"></td>
				<td class='col-xs-1' style='border:1px solid white;' >
					<div class='col-xs-12 btn' id='minus' style='padding:10px;width:35px;'>
					<span class='glyphicon glyphicon-minus' ></span>
					</div>
				</td>
			</tr>
			<tr class='row'>
				<td><input class='col-xs-12 s_day' name="s_day"></td>
				<td><input class='col-xs-12 s_goods' name="s_goods"></td>
				<td><input class='col-xs-12 s_money' name="s_money"></td>
				<td><input class='col-xs-12 s_amount' name="s_amount"></td>
				<td><input class='col-xs-12 s_content' name="s_content"></td>
				<td class='col-xs-1' style='border:1px solid white;' >
					<div class='col-xs-12 btn' id='minus' style='padding:10px;width:35px;'>
					<span class='glyphicon glyphicon-minus' ></span>
					</div>
				</td>
			</tr>
			<tr class='row'>
				<td colspan="2" class="active" style="font-size:29px;">합 계</td>
				<td><input class='col-xs-12' readonly="readonly" value="0" id="s_moneySum"></td>
				<td><input class='col-xs-12' readonly="readonly" value="0" id="s_amountSum" ></td>
				<td><input class='col-xs-12'></td>
			</tr>
				
			<tr class="row" id="detail">
				<td colspan="5"><div class="btn col-xs-12" id="insert" style="font-size:20px;">+추가하기</div></td>
			</tr>
		</table>
			<input class="col-xs-4 col-xs-offset-4" type="submit" class="btn" value="제출하기" style="height:50px;">
		</form>
	</div>
	
</div>

</div>
