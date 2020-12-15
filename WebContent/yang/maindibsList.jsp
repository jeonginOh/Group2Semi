<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script> <!-- 사이트에서 아이콘 가져옴 -->
<meta charset="UTF-8">
<title>yang/dibsList</title>
<style>
	#whole{width: 300px;}
	.pg{position: relative; left: 80px;}
	.pg:link{color: black;} /* a태그 색 : 링크가 걸려있는경우,방문한경우,현재활동페이지인경우 모두 검정으로 처리 */
	.pg:visited{color:black;}
	.pg:active{color:black;}
	.box{display: inline-block;}
	.item{width:100px; height: 100px; }
	span{font-size: 0.8em;}
</style>
<script type="text/javascript">
	var pageNum=1; //모든 함수에서 쓰기때문에 전역변수로
	function listDibs(){
		//console.log(pageNum); //몇페이지인지 확인용
		var xhr=new XMLHttpRequest();
		var pgup=document.createElement("a");
		if(pageNum==1){
			pgup.style.visibility="hidden";
		}
		pgup.href="javascript:prevDibs()";
		pgup.className="pg";
		
		var pageUp=document.createElement("i");
		pageUp.className="fas fa-sort-up fa-2x";
		
		pgup.appendChild(pageUp);
		
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4 && xhr.status==200){
				var json=JSON.parse(xhr.responseText);
				var wrap=document.getElementById("wrap");
				//var count=0;
				for(let i=1;i<json.length;i++){ //배열의 첫번째에는 페이지개수가 나오기때문에 그것을 빼고 빼옴
					var div=document.createElement("div");
					//div.className="box";
					var div1=document.createElement("div");
					var div2=document.createElement("div");
					div1.className="box";
					div2.className="box";
					div2.style.textAlign="center";
					var a=document.createElement("a");
					var img=document.createElement("img");
					img.className="item";
					var itemname=document.createElement("span");
					var br1=document.createElement("br");
					var br2=document.createElement("br");
					var br3=document.createElement("br");
					var avail=document.createElement("span");
					itemname.style.lineHeight="50px";
					itemname.innerHTML=json[i].itemname;
					if(json[i].avail==0){
						avail.innerHTML="판매불가";
					}else{
						avail.innerHTML="판매중";
					}
					
					a.href="yangsuccess.html";
					img.src="images/"+json[i].image;
					a.appendChild(img);
					
					
					
					div1.appendChild(a);
					div2.appendChild(itemname);
					div2.appendChild(br2);
					div2.appendChild(avail);
					div2.appendChild(br3);
					div.appendChild(div1);
					div.appendChild(div2);
					
					wrap.appendChild(div);
					
				}
				var pgdwn=document.createElement("a");
				console.log(pageNum);
				console.log(json.lastpage);
				if(pageNum>=json[0].lastpage){ //마지막페이지보다 같거나 클때 아래화살표를 안보이게 하기
					pgdwn.style.visibility="hidden";
				}
				pgdwn.href="javascript:nextDibs()";
				pgdwn.className="pg";
				var pageDown=document.createElement("i");
				pageDown.className="fas fa-sort-down fa-2x";
				
				pgdwn.appendChild(pageDown);
				
				wrap.prepend(pgup);
				wrap.append(pgdwn);
			}
		}
		xhr.open('get','../mainlist.yang?bd=d&pageNum='+pageNum,true);
		xhr.send();
	}
	listDibs();
	function prevDibs(){ //위버튼
		pageNum--;
		var wrap=document.getElementById("wrap");
		while(wrap.firstChild){ //기존의 리스트를 전부 삭제
			wrap.removeChild(wrap.lastChild);
		}
		listDibs(); //페이지넘버가 바뀌고 다시 리스트를 가져옴
	}
	function nextDibs(){ //아래버튼
		pageNum++;
		var wrap=document.getElementById("wrap");
		while(wrap.firstChild){
			wrap.removeChild(wrap.lastChild);
		}
		listDibs();
	}
</script>
</head>
<body>

<h1>찜목록</h1>
<div id="wrap">
</div>
</body>
</html>