<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>yang/dibsListpage</title>
<style>
	.item{width: 150px; height: 150px;}
	#tbl{width: 1400px; border: 1px solid black;}
	tr{text-align: center;}
</style>
</head>
<body>
<h1>찜페이지</h1>
<table id="tbl">
	<tr>
		<th><input type="checkbox" id="allck" onclick="checkAll()">전체선택</th>
		<th>상품</th>
		<th>가격</th>
		<th>남은수량</th>
		<th>판매여부</th>
		<th>삭제</th>
		<th>장바구니 추가</th>
	</tr>
</table>
<div>
	<input type="button" value="찜삭제" onclick="checkDel()">
</div>
<script type="text/javascript">
var json=null
function listDibs(){
	var xhr=new XMLHttpRequest();
	xhr.onreadystatechange=function(){
		if(xhr.readyState==4 && xhr.status==200){
			json=JSON.parse(xhr.responseText);
			var tbl=document.getElementById("tbl");
			//var count=0;
			for(let i=0;i<json.length;i++){
				var tr=document.createElement("tr");
				tr.className="listtr"; //나중에 찜삭제,수정시 tr의 자식들을 한꺼번에 지웠다가 다시 리스트 만들기 위함
				tbl.appendChild(tr);
				var td1=document.createElement("td");
				tr.appendChild(td1);
				var ckbox=document.createElement("input"); //체크박스
				ckbox.type="checkbox";
				ckbox.className="checkbx";
				td1.appendChild(ckbox);
				
				var td2=document.createElement("td"); //이미지,이름
				tr.appendChild(td2);
				var a=document.createElement("a");
				var img=document.createElement("img");
				a.href="yangsuccess.html";
				img.src="images/"+json[i].image;
				img.className="item";
				var itemname=document.createElement("span");
				itemname.innerHTML=json[i].itemname;
				td2.appendChild(a);
				a.appendChild(img);
				td2.appendChild(itemname);
				
				
				var td3=document.createElement("td"); //가격
				tr.appendChild(td3);
				var price=document.createElement("span");
				price.innerHTML=json[i].price+"원";
				td3.appendChild(price);
				
				var td4=document.createElement("td"); //남은수량
				tr.appendChild(td4);
				var stock=document.createElement("span");
				if(json[i].stock==0){
					stock.innerHTML="재고없음";
				}else{
					stock.innerHTML="남은수량: "+json[i].stock;
				}
				td4.appendChild(stock);
				
				var td5=document.createElement("td"); //판매여부
				tr.appendChild(td5);
				var avail=document.createElement("span");
				if(json[i].avail==0){
					avail.innerHTML="판매불가";
				}else{
					avail.innerHTML="판매중";
				}
				td5.appendChild(avail);
				
				var td6=document.createElement("td"); //찜삭제
				tr.appendChild(td6);
				var delDibs=document.createElement("input");
				delDibs.type="button";
				delDibs.value="삭제";
				delDibs.setAttribute("onclick", "delDibs()"); //삭제버튼 누르면 delDibs실행
				td6.appendChild(delDibs);
			}
		}
	}
	xhr.open('get','../basketlist.do',true);
	xhr.send();
	
}
listDibs();

function checkAll(){ //전체체크 눌렀을때
	var check=document.getElementById("allck");
	//json=JSON.parse(xhr.responseText);
	if(check.checked==true){
		for(let i=0;i<json.length;i++){
			document.getElementsByClassName("checkbx")[i].checked=true;
		}
	}
	if(check.checked==false){
		for(let i=0;i<json.length;i++){
			document.getElementsByClassName("checkbx")[i].checked=false;
		}
	}
}

function delDibs(){
	xhr=new XMLHttpRequest();
	xhr.onreadystatechange=function(){
		if(xhr.readyState==4 && xhr.status==200){
			var json=JSON.parse(xhr.responseText);
			var list=document.getElementsByClassName("listtr");
			for(let i=list.length-1;i>=0;i--){ //listtr들을 모두 삭제(안에있는 자식들까지 모두)
				list[i].remove();
			}
			listDibs(); //list를 다시 생성
		}
	}
	xhr.open('get','../basketdelete.do?itemid=11',true); //%%%%%%%%%여기 숫자 바꿔야함%%%%%%%%%%%%
	xhr.send();
}

</script>
</body>
</html>