<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

    <script type="text/javascript">
		window.onload=function(){
			var xhr = null;
			xhr = new XMLHttpRequest();
			xhr.onreadystatechange=function(){
				if(xhr.readyState==4 && xhr.status==200){
					var json = JSON.parse(xhr.responseText);
					var row = document.getElementsByClassName("row");
					var paging = document.getElementById("paging");
					
					for(var i=0;i<json.arr.length;i++){
						var img = json.arr[i].image;
						var itemname = json.arr[i].itemname;
						var price = json.arr[i].price;
						
						var col3=document.createElement("div");
						col3.className="col-3";
						var card=document.createElement("div");
						card.className="card";
						var imglink = document.createElement("img");
						imglink.src = "<%=request.getContextPath()%>/product/"+img;
						imglink.style.width="193px";
						imglink.style.height="150px";
						imglink.alt = "";
						var cardbody = document.createElement("div");
						cardbody.className="card-body";
						var cardtitle = document.createElement("h5");
						cardtitle.className="card-title";
						cardtitle.innerHTML=itemname;
						var cardtext = document.createElement("p");
						cardtext.className="card-text";
						cardtext.innerHTML=price;
						var btn = document.createElement("a");
						//아래 경로부분 바꿈%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
						btn.href="<%=request.getContextPath()%>/jeungIn/main.jsp?spage=/jeungIn/itemdetail.jsp?itemid="+json.arr[i].itemid;
						btn.className="btn btn-primary";
						btn.innerHTML="More";
						var btn2 = document.createElement("a");
						btn2.style="cursor:pointer";
						btn2.setAttribute("onclick", "insertDibs(event)");
						btn2.className="jjim";
						var heart = document.createElement("i");
						heart.className="fas fa-heart";
						btn2.appendChild(heart);
						var hdHeart=document.createElement("input");
						hdHeart.type="hidden";
						hdHeart.value=json.arr[i].itemid;
						
						row[0].appendChild(col3);
						col3.appendChild(card);
						card.appendChild(imglink);
						card.appendChild(cardbody);
						cardbody.appendChild(cardtitle);
						cardbody.appendChild(cardtext);
						cardbody.appendChild(btn);
						cardbody.appendChild(btn2);
						cardbody.appendChild(hdHeart);
					}
					if(json.startPageNum>10){
						var prev = document.creatElement("a");
						<%-- prev.href="<%=request.getContextPath() %>/list.do?pageNum="+json.startPageNum; --%>
						prev.href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=cardlist.jsp?pageNum="+json.startPageNum;
						prev.innerHTML="이전";
						paging.appendChild(prev);
					}
					for(var i = json.startPageNum;i<=json.endPageNum;i++){
						var pageN = document.createElement("a");
						<%-- pageN.href="<%= request.getContextPath() %>/list.do?pageNum="+i; --%>
						pageN.href="<%= request.getContextPath() %>/jeungIn/main.jsp?spage=cardlist.jsp?pageNum="+i;
						pageN.innerHTML=i;
						paging.appendChild(pageN);
					}if(json.endPageNum<json.pageCount){
						var next = document.createElement("a");
						next.href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=cardlist.jsp?pageNum="+json.endPagenum+1;
						next.innerHTML="다음";
						paging.appendChild(next);
					}
					
				}
			};
			var pageNum = '${ param.pageNum }';
			if(!pageNum) {
				pageNum = 1;
			}
			xhr.open('get','<%= request.getContextPath() %>/list.do?pageNum='+pageNum ,true);
			xhr.send();
		}
		
		function insertDibs(e){
			var itemid=e.target.parentNode.nextSibling.value;
			xhr=new XMLHttpRequest();
			xhr.onreadystatechange=function(){
				if(xhr.readyState==4 && xhr.status==200){
					var json=JSON.parse(xhr.responseText);
					if(json.code=="success"){
						alert("찜 성공!");
					}else if(json.code=="overlap"){
						alert("이미 찜 목록에 있는 상품입니다");
					}else if(json.code=="fail"){
						alert("오류로 인해 실패");
					}
				}
			}
			xhr.open('get','../basketinsert.do?&bd=d&itemid='+itemid,true);
			xhr.send();
		}
	</script>

    <div class="container">
      <div class="row">
      </div>
    </div>
    
    <div id="paging"></div>
