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

	<table border="2" style="width: 500px">
		<thead id="test">
			<tr>
				<th>순위</th>
				<th>물품이름</th>
				<th onclick="List();" id="reviewsoo">리뷰수</th>
				<th onclick="getstar()" id="star11">평균평점</th>
			</tr>
		</thead>
		<tbody id="tbo"></tbody>
	</table>

	<script type="text/javascript">
		var xhr = null;
	var cc=0;
	var dd=0;
	
		function List() {
			deletelist2();
			if(cc==0){
				cc++;
				
				var rew=document.getElementById("reviewsoo");
				var dd=document.createElement("span")
				dd.style.color="red";
				dd.innerHTML="리뷰 적은 순서";
				console.log(dd)
				rew.appendChild(dd);
				rew.axis="오름차순";
				
			}else{
				cc--;
				var rew=document.getElementById("reviewsoo");
				var dd=document.createElement("span")
				dd.style.color="blue";
				dd.innerHTML="리뷰 많은 순서";
				console.log(dd)
				rew.appendChild(dd);
			}
			
	
			xhr = new XMLHttpRequest();
			
			xhr.onreadystatechange=getList;
			xhr.open('get',"../review_admin1?namnam="+cc,'true');
			xhr.send();
			
				
		}
		function getstar(){
			deletelist3();
			if(dd==0){
				var rew=document.getElementById("star11");
				var ddd=document.createElement("span")
				ddd.style.color="blue";
				ddd.innerHTML="높은순서";
				console.log(dd)
				rew.appendChild(ddd);
				dd++;

				
			}else{
				var rew=document.getElementById("star11");
				var ddd=document.createElement("span")
				ddd.style.color="red";
				ddd.innerHTML="낮은순서";
				console.log(dd)
				rew.appendChild(ddd);
				dd--;
			}
			console.log(dd);
			xhr = new XMLHttpRequest();
			
			xhr.onreadystatechange=getList;
			xhr.open('get',"../review_admin1?dd="+dd,'true');
			xhr.send();
			
		}
				function getList() {
					deletelist();
					var tt = document.getElementById("test");
				if (xhr.readyState == 4 && xhr.status == 200) {
				
					var data = xhr.responseText;
					var tbo=document.getElementById("tbo");
					var json = JSON.parse(data);
					var str = "";
					var name = "";
					var cnt = "";
					var star = "";
					for (var i = 0; i < json.length; i++) {
						var tt0 = document.createElement("tr");
						var tt1 = document.createElement("td");
						var tt2 = document.createElement("td");
						var tt3 = document.createElement("td");
						var tt4 = document.createElement("td");
						
						
						name = json[i].itemname;
						cnt = json[i].cnt;
						star = json[i].star;
						str1 = ""
						str = "<td>"+(i+1)+"</td><td><a href='../Admin_review?itemname="+name +"'>"+name+"</a></td><td>" + cnt
								+ "</td><td>" + star + "</td>";
						tt0.innerHTML = str;
						tbo.appendChild(tt0);
						
					}
					
				}
				
			};
			function deletelist(){
				var tbo = document.getElementById("tbo");
				while(tbo.firstChild){
					tbo.removeChild(tbo.lastChild);
				}
			}
			function deletelist2(){
				var rew=document.getElementById("reviewsoo");
		
				while(rew.firstChild){
					rew.removeChild(rew.lastChild);
				}
			}
			function deletelist3(){
				var rew=document.getElementById("star11");
				while(rew.firstChild){
					rew.removeChild(rew.lastChild);
				}
			}
			List();
			getstar();
	</script>
</body>
</html>