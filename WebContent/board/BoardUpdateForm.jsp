<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.js"></script>
<script src="/semi/board/plugins/summernote/lang/summernote-ko-KR.js"></script>
<script src="/semi/board/js/board.js"></script>

<script>

//업로드파일 삭제
function deleteFile(board_num){
	var msg = confirm("업로드된 파일을 정말 삭제하시겠습니까? \n 삭제된 파일은 복구가 불가능합니다.");    
	if(msg == true){ // 확인을 누를경우
		$.ajax({
			type: "post",
			url: "/semi/fileDelete.ls",
			data:{
			"board_num": board_num,
			},
			success: function(response){ // 통신 성공시 - 동적으로 좋아요 갯수 변경, 유저 목록 변경
		        $("#uploaded_file").detach();
		        $("#userfile").val("");
		    }
		});
	}else{
	return false; // 삭제취소
	}
}



$(document).ready(function(){
	
	if(${article.board_notice_top}==1){
	$('input:checkbox[name="board_notice_top"]').attr("checked", true);
	}
	
	$("#board_cat").val("${article.board_cat}").attr("selected", "selected");
	if($("#board_cat option:selected").val()!="0"){
		$("#cat_select").hide()
	}
	
	
	//공지상단등록 체크박스 기본 ; 숨김
	$("#cat_notice").hide();
	
	if($("#board_cat option:selected").val()=="공지사항"){
		$("#cat_notice").show()
	}

	//파일첨부
	$("#fileInput").on('change', function(){  // 값이 변경되면
		if(window.FileReader){  // modern browser
			var filename = $(this)[0].files[0].name;
		} else {  // old IE
			var filename = $(this).val().split('/').pop().split('\\').pop();  // 파일명만 추출
		}
		// 추출한 파일명 삽입
		$("#userfile").val(filename);
	});
	
	//카테고리, 제목,내용 공백처리
	$("#write").click(function(){
		var form = document.getElementById("update");
        var board_content = form.board_content.value
        var board_subject = form.board_subject.value
		var board_cat = form.board_cat.value
		if(board_subject==null || board_subject==""){
        	 alert("제목을 입력하세요.");
             return false;
        }
        
        if(board_content==null ||board_content=="" )
        {   alert("내용을 입력하세요.");
            return false;
        }
        
        if(board_cat=="0"){
        	alert("게시글 카테고리를 선택해주세요")
        	return false;
        }
	});
	
	//카테고리 변경시
	$("#board_cat").change(function() {
		var detachNode;
		//카테고리 선택 안했을경우
 		if($("#board_cat option:selected").val()=="0"){
			$("#cat_select").show();
		}
		//공지사항 선택했을경우
		if($("#board_cat option:selected").val()!="0"){
			$("#cat_select").hide()
		}
	});
	
	//공지사항 선택여부에 따라 공지 상단 등록 체크박스 활성화 및 숨김
	$("#board_cat").change(function() {
		console.log($("#board_cat option:selected").val());
		if($("#board_cat option:selected").val()=="공지사항"){
		$("#cat_notice").show();
		}
		if($("#board_cat option:selected").val()!="공지사항"){
		$("#cat_notice").hide();
		}
	});
	});
</script>


