<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<link href="/semi/boot/css/bootstrap.css" rel="stylesheet" type="text/css"> 
<link href="/semi/ab/css/style.css" rel="stylesheet" type="text/css">
<link href="/semi/ab/css/pbmain.css" rel="stylesheet" type="text/css">

<script>
   setTimeout(function(){
	   $('.graph-bar').each(function(){
	    	  var dataWidth = $(this).data('value');
	    	  $(this).css("width",dataWidth+"%");
   })
   },400)
   
</script>

<div class="container" style="width:80%"> 
	<ul class="nav nav-pills">
 		<li role="presentation" id="프로젝트 현황" class="active"><a href="/semi/projectList.ls" >프로젝트 현황</a></li>
 		<li role="presentation" id="프로젝트 자료실" class="active"><a href="/semi/pbList.ls">프로젝트 자료실</a></li>
	</ul>
 
    <div class="row col-xs-12">
      <div class="row">
         <div class="col-xs-12 text-center"><h1>프로젝트 현황</h1></div> 
      </div>
      <div style="height:50px;"></div>
      <div style="height:3px;background-color:#18bcd7;"></div>
      
      <table class="table board">
         <tr>
            <th class="col-xs-1" style="text-align:center;">번호</th>
            <th class="col-xs-3" style="text-align:center;">프로젝트명</th>
            <th class="col-xs-2" style="text-align:center;">프로젝트 기간</th> 
            <th class="col-xs-1" style="text-align:center;">작성자</th>
            <th class="col-xs-2" style="text-align:center;">작성일</th>
            <th class="col-xs-2" style="text-align:center;">진행률</th>  
            <th class="col-xs-1"></th>
         </tr>
      <c:if test="${count > 0}">
         <c:forEach var="article" items="${articleList}">
         <tr>
            <td class="board_number" style="text-align:center;">
              <c:out value="${number}"/>
  			  <c:set var="number" value="${number - 1}"/>           
            </td>
            <td><a href="/semi/divMain.ls?proj_num=${article.proj_num}">${article.proj_subject}</a></td>
           <td style="text-align:center;">${article.proj_day}</td>  
            <td class="board_writer" style="text-align:center;">${article.ename}</td>
            <td class="board_time" style="text-align:center;">
            <fmt:formatDate value="${article.proj_reg_date}" pattern="yyyy-MM-dd HH:mm"/>
			</td>                
            <td class="graph-bar" data-value="${article.proj_ing }" ></td>
            <td style="text-align:center;">
            	<a href="">수정</a>
            	<a href="">삭제</a>
            </td>
         </tr>
         </c:forEach>
 	</c:if>       
      </table>
   
   		<input type="button" class="col-xs-offset-10 col-xs-2" style="margin-top:-21px;" value="프로젝트 추가" onclick="window.location='/semi/projectWrite.ls'"> 

		
<div class="row">
<div class="clearfix" style="text-align: center;">

<!--페이지 카운터링 소스 작성-->
<ul class="pagination pagination-sm">
<c:if test="${count > 0}"> 
<c:set var="pageCount" value="${count / pageSize + ( count % pageSize == 0 ? 0 : 1)}"/>
   
<!-- 한번에 보여질 페이지네이션 갯수 : 최대 10개 -->
<c:set var="pageBlock" value="${10}"/>
<fmt:parseNumber var="result" value="${currentPage / 10}" integerOnly="true" />
<c:set var="startPage" value="${result * 10 + 1}" /> 
<c:set var="endPage" value="${startPage + pageBlock-1}"/>
<c:if test="${endPage > pageCount}">
<c:set var="endPage" value="${pageCount}"/>
</c:if>
   
<%-- 검색 결과가 아닌경우 --%>
<c:if test="${empty search || search eq null }">
<%--[이전 아이콘]--%>
<c:if test="${startPage > 10}">
<li><a class="page-link" href="/semi/projectList.ls?proj_num=${proj_num}&pageNum=${startPage - 10}"><span>&laquo;</span></a>
</c:if>
<%--페이지--%> 
<c:forEach var="i" begin="${startPage}" end="${endPage}"> 
<c:choose> 
<c:when test="${i==currentPage}">
<li class="active"><a href="/semi/projectList.ls?proj_num=${proj_num}&pageNum=${i}">${i}</a></li>
</c:when>
<c:otherwise>
<li><a href="/semi/projectList.ls?proj_num=${proj_num}&pageNum=${i}">${i}</a></li>
</c:otherwise>
</c:choose>
</c:forEach>

<%--[다음 아이콘]--%>
<c:if test="${endPage < pageCount}">
<li><a class="page-link" href="/semi/projectList.ls?proj_num=${proj_num}&pageNum=${startPage + 10}"><span>&raquo;</span></a></li>
</c:if>
</c:if>
</c:if> 
</ul> 
</div>
</div>
</div>
</div>	
<div style="height:150px;"></div>
