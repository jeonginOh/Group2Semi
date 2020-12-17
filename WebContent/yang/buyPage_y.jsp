<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>구매페이지</h1>
<br><br>
<div id="buywrap">
<div>
	<h3>구매목록정보</h3>
	<div>
	<br>
		<table>
			<tr>
				<th>상품정보</th>
				<th>상품금액</th>
			</tr>
			<c:forEach var="vo" items="${list }" varStatus="status">
			<tr>
				<td><div>${vo.itemname }</div><div id="detailInfo">${amountlist[status.index] }개/개 당${vo.price }원</div></td>
				<td>${vo.price*amountlist[status.index] }</td>
			</tr>
			</c:forEach>
		</table>
	</div>
</div>
<br>
<div>
	<h3>주문자 정보</h3>
	<table>
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
	<div>
		${mem.addr }
		<br>
		<input type="button" value="배송지변경">
	</div>
</div>
<br>
<c:if test="${mem.status==1 }">
<div>
	<h3>쿠폰 적용</h3>
	<div>
		<c:when test="${empty coup.coupid }">
			<span>쿠폰이 없습니다.</span>
		</c:when>
		<c:otherwise>
			${coup.context }
		</c:otherwise>
	</div>
</div>
</c:if>
</div>