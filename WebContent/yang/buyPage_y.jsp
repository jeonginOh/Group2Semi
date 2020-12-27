<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>구매페이지</h1>
<br><br>
<form method="post" action="<%=request.getContextPath() %>/buyItemsSave.yang.do">
<div id="buywrap" style="width: 950px;">
<div>
	<h3>구매목록정보</h3>
	<div>
	<br>
		<table style="width: 300px; text-align: center;">
			<tr>
				<th style="font-size: 1.4em;">상품정보</th>
				<th style="font-size: 1.4em;">상품금액</th>
			</tr>
			<c:forEach var="vo" items="${list }" varStatus="status">
			<tr>
				<td><div>${vo.itemname }</div><div id="detailInfo">${amountlist[status.index] }개/개 당${vo.price }원</div></td>
				<td>${vo.price*amountlist[status.index] }</td>
			</tr>
			<input type="hidden" name="item" value="${vo.itemid }">
			<input type="hidden" name="amount" value="${amountlist[status.index] }">
			<input type="hidden" name="itemname" value="${vo.itemname }">
			</c:forEach>
		</table>
	</div>
</div>
<div style="position: absolute; right: 650px; top: 350px; 
border: 2px solid black; width: 200px; height: 200px; text-align: center;">
	<h2>총 결제금액</h2>
	<div>
		<span style="font-size: 1.2em; line-height: 150px;">${totprice }원</span>
		<input type="hidden" name="totprice" value="${totprice }">
	</div>
</div>
<br>
<div>
	<h3>주문자 정보</h3>
	<table style="width: 300px; text-align: center;">
		<tr>
			<th>아이디</th>
			<td>
				${mem.id }
			</td>
		</tr>
		<tr>
			<th>휴대폰</th>
			<td>
				${mem.phone }
			</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>
				${mem.email }
			</td>
		</tr>
	</table>
</div>
<br>
<div>
	<h3>배송지 정보</h3>
	<div id="destination">
		${mem.addr }
	</div>
	<div>
		<input type="button" value="배송지 변경" onclick="changeDes()" id="changebtn">
		<div id="logiChange"></div>
	</div>
</div>
<br>
<c:if test="${mem.status==1 }">
<div>
	<h3>쿠폰 적용</h3>
	<div>
		<span>쿠폰이 없습니다.</span>
	</div>
</div>
</c:if>
<br>
<div>
	<h3>결제수단</h3>
	<input type="radio" name="paywith" value="현금결제" checked="checked">
	<span>현금결제</span>
	<input type="radio" name="paywith" value="카드결제">
	<span>카드결제</span>
</div>
<br>
<div>
	<input type="submit" value="결제하기">
</div>
</div>
	<input type="hidden" name="memid" value="${mem.memid }">
	<input type="hidden" name="addr" id="addr" value="${mem.addr }">
	<input type="hidden" name="totprice" value="${totprice }">
</form>

<script type="text/javascript">
var msg='${code}';
if(msg!=""){
	alert(msg);
}
function changeDes(){
	var logichange=document.getElementById("logiChange");
	var changebtn=document.getElementById("changebtn");
	var destination=document.createElement("input");
	changebtn.style.visibility="hidden";
	destination.setAttribute("id", "changeD");
	destination.type="text";
	destination.style.width="450px";
	destination.setAttribute("placeholder", "변경할 주소를 적어주세요");
	var change=document.createElement("input");
	change.type="button";
	change.value="변경하기";
	change.setAttribute("onclick", "commitDes()");
	logichange.appendChild(destination);
	logichange.appendChild(change);
}
function commitDes(){
	var des=document.getElementById("destination");
	var changeDes=document.getElementById("changeD");
	var addr=document.getElementById("addr");
	var changebtn=document.getElementById("changebtn");
	console.log(changeDes.value);
	if(changeDes.value==""){
		alert("주소를 입력해주세요.");
		return;
	}else{
		des.innerHTML=changeDes.value;
		addr.value=changeDes.value;
		changebtn.style.visibility="visible";
	}
	
	var logichange=document.getElementById("logiChange");
	while(logichange.firstChild){
		logichange.removeChild(logichange.lastChild);
	}
}
</script>