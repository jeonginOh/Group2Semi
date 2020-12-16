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
      .card p { margin:20px 0px; }
    </style>
    <script type="text/javascript">
		window.onload=function(){
			var xhr = null;
			xhr = new XMLHttpRequest();
			xhr.onreadystatechange=function(){
				if(xhr.readyState==4 && xhr.status==200){
					var arr = JSON.parse(xhr.responseText);
					var row = document.getElementsByClassName("row");
					
					for(var i=0;i<arr.length;i++){
						var img = arr[i].image;
						var itemname = arr[i].itemname;
						var price = arr[i].price;
						
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
						btn.href="itemdetail.jsp?itemid="+arr[i].itemid;
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
				}
			};
			xhr.open('get','<%= request.getContextPath() %>/cateList.do?catid=${ param.catid}',true);
			xhr.send();
		}
	</script>
  </head>
  <body>
    <div class="container">
      <div class="row">
        <div class="col-3">
          <div class="card">
            <img src="<%=request.getContextPath() %>/images/귤.png" alt=""/>
            <div class="card-body">
              <h5 class="card-title">Lorem</h5>
              <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam egestas sed sem ut malesuada.</p>
              <a href="#" class="btn btn-primary">More</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>