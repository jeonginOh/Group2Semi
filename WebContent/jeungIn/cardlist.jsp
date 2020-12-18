<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap 4</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <style>
      .card p { margin:20px 0px;}
      #paging{text-align:center;}
    </style>
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
						imglink.src = img;
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
						btn.href="itemdetail.jsp?itemid="+json.arr[i].itemid;
						btn.className="btn btn-primary";
						btn.innerHTML="More";
						
						row[0].appendChild(col3);
						col3.appendChild(card);
						card.appendChild(imglink);
						card.appendChild(cardbody);
						cardbody.appendChild(cardtitle);
						cardbody.appendChild(cardtext);
						cardbody.appendChild(btn);
					}
					if(json.startPageNum>10){l
						var prev = document.creatElement("a");
						<%-- prev.href="<%=request.getContextPath() %>/list.do?pageNum="+json.startPageNum; --%>
						prev.href="<%=request.getContextPath() %>/jeungIn/cardlist.jsp?pageNum="+json.startPageNum;
						prev.innerHTML="이전";
						paging.appendChild(prev);
					}
					for(var i = json.startPageNum;i<=json.endPageNum;i++){
						var pageN = document.createElement("a");
						<%-- pageN.href="<%= request.getContextPath() %>/list.do?pageNum="+i; --%>
						pageN.href="<%= request.getContextPath() %>/jeungIn/cardlist.jsp?pageNum="+i;
						pageN.innerHTML=i;
						paging.appendChild(pageN);
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
	</script>
  </head>
  <body>
    <div class="container">
      <div class="row">
      </div>
    </div>
    
    <div id="paging"></div>
  </body>
</html>