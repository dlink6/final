<%@ page contentType="text/html; charset=UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href='/semi/scheduler/css/fullcalendar.css' rel='stylesheet' />
<link href='/semi/scheduler/css/fullcalendar.print.min.css' rel='stylesheet' media='print' />
<link href="/semi/scheduler/css/Schedule.css" rel="stylesheet"/>

<script src='/semi/scheduler/lib/moment.min.js'></script>
<script src='/semi/scheduler/lib/jquery.min.js'></script>
<script src='/semi/scheduler/js/fullcalendar.js'></script>
<script src='/semi/scheduler/lib/ko.js'></script>

<script>
$(document).ready(function() {
    $('#calendar').fullCalendar({
      height: 800,
      header: { 
        left: 'prev,next today', 	
        center: 'title', 			
        right: 'month,agendaWeek,agendaDay' 
      },
      selectable: false,
      defaultDate: new Date(), 
      navLinks: true, 
      editable: false, 
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
  });

</script>

<script>
$(document).ready(function() {
	  var pathNull = document.location.href;
	  pathNull = pathNull.substring(pathNull.lastIndexOf("/"));
	  
	   if (pathNull.indexOf("schedulePage.ls") != -1) {
	    $( "#aaa" ).addClass( "active" );
	  }else if(pathNull.indexOf("reservationPage.ls") != -1) {
	    $( "#bbb" ).addClass( "active" );
	  }
	});
</script>

<div id="page-wrapper">
	<!-- 카테고리 네비게이션 -->
	<ul class="nav nav-pills board">
 		<li role="presentation" id="aaa"><a href="/semi/schedulePage.ls">일정 조회/등록</a></li>
  		<li role="presentation" id="bbb"><a href="/semi/reservationPage.ls">회의실 예약/현황</a></li>
	</ul>
	
  <!-- 본문 -->
  <div id="page-content-wrapper">
    <div class="container-fluid">
    	<form>
    		<button id="bu" class="btn btn-warning" onClick="window.open('/semi/scheduleReg.ls','일정 등록','top=20px, left=520px, width=500px, height=575px, menubar=no, toolbar=no')">일정 등록</button>
    		<span class="line" id="a" style="width:4%"><span>회사일정</span></span>
    		<span class="line" id="b" style="width:4%"><span>부서일정</span></span>
    		<span class="line" id="c" style="width:4%"><span>나의일정</span></span>
    	</form>
    </div>
    <div id='calendar'></div>
   
   
    <div id="eventContent" title="Event Details" style="display:none;">
    	Start: <span id="startTime"></span><br>
   		End: <span id="endTime"></span><br><br>
    <p id="eventInfo"></p>
    <p><strong><a id="eventLink" href="" target="_blank">Read More</a></strong></p>
</div>
  </div>

</div>