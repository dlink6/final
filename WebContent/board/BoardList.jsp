<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script>
$(function () {
	  $('[data-toggle="tooltip"]').tooltip()
	})

$(document).ready(function() {
	  var pathNull = document.location.href;
	  var board_cat_var="";
	  pathNull = pathNull.substring(pathNull.lastIndexOf("/"));
	  console.log(pathNull);
	  
	   if (pathNull.indexOf("board_cat=%EA%B3%B5%EC%A7%80%EC%82%AC%ED%95%AD") != -1) {
	    $( "#공지사항" ).addClass( "active" );
	    board_cat_var = "공지사항";
	  }
	  else if (pathNull.indexOf("board_cat=%EB%B6%80%EC%84%9C%EB%B3%84%EC%86%8C%EC%8B%9D") != -1) {
	    $( "#부서별소식" ).addClass( "active" );
	    board_cat_var = "부서별소식";
	  }
	  else if (pathNull.indexOf("board_cat=%EC%9E%90%EC%9C%A0%EA%B2%8C%EC%8B%9C%ED%8C%90") != -1) {
	    $( "#자유게시판" ).addClass( "active" );
	    board_cat_var = "자유게시판";
	  }
	   else{
		  $( "#전체보기" ).addClass( "active" );
	  }
	});
</script>

<div class="container" style="width:55%; margin-top:50px; margin-bottom:50px">
	<div class=row>
