<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>yang/basketListpage</title>
<style>
	.item{width: 150px; height: 150px;}
	.itemname{display:inline-block; width: 150px;}
	#tbl{width: 1400px; border: 1px solid black;}
	tr{text-align: center;}
</style>
</head>
<body>
<h1>장바구니 페이지</h1>
<form method="post" action="#">
<table id="tbl">
	<tr>
		<th><input type="checkbox" id="allck" onclick="checkAll()">전체선택</th>
		<th>상품</th>
		<th>가격</th>
		<th>남은수량</th>
		<th>판매여부</th>
		<th>개수</th>
		<th>삭제</th>
	</tr>
</table>
<div>
	<input type="submit" value="구매하기">
</div>
</form>
<div>
	<input type="button" value="장바구니에서 삭제" onclick="checkDel()">
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
				ckbox.setAttribute("name", json[i].itemid);
// 				var itemid=document.createElement("input");
// 				itemid.type="hidden";
				
				td1.appendChild(ckbox);
				//td1.appendChild(itemid);
				
				var td2=document.createElement("td"); //이미지,이름
				tr.appendChild(td2);
				var a=document.createElement("a");
				var img=document.createElement("img");
				a.href="yangsuccess.html";
				img.src="images/"+json[i].image;
				img.className="item";
				var itemname=document.createElement("span");
				itemname.innerHTML=json[i].itemname;
				itemname.className="itemname";
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
				
				var td6=document.createElement("td"); //살 개수
				tr.appendChild(td6);
				var itemCount=document.createElement("input");
				itemCount.type="number";
				itemCount.setAttribute("min", "1");
				itemCount.setAttribute("max", json[i].stock); //최대개수는 재고수
				itemCount.value="1";
				td6.appendChild(itemCount);
				
				var td7=document.createElement("td"); //장바구니 삭제
				tr.appendChild(td7);
				var delDibs=document.createElement("input");
				delDibs.type="button";
				delDibs.value="삭제";
				delDibs.setAttribute("name", json[i].itemid);
				delDibs.setAttribute("onclick", "delDibs(event)"); //삭제버튼 누르면 delDibs실행
				td7.appendChild(delDibs);
			}
		}
	}
	xhr.open('get','../basketlist.do?bd=b',true); //bd=b : 장바구니상태로 보내기
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

function delDibs(e){
	xhr=new XMLHttpRequest();
	var itemid=e.target.name;
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
	xhr.open('get','../basketdelete.do?bd=b&itemid='+itemid,true); //%%%%%%%%%여기 숫자 바꿔야함%%%%%%%%%%%%
	xhr.send();
}

function checkDel(){
	var checkbox=document.getElementsByClassName("checkbx"); //위에서 선언된 checkbox들을 가져옴
	var itemid="";
	for(let i=0;i<checkbox.length;i++){
		if(checkbox[i].checked){
			itemid+=checkbox[i].getAttribute("name")+",";
		}
	}
	console.log(itemid);
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
	xhr.open('get','../basketdelete.do?bd=b&itemid='+itemid,true); //%%%%%%%%%여기 숫자 바꿔야함%%%%%%%%%%%%
	xhr.send();
}

</script>
</body>
</html>