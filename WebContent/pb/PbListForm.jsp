<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<link href="/semi/boot/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="/semi/ab/css/style.css" rel="stylesheet" type="text/css">
<link href="/semi/pb/css/pbListForm.css" rel="stylesheet" type="text/css"> 

<div class="container"> 
	<ul class="nav nav-pills board">
 		<li role="presentation" id="프로젝트 현황" class="active"><a href="/semi/projectList.ls" >프로젝트 현황</a></li>
 		<li role="presentation" id="프로젝트 자료실" class="active"><a href="/semi/pbList.ls">프로젝트 자료실</a></li>
	</ul>
 
    <div class="row col-xs-12">
      <div class="row">
         <div class="col-xs-12 text-center"><h1>보관함</h1></div> 
      </div>
      <div style="height:50px;"></div>
      <div style="height:3px;background-color:#18bcd7;"></div>
      
      <table class="table board">
         <tr>
            <th class="col-xs-1">번호</th>
            <th class="col-xs-4">제목</th>
            <th class="col-xs-1">작성자</th>
            <th class="col-xs-3">작성일</th>
            <th class="col-xs-4">조회수</th> 
         </tr>
      <c:if test="${count > 0}"> 
         <c:forEach var="article" items="${articleList}">
         <tr>
         	<td>
          <c:out value="${number}"/>
  	      <c:set var="number" value="${number - 1}"/>
            
     </td>
     <td class="board_number">
     <a href="/semi/pbContent.ls?pb_num=${article.pb_num}"> ${article.pb_subject}</a>
     </td>
     <td class="board_writer">
   	 ${article.ename} 
     </td>   
     <td class="board_time">
     <fmt:formatDate value="${article.pb_date}" pattern="yyyy-MM-dd HH:mm"/>
     </td class="board_number">  
     <td>${article.pb_readcount}</td>
     </tr> 
         </c:forEach> 
 	</c:if>   
   
         
      </table>
      <input type="button" value="작성하기" class="col-xs-offset-10 col-xs-2" onclick="javascript:window.location='/semi/pbWrite.ls'">
 
<center>
<form>
<!-- <input type=hidden name="pb_search"} -->
<select name="searchn">
<option value="0">작성자</option>
<option value="1">제목</option>
<option value="2">내용</option>
</select>
<input type="text" name="search" size="15" maxlength="50">
<button type="submit" class="btn btn-sm">
<span class="glyphicon glyphicon-search" aria-hidden="true"></span> 검색
</button>
</form>      
</center>

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
<li><a class="page-link" href="/semi/pbList.ls?pb_num=${pb_num}&pageNum=${startPage - 10}"><span>&laquo;</span></a>
</c:if>
<%--페이지--%> 
<c:forEach var="i" begin="${startPage}" end="${endPage}"> 
<c:choose> 
<c:when test="${i==currentPage}">
<li class="active"><a href="/semi/pbList.ls?pb_num=${pb_num}&pageNum=${i}">${i}</a></li>
</c:when>
<c:otherwise>
<li><a href="/semi/pbList.ls?pb_num=${pb_num}&pageNum=${i}">${i}</a></li>
</c:otherwise>
</c:choose>
</c:forEach>

<%--[다음 아이콘]--%>
<c:if test="${endPage < pageCount}">
<li><a class="page-link" href="/semi/pbList.ls?pb_num=${pb_num}&pageNum=${startPage + 10}"><span>&raquo;</span></a></li>
</c:if>
</c:if>

<!-- 검색결과인 경우 -->
<c:if test="${!empty search || search ne null }">

<%--[이전]--%>
<c:if test="${startPage > 10}">
<li><a class="page-link" href="/semi/pbList.ls?pb_num=${pb_num}&pageNum=${startPage - 10}&searchn=${searchn}&search=${search }"><span>&laquo;</span></a>   
</c:if>
  		
<%--페이지--%>
<c:forEach var="i" begin="${startPage}" end="${endPage}">
<c:choose>
<c:when test="${i==currentPage }">
<li class="active"><a href="/semi/pbList.ls?pb_num=${pb_num}&pageNum=${i}&searchn=${searchn}&search=${search }">${i}</a></li>
</c:when>
<c:otherwise>
<li><a href="/semi/list.ls?pb_num=${pb_num}&pageNum=${i}&searchn=${searchn}&search=${search }">${i}</a></li>
</c:otherwise>
</c:choose>
</c:forEach>

<%--[다음]--%>
<c:if test="${endPage < pageCount}">
<li><a class="page-link" href="/semi/pbList.ls?pb_num=${pb_num}&pageNum=${startPage + 10}&searchn=${searchn}&search=${search}"><span>&raquo;</span></a></li>
</c:if>
</c:if>
</c:if>
</ul> 
</div>		
</div>
  </div>
  </div>
<div style="height:150px;"></div>
