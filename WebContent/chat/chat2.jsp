<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html >
<html>
<head>
<title>인트라넷 메신저</title>  
<meta name="viewport" content="width=device-width", initial-scale="1" />
<link rel="stylesheet" href="/semi/tiles/css/chatCSS/bootstrap.css">   <!-- css를 사용하겠다. -->
<link rel ="stylesheet" href ="/semi/tiles/css/chatCSS/codingBooster.css">
<link rel ="stylesheet" href ="/semi/tiles/css/chatCSS/custom.css">
<link href="/semi/tiles/css/chatCSS/a.css"  type="text/css"  rel="stylesheet">
<link rel="stylesheet" href="/semi/tiles/css/chatCSS/chatList.css">
<link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
$(function(){
	chatListFunction('ten');
	getInfiniteChat();
})
</script>
<script type="text/javascript">
$(function(){
	$("#menu-toggle").click(function(e) {
					e.preventDefault();
					$("#wrapper").toggleClass("toggled");
	});
	
	$(document).on("click",".open",function(){
		window.open('/semi/chat.ls?participant='+$(this).attr("id"),'','width=400 height=466 top='+Math.floor(Math.random()*500)+', left='+Math.floor(Math.random()*500) +' resize = "no"');
	});
$('#chatContent').keydown(function(e){
	if(e.keyCode == 13){
		submitFunction();
	}
});
});

</script>
<script type="text/javascript">
	var empno = ${memId.empno};
	function autoClosingAlert(selector,delay){
	alert.show();
	window.setTimeout(function(){alert.hide()},delay);
	} 
	var roomno = ${roomno};
	var personNo = ${personNo};
	var participants  = [${participants}];
	function submitFunction(){  
		var chatContent = $('#chatContent').val();
		$.ajax({
			type:"post",
			url: "./ChatSubmitServlet",
			cache : false,
			data:{
			empno: encodeURIComponent(empno),
			chatContent: encodeURIComponent(chatContent),
			roomno: encodeURIComponent(roomno),   
			personNo: encodeURIComponent(personNo),
			participants: encodeURIComponent(participants)
			},  
			success : function(result){
				if(result == 1){
					autoClosingAlert('#successMessage',2000);
				}else if(result ==0){
					autoClosingAlert('#dangetMessage',2000);
				}else{
					autoClosingAlert('#warningMessage',2000);
				}
			} 
		});  
		$('#chatContent').val('');
	}
	function getInfiniteChat(){
		  setInterval(function(){
			chatListFunction();
		},2000);
	}
		
		function chatListFunction(){
		$.ajax({
			type:"post",
			url:"./ChatListServlet",
			data:{		
				roomno : encodeURIComponent(roomno)
				},
			success:function(data){
				if(data == "") {return;};
				var parsed = JSON.parse(data);
				var result = parsed.result;
					$('#chatList').html("");
				for(var i=0; i< result.length; i++){
					if(result[i][0].value==empno){
						//session 아이디 값이랑 비교
 					// 0사원번호,1사원이름 ,2부서번호,3부서이름,4내용,5보낸시간
						addChat1(result[i][4].value,result[i][5].value,result[i][6].value); 	
					}else{
					addChat2(result[i][1].value, result[i][3].value, result[i][4].value, result[i][5].value,result[i][6].value,result[i][7].value);
					}
				}
			}
		});
	} 

	/* 화면에 보여줌 나일떄 */
	 function addChat1(chatContent,chatTime,cnt){
		$('#chatList').append(
				'<div class="direct-chat-msg doted-border">'+
				'<div class="direct-chat-text direct-chat-name pull-right"  style="background-color: #ddea16;">' +
				chatContent+
				'</div>'+
				'<div class="direct-chat-info clearfix">'+
				'<div class="direct-chat-name pull-right">'+
				'<span class="direct-chat-timestamp">'+chatTime+'</span>'+
				'<span style ="color:yellow; padding: 10px;">'+cnt+'</span>'+
                    '</div>'+
                    '</div>'+
                    '</div>'	);
		$("#chatList").scrollTop($('#chatList')[0].scrollHeight);
	}
	
	
//// 사원번호 , 이름 , 내용 , 보낸시간
	 function addChat2(ename,dname,chatContent,chatTime,cnt,profile){
			$('#chatList').append(
					'<div class="direct-chat-msg doted-border">'+
					'<div class="direct-chat-info clearfix">'+
					'<span class="direct-chat-name pull-left">'+ename+"("+dname+")"+'</span>'+
						'</div>'+
						'<img alt="message user image"'+
						'src="'+profile+'"'+
						'class="direct-chat-img">'+
						'<div class="direct-chat-text direct-chat-name pull-left" style="background-color: #ffffff; margin-left: 2% !important; ">'+
						chatContent+'</div>'+
						'<div class="direct-chat-info clearfix">'+'<br>'+
						'<span class="direct-chat-timestamp pull-left">'+chatTime+'</span>'+
						'<span style ="color:yellow; padding: 10px;">'+cnt+'</span>'+
						'</div>'+
						'</div>'+
						'</div>');
			$("#chatList").scrollTop($('#chatList')[0].scrollHeight);
		}
		
