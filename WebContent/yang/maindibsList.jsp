<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>yang/dibsList</title>
<style>
	.box{display: inline-block;}
	.item{width:200px; height: 200px; }
</style>
<script type="text/javascript">
	function listDibs(){
		var xhr=new XMLHttpRequest();
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4 && xhr.status==200){
				var json=JSON.parse(xhr.responseText);
				var wrap=document.getElementById("wrap");
				//var count=0;
				for(let i=0;i<json.length;i++){
					var div=document.createElement("div");
					div.className="box";
					var a=document.createElement("a");
					var img=document.createElement("img");
					img.className="item";
					var itemname=document.createElement("span");
					var br1=document.createElement("br");
					var br2=document.createElement("br");
					var br3=document.createElement("br");
					var br4=document.createElement("br");
					var br5=document.createElement("br");
					var price=document.createElement("span");
					var stock=document.createElement("span");
					var avail=document.createElement("span");
					itemname.innerHTML=json[i].itemname;
					price.innerHTML=json[i].price+"원";
					if(json[i].stock==0){
						stock.innerHTML="재고없음";
					}else{
						stock.innerHTML="남은수량: "+json[i].stock;
					}
					if(json[i].avail==0){
						avail.innerHTML="판매불가";
					}else{
						avail.innerHTML="판매중";
					}
					
					a.href="yangsuccess.html";
					img.src="images/"+json[i].image;
					a.appendChild(img);
					div.appendChild(a);
					div.appendChild(br1);
					div.appendChild(itemname);
					div.appendChild(br2);
					div.appendChild(price);
					div.appendChild(br3);
					div.appendChild(stock);
					div.appendChild(br4);
					div.appendChild(avail);
					div.appendChild(br5);
					wrap.appendChild(div);
				}
			}
		}
		xhr.open('get','../basketlist.do',true);
		xhr.send();
	}
	listDibs();
</script>
</head>
<body>
<h1>찜목록</h1>
<div id="wrap">
</div>
</body>
</html>