<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin_jeungin/storageinfo.jsp</title>
<script type="text/javascript">
	window.onload=function(){
		catEvent();
	}
	
	function catEvent(){
		
		var bigCate = document.getElementById("bigCate");
		var smallCate = document.getElementById("smallCate");
		var div = document.getElementById("opt");
		if(smallCate!=null){
			smallCate.remove();
		}else{}
		if(bigCate.value=="전체"){
			var select = document.createElement("select");
			select.id = "smallCate";
			select.name="smallCate";
			
			var option = document.createElement("option");
			option.value = "전체";
			option.innerHTML ="전체";
			
			select.appendChild(option);
			div.appendChild(select);
		}else if(bigCate.value=="채소"){
			var select = document.createElement("select");
			select.id="smallCate";
			select.name="smallCate";
			
			var option = document.createElement("option");
			option.value = "전체";
			option.innerHTML = "전체";
			
			var option1 = document.createElement("option");
			option1.value="콩나물/버섯류";
			option1.innerHTML="콩나물/버섯류";
			
			var option2 = document.createElement("option");
			option2.value="시금치/부추/나물";
			option2.innerHTML="시금치/부추/나물";
			
			var option3 = document.createElement("option");
			option3.value="양파/마늘/생강/파";
			option3.innerHTML="양파/마늘/생강/파";
			
			var option4 = document.createElement("option");
			option4.value="기본 채소";
			option4.innerHTML="기본 채소";
			
			var option5 = document.createElement("option");
			option5.value="간편 채소";
			option5.innerHTML="간편 채소";
			
			select.appendChild(option);
			select.appendChild(option1);
			select.appendChild(option2);
			select.appendChild(option3);
			select.appendChild(option4);
			select.appendChild(option5);
			
			div.appendChild(select);
		}else if(bigCate.value=="과일"){
			var select = document.createElement("select");
			select.id="smallCate";
			select.name="smallCate";
			
			var option = document.createElement("option");
			option.value = "전체";
			option.innerHTML = "전체";
			
			var option1 = document.createElement("option");
			option1.value="제철 과일";
			option1.innerHTML="제철 과일";
			
			var option2 = document.createElement("option");
			option2.value="국산";
			option2.innerHTML="국산";
			
			var option3 = document.createElement("option");
			option3.value="수입";
			option3.innerHTML="수입";
			
			var option4 = document.createElement("option");
			option4.value="냉동";
			option4.innerHTML="냉동";
			
			var option5 = document.createElement("option");
			option5.value="견과류";
			option5.innerHTML="견과류";
			
			select.appendChild(option);
			select.appendChild(option1);
			select.appendChild(option2);
			select.appendChild(option3);
			select.appendChild(option4);
			select.appendChild(option5);
			
			div.appendChild(select);
		}else if(bigCate.value=="수산"){
			var select = document.createElement("select");
			select.id="smallCate";
			select.name="smallCate";
			
			var option = document.createElement("option");
			option.value = "전체";
			option.innerHTML = "전체";
			
			var option1 = document.createElement("option");
			option1.value="생선";
			option1.innerHTML="생선";
			
			var option2 = document.createElement("option");
			option2.value="갑각류";
			option2.innerHTML="갑각류";
			
			var option3 = document.createElement("option");
			option3.value="어패류";
			option3.innerHTML="어패류";
			
			var option4 = document.createElement("option");
			option4.value="가공품";
			option4.innerHTML="가공품";
			
			var option5 = document.createElement("option");
			option5.value="기타";
			option5.innerHTML="기타";
			
			select.appendChild(option);
			select.appendChild(option1);
			select.appendChild(option2);
			select.appendChild(option3);
			select.appendChild(option4);
			select.appendChild(option5);
			
			div.appendChild(select);
			
		}else if(bigCate.value=="정육"){
			var select = document.createElement("select");
			select.id="smallCate";
			select.name="smallCate";
			
			var option = document.createElement("option");
			option.value = "전체";
			option.innerHTML = "전체";
			
			var option1 = document.createElement("option");
			option1.value="소";
			option1.innerHTML="소";
			
			var option2 = document.createElement("option");
			option2.value="돼지";
			option2.innerHTML="돼지";
			
			var option3 = document.createElement("option");
			option3.value="조류";
			option3.innerHTML="조류";
			
			var option4 = document.createElement("option");
			option4.value="양념";
			option4.innerHTML="양념";
			
			var option5 = document.createElement("option");
			option5.value="계란류";
			option5.innerHTML="계란류";
			
			select.appendChild(option);
			select.appendChild(option1);
			select.appendChild(option2);
			select.appendChild(option3);
			select.appendChild(option4);
			select.appendChild(option5);
			
			div.appendChild(select);
			
		}else if(bigCate.value=="완제품"){
			var select = document.createElement("select");
			select.id="smallCate";
			select.name="smallCate";
			
			var option = document.createElement("option");
			option.value = "전체";
			option.innerHTML = "전체";
			
			var option1 = document.createElement("option");
			option1.value="샐러드/도시락";
			option1.innerHTML="샐러드/도시락";
			
			var option2 = document.createElement("option");
			option2.value="간편식/냉동";
			option2.innerHTML="간편식/냉동";
			
			var option3 = document.createElement("option");
			option3.value="밥/면/즉석식품";
			option3.innerHTML="밥/면/즉석식품";
			
			var option4 = document.createElement("option");
			option4.value="만두/튀김";
			option4.innerHTML="만두/튀김";
			
			var option5 = document.createElement("option");
			option5.value="선식/씨리얼";
			option5.innerHTML="선식/씨리얼";
			
			select.appendChild(option);
			select.appendChild(option1);
			select.appendChild(option2);
			select.appendChild(option3);
			select.appendChild(option4);
			select.appendChild(option5);
			
			div.appendChild(select);
			
		}else if(bigCate.value=="음료"){
			var select = document.createElement("select");
			select.id="smallCate";
			select.name="smallCate";
			
			var option = document.createElement("option");
			option.value = "전체";
			option.innerHTML = "전체";
			
			var option1 = document.createElement("option");
			option1.value="생수/음료/쥬스";
			option1.innerHTML="생수/음료/쥬스";
			
			var option2 = document.createElement("option");
			option2.value="커피/차";
			option2.innerHTML="커피/차";
			
			var option3 = document.createElement("option");
			option3.value="유제품";
			option3.innerHTML="유제품";
			
			var option4 = document.createElement("option");
			option4.value="초콜릿/젤리/캔디";
			option4.innerHTML="초콜릿/젤리/캔디";
			
			var option5 = document.createElement("option");
			option5.value="간식";
			option5.innerHTML="간식";
			
			select.appendChild(option);
			select.appendChild(option1);
			select.appendChild(option2);
			select.appendChild(option3);
			select.appendChild(option4);
			select.appendChild(option5);
			
			div.appendChild(select);
		}
	}
