<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" rel="stylesheet">
<style>
	.bigcate .smallcate {list-style:none;display:none;position:absolute;left:60px;top:0px;}
	.bigcate li:hover>ul{display:block;}
	.bigcate {list-style:none;margin:0px;padding:0px;position:relative;}/*마진:바깥 여백, 패딩:안쪽여백*/

</style>
    <ul class = "bigcate">
    	<li>
    		<a href="#"><i class="fas fa-carrot"></i>채소</a>
    		<ul class ="smallcate">
    			<li><a href="#">콩나물/버섯류</a></li>
    			<li><a href="#">시금치/부추/나물</a></li>
    			<li><a href="#">양파/마늘/생각/파</a></li>
    			<li><a href="#">기본채소</a></li>
    			<li><a href="#">간편포장</a></li>
    		</ul>
    	</li>
    	<li>
    		<a href="#"><i class="fas fa-apple-alt"></i>과일</a>
    		<ul class ="smallcate">
    			<li><a href="#">계절과일</a></li>
    			<li><a href="#">국산</a></li>
    			<li><a href="#">수입</a></li>
    			<li><a href="#">냉동</a></li>
    			<li><a href="#">견과류</a></li>
    		</ul>
    	</li>
    	<li>
    		<a href="#"><i class="fas fa-fish"></i>수산</a>
    		<ul class ="smallcate">
    			<li><a href="#">생선</a></li>
    			<li><a href="#">갑각류</a></li>
    			<li><a href="#">어패류</a></li>
    			<li><a href="#">가공품</a></li>
    			<li><a href="#">기타</a></li>
    		</ul>
    	</li>
    	<li>
    		<a href="#"><i class="fas fa-drumstick-bite"></i>정육</a>
    		<ul class ="smallcate">
    			<li><a href="#">소</a></li>
    			<li><a href="#">돼지</a></li>
    			<li><a href="#">조류</a></li>
    			<li><a href="#">양념</a></li>
    			<li><a href="#">계란류</a></li>
    		</ul>
    	</li>
    	<li>
    		<a href="#"><i class="fas fa-box"></i>완제품</a>
    		<ul class ="smallcate">
    			<li><a href="#">샐러드/도시락</a></li>
    			<li><a href="#">간편식/냉동</a></li>
    			<li><a href="#">밥/면/즉석식품</a></li>
    			<li><a href="#">만두/튀김</a></li>
    			<li><a href="#">선식/씨리얼</a></li>
    		</ul>
    	</li>
    	<li>
    		<a href="#"><i class="fas fa-wine-bottle"></i>음료/간식</a>
    		<ul class ="smallcate">
    			<li><a href="#">생수/음료/쥬스</a></li>
    			<li><a href="#">커피/차</a></li>
    			<li><a href="#">유제품</a></li>
    			<li><a href="#">초콜릿/젤리/캔디</a></li>
    			<li><a href="#">간식</a></li>
    		</ul>
    	</li>
    </ul>