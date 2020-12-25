<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.search_item{display: inline-block; padding-left: 40px;}
.numbox{display: inline-block; padding-left: 10px;}
</style>
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
	<div id="imgbox">
	<div id="commlist"></div>
	</div>
	<div id="pagelist"></div>

	<script type="text/javascript">
		var xhr = null;
		var startnum=1;
		function getList(startnum) {

			xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function() {
				
				if (xhr.readyState == 4 && xhr.status == 200) {
					deletelist();
					var data = xhr.responseText;
					var pagelist=document.getElementById("pagelist");
					var commlist = document.getElementById("commlist");
					var imgbox=document.getElementById("imgbox");
					var paging = JSON.parse(data);
					var ss=JSON.stringify(data);
					console.log(ss);
					var all_list="";
						console.log(paging.itemlist);
						if(paging.itemlist==""){
							alert("일치하는 물품이없습니다");
							var div1=document.createElement("div");
							commlist.innerHTML="";
							return;
						}
							 
					for (var i = 0; i < paging.itemlist.length; i++) {
						var a=document.createElement("a");
						var div = document.createElement("div");
						var imglist=document.createElement("div");
						var img1=document.createElement("img");
						var itemimg=paging.itemlist[i].itemimg;			
						img1.setAttribute("src", "fileFolder/"+itemimg);
						var itemid=paging.itemlist[i].itemid;
						var itemName=paging.itemlist[i].itemname;
						var price=paging.itemlist[i].price;
						var factory=paging.itemlist[i].factory;
						var origin=paging.itemlist[i].origin;
						var regdate=paging.itemlist[i].regdate;
						all_list="<a href='<%=request.getContextPath()%>/jeungIn/main.jsp?spage=/jeungIn/itemdetail.jsp?itemid="+itemid+"'>"
						+"<img src='<%=request.getContextPath()%>/fileFolder/"+itemimg+"'style=width:100px></a><br>상품명: "+itemName+"<br>가격: "+price+"<br>원산지:  "+origin
						+"<br>제조사: "+factory+"<br>제조일:"+regdate;
					
						div.className="search_item";
					/* 	
						a.appendChild(img1);
						div.appendChild(a); */
						console.log( paging.itemlist[i].itemname);
						div.innerHTML = all_list;
						commlist.appendChild(div);
						commlist.appendChild(a);

					}
					deletepagenum();
				/* 짜고나서 이해 안됨->deletepagenum()메소드가 1~10에서 다음을 누르면 1~10을 지우는 방식??
						마지막페이지가 약간 잘못 출력되는것을 확인... */
 					if (startnum >10) {
 						var stdiv=document.createElement("div");
						stdiv.innerHTML="<a href='javascript:getList("+(paging.startPageNum-1) +")'>이전</a>";
							stdiv.className="numbox";						
						
						pagelist.appendChild(stdiv);

					
					}
					for (var i = paging.startPageNum; i <= paging.endPageNum; i++) {
						var div1=document.createElement("div");
						console.log(paging.endPageNum+"마지막페이지테스트");
						if(i==startnum){
						div1.innerHTML="<a href='javascript:getList("+i+")'><span style='color:black'>"+i+"</span></a>";
						}else{
							div1.innerHTML="<a href='javascript:getList("+i+")'><span style='color:gray'>"+i+"</span></a>";
						}
							div1.className="numbox";						
						pagelist.appendChild(div1);
						console.log(i);
						console.log(" 페이징스타트넘버확인"+paging.startPageNum);
						
					}
					if (paging.endPageNum < paging.pageCount) {
						var endiv=document.createElement("div");
						endiv.innerHTML="<a href='javascript:getList("+(paging.endPageNum+1)+")'>다음</a>";
						endiv.className="numbox";					
						console.log("엔드페이지"+paging.endPageNum);
					pagelist.appendChild(endiv);
					}

				}
			};
			xhr.open('post', '../search_list.do', true);
			//post방식인경우 아래 코드 추가
			var f = document.getElementById("field");
			var search = document.getElementById("search").value;
			 if(search==null || search==""){
				alert("값을 입력하세요");
				return;  
			} 
			 
			var field = f.options[f.selectedIndex].value;
			xhr.setRequestHeader('Content-Type',
					'application/x-www-form-urlencoded');
			var param = "field=" + field + "&search=" + search + "&pagenum="+startnum;
			xhr.send(param);
		}
		function deletelist(){
			var commlist=document.getElementById("commlist");
	        while(commlist.firstChild){ //기존의 리스트를 전부 삭제
	            commlist.removeChild(commlist.lastChild);
	        }
		}
		function deletepagenum(){
			var pagelist=document.getElementById("pagelist");
			while(pagelist.firstChild){
				pagelist.removeChild(pagelist.lastChild);
			}
		}

		
	</script>


</body>
</html>