<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
/*	.pgdibsitem{width: 150px; height: 150px;}
	.dibsitemname{display:inline-block; width: 150px;}
	#pgDibsTbl{width: 890px; border: 1px solid black;}
	.dibslisttr{text-align: center;}*/
</style>

<h1>찜페이지</h1>
<table id="pgDibsTbl">
	<tr class="dibslisttr">
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
	<br>
	<input type="button" value="찜삭제" onclick="checkDel()">
</div>
<script type="text/javascript">
var json=null
function pgListDibs(){
	var xhr=new XMLHttpRequest();
	xhr.onreadystatechange=function(){
		if(xhr.readyState==4 && xhr.status==200){
			json=JSON.parse(xhr.responseText);
			var tbl=document.getElementById("pgDibsTbl");
			//var count=0;
			for(let i=0;i<json.length;i++){
				var tr=document.createElement("tr");
				tr.className="dibslisttr"; //나중에 찜삭제,수정시 tr의 자식들을 한꺼번에 지웠다가 다시 리스트 만들기 위함
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
				img.src="<%=request.getContextPath()%>/yang/images/"+json[i].image;
				img.className="pgdibsitem";
				var itemname=document.createElement("span");
				itemname.innerHTML=json[i].itemname;
				itemname.className="dibsitemname";
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
				var avail1=document.createElement("input"); //재고가 있는지없는지 밸류값넘기기위해
				avail1.type="hidden";
				avail1.className="stock";
				avail1.value=json[i].stock;
				td4.appendChild(stock);
				td4.appendChild(avail1);
				
				var td5=document.createElement("td"); //판매여부
				tr.appendChild(td5);
				var avail=document.createElement("span");
				avail.className="avail";
				if(json[i].avail==0){
					avail.innerHTML="판매불가";
				}else{
					avail.innerHTML="판매중";
				}
				var avail2=document.createElement("input"); //판매가능인지 아닌지 밸류값넘기기위해
				avail2.type="hidden";
				avail2.className="avail";
				avail2.value=json[i].avail;
				td5.appendChild(avail);
				td5.appendChild(avail2);
				
				var td6=document.createElement("td"); //찜삭제
				tr.appendChild(td6);
				var delDibs=document.createElement("input");
				delDibs.type="button";
				delDibs.value="삭제";
				delDibs.setAttribute("name", json[i].itemid);
				delDibs.setAttribute("onclick", "delDibs(event)");
				td6.appendChild(delDibs);
				
				var td7=document.createElement("td"); //장바구니 담기
				tr.appendChild(td7);
				var insbasket=document.createElement("input");
				insbasket.type="button";
				insbasket.value="장바구니 담기";
				insbasket.setAttribute("name", json[i].itemid);
				insbasket.setAttribute("onclick", "insBasket(event)");
				td7.appendChild(insbasket);
			}
		}
	}
	xhr.open('get','<%=request.getContextPath()%>/basketlist.do?bd=d',true); //bd=d 찜상태로 보내기
	xhr.send();
	
}
pgListDibs();

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

function delDibs(e){ //개별삭제
	xhr=new XMLHttpRequest();
	itemid=e.target.name;
	xhr.onreadystatechange=function(){
		if(xhr.readyState==4 && xhr.status==200){
			var json=JSON.parse(xhr.responseText);
			var list=document.getElementsByClassName("dibslisttr");
			for(let i=list.length-1;i>=0;i--){ //listtr들을 모두 삭제(안에있는 자식들까지 모두)
				list[i].remove();
			}
			pgListDibs(); //list를 다시 생성
		}
	}
	xhr.open('get','<%=request.getContextPath()%>/basketdelete.do?bd=d&itemid='+itemid,true); //%%%%%%%%%여기 숫자 바꿔야함%%%%%%%%%%%%
	xhr.send();
}

function checkDel(){ //체크박스 삭제
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
			var list=document.getElementsByClassName("dibslisttr");
			for(let i=list.length-1;i>=0;i--){ //listtr들을 모두 삭제(안에있는 자식들까지 모두)
				list[i].remove();
			}
			pgListDibs(); //list를 다시 생성
		}
	}
	xhr.open('get','<%=request.getContextPath()%>/basketdelete.do?bd=d&itemid='+itemid,true); //%%%%%%%%%여기 숫자 바꿔야함%%%%%%%%%%%%
	xhr.send();
}

function insBasket(e){
	let stock=document.getElementsByClassName("stock");
	let avail=document.getElementsByClassName("avail");
	for(let i=0;i<stock.length;i++){
		if(stock[i].value<=0 || avail[i].value==0){ //수량,상태체크후 판매불가시 장바구니에 못담게함
			alert("수량이없거나 판매불가상태입니다.");
			return;
		}
	}
	xhr=new XMLHttpRequest();
	itemid=e.target.name;
	xhr.onreadystatechange=function(){
		if(xhr.readyState==4 && xhr.status==200){
			var json=JSON.parse(xhr.responseText);
			if(json.code=="success"){
				if(confirm("장바구니로 옮겼습니다!\n옮긴 항목을 찜목록에서 삭제하시겠습니까?")==true){
					delDibs(e);
				}
			}else if(json.code=="overlap"){
				alert("이미 장바구니에 있는 상품입니다.");
			}else{
				alert("오류로 인해 장바구니 옮기기 실패");
			}
		}
	}
	xhr.open('get','<%=request.getContextPath()%>/basketinsert.do?amount=1&bd=b&itemid='+itemid,true); //%%%%%%%%%여기 바꿔야함%%%%%%%%%%%%
	xhr.send();
}

</script>
