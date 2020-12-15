<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<c:set var="cp" value="${pageContext.request.contextPath }" />
	<h1>상품리뷰</h1>
	<a
		href="${pageContext.request.contextPath }/parks_review/reviewlist1.jsp">물품리스트</a>

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
	<!-- 리뷰평균 -->
	<div id="reviewstory"  style="width: 300px">
	<c:forEach var="vo" items="${list }" varStatus="status">
		<br>
		제목: ${vo.title }
		<br>
		내용: ${vo.context }
		<br>
		작성일:${vo.revdate }
		<br>작성자:${username[status.index] }
		<br>
		<img src="fileFolder/${vo.image }" style="width: 200px;"
			onclick="imgup(event); imgout(event);" id="img1">
		<br>
		<a href="${cp }/reviewdelete?revid=${vo.revid }&itemid=${vo.itemid}">삭제</a>
		<a href="${cp }/reviewupdate?revid=${vo.revid }&itemid=${vo.itemid}">수정</a>
		<c:choose>
			<c:when test="${vo.star>4.6 }">
				<br>
		평점 : ${ vo.star} 
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

		</c:choose>

	</c:forEach>
	</div>
	<div>
		<c:if test="${startPageNum>10 }">
			<a href="${cp }/board/list?pageNum=${startPageNum-1}">[이전]</a>
		</c:if>
		<c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
			<a href="${cp }/board/list?pageNum=${i}">[${i }]</a>
		</c:forEach>
		<c:if test="${endPageNum<pageCount }">
			<a href="${cp }/board/list?pageNum=${endPageNum+1}">[다음]</a>
		</c:if>


	</div>
	<script type="text/javascript">
	var cnt=0;
		function imgup(e) {
			if(cnt==0){
			e.target.style.width = "500px";
				cnt++;
			}else{
				e.target.style.width = "200px";
				cnt--;
			}
			
		}
		
		

	</script>





</body>
</html>