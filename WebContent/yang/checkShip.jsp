<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="logiAdmWrap">
<h1>배송조회</h1>
<div>
	<h2>물품준비중</h2>
	<table style="width: 880px; border: 1px solid black; text-align: center;">
		<tr>
			<th>물품이름/개수</th>
			<th>배송지주소</th>
			<th>배송상태</th>
		</tr>
		<c:forEach var="vo" items="${logivo1 }" varStatus="status">
		<tr>
			<td>
				${itemname1[status.index] }/${buyvo1[status.index].count }
			</td>
			<td>${vo.addr }</td>
			<td>
				${vo.logiinfo }
				<input type="hidden" value="${vo.logiinfo }">
			</td>
		</tr>
		</c:forEach>
	</table>
</div>
<div>
	<h2>발송완료</h2>
	<table style="width: 880px; border: 1px solid black; text-align: center;">
		<tr>
			<th>물품이름/개수</th>
			<th>배송지주소</th>
			<th>배송상태</th>
		</tr>
		<c:forEach var="vo" items="${logivo2 }" varStatus="status">
		<tr>
			<td>
				${itemname2[status.index] }/${buyvo2[status.index].count }
			</td>
			<td>${vo.addr }</td>
			<td>
				${vo.logiinfo }
				<input type="hidden" value="${vo.logiinfo }">
			</td>
		</tr>
		</c:forEach>
	</table>
</div>
<div>
	<h2>배송완료</h2>
	<table style="width: 880px; border: 1px solid black; text-align: center;">
		<tr>
			<th>물품이름/개수</th>
			<th>배송지주소</th>
			<th>배송상태</th>
		</tr>
		<c:forEach var="vo" items="${logivo3 }" varStatus="status">
		<tr>
			<td>
				${itemname3[status.index] }/${buyvo3[status.index].count }
			</td>
			<td>${vo.addr }</td>
			<td>
				${vo.logiinfo }
				<input type="hidden" value="${vo.logiinfo }">
			</td>
		</tr>
		</c:forEach>
	</table>
</div>
<div>
	<h2>반품목록</h2>
	<table style="width: 880px; border: 1px solid black; text-align: center;">
		<tr>
			<th>물품이름/개수</th>
			<th>배송지주소</th>
		</tr>
		<c:forEach var="vo" items="${logivo4 }" varStatus="status">
		<tr>
			<td>
				${itemname4[status.index] }/${buyvo4[status.index].count }
			</td>
			<td>${vo.addr }</td>
		</tr>
		</c:forEach>
	</table>
</div>
</div>