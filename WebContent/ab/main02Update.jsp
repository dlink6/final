<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<link href="/semi/boot/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="/semi/ab/css/style.css" rel="stylesheet" type="text/css">
<link href="/semi/ab/css/main02Content.css" rel="stylesheet" type="text/css">
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
	var proj_day = form.proj_day.value 
	var proj_subject = form.proj_subject.value
	var proj_ing = form.proj_ing.value 
			if(proj_subject==null || proj_subject==""){
	        alert("프로젝트명을 입력하세요.");
	        return false;
	        }
		if(proj_day==null || proj_day==""){
        alert("프로젝트 기간을 입력하세요.");
        return false;
        }  
	        if(proj_ing==null || proj_ing=="" )
	        {   alert("진행률을 입력하세요.");
	            return false;
	        } 	         
	}); 
}); 
  
</script>

<div class="container">  
	<ul class="nav nav-pills board">
 		<li role="presentation" id="프로젝트 현황" class="active"><a href="/semi/projectList.ls" >프로젝트 현황</a></li>
 		<li role="presentation" id="프로젝트 자료실" class="active"><a href="/semi/pbList.ls">프로젝트 자료실ㄴ</a></li>
	</ul>
 
    <div class="row col-xs-12">
      <div class="row">

   <div class="row col-xs-2" style="width:250px;"></div>
   <form action="/semi/divUpdatePro.ls">
   <input type="hidden" name="proj_num" value="${article.proj_num}">   
   <input type="hidden" name="div_num" value="${article.div_num }">

         <div class="col-xs-12 text-center"><h1>프로젝트별 현황 수정</h1></div>
      </div>
      <div style="height:50px;"></div>
      <div style="height:3px;background-color:#18bcd7;"></div>
      
      <table class="table">
         <tr class="row">
         	<th class="col-xs-2 active">프로젝트명</th>
         	<td><input class="col-xs-12"  value="${article.div_subject}" name="div_subject"></td>
         </tr>
<%--          <tr class="row">
         	<th class="col-xs-2 active">작성자</th>
         	<td><input class="col-xs-12"  value="${article.ename}" name="ename"></td>
         </tr> --%> 
         <tr class="row">
         	<th class="col-xs-2 active">진행 기간</th>
         	<td><input class="col-xs-12" value="${article.div_day}" name="div_day"> </td>
         </tr>
         <tr class="row" style="">
         	<th class="col-xs-2 active">진행률</th>
         	<td><input class="col-xs-12" value="${article.div_ing}" name="div_ing"></td>
         </tr>
         <tr class="row">
         	<th class="col-xs-2 active">진행 내용</th>
         	<td><textarea class="col-xs-12"  name="div_content" style="resize:none; height:200px;">${article.div_content}</textarea></td>
         </tr>
      </table>
	      <input class="col-xs-offset-8 col-xs-2" type="submit" value="수정완료"> 
	      <input class="col-xs-2" type="button" value="이전" onclick="window.location='/semi/divMain.ls?proj_num=${article.proj_num}'">    
	</div> 
	</form>     
</div>  
   
<div style="height:150px;"></div> 

