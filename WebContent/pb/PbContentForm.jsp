<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<link href="/semi/boot/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="/semi/ab/css/style.css" rel="stylesheet" type="text/css">
<link href="/semi/pb/css/pbContentForm.css" rel="stylesheet" type="text/css">
<script> 
$(document).ready(function(){
    //글삭제
     $("#deleteArticle").click(function(){
        var msg = confirm("글을 진심으로 삭제하시겠습니까?\n\n삭제된 글은 복구가 불가능합니다.");    
           if(msg == true){
           }else{
              return false;
           }
     });
});
</script>

<div class="container"> 
	<ul class="nav nav-pills board">
 		<li role="presentation" id="프로젝트 현황" class="active"><a href="/semi/projectList.ls" >프로젝트 현황</a></li>
 		<li role="presentation" id="프로젝트 보관함" class="active"><a href="/semi/pbList.ls">프로젝트 자료실</a></li>
	</ul>
 
    <div class="row col-xs-12">
      <div class="row"> 
      <div class="col-xs-12 text-center"><h1>상세 페이지</h1></div>
      </div>
      <div style="height:50px;"></div>
      <div style="height:3px;background-color:#18bcd7;"></div>
      
      <table class="table board">
         <tr class="row">
         	<th class="col-xs-2 active">작성자</th> 	
         	<td class="board_writer" style="width:40%,">&nbsp;&nbsp;&nbsp; ${article.ename}</td> 
         </tr>
         <tr class="row"> 
         	<th class="col-xs-2 active">제목</th>
         	<td style="width:40%,">&nbsp;&nbsp;&nbsp; ${article.pb_subject}</td> 
         </tr> 
<tr class="row">
<th class="col-xs-2 active">작성일</th>
<td class="board_time">&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${article.pb_date}" pattern="yyyy-MM-dd HH:mm"/>
</td>  
</tr>
<tr class="row">
<th class="col-xs-2 active">내용</th>
<%-- <td style="width:40%,">${article.div_day}</td> --%> 
<td><textarea class="col-xs-12"style="border:0px; height:200px;" readonly="readonly">${article.pb_content}</textarea></td>
</tr> 
<tr class="row" style="">
<th class="col-xs-2 active">첨부파일</th>
<td style="width:40%,">&nbsp;&nbsp;&nbsp; <a href= "/semi/fileAction.ls?pf_name=${fileData.pf_name}&pf_save=${fileData.pf_save}&pb_num=${article.pb_num}"> ${fileData.pf_name} </a></td>     
</tr>   

      </table>
      <form action="/semi/pbUpdate.ls" method="get">
      <c:if test="${memId.empno == article.empno}"> 		 
   	  <input type="hidden" name="pb_num" value="${article.pb_num}">  		   			
	  <input class="col-xs-offset-10 col-xs-1" type="submit" value="수정"> 
	  </c:if> 
	  </form>  
	    <form action="/semi/pbDeletePro.ls" method="get"> 
	    <c:if test="${memId.empno == article.empno}">  
   		<input type="hidden" name="pb_num" value="${article.pb_num}">  
	    <input class="col-xs-1" id="deleteArticle" type="submit" value="삭제">  
	   </c:if> 
	    </form>
	    <input class="col-xs-offset-10 col-xs-2" type="button" value="목록" onclick="window.location='/semi/pbList.ls'"> 
   		
	</div>        
</div>    
   
<div style="height:150px;"></div> 

