<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<link href="/semi/boot/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="/semi/ab/css/style.css" rel="stylesheet" type="text/css">
<link href="/semi/ab/css/main02Write.css" rel="stylesheet" type="text/css">

<link href="/semi/electronicApproval/calendar/calendar1.css" rel="stylesheet">
<script src="/semi/electronicApproval/calendar/calendar2.js"></script>
<script src="/semi/electronicApproval/calendar/calendar3.js"></script>
<script src="/semi/electronicApproval/calendar/calendar4.js"></script>  

<script>
   setTimeout(function(){
	   $('.graph-bar').each(function(){
	    	  var dataWidth = $(this).data('value');
	    	  $(this).css("width",dataWidth+"%");
   })
   },400)
   
 $(document).ready(function(){  
   	//카테고리, 제목,내용 공백처리
	$("#write").click(function(){
	var form = document.getElementById("writeForm");
	var div_subject = form.div_subject.value 
	var div_day = form.div_day.value
	var div_ing = form.div_ing.value
	var div_content = form.div_content.value
			if(div_subject==null || div_subject==""){
	        alert("제목을 입력하세요.");
	        return false;
	        }         
	        if(div_day==null || div_day=="" )
	        {   alert("프로젝트 기간을 입력하세요.");
	            return false;
	        } 
	        if(div_ing==null || div_ing=="" )
	        {   alert("진행률을 입력해주세요."); 
	            return false;
	        } 
	        if(div_content==null || div_content=="" )
	        {   alert("내용을 입력하세요.");
	            return false;
	        }  
	        
	        if(!($.isNumeric(div_ing))){
	        	alert("숫자만 입력해주세요.");
	        	$("#div_ing").val("");
	        	$("#div_ing").focus();
	        	return false;
	        }else{
	       		if(div_ing >100){
	       			alert("진행률은 100이상 입력이 불가합니다.");
	       			$("#div_ing").val("");
	       			$("#div_ing").val("");
	       			return false;
	       		}
	        }
	        
	        
	        
	});
	$('.input-group.date').datepicker({
	    calendarWeeks: true,	
	    todayHighlight: true,
	    autoclose: true
	});
});  
</script> 


<div class="container"> 
	<ul class="nav nav-pills board">
 		<li role="presentation" id="프로젝트 현황" class="active"><a href="/semi/projectList.ls" >프로젝트 현황</a></li>
 		<li role="presentation" id="프로젝트 자료실" class="active"><a href="/semi/pbList.ls">프로젝트 자료실</a></li> 
	</ul>
 
    <div class="row col-xs-12">
      <div class="row">

   <div class="row col-xs-2" style="width:250px;"></div> 
   <form id="writeForm" action="/semi/divWritePro.ls">
   <input type="hidden" name="empno" value="${memId.getEmpno()}">
   <input type="hidden" name="ename" value="${memId.getEname()}">

         <div class="col-xs-12 text-center"><h1>프로젝트별 현황 추가</h1></div>
      </div>
      <div style="height:50px;"></div>
      <div style="height:3px;background-color:#18bcd7;"></div>
      
      <table class="table">
         <tr class="row">
         	<th class="col-xs-2 active">제목</th>
         	<td><input class="col-xs-12" name="div_subject" placeholder="제목을 입력해주세요."></td>
         </tr>
 <!--       <tr class="row">
         	<th class="col-xs-2 active">작성자</th>
         	<td><input class="col-xs-12" name="div_member"></td>
         </tr> --> 
         <tr class="row">
         	<th class="col-xs-2 active">진행 시작일</th>
         	<td><div class="input-group date col-xs-12"><input class="col-xs-12 from-control" name="div_day_start"><span class="btn" style="display:none;"></span></div></td>
         </tr>
         <tr class="row">
         	<th class="col-xs-2 active">진행 종료일</th>
         	<td><div class="input-group date col-xs-12"><input class="col-xs-12 from-control" name="div_day_end"><span class="btn" style="display:none;"></span></div></td>
         </tr>
         <tr class="row" style="">
         	<th class="col-xs-2 active">진행률</th>
         	<td><input class="col-xs-12" name="div_ing" id="div_ing" placeholder="진행상태를 숫자로 입력해주세요."></td>
         </tr>
         <tr class="row">
         	<th class="col-xs-2 active">진행 내용</th>
         	<td><textarea class="col-xs-12" name="div_content" style="resize:none; height:200px;" placeholder="내용을 입력해주세요."></textarea></td>
         </tr>
      </table>
      	  <input type="hidden" name="proj_num" value="${proj_num}"> 
	      <input class="col-xs-offset-8 col-xs-2" id="write" type="submit" value="완료">
	      <input class="col-xs-2" type="button" value="목록" onclick="window.location='/semi/divMain.ls?proj_num=${proj_num}'"> 
		</div>      
		</div>
   		</form>   
		<div style="height:150px;"></div>