<!-- 카테고리 네비게이션 -->
	<ul class="nav nav-pills board">
 		<li role="presentation" id="전체보기"><a href="/semi/list.ls">전체보기</a></li>
  		<li role="presentation" id="공지사항"><a href="/semi/list.ls?board_cat=공지사항">공지사항</a></li>
  		<li role="presentation" class="dropdown" id="부서별소식">
  			<a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false" >
     		 부서별 소식 <span class="caret"></span>
   			</a>
   			 <ul class="dropdown-menu" style="width:20px;">
   				<li><a href="/semi/list.ls?board_cat=부서별소식">전체보기</a></li>
   				<li role="separator" class="divider"></li>
      			<li><a href="/semi/list.ls?board_cat=부서별소식.인사팀">인사팀</a></li>
      			<li><a href="/semi/list.ls?board_cat=부서별소식.영업팀">영업팀</a></li>
      			<li><a href="/semi/list.ls?board_cat=부서별소식.홍보팀">홍보팀</a></li>
      			<li><a href="/semi/list.ls?board_cat=부서별소식.기획팀">기획팀</a></li>
      			<li><a href="/semi/list.ls?board_cat=부서별소식.개발팀">개발팀</a></li>
   			 </ul>
  		<!-- <a href="/semi/list.ls?board_cat=부서별소식">부서별 소식</a> -->
  		<li role="presentation" id="자유게시판"><a href="/semi/list.ls?board_cat=자유게시판">자유게시판</a></li>
	</ul>
	
	<!-- 테이블 -->
	<table class="table table-hover board">
		<thead>
			<tr style="font-weight:bold;">
				<th style="text-align:center;">번호</th>
				<th style="text-align:center;">제목</th>
				<th style="text-align:center;">파일</th>
				<th style="text-align:center;">작성자</th>
				<th style="text-align:center;">카테고리</th>
				<th style="text-align:center;">작성일</th>
				<th style="text-align:center;">조회수</th>
			</tr>
		</thead>
		
		<tbody>
		<!-- 게시글이 없는경우-->
		<c:if test="${count==0 }">
			<tr>
				<td colspan="7" style="background-color:#eeee; text-align:center;">게시글이 없습니다.</td>
			</tr>
		</c:if>
		
		<!-- 게시글이 있는경우-->
		<c:if test="${count>0 }">
			<!-- 공지글 받아오기 -->
			<c:forEach var="notice" items="${noticeList }">
			<fmt:formatDate value="${today}" pattern="yyyy-MM-dd" var="now"/>
			<fmt:formatDate value="${notice.board_date}" pattern="yyyy-MM-dd" var="article_date"/>
			<tr height="90%" style="background-color:#EFF8FB;">
			<!-- 공지번호(라벨) -->
				<td style="text-align:center;">
					<span class="label label-info">공지</span>
				</td>
			<!-- 공지 제목 -->
				<td style="font-weight:550;">
					<a class="board_title notice" href="/semi/content.ls?board_cat=${notice.board_cat }&board_num=${notice.board_num}&pageNum=${currentPage}">
        			  ${notice.board_subject}</a>&nbsp;
        			  <!-- 오늘 올라온 공지일 경우 new 아이콘 -->
						<c:if test="${now eq article_date}">
							<img src=/semi/board/images/new.gif>
						</c:if>
				</td>
			<!-- 공지 파일 -->
				<td style="text-align:center;">
				<c:if test="${notice.f_name!=null}">
						<c:set var="fileName" value="${fn:split(notice.f_name, '.')}" />
						<c:choose>
							<c:when test="${fileName[fn:length(fileName)-1]=='png'||fileName[fn:length(fileName)-1]=='jpg'||fileName[fn:length(fileName)-1]=='jepg'||fileName[fn:length(fileName)-1]=='bmp'||fileName[fn:length(fileName)-1]=='gif'||fileName[fn:length(fileName)-1]=='psd'}">
								<span class="glyphicon glyphicon-picture" title="${notice.f_name}"> </span>
							</c:when>
							<c:when test="${fileName[fn:length(fileName)-1]=='hwp'||fileName[fn:length(fileName)-1]=='txt'||fileName[fn:length(fileName)-1]=='pdf'||fileName[fn:length(fileName)-1]=='doc'||fileName[fn:length(fileName)-1]=='ppt'||fileName[fn:length(fileName)-1]=='html'||fileName[fn:length(fileName)-1]=='java'||fileName[fn:length(fileName)-1]=='jsp'}">
								<span class="glyphicon glyphicon-list-alt" title="${notice.f_name}"></span>
							</c:when>
							<c:otherwise>
								<span class="glyphicon glyphicon-floppy-disk" title="${notice.f_name}"></span>
							</c:otherwise>
						</c:choose>
				</c:if>
				</td>
				
				<!-- 공지 작성자 -->
				<td class="board_writer" style="text-align:center;"><span data-toggle="tooltip" data-placement="left" title="관리자">${notice.ename }</span></td>
				<!-- 공지 카테고리 -->
				<td class="board_category" style="text-align:center;">
					<c:set var="board_cat" value="${fn:split(notice.board_cat, '.')}" />
					${board_cat[fn:length(board_cat)-1]}
					<%-- ${article.board_cat} --%>
				</td>
				<!-- 공지 작성일 -->
				<td class="board_time" style="text-align:center;">
					<fmt:formatDate value="${today}" pattern="yyyy-MM-dd" var="now"/>
					<fmt:formatDate value="${notice.board_date}" pattern="yyyy-MM-dd" var="article_date"/>
					<c:if test="${now eq article_date}">
						<fmt:formatDate value="${notice.board_date}" pattern="HH:mm"/>
					</c:if>
					<c:if test="${now ne article_date}">
						<fmt:formatDate value="${notice.board_date}" pattern="yyyy.MM.dd"/>
					</c:if>
				</td> 
				<!-- 공지 조회수 -->
				<td class="board_readcount" style="text-align:center;">${notice.board_readcount }</td>
			</tr>
			</c:forEach>
			
		<!-- 게시글 리스트 받아오기-->
			<c:forEach var="article" items="${articleList }">
				<fmt:formatDate value="${today}" pattern="yyyy-MM-dd" var="now"/>
				<fmt:formatDate value="${article.board_date}" pattern="yyyy-MM-dd" var="article_date"/>
			
			<tr>
				<!-- 번호 -->
				<td class="board_number" style="text-align:center;">
					<!-- 공지인경우 번호대신 공지 라벨 -->
					<c:if test="${article.board_cat eq '공지사항'}">
						<span class="label label-info">공지</span>
						<c:set var="number" value="${number -1}"/>
					</c:if>
					<c:if test="${article.board_cat ne '공지사항'}">
						<c:out value="${number}" />
						<c:set var="number" value="${number -1}"/>
					</c:if>
				</td>
				<!-- 제목 -->
				<td style="width:50%;"> 
				<!-- 답글인경우 제목 앞에 공백 -->
				<c:if test="${article.board_re_lev > 0}">
  					<img src="/semi/board/images/level.gif" width="${10 * article.board_re_lev}">┗&nbsp;&nbsp;
 				</c:if>
 				<!-- 답글이 아닌 경우 -->
  				<c:if test="${article.board_re_lev == 0}">
   					<%-- <img src="/semi/board/images/level.gif" width="${5 * article.board_re_lev}"> --%>
				</c:if>
					 <a class="board_title" href="/semi/content.ls?board_cat=${article.board_cat }&board_num=${article.board_num}&pageNum=${currentPage}">
        			  ${article.board_subject}</a>
        			<!-- 코멘트수 출력 -->
        			  <c:if test="${article.recnt > 0}">
        			  <cmcnt>&nbsp;[${article.recnt}]</cmcnt>
        			  </c:if>
        			  <!-- 오늘 올라온 공지일 경우 new 아이콘 -->
						<c:if test="${now eq article_date and article.board_cat eq '공지사항'}">
							&nbsp;<img src=/semi/board/images/new.gif>
						</c:if>
        		</td>

				<!-- 파일 종류-->
				<td style="text-align:center;">
				
					<c:if test="${article.f_name!=null}">
						<c:set var="fileName" value="${fn:split(article.f_name, '.')}" />
						<c:choose>
							<c:when test="${fileName[fn:length(fileName)-1]=='png'||fileName[fn:length(fileName)-1]=='jpg'||fileName[fn:length(fileName)-1]=='jepg'||fileName[fn:length(fileName)-1]=='bmp'||fileName[fn:length(fileName)-1]=='gif'||fileName[fn:length(fileName)-1]=='psd'}">
								<span class="glyphicon glyphicon-picture" title="${article.f_name}"></span>
							</c:when>
							<c:when test="${fileName[fn:length(fileName)-1]=='hwp'||fileName[fn:length(fileName)-1]=='txt'||fileName[fn:length(fileName)-1]=='pdf'||fileName[fn:length(fileName)-1]=='doc'||fileName[fn:length(fileName)-1]=='ppt'||fileName[fn:length(fileName)-1]=='html'||fileName[fn:length(fileName)-1]=='java'||fileName[fn:length(fileName)-1]=='jsp'||fileName[fn:length(fileName)-1]=='pptx'}">
								<span class="glyphicon glyphicon-list-alt" title="${article.f_name}"></span>
							</c:when>
							<c:otherwise>
								<span class="glyphicon glyphicon-floppy-disk" title="${article.f_name}"></span>
							</c:otherwise>
						</c:choose>
						</c:if>
				
				</td>
				<!-- 작성자 -->
				<td class="board_writer" style="text-align:center;"><span data-toggle="tooltip" data-placement="left" title="${article.dname} (${article.empno})">${article.ename }</span></td>
				
				<!-- 카테고리 -->
				<td class="board_category" style="text-align:center;">
					<c:set var="board_cat" value="${fn:split(article.board_cat, '.')}" />
					${board_cat[fn:length(board_cat)-1]}
					<%-- ${article.board_cat} --%>
				</td>
				
				<!-- 작성일 -->
				<td class="board_time" style="text-align:center;">
					<c:if test="${now eq article_date}">
						<fmt:formatDate value="${article.board_date}" pattern="HH:mm"/>
					</c:if>
					<c:if test="${now ne article_date}">
						<fmt:formatDate value="${article.board_date}" pattern="yyyy.MM.dd"/>
					</c:if>
				</td> 
				<!-- 조회수 -->
				<td class="board_readcount" style="text-align:center;">${article.board_readcount }</td> <!-- 조회수 -->
			</tr>
			</c:forEach>
		</c:if>
		</tbody>
	</table>
	</div>