</script>
<style>
	 body {
    overflow-x: hidden;
    overflow-y: hidden;
 }
#wrapper {
    padding-right: 0;
    -webkit-transition: all 0.6s ease;
    -moz-transition: all 0.6s ease;
    -o-transition: all 0.6s ease;
    transition: all 0.6s ease;
}
#wrapper.toggled {
    padding-right: 200px;
}
#sidebar-wrapper {
    z-index: 1000;
    position: fixed;
    right: 250px;
    margin-right: -250px;
    overflow-y: auto;
   	overflow-x:hidden;
   	background-color:#fbfbfb !Important;
    -webkit-transition: all 0.5s ease;
    -moz-transition: all 0.5s ease;
    -o-transition: all 0.5s ease;
    transition: all 0.5s ease;
        width: 200px;
    top: 106px;
    height: 306px;
    right: 285px;
}
#wrapper.toggled #sidebar-wrapper {
    width: 0;
}
#page-content-wrapper {
    width: 100%;
    padding: 10px;   
}
#wrapper.toggled #page-content-wrapper {
    position: absolute;
    margin-right:-250px;
}
.sidebar-nav {
    top: 0;
    left:15px;
    width: 200px;  
    margin: 0;
    padding: 0;
    list-style: none;
}
.sidebar-nav li {
    text-indent: 20px;
    line-height: 40px;
}
.sidebar-nav li a {
    display: block;
    text-decoration: none;
}
.sidebar-nav li a:hover {
    text-decoration: none;
    color: #fff;
    background: #312A25;
}
.sidebar-nav li a:active,
.sidebar-nav li a:focus {
    text-decoration: none;
}
.sidebar-nav > .sidebar-brand {
    height: 65px;
    font-size: 18px;
    line-height: 60px;
}
.sidebar-nav > .sidebar-brand a {
    color: #999999;
}
.sidebar-nav > .sidebar-brand a:hover {
    color: #fff;
    background: none;
}
    #wrapper {
        padding-right: 220px;
    }
    #wrapper.toggled {
        padding-right: 0;
    }
    #sidebar-wrapper {
        width: 200px;
        opacity: 0.85;
    }
#wrapper.toggled span {
        visibility:hidden;
    }
  #wrapper.toggled   i {
 float:left;
 } 
    #page-content-wrapper {
        padding: 20px;
        position: relative;
    }
    #wrapper.toggled #page-content-wrapper {
        position: relative;
        margin-left: 0;
    }  
    
    .open:hover{
	color: #FEFEFE;
    BACKGROUND-COLOR: #BBBBBB;    
    }
</style>

</head>
<body>
 

<div class ="container bootstrap snippet">
	<div class="row">
		 <div class ="col-xs-12">
		 	<div class ="portlet portlet-default">
		 	<div class="portlet-heading" style="padding: 10px">
		 		<div class ="portlet-title">
		 		<a style="font-size: 16px; padding: 16px" >${roomName}</a>
		 		</div>
		 		  <a href="#menu-toggle" id="menu-toggle"
						style="float:right;"><span>aside</span><i class="fa fa-comment-o"
							style="font-size:20px !Important; padding: 10px" aria-hidden="true"
							aria-hidden="true"></i> 
              </a>
		 		 <div class ="clearfix"> </div>
		 	</div>
		 	<div id ="chat" class ="panel-collapse collapse in">
		 		<div class ="portlet-body chat-widget" style ="overflow-y:auto; height: 360px; background-color: #2682ac;">
							<div class="popup-messages">
								<div class="direct-chat-messages" id ="chatList" style="overflow: hidden;">
        <!-- /#sidebar-wrapper -->
								<!-- 채팅 내용 들어올곳  -->
								</div>
								</div>
								</div>
								<div class="portlet-footer">
									<div class="row"></div>
									<div class="row" style="height: 90px">
										<div class="form-group col-xs-10">
											<textarea style="height: 80px; resize: none;"
												id="chatContent" class="form-control"
												placeholder="메시지를 입력하세요."></textarea>
										</div>
										<div class="form-group col-xs-2">
											<button type="button" class="btn btn-default pull-right"
												onclick="submitFunction();" style=" position: absolute; bottom: -41px; right: 10px;">전송</button>
											<div class="clearfix"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
</div>
</div>
</div>


<div id="wrapper" class ="toggled">
        <div id="sidebar-wrapper" class="form-dark scrollbar-dusty-grass thin square" >
        <div style="text-align: center; padding: 6px;">
		<p>${participants}</p>
        <span style=" font-size: 11px;">(${personNo})</span>
        </div>
        <div>
            <ul class="sidebar-nav" style="margin-left:0; position: relative;">
						<c:catch var ="ex">
			   			<c:forEach var ="i" items ="${list}">
			   			<li id ="${i.empno}" class ="open"><img  style="border-radius: 50%; " width="46px"; height="46px" src ="${i.profilePath}">${i.ename}-${i.dname}</li>
						</c:forEach>
						</c:catch>
					<c:if test="${ex !=null}">
					익셉션이 발생하였습니다. :<br>
					${ex}
					</c:if>
            </ul>
            </div>
        </div>
        </div>

</body>
</html>