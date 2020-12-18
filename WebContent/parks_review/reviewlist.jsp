<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body align="center">
	<c:set var="cp" value="${pageContext.request.contextPath }" />
	<h1></h1>
	<h1>
		<c:set var="ave" value="${ave }" />
		<c:choose>
			<c:when test="${ave>4.6 }">
				<br>
				<h1>
					평균평점 : ${ ave} <br> ★★★★★ <br>
				</h1>
			</c:when>
			<c:when test="${ave>3.6 }">
				<br>
				<h1>
					평균평점 : ${ ave} <br> ★★★★ <br>
				</h1>
			</c:when>
			<c:when test="${ave>2.6 }">
				<br>
				<h1>
					평균평점 : ${ ave} <br> ★★★ <br>
				</h1>
			</c:when>
			<c:when test="${ave>1.6 }">
				<br>
				<h1>
					평균평점 : ${ ave} <br> ★★ <br>
				</h1>
			</c:when>
			<c:when test="${ave>0.6 }">
				<h1>
					<br> 평균평점 : ${ ave} <br> ★ <br>
				</h1>
			</c:when>
		</c:choose>
	</h1>
	<a
		href="${pageContext.request.contextPath }/parks_review/reviewlist1.jsp">물품리스트</a>
	<div id="box">
		<table style="table-layout: fixed;" border="1" bordercolor="red"
			width="1400" align="center">
			<tr>
				<th
					style="width: 7%; text-overflow: ellipsis; overflow: hidden; white-space: nowrap;">제목</th>
				<th
					style="width: 7%; text-overflow: ellipsis; overflow: hidden; white-space: nowrap;">내용</th>
				<th
					style="width: 7%; text-overflow: ellipsis; overflow: hidden; white-space: nowrap;">물품명</th>
				<th
					style="width: 10%; text-overflow: ellipsis; overflow: hidden; white-space: nowrap;">작성일</th>
				<th
					style="width: 10%; text-overflow: ellipsis; overflow: hidden; white-space: nowrap;">작성자</th>
				<th
					style="width: 400px; text-overflow: ellipsis; overflow: hidden; white-space: nowrap;">사진</th>
				<th
					style="width: 100px; text-overflow: ellipsis; overflow: hidden; white-space: nowrap;">유저평점</th>
			</tr>

			<c:forEach var="vo" items="${list }" varStatus="status">
				<tr>
					<td
						style="width: 12%; text-overflow: ellipsis; overflow: hidden; white-space: nowrap;">
						<a
						href="${cp}/reviewdetail?revid=${vo.revid}&itemid=${vo.itemid } ">${vo.title }</a>
					</td>

					<td
						style="width: 7%; text-overflow: ellipsis; overflow: hidden; white-space: nowrap;">${vo.context }</td>
					<td>${itemname }</td>
					<td>${vo.revdate }</td>
					<td>${username[status.index] }</td>
					<td><c:choose>
							<c:when test="${ vo.image==null }">
						등록된 사진이 없습니다.
						</c:when>
							<c:when test="${vo.image!=null }">
								<img src="fileFolder/${vo.image }" style="width: 200px;"
									onclick="imgup(event); imgout(event);" id="img1">
							</c:when>
						</c:choose></td>


					<td><c:choose>
							<c:when test="${vo.star>4.6 }">
								<br>
		평점 : ${ vo.star} <BR>
		★★★★★
		<br>
							</c:when>
							<c:when test="${vo.star>3.6 }">
								<br>
 	평점 : ${ vo.star}  
		<br>
		★★★★
		<br>

							</c:when>
							<c:when test="${vo.star>2.6 }">
								<br>
		평점 : ${ vo.star}  
		<br>
		★★★
		<br>

							</c:when>
							<c:when test="${vo.star>1.6 }">
								<br>
		평점 : ${ vo.star}  
		<br>
		★★
		<br>

							</c:when>
							<c:when test="${vo.star>0.6 }">
								<br>
		평점 : ${ vo.star}  
		<br>
		★
		<br>
							</c:when>

						</c:choose></td>
				</tr>

			</c:forEach>


		</table>
	</div>
	<c:if test="${startPageNum>10 }">
		<a
			href="${cp }/reviewlist2.do?pageNum=${startPageNum-1}&itemid=${itemid1}">[이전]</a>
	</c:if>
	<c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
		<a href="${cp }/reviewlist2.do?pageNum=${i}&itemid=${itemid1}">[${i }]</a>
	</c:forEach>
	<c:if test="${endPageNum<pageCount }">
		<a
			href="${cp }/reviewlist2.do?pageNum=${endPageNum+1}&itemid=${itemid1}">[다음]</a>
	</c:if>

	<script type="text/javascript">
		var cnt = 0;
		function imgup(e) {
			if (cnt == 0) {
				e.target.style.width = "500px";
				cnt++;
			} else {
				e.target.style.width = "200px";
				cnt--;
			}
		}
	</script>





</body>
</html>
