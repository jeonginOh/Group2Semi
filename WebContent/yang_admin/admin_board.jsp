<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
	tr.exline td{border-bottom: 1px solid black;}
</style>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);
      google.charts.setOnLoadCallback(drawChart2);

window.onload=function(){
	var rank=document.getElementById("rank");
	var sales=document.getElementById("sales");
	var code="<c:out value='${code}'/>";
	
	if(code=='rank'){
		rank.style.visibility="visible";
		sales.style.visibility="hidden";
	}else if(code=='sales'){
		rank.style.visibility="hidden";
		sales.style.visibility="visible";
	}
}

function drawChart() {
	
var itemid=document.getElementsByName("itemid");
var cnt=document.getElementsByName("count");
var data = new google.visualization.DataTable();

data.addColumn('string','Itemname');
data.addColumn('number','cnt');
for(let i=0;i<cnt.length;i++){
	data.addRow([itemid[i].value,cnt[i].value*1]);
}

var options = {
	title: '판매량 가장 높은 순위5개'
};

var chart = new google.visualization.PieChart(document.getElementById('piechart'));

chart.draw(data, options);
}

function drawChart2() {
	
	var dat=document.getElementsByName("dateD");
	var totprice=document.getElementsByName("totprice");
	var data = new google.visualization.DataTable();

	data.addColumn('string','시간');
	data.addColumn('number','판매액');
	for(let i=0;i<totprice.length;i++){
		data.addRow([dat[i].value,totprice[i].value*1]);
	}

	var options = {
			title: '날짜별 판매금액', 
			fontSize: '12',
			fontName: '굴림체',
			hAxis: {
				title: '날짜', 
				titleTextStyle: {color: 'red', fontName: '굴림체'}
			} ,      

			vAxis: {
				title: '매출액', 
				titleTextStyle: {color: 'blue', fontName: '굴림체'}
			} 
	};
	var chart = new google.visualization.ColumnChart(document.getElementById("barchart_values"));
	chart.draw(data, options);
}

function categoryChange(e){
	var detailY=["전체"];
	var detailM=["전체","2016년","2017년","2018년","2019년","2020년"];
	var detailD=["전체","1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"];
	var detail=document.getElementById("detail");
	
	if(e.value=='y') var d=detailY;
	else if(e.value=="m") var d=detailM;
	else if(e.value=="d") var d=detailD;
	
	detail.options.length=0;
	
	for (x in d) {
	    var opt = document.createElement("option");
	    opt.value = d[x];
	    opt.innerHTML = d[x];
	    detail.appendChild(opt);
	  }
}

function showRank(){
	var rank=document.getElementById("rank");
	var sales=document.getElementById("sales");
	rank.style.visibility="visible";
	sales.style.visibility="hidden";
}

function showSales(){
	var rank=document.getElementById("rank");
	var sales=document.getElementById("sales");
	rank.style.visibility="hidden";
	sales.style.visibility="visible";
}
</script>
<div id="wrap" style="width: 800px;">
<input type="button" value="판매순위" onclick="showRank()">
<input type="button" value="매출표" onclick="showSales()">
<div id="rank" style="text-align: center; visibility: hidden; position: absolute; left: 300px; top: 150px;">
<h1>판매순위(상위 5개)</h1>
<form action="<%=request.getContextPath() %>/admbuystats.do" method="post">
<input type="radio" name="ymd" value="y" checked="checked"><span>연도별</span>
<input type="radio" name="ymd" value="m"><span>월별</span>
<input type="radio" name="ymd" value="d"><span>일별</span>
<br>
<input type="text" style="width: 400px;"placeholder="ex)2020 ex)202001 ex)20201223" name="dat">
<input type="submit" value="조회">
</form>
<c:forEach var="vo" items="${list }" varStatus="status">
	<input type="hidden" value="${itemname[status.index] }" name="itemid">
	<input type="hidden" value="${vo.count }" name="count">
</c:forEach>
<div id="piechart" style="width: 900px; height: 500px;"></div>
<!-- <div id="barchart_values" style="width: 900px; height: 300px;"></div> -->
</div>


<div id="sales" style="text-align: center; visibility: hidden; position: absolute; left: 300px; top: 150px;">
<form action="<%=request.getContextPath() %>/admsales.do" method="post">
<select onchange="categoryChange(this)" name="ymd">
	<option value="y">연도별 매출</option>
	<option value="m">월별 매출</option>
	<option value="d">올해의 날짜별 매출</option>
</select>
<select id="detail" name="detail">
	<option value="전체">전체</option>
</select>
<input type="submit" value="조회하기">
</form>
<c:forEach var="sales" items="${sales }">
	<input type="hidden" value="${sales.dat }" name="dateD">
	<input type="hidden" value="${sales.price }" name="totprice">
</c:forEach>
<div id="barchart_values" style="width: 900px; height: 300px;"></div>
<div style="text-align: center;">
	<table style="border: 1px solid black; width: 700px;">
		<tr>
			<th>날짜</th>
			<th>물품</th>
			<th>판매액</th>
		</tr>
		<c:forEach var="tblsales" items="${tblsales }">
		<c:choose>
		<c:when test="${tblsales.itemname eq '합계' }">
		<tr class="exline">
			<td>${tblsales.dat }</td>
			<td>${tblsales.itemname }</td>
			<td>${tblsales.price }</td>
		</tr>
		</c:when>
		<c:otherwise>
		<tr>
			<td>${tblsales.dat }</td>
			<td>${tblsales.itemname }</td>
			<td>${tblsales.price }</td>
		</tr>
		</c:otherwise>
		</c:choose>
		</c:forEach>
	</table>
</div>
</div>
</div>