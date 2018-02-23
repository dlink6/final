<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" , initial-scale="1" />
<link rel="stylesheet" href="/semi/tiles/css/chatCSS/scrol.css">
<link rel="stylesheet" href="/semi/tiles/css/chatCSS/nav.css">
<link rel="stylesheet" href="/semi/tiles/css/chatCSS/ListEx.css">
<link rel="stylesheet" href="/semi/tiles/css/chatCSS/addChat.css">
<link rel="stylesheet" href="/semi/tiles/css/chatCSS/chat.css">
<script type="text/javascript">
	var searchRoomRequest = new XMLHttpRequest();
	var modalRequest = new XMLHttpRequest();
	var searchRequest = new XMLHttpRequest();

	function searchFunction() {
		var username = document.getElementById("userName").value;
		/*  프로필 사진 사원번호 사원 부서 번호(매개변수 있을떄) 사원 이름 */
		searchRequest.open("Post",
				"./empSearchSevlet?userName="
						+ encodeURIComponent(document
								.getElementById("userName").value), true);
		searchRequest.onreadystatechange = searchProcess;
		searchRequest.send(null);
	}
	function searchProcess() {
		var dept1 = document.getElementById("ajaxTable10");
		dept1.innerHTML = "";
		var dept2 = document.getElementById("ajaxTable20");
		dept2.innerHTML = "";
		var dept3 = document.getElementById("ajaxTable30");
		dept3.innerHTML = "";
		var dept4 = document.getElementById("ajaxTable40");
		dept4.innerHTML = "";
		var dept5 = document.getElementById("ajaxTable50");
		dept5.innerHTML = "";

		if (searchRequest.readyState == 4 && searchRequest.status == 200) {
			var object = eval('(' + searchRequest.responseText + ')');
			var result = object.result;

			for (var i = 0; i < result.length; i++) {
				var deptno = result[i][2].value;
				var table = document.getElementById("ajaxTable" + deptno);
				var row = table.insertRow(0);
				var cell = row.insertCell(0);
				cell.innerHTML = '<div class="row sideBar-body">'
						+ '<div class="col-sm-3 col-xs-3 sideBar-avatar">'
						+ '<div class="avatar-icon">'
						+ '<a class="dodo" data-target="#modo" data-toggle="modal" id="'+result[i][3].value+'" title="'+result[i][1].value+'">'
						+ '<img src="'+result[i][3].value+'"/>'
						+ '</a>'
						+ '</div>'
						+ '</div>'
						+ '<div class="col-sm-9 col-xs-9 sideBar-main">'
						+ '<div class="row">'
						+ '<div class="col-sm-8 col-xs-8 sideBar-name open1" id ="'+result[i][0].value+'">'
						+ '<span class="name-meta">' + result[i][1].value
						+ '</span>' + '</div>' + '</div>' + '</div>'
						+ '<div><span style ="color:#4091d6;">사원</span></div>'
						+ '</div>';
			}
		}
	}
	/*------------2번쨰 대화방 ------------------  *///0참가자 1참가인원 2방이름 3방번호  ,,// 4.해당방의 안읽은 채팅의 개수 ,
	function searchRoom() {
		searchRoomRequest
				.open(
						"Post", //session아이디 값의 의해서 가져옴
						"./RoomSearchSevlet?searchword="
								+ encodeURIComponent(document
										.getElementById("roomName").value/* +"&memID ="+${memID} */),
						true);
		searchRoomRequest.onreadystatechange = searchProcess2;
		searchRoomRequest.send(null);
	}
	function searchProcess2() {
		var table = document.getElementById("ajaxRoom");
		if (searchRoomRequest.readyState == 4
				&& searchRoomRequest.status == 200) {
			var object = eval('(' + searchRoomRequest.responseText + ')');
			var result = object.result;
			table.innerHTML = ""
			for (var i = 0; i < result.length; i++) {
				if (result[i][3].value == "null") {
					result[i][3].value = "";
				}
				var row = table.insertRow(0);//0 				1 			2		3		4			5			6
				var cell = row.insertCell(0);//자기자신() , 참가 인원 , 방이름 , 시간 , 방번호, 안읽은 개수 , 참가자들
				cell.innerHTML = '<div class="row sideBar-body openRoom" id ="'+result[i][4].value+'/'+result[i][6].value+'/'+result[i][1].value+'">'
						+ // 방번호
						'<div class="col-sm-9 col-xs-9 sideBar-main">'
						+ //방번호 ,, 참가자들 ,, 참가인원
						'<div class="row">'
						+ '<div class="col-sm-8 col-xs-8 sideBar-name">'
						+ '<span class="name-meta">'
						+ result[i][2].value
						+ '('
						+ result[i][1].value
						+ ')'
						+ '<span class="badge" style="margin-left: 14px; display: inherit; background-color:red">'
						+ result[i][5].value
						+ '</span></span>'
						+ '</div>'
						+ //방이름 // 참가인원																																																//안읽은 개수														
						'</div>'
						+ '<div class ="row">'
						+ '<div class="sideBar-time">'
						+ '<span class="time-meta pull-right">'
						+ result[i][3].value + //시간
						'</span>' + '</div>' + '</div>' + '</div>' + '</div>';
			}
		}
	}
	/* --4번쨰 모달 부분-------------------- */
	function modalFunction() {
		modalRequest.open("Post",
				"./empSearchSevlet?userName="
						+ encodeURIComponent(document
								.getElementById("recipient-name").value), true);
		modalRequest.onreadystatechange = modalProcess;
		modalRequest.send(null);
	}
	function modalProcess() {
		var added = document.getElementById("modalTable");
		if (modalRequest.readyState == 4 && modalRequest.status == 200) {
			var object = eval('(' + modalRequest.responseText + ')');
			var result = object.result;

			added.innerHTML = "";
			for (var i = 0; i < result.length; i++) {
				var row = added.insertRow(0); //0 empno  1ename  result[i][0].value  (부서이름을 가져올까 생각중)
				row.innerHTML = '<td style="width: 366px">'
						+ '<div>'
						+ '<input type="checkbox" name="fancy-checkbox-success-custom-icons" id="'+result[i][0].value+'" autocomplete="off" value ="'+result[i][1].value+'"/>'
						+ '<div class="[ btn-group ]"  style="display: -webkit-inline-box;" >'
						+ '<label id = "'+result[i][0].value+'" for="'+result[i][0].value+'" class="[ btn btn-default active ]" value = "'+result[i][1].value+'">'
						+ '<div class="row" style="margin : 0px;">'
						+ '<div class="col-sm-3 col-xs-3 sideBar-avatar">'
						+ '<div class="avatar-icon">'
						+ '<img src="'+result[i][3].value+'">'
						+ '</div>'
						+ '</div>'
						+ '<div class="col-sm-9 col-xs-9 sideBar-main">'
						+ '<div class="row">'
						+ '<div class="col-sm-8 col-xs-8 sideBar-name">'
						+ '<p>'
						+ result[i][1].value
						+ '</p>'
						+ '</div>'
						+ '<div class="col-sm-4 col-xs-4 pull-right sideBar-time">'
						+ '<p class="time-meta pull-right">사원'
						+ '<p>'
						+ '</div>'
						+ '</div>'
						+ '</div>'
						+ '</div>'
						+ '</label>'
						+ '<label value = "'+result[i][1].value+'" id = "'+result[i][0].value+'"for="'+result[i][0].value+'" class="[ btn btn-success ]" style="line-height: 2;  font-size: 28px;">'
						+ '<span class="[ glyphicon glyphicon-plus ]"></span>'
						+ '<span class="[ glyphicon glyphicon-plus ]"></span>'
						+ '</label>' + '</div>' + '</div>' + '</td>';
			}
		}
	}
	$(function() {
		$(document).on("click", ".dodo", function() {
			$("#imagepreviews").attr("src", $(this).attr("id"))
			$("#text").text($(this).attr("title"))
		})
	})
	$(function() {
		$("#flower").click(function() {
			$("#margin:not(:animated)").animate({
				marginLeft : "-0px"
			}, "fast", "swing");
		});
		$("#building").click(function() {
			$("#margin:not(:animated)").animate({
				marginLeft : "-200px"
			}, "fast", "swing");
		});
		$("#so2").click(function() {
			$("#margin:not(:animated)").animate({
				marginLeft : "-400px"
			}, "fast", "swing");
		});

		$(document).on(
				"click",
				".open1",
				function() {
					window.open('/semi/chat.ls?participant='
							+ $(this).attr("id"), '',
							'width=400 height=548 top='
									+ Math.floor(Math.random() * 500)
									+ ', left='
									+ Math.floor(Math.random() * 500)
									+ 'resize = "no"');
				});
		$(document).on(
				"click",
				".openRoom",
				function() {
					window.open('/semi/room.ls?room=' + $(this).attr("id"), '',
							'width=400 height=548 top='
									+ Math.floor(Math.random() * 500)
									+ ', left='
									+ Math.floor(Math.random() * 500)
									+ 'resize = "no"');
				});
		$(document).on(
				"click",
				"#modalForm",
				function() {
					var count = $('.addee').length;
					if (count == 0) {
						alert("대화할 상대를 선택하시세요.");
						return false;
					}
					var title = document.getElementById("title").value;
					if (title == "") {
						alert("대화명을 입력하세여");
						return false;
					}
					if (title != "" && count != 0) {
						var s = "";
						$('.addee').each(function(index) {
							if (index == 0) {
								s += $(this).attr("id");
							}
							if (index != 0) {
								s += "," + $(this).attr("id");
							}
						});
						window.open("/semi/createRoom.ls?participants=" + s
								+ '&roomname=' + title + '&personNo=' + count,
								'', 'width=400 height=548  top='
										+ Math.floor(Math.random() * 500)
										+ ', left='
										+ Math.floor(Math.random() * 500)
										+ 'resize = "no"');
						/* $('#exampleModal').modal('hide'); */
						location.reload();
					}

				})
		$("#menu-toggle").click(function(e) {
			$("#ajaxTable10").empty();
			$("#ajaxTable20").empty();
			$("#ajaxTable30").empty();
			$("#ajaxTable40").empty();
			$("#ajaxTable50").empty();
			e.preventDefault();
			$("#wrapper").toggleClass("toggled");
		});
		$(function() {
			$(".menu").click(function() {
				$(this).closest("thead").next().slideToggle("fast");
				return false;
			});
		});
		$("#allList").click(function(e) {
			e.preventDefault();
			$("#ajaxTableList").slideToggle("fast")
		})

		$("#userName").focus(function() {
			searchFunction();
		})

		var names = [];
		var id = [];
		var uniqueNames = [];
		var uniqueId = [];

		$(document)
				.on(
						"click",
						"label",
						function() {
							names.push($(this).attr("value"));
							id.push($(this).attr("id"));
							var s = $(this).attr("value");
							var y = $(this).attr("id");

							$(".addee").each(function() {
								if ($(this).attr("id") == y) {
									$(this).remove();
								}
							});

							$("#added")
									.append(
											"<span class = 'addee' id ="+y+"  value ='"+s+"'><a class ='label label-success added' >"
													+ s
													+ "</a><span class='glyphicon glyphicon-remove'></span><br><br></span>");

						});
		$(document).on("click", ".addee", function() {
			$(this).remove();

		})

	});
	window.onload = function() {
		modalFunction();
		searchRoom();
	}
