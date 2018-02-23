<%@ page contentType = "text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${check==1}">
<meta http-equiv="Refresh" content="0;url=/semi/list.ls" >
</c:if>
<c:if test="${check==0}">

에러
<br>
삭제실패
<a href="javascript:history.go(-1)">[글삭제 폼으로 돌아가기]</a>
</c:if>
