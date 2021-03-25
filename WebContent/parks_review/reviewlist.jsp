<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<title>Insert title here</title>
<style type="text/css">
#leftconetnt{
height: 100%
}
#rightconetnt{
height: 100%
}
 a:visited { text-decoration:none;color:black;}
 
</style>
</head>

<body align="center">

	<c:set var="cp" value="${pageContext.request.contextPath }" />


		<c:set var="ave" value="${ave }" />
		<c:choose>
			<c:when test="${ave>4.6 }">
				<br>
				<h1>
				 <button type="button" class="btn btn-primary btn-lg">평균평점:${ ave}</button>  <br> ★★★★★ <br>
				</h1>
			</c:when>
			<c:when test="${ave>3.6 }">
				<br>
				<h1>
				<button type="button" class="btn btn-success btn-lg">평균평점:${ ave} </button><br> ★★★★ <br>
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
					<br> 평균평점 : ${ ave}$ <br> ★<br>
				</h1>
			</c:when>
		</c:choose>
	</h1>
		<c:if test="${startPageNum>10 }">
		<a
			href="${cp }/reviewlist2.do?pageNum=${startPageNum-1}&itemid=${itemid1}" style='color:red;'>[이전]</a>
	</c:if>
	<c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
	<c:choose>
	<c:when test="${pageNum==i }">
		<a href="${cp }/jeungIn/main.jsp?spage=/jeungIn/itemdetail.jsp?pageNum=${i}&itemid=${itemid1}" style='color:gray'>[${i }]</a>
	</c:when>
	<c:otherwise>
		<a href="${cp }/jeungIn/main.jsp?spage=/jeungIn/itemdetail.jsp?pageNum=${i}&itemid=${itemid1}" style='color:black'>[${i }]</a>
	</c:otherwise>
	</c:choose>

	</c:forEach>
	<c:if test="${endPageNum<pageCount }">
		<a
			href="${cp }/reviewlist2.do?pageNum=${endPageNum+1}&itemid=${itemid1}" style='color:red;'>[다음]</a>
	</c:if>
		<c:if test="${buycount >0}">
		<button type="button" class="btn btn-success"><a href="${cp }/reviewinsert.do?itemid=${itemid1 }">리뷰등록</a></button>
		</c:if>
		<a>${buycount[status.index]}</a>
	<div id="box" style='position: relative;'>
		<table style="table-layout: fixed; border:2px black solid; background-color:white;"   class="table table-striped"   
			width="900" align="center">
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
						style="width: 12%; text-overflow: ellipsis; overflow: hidden; white-space: nowrap;"  >
						<a
						href="${cp}/reviewdetail?revid=${vo.revid}&itemid=${vo.itemid } " style='color:blue;'>${vo.title }</a>
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
								<img src="<%=request.getContextPath() %>/fileFolder/${vo.image }" style="width: 200px; "
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
