<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type = "text/javascript" src = "http://code.jquery.com/jquery-latest.min.js"></script>
<script type = "text/javascript" src = "https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.min.js"></script>
<script type = "text/javascript" src = "https://html2canvas.hertzen.com/dist/html2canvas.min.js"></script>

<div id="pdf" style="background-color: #EAEAEA; text-align: center; width: 890px;">

<h1>영수증</h1>
<br>
<br>
<h3>구매정보</h3>
<span>주문번호:${ordernum }</span>
<br>
<span>상품명:${repItem } 등 ${itemAmount }개</span>
<br>
<span>거래일시:${buydate }</span>
<br>
<span>부가세:${surtax }원 /</span>
<span>합계금액:${totprice }원</span>
<br>
<span>결제수단:${paywith }</span>
<br><br>
<h3>이용상점정보</h3>
<span>판매자 상호 : 신토불이 슈퍼마켓</span>
<br>
<span>판매자 사업자등록번호 : 123-45-67890</span>
<br>
<span>판매자 주소 : 서울 종로구 율곡로10길 105</span>
<br>
<br><br>
<p style="font-size: 0.8em;">위 영수증은 부가가치세법 제 32조의 2제 3항에 의하여 발행된것이 아니며</p>
<p style="font-size: 0.8em;">동법 시행령 제57조 2항과는 아무 관련이 없습니다.</p>

</div>
<div style="text-align: center;">
<div>
	<input type="button" id="createPDF" value="영수증 저장">
</div>
<div>
	<a href="<%=request.getContextPath()%>/jeungIn/main.jsp">홈으로</a> ||
	<a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=/jeungIn/cardlist.jsp">상품 더보기</a>
</div>
</div>

<script type = "text/javascript">
$('#createPDF').click(function() {
	  //div를 canvas객체로 변환
	  html2canvas($('#pdf')[0]).then(function(canvas) {
	    var doc = new jsPDF('p', 'mm', 'a4'); //jspdf객체 생성
	    var imgData = canvas.toDataURL('image/png'); //캔버스를 이미지로 변환
	    doc.addImage(imgData, 'PNG', 0, 0); //이미지를 기반으로 pdf생성
	    doc.save('recipt.pdf'); //pdf저장
	  });
	});
	
function processKey() {
    if( (event.ctrlKey == true && (event.keyCode == 78 || event.keyCode == 82)) || (event.keyCode >= 112 && event.keyCode <= 123) || event.keyCode == 8)
    { 
         event.keyCode = 0; 
         event.cancelBubble = true; 
         event.returnValue = false;
    }
}

document.onkeydown = processKey;
</script>