</script>

<style>
<!--
li {
	border: none !important;
}

.msg td,tr{
	border: none !important;
}

.menu{
	color: #ffa686 !important;
}

.dodo:hover {
	background-color: white !important;
}

.dodo:focus {
	background-color: white !important;
}

.imaget:hover {
	background-color: white !important;
}
-->
</style>
<aside>
	<div id="wrapper" class="toggled">
		<!-- Sidebar -->
		<div id="sidebar-wrapper"
			class="form-dark scrollbar-dusty-grass thin square">
			<ul class="sidebar-nav" style="margin-left: 0;">
				<li class="sidebar-brand"><a href="#menu-toggle"
					id="menu-toggle" style="margin-top: 20px; float: right;"><span>인트라넷메신저</span>
						<i class="glyphicon glyphicon-off"
						style="font-size: 20px !Important;" aria-hidden="true"></i></a></li>
				<li class="sidebar-brand" style="display: inline-flex;"><a
					style="margin-right: -20px;"> <i
						class="glyphicon glyphicon-user"
						style="font-size: 20px !Important; padding: 10px; padding-right: 36px;"
						aria-hidden="true" id="flower"> </i></a> <a> <i
						class="glyphicon glyphicon-envelope"
						style="font-size: 20px !Important; padding: 10px;"
						aria-hidden="true" id="building"></i></a></li>
				<!--                 검색창                     -->
				<li>
					<div id="margin" style="display: inline-flex;">
						<div id="building" style="width: 200px; margin-left: -16px;"
							class="bb">
							<table class="table msg"
								style="text-align: center; border: none; color: green;">
								<thead>
									<tr>  
										<td>
											<div class="row sideBar-body">
												<div class="col-sm-3 col-xs-3 sideBar-avatar">
													<div class="avatar-icon">
														<a class="dodo" data-target="#modo" data-toggle="modal"
															id="${memId.profilePath}" title="${memId.ename}"> <img
															class="imaget" src="${memId.profilePath}">
														</a>
													</div>
												</div>
												<div class="col-sm-9 col-xs-9 sideBar-main">
													<div class="row">
														<div class="col-sm-8 col-xs-8 sideBar-name">
															<span class="name-meta">${memId.ename }(나)</span>
														</div>
													</div>
												</div>
												<span style="color: #4091d6;">사원</span>
											</div>
										</td>
									</tr>
								</thead>
								<thead>
									<tr>
										<td>
											<!-- 부서별 사원 출력하는 공간 --> <input type="text"
											placeholder="Search"
											style="width: 140px; height: 30px; background-color: #00c73c; opacity: 0.5; border: none;"
											id="userName" onkeyup="searchFunction()">
										</td>
									</tr>

									<tr class="menu">
										<td style="cursor: pointer;">인사팀</td>
									</tr>
								</thead>
								<tbody id="ajaxTable10" class="deptno">
								</tbody>
								<thead>
									<tr class="menu">
										<td style="cursor: pointer;">영업팀</td>
									</tr>
								</thead>
								<tbody id="ajaxTable20" class="deptno">
								</tbody>
								<thead>
									<tr class="menu">
										<td style="cursor: pointer;">개발팀</td>
									</tr>
								</thead>
								<tbody id="ajaxTable30" class="deptno">
								</tbody>
								<thead>
									<tr class="menu">
										<td style="cursor: pointer;">홍보팀</td>
									</tr>
								</thead>
								<tbody id="ajaxTable40" class="deptno">
								</tbody>
								<thead>
									<tr class="menu">
										<td style="cursor: pointer;">기획팀</td>
									</tr>
								</thead>
								<tbody id="ajaxTable50" class="deptno">
								</tbody>
							</table>
						</div>
						<div id="flower" style="width: 200px" class="bb">
							<table class="table msg"
								style="text-align: center; border: none; color: green; overflow: hidden;">
								<thead>
									<tr>
										<td><input type="text" placeholder="방이름 or 참여자 검색"
											style="width: 140px; height: 30px; background-color: #00c73c; opacity: 0.5; border: none;"
											id="roomName" onkeyup="searchRoom()"></td>
									</tr>
									<tr>
										<td><span>
												<button type="button" class="btn btn-primary"
													data-toggle="modal" data-target="#exampleModal"
													data-whatever="@mdo">새로운 채팅</button>
												<button class="glyphicon glyphicon-repeat"
													style="width: 40px; height: 40px;" onclick="searchRoom()"></button>
										</span></td>
									</tr>
								</thead>
								<tbody id="ajaxRoom">
								</tbody>
							</table>

						</div>
					</div>
				</li>
			</ul>
		</div>
	</div>

	<!-- modal -->

	<form action="test.jsp">
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content" style="width: 489px;">
					<div class="modal-header" style="padding: 10px;">

						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="exampleModalLabel">대화방 생성</h4>
					</div>
					<div class="row" style="text-align: center;">
						<div class="modal-body col-sm-9">
							<div class="[ form-group ]" style="display: -webkit-inline-box;">
								<input type="text" class="form-control" style="width: 300px"
									id="title" name="roomname" placeholder="대화방 제목 입력">
							</div>
						</div>
					</div>
					<div class="modal-body col-sm-7" style="height: 400px;">
						<div style="overflow: auto;">
							<div class="form-group">
								<input type="text" class="form-control" id="recipient-name"
									placeholder="이름 검색" onkeyup="modalFunction()">
							</div>
							<div class="form-group">
								<label class="control-label">사원 :</label>
								<hr style="margin-top: 0px; margin-bottom: 0px">
								<br>
								<div style="height: 300px; overflow: auto;">
									<table id="modalTable">
										<!-- modal 사원 list 가져 오는 곳 -->

									</table>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-body col-sm-5"
						style="border-left: 1px solid #e5e5e5; height: 400px; float: right; overflow: hidden; overflow-y: auto;"
						id="added"></div>
					<div class="modal-footer" style="border-top: 1px solid #e5e5e5;">
						<button type="button" class="btn btn-default pull-right"
							data-dismiss="modal">취소</button>
						<button type="button" id="modalForm"
							class="btn btn-primary pull-right" data-dismiss=""
							style="margin-right: 14px;">확인</button>
					</div>
				</div>
			</div>
		</div>



		<div class="row">
			<div class="modal" id="modo" tabindex="-1">
				<div class="modal-dialog">
					<div class="modal-content"
						style="border: none; width: 600px; height: 600px;">
						<div class="modal-header"
							style="background-color: black; padding: 15px; border-bottom: none; min-height: 16.42857px; color: white; text-align: center;">
							<span id="text"></span>
							<button class="close" data-dismiss="modal"
								style="color: #f6f6f6; font-size: 33px;">&times;</button>
						</div>
						<div class="modal-body"
							style="text-align: center; background-color: black; padding-top: 0px;">
							<img alt="이미지" src="" id="imagepreviews"
								style="width: 100%; height: 100%; padding-right: 33px; padding-left: 33px; padding-bottom: 33px;">
						</div>
					</div>
				</div>
			</div>
		</div>

	</form>
</aside>