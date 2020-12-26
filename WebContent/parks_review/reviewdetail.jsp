<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
#commList {
	display:inline-block;
	position: relative;
	left: 505px;
	bottom: 512px;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>상세보기</h1>
	<table border="1" width="500" id="table1">
		<tr>
			<td>제목</td>
			<td>${vo.title }</td>
		</tr>
		<tr>
			<td>작성일</td>
			<td>${vo.revdate }</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${username }</td>
		<tr>
			<td>이미지</td>
			<td><c:choose>
					<c:when test="${ vo.image==null }">
						등록된 사진이 없습니다.
						</c:when>
					<c:when test="${vo.image!=null }">
						<img src="fileFolder/${vo.image }" style="width: 200px;"
							onclick="imgup(event); imgout(event);" id="img1">
					</c:when>
				</c:choose></td>
		</tr>
		<tr>
			<td>물품명</td>
			<td>${itemname }</td>
		</tr>
		<tr>
			<td>현재별점</td>
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
		<tr>
			<td>글내용</td>
			<td><textarea rows="5" cols="50" readonly="readonly">${vo.context }</textarea></td>
		</tr>
	
	</table>
	<c:if test="${revcount>0 }">
	<a
		href="${pageContext.request.contextPath }/reviewdelete?revid=${vo.revid }&itemid=${vo.itemid}">삭제</a>
	<a
		href="${pageContext.request.contextPath }/reviewupdate?revid=${vo.revid }&itemid=${vo.itemid}">수정</a>
		</c:if>
	<a 
		href="${pageContext.request.contextPath }/jeungIn/main.jsp?spage=/jeungIn/itemdetail.jsp?itemid=${vo.itemid }">리뷰목록으로....</a>	
	<div id="commAdd">
		<input type="hidden" value=${username } id="user" name="user"
			readonly="readonly">


	</div>
	<div id="box">
		<label>댓글: </label>
		<textarea rows="1" cols="60" id="context"></textarea>
		<br>
		<c:if test="${revcount>0 }">  
		 <input type="button" value="등록" onclick="insertComm()">
		</c:if>
	</div>
	<div id="commList"></div>
	<script type="text/javascript">
		function insertComm() {
			var xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4 && xhr.status == 200) {
					var xml = xhr.responseXML;
					var code = xml.getElementsByTagName("code")[0].textContent;
					alert("입력완료");
					getList();
				}
			};
			xhr.open('post', 'reviewchildinsert.do', true);
			xhr.setRequestHeader('Content-Type',
					'application/x-www-form-urlencoded');
			var context = document.getElementById("context").value;
			var param = "&context=" + context + "&revid=" + ${vo.revid};
			console.log(param);
			xhr.send(param);
		}
		function getList() {
			var xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4 && xhr.status == 200) {
					var xml = xhr.responseXML;
					var comm = xml.getElementsByTagName("comm");
					var commList = document.getElementById("commList");
					var childs = commList.childNodes;
					for (let i = childs.length - 1; i >= 0; i--) {
						var child = childs.item(i);
						commList.removeChild(child);
					}
					for (let i = 0; i < comm.length; i++) {
						var context = comm[i].getElementsByTagName("context")[0].textContent;
						var userid = comm[i].getElementsByTagName("userid")[0].textContent;
						var rchilddate = comm[i]
								.getElementsByTagName("rchilddate")[0].textContent;
						var rchildid = comm[i].getElementsByTagName("rchildid")[0].textContent;
						var div = document.createElement("div");

						div.innerHTML = "  " + userid + "  :" + context
								+ "<br>작성일: " + rchilddate
								+ "<a href='javascript:removeComm(" + rchildid
								+ ")'>삭제</a>";
						div.className = "comm";
						div.style.border = "solid 2px black";
						div.style.marginTop = "10px";
						div.style.width = "380px";
						commList.appendChild(div);
			/* if문으로 글쓴이일 경우 상태+운영자일경우 삭제버튼(전체삭제버튼도 생김)이 생기게 할수 있음 */
					}
				}
			};
			xhr.open('get', 'childlist.do?revid=${vo.revid}', true)
			xhr.setRequestHeader('Content-Type',
					'application/x-www-form-urlencoded');
			var context = document.getElementById("context").value;
			xhr.send();

		}
		getList();
		function removeComm(rchildid) {
			var xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4 && xhr.status == 200) {
					var xml = xhr.responseXML;
					var code = xml.getElementsByTagName("code")[0].textContent;
					if (code == 'success') {
						alert("삭제완료");
						getList();
					} else {
						alert('삭제실패');
					}
				}
			};
			xhr.open('get', 'child_delete?rchildid=' + rchildid, true);
			xhr.send();
		}
		function delComm(){
			var commlist=document.getElementById("commlist");
			var childs=commlist.childNodes;
			for(var i=childs.length-1; i>=0; i--){
				var child=childs.item(i);
				commlist.removeChild(child);
			}
		}
	</script>


</body>
</html>