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


	<select name="field" id="field">
		<option value="itemname" selected="selected">상품명</option>
		<option value="factory">제조사</option>
		<option value="origin">원산지</option>
	</select>>
	<input type="text" id="search" name="search">
	<input type="button" value="조회" onclick="getList()">
	<div id="commlist">
	<div id="imgebox"></div>
	</div>
	<div id="pagelist"></div>
	<script type="text/javascript">
		var xhr = null;
		function getList() {
			xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4 && xhr.status == 200) {
					var data = xhr.responseText;
					var pagelist=document.getElementById("pagelist");
					var commlist = document.getElementById("commlist");
				
					var paging = JSON.parse(data);
					var all_list="";
						console.log(paging.itemlist);
						if(paging.itemlist==""){
							alert("일치하는 물품이없습니다");
							var div1=document.createElement("div");
							commlist.innerHTML="";
							return;
						}
					for (var i = 0; i < paging.itemlist.length; i++) {
						
						var div = document.createElement("div");
						var imglist=document.createElement("div");
						var img1=document.createElement("img");
						var itemimg=paging.itemlist[i].itemimg;
						img1.src= itemimg;
						img1.alt="";
						var itemName=paging.itemlist[i].itemname;
						var price=paging.itemlist[i].price;
						var factory=paging.itemlist[i].factory;
						var origin=paging.itemlist[i].origin;
					
						all_list+="<br>상품명: "+itemName+"<br>가격: "+price+"<br>원산지:  "+origin
						+"<br>제조사: "+factory;
						img1.src=itemimg;
						commlist.innerHTML="";
						div.innerHTML = all_list;
						imglist.appendChild(img1)
						commlist.appendChild(div);
						"<br>"
						div.appendChild(img1)

					}
					
 					if (paging.startPageNum >10) {
 						var startpage=document.createElement("a");
 						startpage.href="<%=request.getContextPath() %>/search_item/search_list.jsp?pageNum="+paging.startPageNum;
 						startpage.innerHTML="이전"
 						pagelist.appendChecked(startpage);
					
					}
					for (let i = paging.startPageNum; i <= paging.endPageNum; i++) {
						var ipagenum=document.createElement("a");
						ipagenum.href="<%=request.getContextPath() %>/search_item/search_list.jsp?pageNum="+i;
						ipagenum.innerHTML=i;
						pagelist.appendChild(ipagenum);
					}
					if (paging.endPageNum < paging.pageCount) {
						
					}

				}
			}; 
			var pageNum='${param.pageNum}';
			if(!pageNum){
				pageNum=1;
			}
			console.log(pageNum+"vpdlwlsja");
			xhr.open('post', '../search_list.do', true);
			//post방식인경우 아래 코드 추가
			var f = document.getElementById("field");
			var search = document.getElementById("search").value;
			/* if(search==null || search==""){
				alert("값을 입력하세요");
				return;  테스트를 위해 주석
			} */ 
			var field = f.options[f.selectedIndex].value;
			
			xhr.setRequestHeader('Content-Type',
					'application/x-www-form-urlencoded');
			var param = "field=" + field + "&search=" + search + "&pagenum="+pageNum;
			console.log(param);
			xhr.send(param);
		}

		
	</script>


</body>
</html>