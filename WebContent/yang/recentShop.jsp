<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<script type="text/javascript">
	var pageNum=1; //모든 함수에서 쓰기때문에 전역변수로
	function recentShop(){
		//console.log(pageNum); //몇페이지인지 확인용
		var xhr=new XMLHttpRequest();
		var pgup=document.createElement("a");
		if(pageNum==1){
			pgup.style.visibility="hidden";
		}
		pgup.href="javascript:prevRecent()";
		pgup.className="pg";
		
		var pageUp=document.createElement("i");
		pageUp.className="fas fa-sort-up fa-2x";
		
		pgup.appendChild(pageUp);
		
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4 && xhr.status==200){
				var json=JSON.parse(xhr.responseText);
				var wrap=document.getElementById("rwrap");
				//var count=0;
				for(let i=1;i<json.length;i++){ //배열의 첫번째에는 페이지개수가 나오기때문에 그것을 빼고 빼옴
					var div=document.createElement("div");
					//div.className="box";
					var div1=document.createElement("div");
					var div2=document.createElement("div");
					div1.className="recbox";
					div2.className="recbox";
					div2.style.textAlign="center";
					var a=document.createElement("a");
					var img=document.createElement("img");
					img.className="recitem";
					var itemname=document.createElement("span");
					var br1=document.createElement("br");
					var br2=document.createElement("br");
					var br3=document.createElement("br");
					var avail=document.createElement("span");
					itemname.className="recspan";
					avail.className="recspan";
					itemname.style.lineHeight="50px";
					itemname.innerHTML=json[i].itemname;
					if(json[i].avail==0){
						avail.innerHTML="판매불가";
					}else{
						avail.innerHTML="판매중";
					}
					
					a.href="<%= request.getContextPath() %>/jeungIn/main.jsp?spage=itemdetail.jsp?itemid="+json[i].itemid;
					img.src="<%=request.getContextPath()%>/product/"+json[i].image;
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
				if(pageNum>=json[0].lastpage){ //마지막페이지보다 같거나 클때 아래화살표를 안보이게 하기
					pgdwn.style.visibility="hidden";
				}
				pgdwn.href="javascript:nextRecent()";
				pgdwn.className="pg";
				var pageDown=document.createElement("i");
				pageDown.className="fas fa-sort-down fa-2x";
				
				pgdwn.appendChild(pageDown);
				
				wrap.prepend(pgup);
				wrap.append(pgdwn);
			}
		}
		xhr.open('get','<%=request.getContextPath()%>/recentShop.yang.do?pageNum='+pageNum,true);
		xhr.send();
	}
	recentShop();
	function prevRecent(){ //위버튼
		pageNum--;
		var wrap=document.getElementById("rwrap");
		while(wrap.firstChild){ //기존의 리스트를 전부 삭제
			wrap.removeChild(wrap.lastChild);
		}
		recentShop(); //페이지넘버가 바뀌고 다시 리스트를 가져옴
	}
	function nextRecent(){ //아래버튼
		pageNum++;
		var wrap=document.getElementById("rwrap");
		while(wrap.firstChild){
			wrap.removeChild(wrap.lastChild);
		}
		recentShop();
	}
</script>

<h1>최근 본 상품</h1>
<div id="recWhole">
<div id="rwrap">
</div>
</div>
