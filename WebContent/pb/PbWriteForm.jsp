<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<link href="/semi/boot/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="/semi/ab/css/style.css" rel="stylesheet" type="text/css">
<link href="/semi/pb/css/pbWriteForm.css" rel="stylesheet">
 
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

	//카테고리, 제목,내용 공백처리
	$("#write").click(function(){
	var form = document.getElementById("writeForm");
	var pb_content = form.pb_content.value
	var pb_subject = form.pb_subject.value
			if(pb_subject==null || pb_subject==""){
	        alert("제목을 입력하세요.");
	        return false;
	        }        
	        if(pb_content==null ||pb_content=="" )
	        {   alert("내용을 입력하세요.");
	            return false;
	        } 	        
	});
});  
</script>


<div class="container"> 
	<ul class="nav nav-pills board"> 
 		<li role="presentation" id="프로젝트 현황" class="active"><a href="/semi/projectList.ls" >프로젝트 현황</a></li>
 		<li role="presentation" id="프로젝트 자료실" class="active"><a href="/semi/pbList.ls">프로젝트 자료실</a></li>
	</ul>
 
    <div class="row col-xs-12">
      <div class="row"> 

   <div class="row col-xs-2" style="width:250px;"></div>
   <form id="writeForm" action="pbWritePro.ls" method="post" enctype="multipart/form-data">
   <input type="hidden" name="empno" value="${memId.getEmpno()}">
   <input type="hidden" name="ename" value="${memId.getEname()}"> 

         <div class="col-xs-12 text-center"><h1>프로젝트 자료 올리기</h1></div> 
      </div>
      <div style="height:50px;"></div>
      <div style="height:3px;background-color:#18bcd7;"></div>
      
      <table class="table">
        <tr class="row">
         	<th class="col-xs-2 active">제목</th>
         	<td><input class="col-xs-12" name="pb_subject" placeholder="제목을 입력해주세요."></td> 
         </tr>
		 <tr class="row">
         	<th class="col-xs-2 active">자료 내용</th>
         	<td><textarea class="col-xs-12" name="pb_content" style="resize:none; height:200px;" placeholder="내용을 입력해주세요."></textarea></td>
         </tr> 
         <tr class="row" style="">
         	<th class="col-xs-2 active">첨부파일</th> 
<!--        <td>
         	<input class="col-xs-7" name="pb_file" placeholder="파일을 선택하시오"  readonly="readonly">
         	<input class="col-xs-5" type="file" name="pb_file" />
         	</td>    -->  
         
         			<td class="form-group">
					
					<input id="fileInput" name="pf_name" filestyle="" type="file" data-class-button="btn btn-default" data-class-input="form-control" data-button-text="" data-icon-name="fa fa-upload" class="form-control" tabindex="-1" style="position: absolute; clip: rect(0px 0px 0px 0px);">
					<div class="bootstrap-filestyle input-group">
					<input type="text" id="userfile" class="form-control" name="userfile" disabled="" placeholder="파일 선택">
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
      	<input type="hidden" name="pb_num" value="${pb_num}">
      	<%-- <input type="hidden" name="pb_count" value="${pb_count }"> --%>   
	    <input class="col-xs-offset-8 col-xs-2" id="write" type="submit" value="등록">  
	    <input class="col-xs-2" type="button" value="취소" onclick="window.location='/semi/pbList.ls'">   
		</div> 
		</form>    
<div style="height:150px;"></div>  
</div>