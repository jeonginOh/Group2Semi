<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<style type="text/css">
#commList {
	display:inline-block;
	    float: right;
	left: 505px;
	bottom: 440px;
}
#setAll a:link{
color:red;
}
textarea{
 resize: none;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id='setAll'>
		<a href="${pageContext.request.contextPath }/jeungIn/main.jsp?spage=/jeungIn/itemdetail.jsp?itemid=${vo.itemid }"><button type="button" class="btn btn-danger">
리뷰목록으로....</button></a>
	<table border="1" width="500" id="table1" class='table'>
		<tr class="active"> 
			<td class="active">제목</td>
			<td class="active">${vo.title }</td>
		</tr>
		<tr class="success">
			<td>작성일</td>
			<td>${vo.revdate }</td>
		</tr>
		<tr class="warning">
			<td>작성자</td>
			<td>${username }</td>
		<tr class="active">
			<td >이미지</td>
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
		<tr class="success">
			<td>물품명</td>
			<td>${itemname }</td>
		</tr>
		<tr class="warning">
			<td class="warning">현재별점</td>
			<td class="warning"><c:choose>
					<c:when test="${vo.star>4.6 }">
						
		평점 : ${ vo.star} 
		<br>
		★★★★★
		
					</c:when>
					<c:when test="${vo.star>3.6 }">
						
 	평점 : ${ vo.star}  
		<br>
		★★★★
		

					</c:when>
					<c:when test="${vo.star>2.6 }">
						
		평점 : ${ vo.star}  
		<br>
		★★★
		

					</c:when>
					<c:when test="${vo.star>1.6 }">
						
		평점 : ${ vo.star}  
		<br>
		★★
		

					</c:when>
					<c:when test="${vo.star>0.6 }">
						
		평점 : ${ vo.star}  
		<br>
		★
		
					</c:when>

				</c:choose></td>
		<tr class="success">
			<td >내용</td>
			<td><textarea rows="5" cols="50" readonly="readonly">${vo.context }</textarea></td>
		</tr>
		<c:if test="${revcount>0 }">
	<a
		href="${pageContext.request.contextPath }/reviewdelete?revid=${vo.revid }&itemid=${vo.itemid}">삭제</a>
	<a
		href="${pageContext.request.contextPath }/reviewupdate?revid=${vo.revid }&itemid=${vo.itemid}">수정</a>
		</c:if>
	</table>
	

		
	<div id="commAdd">
		<input type="hidden" value=${username } id="user" name="user"
			readonly="readonly">


	</div>
	<div id="box">
	<div style="display: inline-block;float: left;">
		<label>댓글: </label>
		<textarea rows="2" cols="1" style='width: 450px;' id="context" class="form-control"></textarea>
		
		<c:choose>
	<c:when test="${empty memid  }">
	
		</c:when>
		<c:otherwise>
			 <input type="button" value="등록" onclick="insertComm()"  class="btn btn-success">
		</c:otherwise>
		</c:choose>
		</div>
		<div id="commList" ></div>
	</div>

	

		
		</div>
	<script type="text/javascript">
		function insertComm() {
			var xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4 && xhr.status == 200) {
					var xml = xhr.responseXML;
					var code = xml.getElementsByTagName("code")[0].textContent;
					alert("입력완료");
					$("#context").val("");
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
						console.log(rchildid);
						if(rchildid==userid){
						div.innerHTML = " 내용  :" + context
								+ "<br>작성일: " + rchilddate
								+ "<a href='javascript:removeComm(" + rchildid
								+ ")'>삭제</a>";
						}else{
							div.innerHTML = " 내용 :" + context
							+ "<br>작성일: " + rchilddate
							+ "<a href='javascript:removeComm(" + rchildid
							+ ")'></a>";
						    
						}
						div.className = "comm";
						div.style.backgroundColor='white';
						div.style.border = "solid";
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