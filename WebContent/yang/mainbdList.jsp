<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style>
/*	#wholeBasket{border: 1px solid black; width:180px; height: 400px; padding-top: 30px; padding-left: 20px;}
	#whole{width: 300px;}
	.pg{position: relative; left: 80px;}
	.pg:link{color: black;}
	.pg:visited{color:black;}
	.pg:active{color:black;}
	.bkbox{display: inline-block;}
	.bkitem{width:100px; height: 100px; }
	.bkspan{font-size: 0.8em;}*/
</style>

<script type="text/javascript">
	var bpageNum=1; //모든 함수에서 쓰기때문에 전역변수로
	function mainListBasket(){
		//console.log(pageNum); //몇페이지인지 확인용
		var xhr=new XMLHttpRequest();
		var bpgup=document.createElement("a");
		if(bpageNum==1){
			bpgup.style.visibility="hidden";
		}
		bpgup.href="javascript:prevBasket()";
		bpgup.className="pg";
		
		var bpageUp=document.createElement("i");
		bpageUp.className="fas fa-sort-up fa-2x";
		
		bpgup.appendChild(bpageUp);
		
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4 && xhr.status==200){
				var json=JSON.parse(xhr.responseText);
				var wrap=document.getElementById("basketWrap");
				//var count=0;
				for(let i=1;i<json.length;i++){ //배열의 첫번째에는 페이지개수가 나오기때문에 그것을 빼고 빼옴
					var div=document.createElement("div");
					//div.className="box";
					var div1=document.createElement("div");
					var div2=document.createElement("div");
					div1.className="bkbox";
					div2.className="bkbox";
					div2.style.textAlign="center";
					var a=document.createElement("a");
					var img=document.createElement("img");
					img.className="bkitem";
					var itemname=document.createElement("span");
					var br1=document.createElement("br");
					var br2=document.createElement("br");
					var br3=document.createElement("br");
					var avail=document.createElement("span");
					itemname.className="bksspan";
					avail.className="bksspan";
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
				var bpgdwn=document.createElement("a");
				if(bpageNum>=json[0].lastpage){ //마지막페이지보다 같거나 클때 아래화살표를 안보이게 하기
					bpgdwn.style.visibility="hidden";
				}
				bpgdwn.href="javascript:nextBasket()";
				bpgdwn.className="pg";
				var bpageDown=document.createElement("i");
				bpageDown.className="fas fa-sort-down fa-2x";
				
				bpgdwn.appendChild(bpageDown);
				
				wrap.prepend(bpgup);
				wrap.append(bpgdwn);
			}
		}
		xhr.open('get','<%=request.getContextPath()%>/mainlist.yang?bd=b&pageNum='+bpageNum,true);
		xhr.send();
	}
	mainListBasket();
	function prevBasket(){ //위버튼
		bpageNum--;
		var wrap=document.getElementById("basketWrap");
		while(wrap.firstChild){ //기존의 리스트를 전부 삭제
			wrap.removeChild(wrap.lastChild);
		}
		mainListBasket(); //페이지넘버가 바뀌고 다시 리스트를 가져옴
	}
	function nextBasket(){ //아래버튼
		bpageNum++;
		var wrap=document.getElementById("basketWrap");
		while(wrap.firstChild){
			wrap.removeChild(wrap.lastChild);
		}
		mainListBasket();
	}
</script>

<h1 style="font-size: 2rem; margin-top: 32px;">장바구니목록</h1>
<div id="wholeBasket">
<div id="basketWrap">
</div>
</div>
<br><br><br>



<style>
/*	#wholeDibs{border: 1px solid black; width:180px; height: 400px; padding-top: 30px; padding-left: 20px;}
	.pg{position: relative; left: 80px;}
	.pg:link{color: black;}
	.pg:visited{color:black;}
	.pg:active{color:black;}
	.dibsbox{display: inline-block;}
	.dibsitem{width:100px; height: 100px; }
	.dibsspan{font-size: 0.8em;}*/
</style>

<script type="text/javascript">
	var dpageNum=1; //모든 함수에서 쓰기때문에 전역변수로
	function mainListDibs(){
		//console.log(pageNum); //몇페이지인지 확인용
		var xhr=new XMLHttpRequest();
		var dpgup=document.createElement("a");
		if(dpageNum==1){
			dpgup.style.visibility="hidden";
		}
		dpgup.href="javascript:prevDibs()";
		dpgup.className="pg";
		
		var dpageUp=document.createElement("i");
		dpageUp.className="fas fa-sort-up fa-2x";
		
		dpgup.appendChild(dpageUp);
		
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4 && xhr.status==200){
				var json=JSON.parse(xhr.responseText);
				var wrap=document.getElementById("dibsWrap");
				//var count=0;
				for(let i=1;i<json.length;i++){ //배열의 첫번째에는 페이지개수가 나오기때문에 그것을 빼고 빼옴
					var div=document.createElement("div");
					//div.className="box";
					var div1=document.createElement("div");
					var div2=document.createElement("div");
					div1.className="dibsbox";
					div2.className="dibsbox";
					div2.style.textAlign="center";
					var a=document.createElement("a");
					var img=document.createElement("img");
					img.className="dibsitem";
					var itemname=document.createElement("span");
					var br1=document.createElement("br");
					var br2=document.createElement("br");
					var br3=document.createElement("br");
					var avail=document.createElement("span");
					itemname.className="dibsspan";
					avail.className="dibsspan";
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
				var dpgdwn=document.createElement("a");
				console.log(dpageNum);
				if(dpageNum>=json[0].lastpage){ //마지막페이지보다 같거나 클때 아래화살표를 안보이게 하기
					dpgdwn.style.visibility="hidden";
				}
				dpgdwn.href="javascript:nextDibs()";
				dpgdwn.className="pg";
				var dpageDown=document.createElement("i");
				dpageDown.className="fas fa-sort-down fa-2x";
				
				dpgdwn.appendChild(dpageDown);
				
				wrap.prepend(dpgup);
				wrap.append(dpgdwn);
			}
		}
		xhr.open('get','<%=request.getContextPath()%>/mainlist.yang?bd=d&pageNum='+dpageNum,true);
		xhr.send();
	}
	mainListDibs();
	function prevDibs(){ //위버튼
		dpageNum--;
		var wrap=document.getElementById("dibsWrap");
		while(wrap.firstChild){ //기존의 리스트를 전부 삭제
			wrap.removeChild(wrap.lastChild);
		}
		mainListDibs(); //페이지넘버가 바뀌고 다시 리스트를 가져옴
	}
	function nextDibs(){ //아래버튼
		dpageNum++;
		var wrap=document.getElementById("dibsWrap");
		while(wrap.firstChild){
			wrap.removeChild(wrap.lastChild);
		}
		mainListDibs();
	}
</script>

<h1 style="font-size: 2rem;">찜목록</h1>
<div id="wholeDibs">
<div id="dibsWrap">
</div>
</div>

