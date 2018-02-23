<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" href="/semi/login/css/bootstrap.css">
<title>Insert title here</title>
</head>
<script src="https://code.jquery.com/jquery-3.2.1.min.js" ></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
var sel_file;

$(function(){
	$("#input_img").on("change",handleImgFileSelect);
});

function handleImgFileSelect(e){
	var files = e.target.files;
	var filesArr = Array.prototype.slice.call(files);
	
	filesArr.forEach(function(f){
		if(!f.type.match("image.*")){
			alert("확장자는 이미지 확장자만 가능합니다.");
			return ;
		}
		sel_file = f;
		
		var reader = new FileReader();
		reader.onload = function(e){
			$("#img").attr("src",e.target.result);
		}
		reader.readAsDataURL(f);
	});
}
</script>
<style>
<!--
.img_wrap{
	width:266px;
	margin-top:50px;
}
.img_wrap img{
max-width: 100%}


-->
</style>
<body>


 

  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-sm">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">프로필 사진 변경</h4>
        </div>
        <div class="modal-body">
          <div>
	<h2><b>이미지 미리보기</b></h2>
	<p class ="title"> 이미지 업로드</p>
	<input type ="file" id = "input_img"/>
</div>

<div>
	<div class ="img_wrap">
		<img  id ="img"/>
	</div>
</div>
          
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>
</body></html>