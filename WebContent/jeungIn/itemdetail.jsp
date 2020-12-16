<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<script type="text/javascript">
	window.onload=function(){
		var xhr = null;
		xhr = new XMLHttpRequest();
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4 && xhr.status==200){
				var json = JSON.parse(xhr.responseText);
				var div = document.getElementById("detailimg");
				
				var itemid = json.itemid;
				var itemname = json.itemname;
				var catid = json.catid;
				var price = json.price;
				var factory = json.factory;
				var origin = json.origin;
				var stock = json.stock;
				var expire = json.expire;
				var storedate = json.storedate;
				var image = json.image;
				var avail = json.avail;
				
				document.getElementById('itemname').innerHTML=itemname;
				document.getElementById('itemprice').innerHTML=price;
				
				document.getElementById('detailfactory').innerHTML=factory;
				document.getElementById('detailorigin').innerHTML=origin;
				document.getElementById('detailexpire').innerHTML=expire;
				document.getElementById('detailstock').innerHTML=stock;
				
				if(세션체크){
					document.getElementById('itemname').innerHTML="로그인 하시면 10% 적립 및 쿠폰혜택 !";
				}
			}
		};
		xhr.open('get','<%= request.getContextPath() %>/detailitem.do?itemid=1234',true);
		xhr.send();
	}
</script>

<div id = "detail">
	<div id = "detailimg"></div>
	<p class = "detailtitle">
		<span id = "itemname"></span>
	</p>
	<p class = "detailprice">
		<span>가격</span>
		<span id = "itemprice">|</span>
	</p>
	<p class = "adv">
		<span id = "salenonsale"></span>
	<div class = "detailitem">
		<dl class = "list">
			<dt class ="tit">제조사</dt>
			<dd id = "detailfactory">|</dd>
		</dl>
		<dl class = "list">
			<dt class ="tit">원산지</dt>
			<dd id = "detailorigin">|</dd>
		</dl>
		<dl class = "list">
			<dt class ="tit">유통기한</dt>
			<dd id = "detailexpire">|</dd>
		</dl>
	</div>
	<div class = "detailamount">
		<dl class ="list">
			<dt class = "tit">구매수량</dt>
			<dd><input type="number" id="age" min="1" max="150"></dd>
		</dl>
		<dl class = "list">
			<dt class ="tit">남은 재고</dt>
			<dd id = "detailstock">|</dd>
		</dl>
	</div>
	<div class = "purchase">
		<button type = "button" onclick="location.href='장바구니 링크 여기 주렴'">
			<img src = "<%=request.getContextPath() %>/images/구매버튼.png" style="width:100px;height:100px">
		</button>
	</div>
</div>