<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<link href="/semi/boot/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="/semi/ab/css/style.css" rel="stylesheet" type="text/css">
<link href="/semi/pb/css/PbUpdateForm.css" rel="stylesheet" type="text/css">
<script>
$(document).ready(function(){	
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
});   
</script>  
   
   <form action="/semi/pbUpdatePro.ls" method="post" enctype="multipart/form-data">  
<div class="container">  
	<ul class="nav nav-pills board">
 		<li role="presentation" id="프로젝트 현황" class="active"><a href="/semi/projectList.ls" >프로젝트 현황</a></li>
 		<li role="presentation" id="프로젝트 자료실" class="active"><a href="/semi/pbList.ls">프로젝트 자료실</a></li>
	</ul>
 
    <div class="row col-xs-12">
      <div class="row"> 
   <div class="row col-xs-2" style="width:250px;"></div>
   <input type="hidden" name="pb_num" value="${article.pb_num }">
         <div class="col-xs-12 text-center"><h1>수정 페이지</h1></div> 
      </div>
      <div style="height:50px;"></div>
      <div style="height:3px;background-color:#18bcd7;"></div>
      
      <table class="table">
         <tr class="row">
         	<th class="col-xs-2 active">제목</th>
         	<td><input class="col-xs-12" name="pb_subject" value="${article.pb_subject}"></td> 
         </tr>
		 <tr class="row">
         	<th class="col-xs-2 active">진행 내용</th>
         	<td><textarea class="col-xs-12" name="pb_content" style="resize:none; height:200px;">${article.pb_content}</textarea></td>
         </tr> 

         <tr class="row" style="">
         	<th class="col-xs-2 active">첨부파일</th>
         	        <td class="form-group"> 			
					<input id="fileInput" name="pf_name" filestyle="" type="file" data-class-button="btn btn-default" data-class-input="form-control" data-button-text="" data-icon-name="fa fa-upload" class="form-control" tabindex="-1" style="position: absolute; clip: rect(0px 0px 0px 0px);">
					<div class="bootstrap-filestyle input-group">
					<input type="text" id="userfile" class="form-control" name="userfile" disabled="" value="${fileData.pf_name}"> 
						<span class="group-span-filestyle input-group-btn" tabindex="0">
							<label for="fileInput" class="btn btn-default ">
								<span class="glyphicon glyphicon-open"></span> 
							</label>
						</span>
					</div>
				<!-- 용량정보 --> 
				<p style="margin:8px;">허용용량 : 최대 50MB</p>
				</td>   
         </tr>  
      </table> 
      	
	      <input class="col-xs-offset-8 col-xs-2" type="submit" value="수정"> 
	      <input class="col-xs-2" type="button" value="취소" onclick="window.location='/semi/pbContent.ls?pb_num=${article.pb_num}'">  
		</div>      
		</div>
		<div style="height:150px;"></div>
		</form>
