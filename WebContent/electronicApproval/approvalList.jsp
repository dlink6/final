<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="/semi/electronicApproval/css/list.css" rel="stylesheet">
<script>
	$(function(){
		$("#list").click(function(){
			$(this).next().toggleClass("listShow")
		})
	})
	
//상단 네비바
	$(document).ready(function() {
	  var pathNull = document.location.href;
	  pathNull = pathNull.substring(pathNull.lastIndexOf("/"));
	  console.log(pathNull);
	  
	   if (pathNull.indexOf("List.ls") != -1) {
	    $( "#결재함" ).addClass( "active" );
	  }
	});
</script>
<div class="container" style="margin-top:10px; position:relative; width:55%;" >
	<div class="row col-xs-12" > 
	<div class="container">
		<!-- <div class="row">
			<div ><h1 class="col-xs-12" style="font-size:50px;">결재함</h1></div>
		</div>
		<div class="row">
			<div class="col-xs-12" id="approvalTitleUnderline" style="border:1px solid black;width:350px;height:5px"></div>
		</div> -->
	</div> 
	
	<div style="height:30px;"></div>
	<div class="row col-xs-12">
		<ul class="nav nav-pills board">
 		<li role="presentation" id="결재함"><a href="/semi/List.ls">결재함</a></li>
  		<li role="presentation" id="결재요청" class="dropdown">
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
	
		<table class="table board" id="approvalList">
			<tr>	
				<th class="col-xs-1">번호</th>
				<th class="col-xs-4">제목</th>
				<th class="col-xs-2">작성자</th>
				<th class="col-xs-1">부서</th>
				<th class="col-xs-3">작성일</th>
				<th class="col-xs-1 btn" style="border-bottom:1px" onclick="window.location='/semi/List.ls?checkNum=${checkNum}'">여부</th>
			</tr>
		
			<c:choose>
				<c:when test="${size == 0 }">
					<tr>
						<td class="col-xs-12 text-center" colspan="5">작성된 게시물이 없습니다</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:if test="${memId.empno ==1000}">
						<c:forEach var="dto" items="${list}">
							<tr>
								<td class="col-xs-1">${dto.a_num}</td>
								<td class="col-xs-4"><a href="/semi/ListDetail.ls?num=${dto.type}&ename=${dto.ename}&dname=${dto.dname}&documentnum=${dto.documentNum}">${dto.a_subject }</a></td>
								<td class="col-xs-2">${dto.ename }</td>
								<td class="col-xs-1">${dto.dname}</td>
								<td class="col-xs-3">${dto.a_date }</td>
								<td class="col-xs-1">${dto.a_check }</td>
							</tr>
						</c:forEach>
						<c:if test="${list.size() == 0}">
							<c:if test="${search != null }">
								<tr>
									<td class="col-xs-12" colspan="6">검색 결과가 없습니다</td>
								</tr>
							</c:if>
							<c:if test="${search == null }">
								<tr>
									<td class="col-xs-12" colspan="6">작성된 게시물이 없습니다</td>
								</tr>
							</c:if>
						</c:if>
					</c:if>
					
					<c:if test="${memId.empno !=1000 }">
						<c:forEach var="dto" items="${list}">
							<tr>
								<td class="col-xs-1">${dto.a_num}</td>
								<td class="col-xs-4"><a href="/semi/ListDetail.ls?num=${dto.type}&ename=${dto.ename}&dname=${dto.dname}&documentnum=${dto.documentNum}">${dto.a_subject }</a></td>
								<td class="col-xs-2">${dto.ename }</td>
								<td class="col-xs-1">${dto.dname}</td>
								<td class="col-xs-3">${dto.a_date }</td>
								<td class="col-xs-1">${dto.a_check }</td>
							</tr>
						</c:forEach>
					
					</c:if>
				</c:otherwise>
			</c:choose>
		
			
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			
		</table>
		<div class="col-xs-12 text-center">
			<nav>
  				<ul class="pagination">
    				<c:if test="${search ==null}">
	    				<li>
	      					<a href="/semi/List.ls?page=${page-1 }&start=${startcount*10-109}&pageNum=${startcount-10}" aria-label="Previous">
	        				<span aria-hidden="true">&laquo;</span>
	      				</a>
	    				</li>
	    				<c:forEach var="i" begin="${startcount}" end="${endcount }">
	    				<c:if test="${pageNum == i}">
	    					<li><a class="pageActive" href="/semi/List.ls?start=${1+((i-1)*10)}&page=${page}&pageNum=${i}">${i}</a></li>
	    				</c:if>
	    				<c:if test="${pageNum != i}">
	    					<li><a href="/semi/List.ls?start=${1+((i-1)*10)}&page=${page}&pageNum=${i}">${i}</a></li>
	    				</c:if>
	    				</c:forEach>
		    			<li>
				     		<a href="/semi/List.ls?page=${endcount<=10 ? page :page+1 }&start=${endcount<=10? 1+(10*endcount)-10 :1+(10*endcount)}&pageNum=${endcount<=10? endcount :endcount+1 }" aria-label="Next">
			    			<span aria-hidden="true">&raquo;</span></a>
	   		 	   		</li>
    				</c:if>
    				
    				<c:if test="${search !=null}">
	    				<li>
	      				<a href="/semi/List.ls?page=${page-1 }&start=${startcount*10-109}&search=${search}" aria-label="Previous">
	        			<span aria-hidden="true">&laquo;</span>
	      				</a>
	    				</li>
    					<c:forEach var="i" begin="${startcount }" end="${endcount}">
    					<li><a href="/semi/List.ls?start=${1+((i-1)*10)}&search=${search}&page=${page}">${i}</a></li>
    					</c:forEach>	
					    <li>
				    		<a href="/semi/List.ls?page=${endcount<=10? page :page+1 }&start=${endcount<=10? 1+(10*endcount)-10 :1+(10*endcount)}&search=${search}&pageNum=${endcount<=10? endcount :endcount+1 }" aria-label="Next">
				    		<span aria-hidden="true">&raquo;</span></a>
	   			 	   </li>
    				</c:if>
  		      </ul>
	       </nav>
		</div>
	
	
		<div class="col-xs-12" style="height:5px;"></div>
		<div class="col-xs-12">
		<form action="/semi/List.ls">
			<input type="text" class="col-xs-5 col-xs-offset-3" name="search">
			<input class="col-xs-1" type="submit" value="검색">
			<input type="hidden" value="${memId.deptno }" name="deptno">
			<input type="hidden" value="${memId.empno }" name="empno">
		</form>
			<div style="height:100px;"></div>
		</div>
		
	</div>
</div>