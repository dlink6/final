<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link href="/semi/tiles/css/main.css" rel="stylesheet" type="text/css">

<!-- 소진이달력 -->
<link href='/semi/scheduler/css/fullcalendar.css' rel='stylesheet' />
<link href='/semi/scheduler/css/fullcalendar.print.min.css' rel='stylesheet' media='print' />

<script src='/semi/scheduler/lib/moment.min.js'></script>
<script src='/semi/scheduler/lib/jquery.min.js'></script>
<script src='/semi/scheduler/js/fullcalendar.js'></script>
<script src='/semi/scheduler/lib/ko.js'></script>
<!-- <link href="/semi/tiles/mainCalendar.css"> -->

<script>
$(document).ready(function() {
    $('#calendar').fullCalendar({
      height: 300,
      header: { 
        left: '', 	
        center: 'title',			
        right: '' 
      },
      //selectable: true,
      defaultDate: new Date(), 
      navLinks: false, 
      editable: true, 
      eventLimit: true, 
      events: "./ScheduleServlet",
      eventClick: function(event) {
    	  var title = event.title;
    	 
    	  var a1 = (event.start).format("YYYY-MM-DD");
    	  var a2 = (event.start).format("HH:mm");
    	  var a3 = a1+"T"+a2;
    	  var start = a3;
    	 
    	  var b1 = (event.end).format("YYYY-MM-DD");
    	  var b2 = (event.end).format("HH:mm");
    	  var b3 = b1+"T"+b2;
    	  var end = b3;
    	  
    	  window.open('/semi/scheduleDel.ls?title='+title+'&start='+start+'&end='+end,'일정 상세보기','top=20px, left=520px, width=500px, height=540px, menubar=no, toolbar=no');
      }
      
    });
    $("#calendar>div.fc-toolbar.fc-header-toolbar>div.fc-center>h2").click(function(){
    	window.location="/semi/schedulePage.ls";
    })
  });
  
</script>
<script>
setTimeout(function(){
	   $('.graph-bar').each(function(){
	    	  var dataWidth = $(this).data('value');
	    	  $(this).css("width",dataWidth+"%");
})
},400)

</script>

<div class="container" style="margin-bottom:200px; margin-top:30px;">

<!-- 우진 공지  -->
	<div class="row" id="body1">
		<div class="col-md-6 col-xs-12">
			<div class="panel panel-default" style="border:0px;">
				<div class="panel-heading" style="background-color:#5caceb; color:white; font-size:1.2em;">
					<div class="panel-title" style="margin-left:15px">
						<a href="/semi/list.ls?board_cat=공지사항" style="text-decoration : none;"><span class="glyphicon glyphicon-bell"></span> 공지사항</a>
					</div>
				</div>
					<table class="table table-hover board">
						<tr>
							<th class="col-xs-1"></th>
							<th class="col-xs-8">제목</th>
							<th class="col-xs-1">파일</th>
							<th class="col-xs-2">작성일</th>
						</tr>
						<c:forEach var="notice" items="${noticeList }">
						<fmt:formatDate value="${today}" pattern="yyyy-MM-dd" var="now"/>
						<fmt:formatDate value="${notice.board_date}" pattern="yyyy-MM-dd" var="article_date"/>
						<tr>
							<td>
							</td>
						<!-- 공지 제목 -->
							<td style="text-align:left;">
								<a href="/semi/content.ls?&board_num=${notice.board_num}">
									${notice.board_subject }
								</a>
								<!-- 오늘 올라온 공지일 경우 new 아이콘 -->
									<c:if test="${now eq article_date}">
										&nbsp;<img src=/semi/board/images/new.gif>
									</c:if>
							</td>
						<!-- 파일 -->
							<td>
								<c:if test="${notice.f_name!=null}">
								<c:set var="fileName" value="${fn:split(notice.f_name, '.')}" />
									<c:choose>
										<c:when test="${fileName[fn:length(fileName)-1]=='png'||fileName[fn:length(fileName)-1]=='jpg'||fileName[fn:length(fileName)-1]=='jepg'||fileName[fn:length(fileName)-1]=='bmp'||fileName[fn:length(fileName)-1]=='gif'||fileName[fn:length(fileName)-1]=='psd'}">
											<span class="glyphicon glyphicon-picture"></span>
										</c:when>
										<c:when test="${fileName[fn:length(fileName)-1]=='hwp'||fileName[fn:length(fileName)-1]=='txt'||fileName[fn:length(fileName)-1]=='pdf'||fileName[fn:length(fileName)-1]=='doc'||fileName[fn:length(fileName)-1]=='ppt'||fileName[fn:length(fileName)-1]=='html'||fileName[fn:length(fileName)-1]=='java'||fileName[fn:length(fileName)-1]=='jsp'||fileName[fn:length(fileName)-1]=='pptx'||fileName[fn:length(fileName)-1]=='xls'||fileName[fn:length(fileName)-1]=='xlsx'}">
											<span class="glyphicon glyphicon-list-alt"></span>
										</c:when>
										<c:otherwise>
											<span class="glyphicon glyphicon-floppy-disk"></span>
										</c:otherwise>
									</c:choose>
								</c:if>
							</td>
						<!-- 작성일 -->
							<td class="board_time" style="text-align:center;">
								<c:if test="${now eq article_date}">
									<fmt:formatDate value="${notice.board_date}" pattern="HH:mm"/>
								</c:if>
								<c:if test="${now ne article_date}">
									<fmt:formatDate value="${notice.board_date}" pattern="yyyy.MM.dd"/>
								</c:if>
							</td> 
						</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		
