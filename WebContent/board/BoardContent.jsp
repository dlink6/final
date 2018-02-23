<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script type="text/javascript">

$(function () {
	  $('[data-toggle="tooltip"]').tooltip()
	})

//코멘트 삭제
function deleteCM(comment_num, empno, board_num, pageNum){
	var msg = confirm("코멘트를 삭제하시겠습니까?\n\n삭제된 글은 복구가 불가능합니다.");    
	if(msg == true){ // 확인을 누를경우
		/* var form = document.getElementById("deleteCommentForm");
		var board_num = form.board_num.value
		var comment_num= form.comment_num.value;
		var empno = form.empno.value; */
		$.ajax({
			type: "post",
			url: "/semi/cmDelete.ls",
			data:{
			"pageNum": pageNum,
			"board_num": board_num,
			"comment_num": comment_num,
			"empno" : empno
			}
		});
	}else{
	return false; // 삭제취소
	}
}

$(document).ready(function(){
	     //글삭제
	      $("#deleteArticle").click(function(){
	         var msg = confirm("글을 삭제하시겠습니까?\n\n삭제된 글은 복구가 불가능합니다.");    
	            if(msg == true){
	            }else{
	               return false;
	            }
	      });
		
		//코멘트 등록
		$("#writeCM").click(function(){
        	 var form = document.getElementById("writeCommentForm");
             var board_num = form.board_num.value
             var pageNum = form.pageNum.value
             var comment_num= form.comment_num.value;
             var empno = form.empno.value;
             if (comment_num == null || comment_num == ""){
            	 comment_num = 1;
             }
             
            //코멘트 줄바꿈 
             var content = form.comment_content.value;
             
             //태그방지!
             content = content.replace(/&/g, "&amp;");
             content = content.replace(/</g, "&lt;");
             content = content.replace(/>/g, "&gt;");
             content = content.replace(/\"/g, "&quot;");
             content = content.replace(/(\r\n|\n)/g, "<br />$1");
             
             if(!content)
             {   alert("내용을 입력하세요.");
                 return false;
             }else{

             $.ajax({
                type: "post",
                url: "/semi/cmWrite.ls",
                data:{
                "comment_content" : content,
                "board_num": board_num,
                "comment_num": comment_num,
                "pageNum" : pageNum,
                "empno" : empno
                	}
            });
             }
            });

		//코멘트 글자수 계산
		  $('#comment_content').keyup(function (e){
	          var content = $(this).val();
	          /* $(this).height(((content.split('\n').length + 1) * 1.5) + 'em'); */
	          $('#counter').html(content.length);
	      });
	      $('#comment_content').keyup();
		
      
	}); 
</script>

<div class="container" style="width:55%;">
<a href="/semi/list.ls" style="text-decoration:none;"><h3>게시판2222(top)</h3></a>
<table class="table board">
		<tr>
			<td class="content_th" style="width:10%; background-color:#FAFAFA"">글번호</td>
			<!-- 공지인경우 라벨출력 -->
			<c:if test="${article.board_cat eq '공지사항' }">
			<td style="width:40%;"><span class="label label-info" >공지</span></td>
			</c:if>
			<!-- 공지가 아닌 경우 글번호 출력 -->
			<c:if test="${article.board_cat ne '공지사항' }">
			<td style="width:40%;">${article.board_num}</td>
			</c:if>
			<td class="content_th" style="width:10%; background-color:#FAFAFA">조회수</td>
			<td class="board_readcount" style="width:40%;">${article.board_readcount}</td>
		</tr>
		<tr>
			<td class="content_th" style="width:10%; background-color:#FAFAFA"">작성자</td>
			<td class="board_writer"><span data-toggle="tooltip" data-placement="right" title="${article.dname}(${article.empno})">${article.ename}</span></td>
			<td class="content_th"  style="width:10%; background-color:#FAFAFA"">작성일</td>
			<td class="board_time"><fmt:formatDate value="${article.board_date}" pattern="yyyy.MM.dd HH:mm" /></td>
		</tr>
		<tr>
			<td class="content_th" style="width:10%; background-color:#FAFAFA">제목</td>
			<td colspan="3">${article.board_subject}</td>
		</tr>
		
		<!-- 본문 -->	
		<tr>
			<td colspan="4">
			<div style="margin:20px; margin-bottom:50px">
			<!-- 첨부파일이 이미지일경우 본문에 출력 -->
			<c:if test="${fileData.file_name ne null}">
			<c:set var="fileName" value="${fn:split(fileData.file_name, '.')}" />
			<c:if test="${fileName[fn:length(fileName)-1]=='png' or fileName[fn:length(fileName)-1]=='jpg' or fileName[fn:length(fileName)-1]=='jpeg' or fileName[fn:length(fileName)-1]=='gif'}">
				<img src='/semi/uploadFiles/${fileData.file_save}' class="img-responsive""><br>
			</c:if>
			</c:if>
				${article.board_content}
			</div>
		<!-- 수정일자 출력-->
			<c:if test="${article.board_mod_date ne null}">
			<div class="board_time" style="text-align:right;">
			<fmt:formatDate value="${article.board_mod_date}" pattern="yyyy.MM.dd HH:mm"/>에 최종수정 되었습니다.
			</div>
			</c:if>
			
		<!-- 첨부파일 -->
		<c:if test="${fileData.file_num ne null}">
		<br>
			<label class="glyphicon glyphicon-floppy-disk"></label> <span class="board_file">첨부파일 :</span>
			<a href="/semi/FileDownload.ls?board_num=${article.board_num}&file_name=${fileData.file_name}&file_save=${fileData.file_save}" title="${fileData.file_name}">
			<!-- 긴 파일이름 축소 -->
				<c:if test="${fn:length(fileData.file_name) > 16}">
    				<c:out value="${fn:substring(fileData.file_name,0,13)}" />...                    
				</c:if>
				<c:if test="${fn:length(fileData.file_name) <= 16}">
    				${fileData.file_name}
 				</c:if> 
			</a>
			<span class="board_time">(${CalcuSize})</span>
			<span class="board_time">다운로드 수 : ${fileData.file_count }</span>
		</c:if>
		
		<!-- (수정, 삭제) -->
			<c:if test="${memId.getEmpno() eq article.empno and memId.getEmpno() ne '1000'}">
				<a href="/semi/deletePro.ls?board_num=${article.board_num}" id="deleteArticle" class="btn btn-default pull-right btn-sm">글삭제</a>
				<a href="/semi/updateForm.ls?board_num=${article.board_num}" class="btn btn-default pull-right btn-sm">글수정</a>
				&nbsp;
			</c:if>
			<c:if test="${memId.getEmpno() eq article.empno and memId.getEmpno() eq '1000'}">
				<a href="/semi/deletePro.ls?board_num=${article.board_num}" id="deleteArticle" class="btn btn-default pull-right btn-sm">글삭제</a>
				<a href="/semi/updateForm.ls?board_num=${article.board_num}" class="btn btn-default pull-right btn-sm">글수정</a>
			</c:if>
			<c:if test="${memId.getEmpno() ne article.empno and memId.getEmpno() eq '1000'}">
				<a href="/semi/deletePro.ls?board_num=${article.board_num}" id="deleteArticle" class="btn btn-danger pull-right btn-sm">글삭제</a>
			</c:if>
			<hr>
			</td>
		</tr>
		<tr>
		
		</tr>
	</table>
	
	<span class="board_writer">&nbsp;댓글 <b style="color:red;">${comment_count}</b>&nbsp;<p class="glyphicon glyphicon-menu-down"></p></span>


	<!-- 코멘트 출력  -->
	<table class="table table-hover board">
		<!-- 코멘트가 없는경우 -->
		<c:if test="${comment_count == 0} }">
		<hr>
		<tr>
			<td>코멘트가 없습니다.</td>
		</tr>
		</c:if>
		
		
		<!-- 코멘트가 있는경우 -->
		<c:if test="${comment_count>0}">
		<c:forEach var="comment" items="${commentList }">
			<tr>
				<td>
				<div style="margin-top:2px; margin-left:10px">
					<div style="float:left">
			<!--코멘트 작성자 -->
					<label class="board_writer" id=name> 
					<span data-toggle="tooltip" data-placement="top" title="${comment.dname} (${comment.empno})">${comment.ename }</span>
					</label>&nbsp;&nbsp;&nbsp;
			<!--코멘트 시간  -->
					<span class="board_time"><fmt:formatDate value="${comment.comment_date}" pattern="yyyy.MM.dd HH:mm"/>&nbsp;</span>
					</div>
			<!-- 코멘트 삭제 -->
					<form method="post" id="deleteCommentForm">
					<%-- <input type=hidden name="comment_num" value="${comment.comment_num}">
					<input type=hidden name="empno" value="${memId.getEmpno()}">
					<input type=hidden name="board_num" value="${article.board_num}">
					<input type=hidden name="pageNum" value="${pageNum}"> --%>
			
			<!-- 세션값이 일치하는 경우에만 삭제버튼 표시 -->
					<c:if test="${memId.getEmpno() == comment.empno or memId.getEmpno()=='1000'}">
					
						<div style="float:right;">
							<button type="submit" onclick="deleteCM(${comment.comment_num},${memId.getEmpno()},${article.board_num},${pageNum});" class="btn btn-danger btn-xs">
								<span style="font-weight:bold; font-size:9px;">X</span>
							</button>&nbsp;&nbsp;&nbsp;
						</div>
					</c:if>
					</form>
				
		<!-- 코멘트 내용 -->
		<br><br>
				<div class="board_comment" style="margin-left:0px; margin-bottom:5px"><c:out value="${comment.comment_content}" escapeXml="false" /></div>
				</div>
				</td>	
			</tr>
		</c:forEach>
		</c:if>
	</table>
	
	<!-- 코멘트 작성  -->
	<form id="writeCommentForm" METHOD="POST">
		<input type="hidden" name="board_num" value="${article.board_num}">
		<input type="hidden" name="pageNum" value="${pageNum}">
		<input type="hidden" name="comment_num" value="${comment_num}">
		<input type="hidden" name="empno" value="${memId.getEmpno()}">
		<table class="table">
		<tr>
		<td>
			<div style="margin-top:10px;">
				<div class="col-md-10">
					<textarea class="form-control" id="comment_content" name="comment_content" rows="4" placeholder="코멘트를 입력해 주세요." maxlength="200" style="resize:none;"></textarea>
				</div>
				<div class="col-md-2">
					<button class="btn btn-lg btn-full" id="writeCM" style="font-size:1em;">작성</button>
					<div style="font-size:0.9em; margin-top:10%">&nbsp;<span id="counter" style="color:red;"></span>/200자 </div>
				</div>
			</div>
		
		</td>
		</tr>
		</table>
	</form>
	
	<!-- 하단 버튼(목록, 답글) -->
	<a class="btn btn-primary pull-left btn-sm" href="/semi/list.ls">목록</a>
	<c:if test="${article.board_cat ne '공지사항' }">
	<a href="/semi/writeForm.ls?board_num=${article.board_num}&board_ref=${article.board_ref}&board_re_seq=${article.board_re_seq}&board_re_lev=${article.board_re_lev}&board_cat=${article.board_cat}" class="btn btn-primary pull-right btn-sm">답글</a>
	</c:if>
	</div><br>
