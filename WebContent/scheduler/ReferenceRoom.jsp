<%@ page contentType="text/html; charset=UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href='/semi/scheduler/css/fullcalendar.css' rel='stylesheet' />
<link href='/semi/scheduler/css/fullcalendar.print.min.css' rel='stylesheet' media='print'/>
<link href='/semi/scheduler/css/scheduler.css' rel='stylesheet' />
<link href="/semi/scheduler/css/ReferenceRoom.css" rel="stylesheet"/>

<script src='/semi/scheduler/lib/moment.min.js'></script>
<script src='/semi/scheduler/lib/jquery.min.js'></script>
<script src='/semi/scheduler/js/fullcalendar.js'></script>
<script src='/semi/scheduler/js/scheduler.min.js'></script>
<script src='/semi/scheduler/lib/ko.js'></script>

<script>
$(document).ready(function() {
    $('#calendar').fullCalendar({
      defaultView: 'agendaTwoDay',
      defaultDate: new Date(),
      weekends: false,
      editable: false,
      selectable: true,
      eventLimit: true,
      header: {
        left: 'prev,next today',
        center: 'title',
        right: 'agendaTwoDay,agendaDay'
      },
      views: {
        agendaTwoDay: {
          type: 'agenda',
          duration: { days: 5 },
          groupByDateAndResource: true
        }
      },
      allDaySlot: false, //종일행 숨기기
      
      resources: [
        { id: 'A', title: 'A 회의실', eventColor: '#C90000' },
        { id: 'B', title: 'B 회의실', eventColor: '#0030DB' }
      ],
      events: "./RegisterServlet",
      
      eventClick: function(event) {
    	  var title = event.resourceId;
    	 
    	  var a1 = (event.start).format("YYYY-MM-DD");
    	  var a2 = (event.start).format("HH:mm");
    	  var a3 = a1+"T"+a2;
    	  var start = a3;
    	 
    	  var b1 = (event.end).format("YYYY-MM-DD");
    	  var b2 = (event.end).format("HH:mm");
    	  var b3 = b1+"T"+b2;
    	  var end = b3;
    	  
    	  window.open('/semi/reservationDel.ls?title='+title+'&start='+start+'&end='+end,'예약 상세보기','top=20px, left=520px, width=400px, height=594px, menubar=no, toolbar=no');
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
    </div>
    <div>
    	<form>
    		<button id="bu" class="btn btn-warning" onClick="window.open('/semi/reservationReg.ls','회의실 예약','top=20px, left=520px, width=400px, height=594px, menubar=no, toolbar=no')">회의실 예약</button>
    	</form>
    </div>
    <div id='calendar'></div>
  </div>

</div>