<%@ page contentType = "text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${check==1}"> 
<meta http-equiv="Refresh" content="0;url=/semi/pbList.ls"> 
</c:if>
<c:if test="${check==0}">
에러입니다 경고! 경고! 경고!
<br>
삭제를 실패하였습니다.
<a href="javascript:history.go(-1)">[글삭제 폼으로 돌아가기]</a>
</c:if>
    