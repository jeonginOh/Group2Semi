<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<style>
		
	</style>
<title>리스트얌</title>
	<script type="text/javascript">
		window.onload=function(){
			var xhr = null;
			xhr = new XMLHttpRequest();
			xhr.onreadystatechange=function(){

				if(xhr.readyState==4 && xhr.status==200){
					var arr = JSON.parse(xhr.responseText);
					var div = document.getElementById("list");
					
					for(var i=0;i<arr.length;i++){
						
						var img = arr[i].image;
						var itemname = arr[i].itemname;
						var price = arr[i].price;
						var br = document.createElement("br");
						var br1 = document.createElement("br");
						var br2 = document.createElement("br");
						var div2 = document.createElement("div");
						var img1 = document.createElement("img");
						var itemname1 = document.createElement("span");
						var price1 = document.createElement("span");
						div2.style.display="inline-block";
						img1.src=img;
						itemname1.innerHTML = itemname;
						price1.innerHTML=price;
						div2.appendChild(img1);
						div2.appendChild(br);
						div2.appendChild(itemname1);
						div2.appendChild(br1);
						div2.appendChild(price1);
						
						div.appendChild(div2);
						
						if(i%3==2){
							div.appendChild(br2);
						}else{}
					}
				}
			};
			xhr.open('get','../list2.do',true);
			xhr.send();
		}
	</script>
</head>
<body>
	<div id="list"></div>
</body>
</html>