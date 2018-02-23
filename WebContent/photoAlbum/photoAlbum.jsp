<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!------ Include the above in your HEAD tag ---------->
<link rel="stylesheet" href="/semi/photoAlbum/photoAlbumCSS.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<script>
	$(function(){
		$("#photo").change(function(){
			$("#photoInsert").trigger("click");
		})
	})
</script>
<div class="container">
<div class="row col-xs">
	<form method="post" enctype="multipart/form-data"  action="/semi/photoAlbumWrite.ls">
		<ul class="nav nav-pills">
			<li><label for="photo" class="btn" style="background-color:#DFDFDF;">사진추가</label><input name="photo" id="photo" type="file" style="display:none;" ></li>
		</ul>
		<input type="submit" id="photoInsert" style="display:none">
	</form>
</div>
</div>
<section>
  <div class="container gal-container">
  	<c:set var="i" value="1" />
	<c:forEach var="list"  items="${photoList }">
	
		<c:if test="${i!=1 }">
			<div class="col-md-4 col-sm-12 co-xs-12 gal-item">
    			<div class="box">
    			    <a href="#" data-toggle="modal" data-target="#${list.num }">
           		    <img src="/semi/uploadPhotos/${list.fileName}">
       		        </a>
       	            <div class="modal fade" id="${list.num }" tabindex="-1" role="dialog">
                    	<div class="modal-dialog" role="document">
            				<div class="modal-content">
                				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
            					<div class="modal-body">
            					<img src="/semi/uploadPhotos/${list.fileName}">
            					</div>
            				</div>
         			    </div>
        		   </div>
         	   </div>
   		    </div>
		</c:if>
		 
		<c:if test="${i==1}">
			<div class="col-md-8 col-sm-12 co-xs-12 gal-item">
	    			<div class="box">
	    			    <a href="#" data-toggle="modal" data-target="#${list.num }">
	           		    <img src="/semi/uploadPhotos/${list.fileName}">
	       		        </a>
	       	            <div class="modal fade" id="${list.num }" tabindex="-1" role="dialog">
	                    	<div class="modal-dialog" role="document">
	            				<div class="modal-content">
	                				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
	            					<div class="modal-body">
	            					<img src="/semi/uploadPhotos/${list.fileName}">
	            					</div>
	            				</div>
	         			    </div>
	        		   </div>
	         	   </div>
	   		    </div>
   		 <c:set var="i" value="2"/>	
		</c:if>
	</c:forEach>
	
<!--     <div class="col-md-8 col-sm-12 co-xs-12 gal-item">
      <div class="box">
        <a href="#" data-toggle="modal" data-target="#abc">
          <img src="/semi/login/images/1111.jpg">
        </a>
        <div class="modal fade" id="abc" tabindex="-1" role="dialog">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
              <div class="modal-body">
                <img src="/semi/login/images/1111.jpg">
              </div>
            </div>
          </div>
        </div>
      </div>
    </div> -->

  </div>
</section>