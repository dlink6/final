<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="check" value="${check}"/>
<c:choose>
	<c:when test="${check == 1 }">
	
	<script type="text/javascript">
	alert("정보가 수정되었습니다.");
	</script>
	</c:when>
	<c:when test="${check == 0 }"><!-- 아이디가 없음 -->
	<script type="text/javascript">
	alert("오류가 발생하였습니다.")
	</script>
	</c:when>
	<c:when test="${check == -1 }"><!-- 비밀번호 틀림  -->
	<script type="text/javascript">
	alert("오류가 발생하였습니다.")
	</script>
	</c:when>
</c:choose>
<script type="text/javascript">
window.location.href="/semi/Main.ls";
</script>
 
