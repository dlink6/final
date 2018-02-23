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
   
</script>

<div class="container"> 
	<ul class="nav nav-pills board">
 		<li role="presentation" id="프로젝트 현황" class="active"><a href="/semi/projectList.ls" >프로젝트 현황</a></li>
 		<li role="presentation" id="프로젝트 자료실" class="active"><a href="/semi/pbList.ls">프로젝트 자료실</a></li>
	</ul>
 
    <div class="row col-xs-12">
      <div class="row"> 
         <div class="col-xs-12 text-center"><h1>프로젝트별 상세보기 페이지</h1></div>
      </div>
      <div style="height:50px;"></div>
      <div style="height:3px;background-color:#18bcd7;"></div>
      
      <table class="table board">
         <tr class="row">
         	<th class="col-xs-2 active">프로젝트명</th>
         	<td style="width:40%,">&nbsp;&nbsp;&nbsp; ${article.div_subject}</td> 
         </tr>
<tr class="row">
<th class="col-xs-2 active">작성일</th>
<td class="board_time">&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${article.div_reg_date}" pattern="yyyy-MM-dd HH:mm"/>
</td>  
</tr>
         <tr class="row">
         	<th class="col-xs-2 active">작성자</th>
         	<td  class="board_writer" style="width:40%,">&nbsp;&nbsp;&nbsp; ${article.ename}</td>
         </tr>
         <tr class="row">
         	<th class="col-xs-2 active">진행 기간</th>
         	<td style="width:40%,">&nbsp;&nbsp;&nbsp; ${article.div_day}</td>
         </tr>

         <tr class="row" style="">
         	<th class="col-xs-2 active">진행률</th> 
         	<td><input class="col-xs-12" style="border:0px;" value="${article.div_ing}"></td>  
         </tr>
         <tr class="row">
         	<th class="col-xs-2 active">진행 내용</th>
         	<%-- <td style="width:40%,">${article.div_day}</td> --%> 
         	<td><textarea class="col-xs-12"style="border:0px; height:200px;" readonly="readonly">${article.div_content}</textarea></td>
         </tr>
      </table>
   			<form action="/semi/divUpdate.ls" method="get">
   			<input type="hidden" name="div_num" value="${article.div_num}">
   			<input type="hidden" name="proj_num" value="${article.proj_num }">
   			
   		<c:if test="${memId.getEmpno() == article.empno or memId.getEmpno() == '1000'}">
	      <input class="col-xs-offset-10 col-xs-2" type="submit" value="수정" > 	 
	      </c:if>     
	      <input class="col-xs-offset-10 col-xs-2" type="button" value="목록" onclick="window.location='/semi/divMain.ls?proj_num=${article.proj_num}'">  
   			
   			</form>
	</div>      
</div>
   
<div style="height:150px;"></div> 