<div class="container" style="width:50%;">
<form id="update" name="update" action="/semi/updatePro.ls?" method="post" enctype="multipart/form-data">
	<input type="hidden" name="board_num" value="${article.board_num}">
	<h3>글 수정</h3>
	<table class="table board">
		<tr>
			<td class="content_th" style="width:10%; background-color:#FAFAFA">글번호</td>
			<!-- 공지인경우 라벨출력 -->
			<c:if test="${article.board_cat eq '공지사항' }">
			<td class="board_number" style="width:40%;"><span class="label label-info" >공지</span> ${article.board_num}</td>
			</c:if>
			<!-- 공지가 아닌 경우 글번호 출력 -->
			<c:if test="${article.board_cat ne '공지사항' }">
			<td style="width:40%;">${article.board_num}</td>
			</c:if>

			<td class="content_th" style="width:10%; background-color:#FAFAFA">조회수</td>
			<td class="board_readcount" style="width:40%;">${article.board_readcount}</td>
		</tr>
		<tr>
			<td class="content_th" style="width:10%; background-color:#FAFAFA">작성자</td>
			<td class="board_writer">${article.ename}</td>
			<td class="content_th" style="width:10%; background-color:#FAFAFA">작성일</td>
			<td class="board_time"><fmt:formatDate value="${article.board_date}" pattern="yyyy-MM-dd HH:mm" /></td>
		</tr>
		<tr>
			<!-- 카테고리 -->
			<td colspan="4">
			<select id="board_cat" name="board_cat" style="margin-bottom : 10px">
				<option value="0">카테고리</option>
				<option value="자유게시판">자유게시판</option>
				<optgroup label="부서별소식">
					<option value="부서별소식.인사팀">인사팀</option>
					<option value="부서별소식.영업팀">영업팀</option>
					<option value="부서별소식.홍보팀">홍보팀</option>
					<option value="부서별소식.기획팀">기획팀</option>
					<option value="부서별소식.개발팀">개발팀</option>
				</optgroup>
				<c:if test="${memId.getEmpno() == '1000' }">
				<option value="공지사항">공지사항</option>
				</c:if>
			</select>
			
		<!-- 카테고리 선택 경고 및 공지등록체크 -->
				
				<label id="cat_notice"><input type="checkbox" name="board_notice_top" value="1"> 공지 상단 등록</label>
				<span id="cat_select" style="color:red;">카테고리를 선택해주세요</span><br>
			
			<!-- 제목 -->
			<input type="text" class="form-control" maxlength="30" name="board_subject" value="${article.board_subject}">
			</td>
		</tr>
		
		<!-- 글 내용  -->
		<tr>
			<td colspan="4">
			<textarea class="summernote" name="board_content">${article.board_content}</textarea>
		
		<!-- 파일첨부 -->
		<div id="uploaded_file">
			<c:if test="${fileData.file_num ne null}">
			<label class="glyphicon glyphicon-floppy-disk"></label>&nbsp;
			<!-- 파일명 -->
			<a href="/semi/FileDownload.ls?board_num=${article.board_num }&file_name=${fileData.file_name}&file_save=${fileData.file_save}" title="${fileData.file_name}">
			<!-- 긴 파일이름 축소 -->
				<c:if test="${fn:length(fileData.file_name) > 16}">
    				<c:out value="${fn:substring(fileData.file_name,0,13)}" />...                    
				</c:if>
				<c:if test="${fn:length(fileData.file_name) <= 16}">
    				${fileData.file_name}
 				</c:if> 
			</a>
			<span class="board_time">(${CalcuSize})</span>
			<span class="board_time">/ 다운로드 수 : ${fileData.file_count}</span>
			
			<!-- 첨부파일 삭제 -->
			<a href="#" onclick='deleteFile(${article.board_num})' style="color:red; margin-left:25px; text-decoration:none;">삭제</a>
			</c:if>
		</div>
		<hr>
		<div class="form-group col-sm-12" style="margin-top:10px;">
				<c:if test="${fileData.file_num ne null}">
					<label for="InputSubject1">첨부파일 변경(기존 파일은 삭제됩니다.)</label>
				</c:if>
				
				<c:if test="${fileData.file_num eq null}">
					<label for="InputSubject1">파일첨부</label>
				</c:if>
				
				<input id="fileInput" name="file_name" filestyle="" type="file" data-class-button="btn btn-default" data-class-input="form-control" data-button-text="" data-icon-name="fa fa-upload" class="form-control" tabindex="-1" style="position: absolute; clip: rect(0px 0px 0px 0px);">
				<div class="bootstrap-filestyle input-group">
				<input type="text" id="userfile" class="form-control" name="userfile" disabled="" value="${fileData.file_name}">
				<span class="group-span-filestyle input-group-btn" tabindex="0">
				<label for="fileInput" class="btn btn-default ">
					<span class="glyphicon glyphicon-open"></span>
				</label>
				</span>
				
				</div>
					<p style="margin-top:8px;">허용용량 : 최대 20MB</p>
		</div>
			
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<input type="submit" class="btn btn-primary pull-right btn-sm" id="write" value="수정완료">
				<a class="btn btn-primary pull-left btn-sm" href="/semi/content.ls?board_num=${article.board_num}&pageNum=${pageNum}">취소</a>	
			</td>
		</tr>
	
	</table>
	
</form>
</div>	
