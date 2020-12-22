<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div id="logidetailWrap">
<h1>상세주문리스트</h1>
<div>
	<span>회원번호</span>
	<span>${memvo.memid }</span>
	<input type="hidden" id="memid" value="${memvo.memid }">
</div>
<div>
	<span>아이디</span>
	<span>${memvo.id }</span>
</div>
<div style="border: 1px solid gray; width: 300px;">
<c:forEach var="vo" items="${logivo }" varStatus="status">
<div style="display: inline-block;">
	<span>물품</span>
	<span>${itemname[status.index] }/</span>
	<input type="hidden" value="${vo.itemid }">
</div>
<div style="display: inline-block;">
	<span>수량</span>
	<span>${buyvo[status.index].count }</span>
</div>
<div>
	<select name="logi">
		<option value="물품준비중"
		<c:if test="${vo.logiinfo=='물품준비중' }">selected</c:if>>물품준비중</option>
		<option value="발송완료"
		<c:if test="${vo.logiinfo=='발송완료' }">selected</c:if>>발송완료</option>
		<option value="배송완료"
		<c:if test="${vo.logiinfo=='배송완료'}">selected</c:if>>배송완료</option>
	</select>
	<input type="hidden" value="${vo.memid }">
	<input type="hidden" value="${vo.logiinfo }">
	<input type="button" value="변경" onclick="changeLogi(event)">
</div>
<br>
</c:forEach>
</div>
<div>
	<span>배송지주소</span>
	<span>${logivo[0].addr }</span>
</div>
<div>
	<span>배송상태</span>
	<span>${logivo[0].logiinfo }</span>
	<input type="hidden" id="logiinfo" value=${logivo[0].logiinfo }>
	<div id="takebackDiv" style="display: none;">
		<input type="button" value="반품처리" onclick="takeback()">
	</div>
</div>
<a href="<%=request.getContextPath()%>/admLogistic.do">뒤로가기</a>
</div>

<script type="text/javascript">
var logiinfo=document.getElementById("logiinfo").value;
var takebackDiv=document.getElementById("takebackDiv");
if(logiinfo=='배송완료'){
	takebackDiv.style.display="";
}
	function takeback(){
		if(confirm("반품처리 하시겠습니까?")){
			var memid=document.getElementById("memid").value;
			location.href="<%=request.getContextPath()%>/logiChange.do?elogiinfo=배송완료&memid="+memid+"&logiinfo=반품처리";
		}
	}
	
	function changeLogi(e){
		var elogiinfo=e.target.previousSibling.previousSibling.value;
		var memid=e.target.previousSibling.previousSibling.previousSibling.previousSibling.value;
		var logiinfo=e.target.previousSibling.previousSibling.previousSibling.previousSibling.previousSibling.previousSibling.value;
		var itemid=e.target.parentNode.previousSibling.previousSibling.previousSibling.previousSibling.childNodes[5].value;
		console.log(itemid);
		if(confirm("배송상태를 바꾸시겠습니까?")){
			if(elogiinfo=='배송완료' || elogiinfo=='반품처리'){
				alert("배송완료 또는 반품상태입니다");
			}else{
				location.href="<%=request.getContextPath()%>/logiChange.do?elogiinfo="+elogiinfo+"&memid="+memid+"&logiinfo="+logiinfo+"&itemid="+itemid;
			}
		}
	}
</script>