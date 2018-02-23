<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="/semi/electronicApproval/css/listDetail.css" rel="stylesheet">
<style>
.approval_title{
font-family : 궁서;	

}


</style>
<script>
	$(function(){
		$("#list").click(function(){
			$(this).next().toggleClass("listShow")
		})
	})
</script>
<!-- <div class="container">
	<div class="row">
		<div ><h1 class="col-xs-12" style="font-size:50px;">결재함</h1></div>
	</div>
	<div class="row">
		<div class="col-xs-2" id="approvalTitleUnderline" style="border:1px solid black;width:350px;height:5px"></div>
	</div>
</div>
<div class="container" style="margin-top:10px; position:relative;" >
	<div class="row col-xs-2" style="border:1px solid black; background-color:orange; height:100%; position:absolute;">
		<div class="col-xs-12 btn" onclick="window.location='/semi/List.ls'"><h4 style="text-align:left;font-weight:600;">결재함</h4></div>
		<div class="col-xs-12 btn" id="list"><h4 style="text-align:left;font-weight:600;">결재 요청</h4></div>
		<div class="listShow">
			<a class="col-xs-12 btn" style="text-align:left; color:black;" href="/semi/requestForVacation.ls">&nbsp;&nbsp;-&nbsp;휴가신청서</a>
			<a class="col-xs-12 btn" style="text-align:left; color:black;" href="/semi/drafting.ls">&nbsp;&nbsp;-&nbsp;기안서</a>
			<a class="col-xs-12 btn" style="text-align:left; color:black;" href="/semi/spendingResolution.ls">&nbsp;&nbsp;-&nbsp;지출결의서</a>
			<a class="col-xs-12 btn" style="text-align:left; color:black;" href="/semi/resignation.ls">&nbsp;&nbsp;-&nbsp;사직서</a>
		</div>
	</div> -->
	<c:set var="list" value="${detailList}"/>
	<c:set var="dto" value="${list.get(0)}"/>
	<div class="container" style="width:60%">
	<div class="row" style="font-size:25px;">
		<div class="col-xs-2 text-center"  style="border:1px solid black">문서번호</div>
		<div class="col-xs-10 text-center" style="border:1px solid black">${dto.documentNum }</div>
	</div>
	<%
		String num_result = request.getParameter("num");
		int num = Integer.parseInt(num_result);
	%>
	<%if(num ==1){ //휴가신청서 %>
	<div class="row" style="border:1px solid black; ">
		<h2 class="col-xs-12 text-center approval_title">휴 가 신 청 서</h2>		
		<div class="col-xs-12" style="height:20px;"></div>
		
		<table class="table col-xs-offset-1 approvalListDetail">
			<tr class="row">
				<th class="active col-xs-2" >성 명</th>
				<th class="col-xs-3">${ename}</th>
				<th class="active col-xs-2" >소 속</th>
				<th class="col-xs-3">${dname}</th>
			</tr>
			<tr class="row">
				<th class="active col-xs-2" >직 위</th>
				<th class="col-xs-6" colspan="3">${dto.job}</th>
			</tr>
			<tr class="row">
				<th class="active col-xs-2" >휴가종류</th>
				<th class="col-xs-6" colspan="3">${dto.v_type }</th>
			</tr>
			<tr class="row">
				<th class="active col-xs-2" >휴가사유</th>
				<th class="col-xs-6" colspan="3">${dto.v_cause}</th>
			</tr>
			<tr class="row">
				<th class="active col-xs-2" style="line-height:50px;" rowspan="2" >휴가기간</th>
				<th class="col-xs-6" colspan="3">${dto.v_start }</th>
			</tr>
			<tr class="row">
				<th class="col-xs-6" colspan="3">${dto.v_end }</th>
			</tr>
			<tr class="row">
				<th class="active col-xs-2" >비상연락망</th>
				<th class="col-xs-6" colspan="3">${dto.v_emergencyNumber }</th>
			</tr>
			<tr class="row">
				<th class="active"style="line-height:184px;">기타사항</th>
				<th colspan="3" style="padding:0;"><textarea class="col-xs-12" style="resize:none; height:200px;" readonly="readonly"  >${dto.v_otherDetail}</textarea></th>
			</tr>
		</table>
	<%}else if(num ==2){%>
	<div class="row" style=" border:1px solid black; ">
		<h2 class="col-xs-12 text-center approval_title"><span class="glyphicon glyphicon-chevron-left"></span>기안서<span class="glyphicon glyphicon-chevron-right"></span></h2>
		<div class="col-xs-12" style="height:20px"></div>
		<table class="table col-xs-offset-1 approvalListDetail">
			<tr class="row">
				<th class="	col-xs-2 active">부서</th>
				<th class="col-xs-4" colspan="2"><div class="col-xs-12">${dname}</div></th>
				<th class="	col-xs-4 active" colspan="2" rowspan="2" style="line-height:65px;">구  분</th>
			</tr> 
			<tr class="row">
				<th class="active">성 명</th>
				<th colspan="2"><div class="col-xs-12">${ename }</div></th>
			
			</tr>
			<tr class="row">
				<th class="active">기 안 일</th>
				<th colspan="2">${dto.d_date}</th>
				<th colspan="2">
					<div class="col-xs-12">${dto.d_category }</div>
	
				</th>
			</tr>
			<tr class="row">
				<th class=" active">제 목</th>
				<th colspan="4"><div class="col-xs-12">${dto.d_subject }</div></th>
			</tr>
			<tr class="row">
				<th colspan="5" style="padding:0;"><textarea class="col-xs-12" style="height:400px; resize:none;" readonly="readonly">${dto.d_content }</textarea></th>
			</tr>
		</table> 
	<%}else if(num == 3){ %>
	<div class="row" style=" border:1px solid black; ">
		<div class="col-xs-12" style="height:20px;"></div>
		<h2 class="col-xs-12 text-center approval_title">지 출 결 의 서</h2>		
		<div class="col-xs-12" style="height:20px;"></div>
		
		<table class="table col-xs-offset-1 approvalListDetail">
			<tr class="row">
				<th class="	col-xs-1 active">영수자</th>
				<th class="col-xs-4" colspan="2">${ename}</th>
				<th class="	col-xs-1 active">부서</th>
				<th class="col-xs-4">${dname }</th>
			</tr>
			<tr class="row">
				<th class="active">발의</th>
				<th colspan="2">${dto.s_writeDate }</th>
				<th class=" active">구분</th>
				<th>${dto.s_category }</th>
			</tr>
			<tr class="row">
				<th class="active">지출</th>
				<th colspan="2">${dto.s_spendingDate }</th>
				<th class="active">결재</th>
				<th>${dto.a_checkDate}</th>
			</tr>
			<tr class="row">
				<th colspan="5" class="active">내 역</th>
			</tr>
			<tr class="row">
				<th class="active">월/일</th>
				<th class="active col-xs-2">적요</th>
				<th class="active col-xs-2">금액</th>
				<th class="active">수량</th>
				<th class="active">비고</th>
			</tr>
				
			<c:forEach var="s_dto" items="${s_list}">
					<tr class='row'>
						<th><div class="col-xs-12">${s_dto.s_day}</div></th>
						<th>${s_dto.s_goods }</th>
						<th>${s_dto.s_money }</th>
						<th>${s_dto.s_amount }</th>
						<th>${s_dto.s_content }</th>
					</tr>
				<c:set var="s_moneySum" value="${s_moneySum+s_dto.s_money}"/>
				<c:set var="s_amountSum" value="${s_amountSum +s_dto.s_amount }"/>
			</c:forEach>
			<tr class='row'>
				<th><div class="col-xs-12">&nbsp;</div></th>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
			</tr>
			<tr class="row">
				<td colspan="2" class="active" style="font-size:29px; padding:0;">합 계</td>
				<th>${s_moneySum}</th>
				<th>${s_amountSum }</th>
				<th></th>
			</tr>
		</table>
	
	<%}else if(num ==4){ %>
	<div class="row" style="border:1px solid black; ">
	<div class="col-xs-12" style="height:20px;"></div>
	<h2 class="col-xs-12 text-center approval_title">사  직  서</h2>	
	<div class="col-xs-12" style="height:20px;"></div>
	<table class="table col-xs-offset-1 approvalListDetail"> 
			<tr class="row">
				<th class="	col-xs-2 active">소 속</th>
				<th class="col-xs-3">${dname}</th>
				<th class="	col-xs-2 active">직 위</th> 
				<th class="col-xs-3">${dto.job }</th>
			</tr>
			<tr class="row"> 
				<th class="active">성 명</th>
				<th colspan="3" >${ename }</th>
			</tr>
			<tr class="row"> 
				<th class="active">주 소</th>
				<th colspan="3" >${dto.address }</th>
			</tr>
			<tr class="row"> 
				<th class="active">주민등록번호</th>
				<th colspan="3" >${dto.jumin1} - ${dto.jumin2 }</th>
			</tr>
			<tr class="row"> 
				<th class="active">입사년월일</th>
				<th colspan="3" >${dto.hireDate }</th>
			</tr>
			<tr class="row"> 
				<th class="active">희망사직일</th> 
				<th colspan="3" >${dto.endDate }</th>
			</tr>
			<tr class="row">
				<th colspan="4" style="padding:0;"><textarea class="col-xs-12" style="height:200px; resize:none;" readonly="readonly">${dto.r_cause }</textarea></th>
			</tr>
		</table>
	<%} %>	
		<div style="height:20px;"></div>
		<c:if test="${check == 1 }">
			<c:if test="${memId.empno == 1000 }">
				<div class="row">
					<div class="col-xs-2"></div>
					<input type="button" style="height:50px;margin-left:70px;" class=" col-xs-3" value="승인" onclick="window.location='/semi/approvalCheck.ls?check=승인&doc=${dto.documentNum}&type=<%=num%>&ename=${ename }'">
					<input type="button" style="height:50px;margin-left:30px;" class=" col-xs-3" value="거절" onclick="window.location='/semi/approvalCheck.ls?check=거절&doc=${dto.documentNum }&type=<%=num%>&ename=${ename }'">
				</div>
			</c:if>
			<c:if test="${memId.empno != 1000 }">
				<div class="row">
					<div class="col-xs-4"></div>
					<input type="button" style="height:50px;margin-left:30px;" class=" col-xs-offset-4 col-xs-3" value="취소" onclick="window.location='/semi/approvalCheck.ls?check=취소&doc=${dto.documentNum}&type=<%=num%>&ename=${ename }'">
				</div>
			</c:if>
		</c:if>
		<c:if test="${check==3 || check==4}">
			<h1 class="text-center">결재가 완료되었습니다.</h1>
		</c:if>
		<c:if test="${check ==2 }">
			<h1 class="text-center">취소 되었습니다.</h1> 
		</c:if>
		<div style="height:30px;"></div>
	</div>
</div>
</div>
</div>
		
</div>
</div>