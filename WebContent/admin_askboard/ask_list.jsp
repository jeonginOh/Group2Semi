<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body >
	<c:set var="cp" value="${pageContext.request.contextPath }" />
	<h1 align="center">문의게시판</h1>	<jsp:include page="FAQ.jsp"/> <br><br>
	<table align="center" style="width:1000;" >
		<tr >
			<th>no.</th>
			<th>카테고리</th>
			<th>사진</th>
			<th>제목</th>
			<th>작성자</th>
			<th>날짜</th>
		</tr>
		<c:forEach var="vo" items="${list }" varStatus="status">
			<tr>
				<td style="width: 30px;" align="center">${vo.askid }</td>
				<td style="width: 200px;" align="center">
				<c:choose>
				<c:when test="${vo.askcat==1 }">
				상품문의
				</c:when>
				<c:when test="${vo.askcat==2 }">
				배송문의
				</c:when>
				<c:when test="${vo.askcat==3 }">
				환불문의
				</c:when>
				<c:when test="${vo.askcat==4 }">
				기타문의
				</c:when>
				</c:choose>
				</td>
				<c:choose>
					<c:when test="${vo.image==null }">
				<td style="width: 150px;" align="center"><img src="${cp }/fileFolder/base.jpg" style="width:150px"></td>
					</c:when>
					<c:when test="${vo.image!=null }">
				<td style="width: 150px;" align="center"><img src="${cp }/fileFolder/${vo.image }" style="width:150px"></td>
				</c:when>
				</c:choose>
				<td style="width: 300px;" align="center"><a href="${cp}/ask_detail?askid=${vo.askid}">${vo.title }</a><br>
				
				<c:forEach var="a" items="${aa}">
				
				

					<c:if test="${a==vo.askid}">
							<a href="${cp }/ans_datail?askid=${vo.askid }" style="text-decoration:none; color: red;" id="alink" onclick="aclick(event)"><span >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ㄴ답변드립니다.</span></a><br>&nbsp;&nbsp;
			
			
					</c:if>
			
				</c:forEach>
				</td>
				<td style="width: 300px;" align="center">${userid[status.index] }</td>
				<td style="width: 300px;" align="center">${vo.askdate }</td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${isuser!=-1 }">
	<a href="${cp }/jeungIn/main.jsp?spage=/admin_askboard/askinsertForm.jsp?memid=${isuser}">글쓰기</a><br>
	</c:if>
	<div align="center">
	<c:if test="${startPageNum>10 }">
		<a href="${cp }/ask_list?pageNum=${startPageNum-1}&field=${field}&keyword=${keyword}">[이전]</a>
	</c:if>
	<c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
	<c:choose>
		<c:when test="${i==pageNum }">
		<a href="${cp }/ask_list?pageNum=${i}&field=${field}&keyword=${keyword}"><span style="color:gray">[${i }]</span></a>
		</c:when>
		<c:otherwise>
		<a href="${cp }/ask_list?pageNum=${i}&field=${field}&keyword=${keyword}"><span style="color:blue">[${i }]</span></a>
		</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:if test="${endPageNum<pageCount }">
	<a href="${cp }/ask_list?pageNum=${endPageNum+1}&field=${field}&keyword=${keyword}">[다음]</a>
	</c:if><br>
	</div>
	<form action="${cp }/ask_list" method="post" align="center">
		<input type="text" name="keyword" id="keyword">
		 <select name="field">
			<option value="title"  <c:if test="${field=='title'}">selected</c:if>>제목</option>
			<option value="context" <c:if test="${field=='context'}">selected</c:if>>내용</option>
			<option value="id" <c:if test="${field=='id'}">selected</c:if>>작성자</option>
		
		</select> <input type="submit" value="검색">
	</form>
	<div align="right">
	</div>
	 <script type="text/javascript">
	 function aclick(e){
		 e.target.style.color="gray";
	 }
	 
	 </script>

	 
</body>
</html>