</script>
</head>
<c:set var="cp" value="${pageContext.request.contextPath }"/>
<body>
	<form action="<%= request.getContextPath() %>/storList.do" method="get">
	<div id = "opt">
		<label>구분</label>
		<select id = 'bigCate' onchange="catEvent()" name='bigCate'>
			<option value = '전체'
				<c:if test="${bigCate=='전체'}">selected</c:if>>전체</option>
			<option value = '채소'
				<c:if test="${bigCate=='채소'}">selected</c:if>>채소</option>
			<option value = '과일'
				<c:if test="${bigCate=='과일'}">selected</c:if>>과일</option>
			<option value = '수산'
				<c:if test="${bigCate=='수산'}">selected</c:if>>수산</option>
			<option value = '정육'
				<c:if test="${bigCate=='정육'}">selected</c:if>>정육</option>
			<option value = '완제품'
				<c:if test="${bigCate=='완제품'}">selected</c:if>>완제품</option>
			<option value = '음료'
				<c:if test="${bigCate=='음료'}">selected</c:if>>음료</option>
		</select>
		<select id = 'smallCate' name='smallCate'>
			<option value = '전체'>전체</option>
		</select>
	</div>
	<br>
	<div>
		<label>재고/매진</label>
		<select id='avail' name='avail'>
			<option value = '재고'
				<c:if test="${avail=='재고'}">selected</c:if>>재고</option>
			<option value = '매진'
				<c:if test="${avail=='매진'}">selected</c:if>>매진</option>
		</select>
	</div>
	<div>
		<label>기간</label>
		<input type ="text" id = 'stDate' name='stDate'value="${stDate }"> ~ <input type ="text" id = 'endDate' name ='endDate' value="${endDate }">
	</div>
	<br>
	<div>
		<label>상품명</label>
		<input type ="text" id='itemname' name='itemname' value="${itemname }">
		<label>제조사</label>
		<input type ="text" id='factory' name='factory' value="${factory }">
		<label>원산지</label>
		<input type ="text" id='origin' name='origin' value="${origin }">
	</div>
	<div>
		<input type ="submit" value="조회">
	</div>
	</form>
	<table border="1">
		<tr>
			<th>상태</th>
			<th>구분</th>
			<th>상품명</th>
			<th>가격</th>
			<th>재고량</th>
			<th>제조사</th>
			<th>원산지</th>
			<th>입고일</th>
			<th>유통기한</th>
		</tr>
		<c:forEach var="vo" items="${list }">
			<tr>
				<td>${vo.avail }</td>
				<td>${vo.catname }</td>
				<td>${vo.itemname }</td>
				<td>${vo.price }</td>
				<td>${vo.stock }</td>
				<td>${vo.factory }</td>
				<td>${vo.origin }</td>
				<td>${vo.storedate }</td>
				<td>${vo.expire }</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<c:if test="${startPageNum>10 }">
			<a href="${cp }/storList.do?pageNum=${startPageNum-1}&">[이전]</a>
		</c:if>
		<c:forEach var ="i" begin="${startPageNum }" end="${endPageNum }">
			<a href="${cp }/storList.do?pageNum=${i}">[${i }]</a>
		</c:forEach>
		<c:if test="${endPageNum<pageCount }">
			<a href ="${cp }/storList.do?pageNum=${endPageNum+1}">[다음]</a>
		</c:if>
	</div>
</body>
</html>