<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.js"></script>
<script src="/semi/board/plugins/summernote/lang/summernote-ko-KR.js"></script>
<script src="/semi/board/js/board.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	//공지상단등록 체크박스 기본 ; 숨김
	$("#cat_notice").hide();
	
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
		var form = document.getElementById("writeForm");
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
<form action="/semi/writePro.ls" id="writeForm" method="post" enctype="multipart/form-data">
<input type="hidden" name="board_num" value="${board_num}">
<input type="hidden" name="board_ref" value="${board_ref}">
<input type="hidden" name="board_re_seq" value="${board_re_seq}">
<input type="hidden" name="board_re_lev" value="${board_re_lev}">
<input type="hidden" name="empno" value="${memId.getEmpno()}">
	<h3>글쓰기</h3>
	<table class="table board">
		<tr>
			<td>
		<!-- 카테고리  -->
		<!-- 답글이 아닌경우 -->
		<c:if test="${board_num == 0}">
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
		</c:if>
		
		<!-- 답글인 경우 -->
			<c:if test="${board_num != 0}">
				<select name="board_cat" style="margin-bottom : 10px">
					<option value="${board_cat}">${board_cat}</option>	
				</select>
			</c:if>
		<!-- 글 제목  -->
			<c:if test="${board_num == 0}">
				<input type="text" class="form-control" maxlength="50" name="board_subject" placeholder="제목을 입력해주세요"><td>
			</c:if>
			<c:if test="${board_num != 0}">
  				<input type="text" class="form-control" maxlength="50" name="board_subject" value="[답변] "><td>
			</c:if>
			
		</tr>
		<!-- 내용부분 (summernote)-->
		<tr>		
			<td>
				<textarea class="summernote" name="board_content"></textarea>
			
			<!-- 파일첨부 -->
				<div class="form-group">
					<label for="InputSubject1">파일첨부</label>
					<input id="fileInput" name="file_name" filestyle="" type="file" data-class-button="btn btn-default" data-class-input="form-control" data-button-text="" data-icon-name="fa fa-upload" class="form-control" tabindex="-1" style="position: absolute; clip: rect(0px 0px 0px 0px);">
					<div class="bootstrap-filestyle input-group">
						<input type="text" id="userfile" class="form-control" name="userfile" disabled="" placeholder="파일 선택">
						<span class="group-span-filestyle input-group-btn" tabindex="0">
							<label for="fileInput" class="btn btn-default ">
								<span class="glyphicon glyphicon-open"></span>
							</label>
						</span>
					</div>
				<!-- 용량정보 -->
					<p style="margin:8px;">허용용량 : 최대 20MB</p>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<!-- 작성버튼 -->
				<input type="submit" id="write" class="btn btn-primary pull-right btn-sm" value="작성">
				<!-- 목록버튼 -->
				<a class="btn btn-primary pull-left btn-sm" href="/semi/list.ls">목록</a>
			</td>
		</tr>
	</table>
</form>
</div>
<br>