<!-- 글쓰기 버튼 -->
<button class="btn btn-primary pull-right btn-sm" onclick="location.href='/semi/writeForm.ls'">글쓰기</button>

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
      			<li><a class="page-link" href="/semi/list.ls?board_cat=${board_catt }&pageNum=${startPage - 10}"><span>&laquo;</span></a>
   			</c:if>
  		 <%--페이지--%>
   			<c:forEach var="i" begin="${startPage}" end="${endPage}">
   			<c:choose>
   				<c:when test="${i==currentPage }">
      				<li class="active"><a href="/semi/list.ls?board_cat=${board_catt }&pageNum=${i}">${i}</a></li>
  				</c:when>
  				<c:otherwise>
  					<li><a href="/semi/list.ls?board_cat=${board_catt }&pageNum=${i}">${i}</a></li>
   				</c:otherwise>
   			</c:choose>
  			</c:forEach>
  		<%--[다음 아이콘]--%>
  			<c:if test="${endPage < pageCount}">
        		<li><a class="page-link" href="/semi/list.ls?board_cat=${board_catt }&pageNum=${startPage + 10}"><span>&raquo;</span></a></li>
  			</c:if>
   		</c:if>

	<!-- 검색결과인 경우 -->
		<c:if test="${!empty search || search ne null }">
		<%--[이전]--%>
 			<c:if test="${startPage > 10}">
   	  			<li><a class="page-link" href="/semi/list.ls?board_cat=${board_catt }&pageNum=${startPage - 10}&searchn=${searchn}&search=${search }"><span>&laquo;</span></a>   
  			</c:if>
  		<%--페이지--%>
  			<c:forEach var="i" begin="${startPage}" end="${endPage}">
   			<c:choose>
   				<c:when test="${i==currentPage }">
      				<li class="active"><a href="/semi/list.ls?board_cat=${board_catt }&pageNum=${i}&searchn=${searchn}&search=${search }">${i}</a></li>
  				</c:when>
  				<c:otherwise>
  					<li><a href="/semi/list.ls?board_cat=${board_catt }&pageNum=${i}&searchn=${searchn}&search=${search }">${i}</a></li>
   				</c:otherwise>
   			</c:choose>
  			</c:forEach>
  		<%--[다음]--%>
			<c:if test="${endPage < pageCount}">
				<li><a class="page-link" href="/semi/list.ls?board_cat=${board_catt }&pageNum=${startPage + 10}&searchn=${searchn}&search=${search }"><span>&raquo;</span></a></li>
			</c:if>
  		</c:if>
		</c:if>
	</ul>
		
		<!-- 검색창 -->
			<form>
				<input type=hidden value=${board_catt } name="board_cat">
				<select name="searchn">
					<option value="1">제목</option>
					<option value="0">작성자</option>
					<option value="2">내용</option>
				</select>
			
				
				<input type="text" name="search" size="15" maxlength="50">
			
				
				<button type="submit" class="btn btn-sm">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span> 검색
				</button>
			</form>
			
</div>
</div>
</div>