<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin_jeungin/storageInsert.jsp</title>
<script type ="text/javascript">
	window.onload=function(){
		var xhr = null;
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4 && xhr.status==200){
				var arr = JSON.parse(xhr.responseText);
				var bigbigbig = document.getElementById("bigbigbig");
				
				for(var i = 0;i<arr.length;i++){
					var bigcatename = arr[i].bigCateName;
					var option = document.createElement("option");
					option.value=bigcatename;
					option.innerHTML=bigcatename;
					
					bigbigbig.appendChild(option);
				}
			}
		}
		xhr.open('get','<%= request.getContextPath() %>/bigCateName',true);
		xhr.send();
	}
	
	function smallsmall(){
		var xhr = null;
		xhr = new XMLHttpRequest();
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4 && xhr.status==200){
				var arr = JSON.parse(xhr.responseText);
				var smallsmall = document.getElementById("cateselect");
				var big = document.getElementById("bigbigbig");
				if(big.value!="선택"){
					var del = document.getElementById("smallsmallsmall");
					if(del!=null){
						del.remove();
					}
					
					var select = document.createElement("select");
					select.id = "smallsmallsmall";
					select.name = "catname";
					
					for(var i = 0;i<arr.length;i++){
						var smallcatename = arr[i].smallCateName;
						var option = document.createElement("option");
						option.value = smallcatename;
						option.innerHTML = smallcatename;
						
						select.appendChild(option);
					}
					smallsmall.appendChild(select);
				}else{}
				
				
			}
		}
		var value=document.getElementById("bigbigbig").value;
		xhr.open('get','<%= request.getContextPath() %>/smallCateName?bigCateName='+value,true);
		xhr.send();
	}
	
</script>
</head>
<body>
	<form method ="post" enctype="multipart/form-data" action="<%= request.getContextPath()%>/storInsert.do">
		<div id = "cateselect">
			<label>분류</label>
			<select id = "bigbigbig" onchange="smallsmall()">
				<option value="골라">선택</option>
			</select>
		</div>
		<br>
		<div>
			<label>상품 이름</label>
			<input type="text" name ="itemname">
			<label></label>
			<label>가격</label>
			<input type="text" name ="price">
			<label>원산지</label>
			<input type="text" name ="origin">
		</div>
		<br>
		<div>
			<label>제조사</label>
			<input type="text" name ="factory">
			<label>유통기한</label>
			<input type="text" name ="expire">
			<label>재고량</label>
			<input type="text" name ="stock">
			<br><br>
			<input type="file" name ="name">
		</div>
		<br>
		<div>
			<input type="submit" value = "등록">
		</div>
	</form>
</body>
</html>