<!-- 소진 일정 -->
			<div class="col-md-6 col-xs-12">
				<div>
					<div style="float:center;" id="calendar"></div>
				</div>
			</div>
	
	</div>

<!-- 2행 시작 -->
	<div class="row" id="body2" style="margin-top:30px">
	
<!-- 프로젝트 -->		
		<div class="col-md-6 col-xs-12">
			<div class="panel panel-default" style="border:0px">
				<div class="panel-heading" style="background-color:#5caceb; color:white; font-size:1.2em;">
					<div class="panel-title" style="margin-left:15px">
					<a href="/semi/projectList.ls" style="text-decoration : none;">
						<span class="glyphicon glyphicon-align-left"></span>
						&nbsp;프로젝트현황</a></div>
					
				</div>
				<table class="table table-hover board">
					<tr>
						<th class="col-xs-1">번호</th>
						<th class="col-xs-4">제목</th>
						<th class="col-xs-1">작성자</th>
						<th class="col-xs-2">작성일</th>
						<th class="col-xs-2">진행률</th>
					</tr>
					
					<c:forEach var="proj" items="${projList }">
					<fmt:formatDate value="${today}" pattern="yyyy-MM-dd" var="now"/>
					<fmt:formatDate value="${proj.proj_reg_date}" pattern="yyyy-MM-dd" var="proj_reg_date"/>
					<tr>
						<td class="board_num">${proj.proj_num }</td>
						<td class="board_title"><a href="/semi/divMain.ls?proj_num=${proj.proj_num}">${proj.proj_subject }</a></td>
						<td class="board_writer">${proj.ename }</td>
						<td class="board_time">${proj_reg_date}</td>
						<td class="graph-bar" data-value="${proj.proj_ing }" style="margin:8px"></td>
					</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		
<!-- 결재요청 -->
		<div class="col-md-6 col-xs-12">
			<div class="panel panel-default" style="border:0px;">
				<div class="panel-heading" style="background-color:#5caceb; color:white; font-size:1.2em;">
					<div class="panel-title" style="margin-left:15px">
					<a href="/semi/List.ls" style="text-decoration : none;">
					<span class="glyphicon glyphicon-list-alt"></span> &nbsp;결재요청</a>
					</div>
					
				</div>
				<table class="table table-hover board">
					<tr>
						<th class="col-xs-2">번호</th>
						<th class="col-xs-3">제목</th>
						<th class="col-xs-2">작성자</th>
						<th class="col-xs-3">작성일</th>
						<th class="col-xs-2">여부</th>
					</tr>
					<c:if test="${memId.deptno ==10 }">
					<c:forEach var="list" items="${approvalList }">
					<tr>
						<td class="board_number">${list.a_num}</td>
						<td class="board_title"><a href="/semi/ListDetail.ls?documentnum=${list.documentNum}&num=${list.type}">${list.a_subject}</a></td>
												<!-- <div class="btn" onclick="window.location='/semi/ListDetail.ls?documentnum=${list.documentNum}&num=${list.type}'"> -->
						<td class="board_writer">${list.ename}</td>
						<td class="board_time">${list.a_date}</td>
						<td>${list.a_check}</td>
					</tr>
					</c:forEach>
					</c:if>
					<c:if test="${memId.deptno !=10 }">
					<c:forEach var="list" items="${approvalList}">
					<tr>
						<td class="board_number">${list.a_num}</td>
						<td class="board_title"><a href="/semi/ListDetail.ls?documentnum=${list.documentNum}&num=${list.type}">${list.a_subject}</a>${list.a_subject}</td>
												<!-- <div class="btn" onclick="window.location='/semi/ListDetail.ls?documentnum=${list.documentNum}&num=${list.type}'">-->
						<td class="board_writer">${list.ename}</td>
						<td class="board_time">${list.a_date}</td>
						<td>${list.a_check}</td>
					</tr>
					</c:forEach>
					</c:if>
				</table>
			</div>
		</div>
	</div>
</div>
