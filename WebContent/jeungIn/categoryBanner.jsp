<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <ul class = "bigcate">
    	<li>
    		<a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=categoryList.jsp?catid=111000"><i class="fas fa-carrot"></i>채소</a>
    		<ul class ="smallcate" id="ul_vegi">
    			<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=categoryList.jsp?catid=111000100">콩나물/버섯류</a></li>
    			<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=categoryList.jsp?catid=111000200">시금치/부추/나물</a></li>
    			<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=categoryList.jsp?catid=111000300">양파/마늘/생각/파</a></li>
    			<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=categoryList.jsp?catid=111000400">기본채소</a></li>
    			<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=categoryList.jsp?catid=111000500">간편포장</a></li>
    		</ul>
    	</li>
    	<li>
    		<a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=categoryList.jsp?catid=222000"><i class="fas fa-apple-alt"></i>과일</a>
    		<ul class ="smallcate">
    			<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=categoryList.jsp?catid=222000100">계절과일</a></li>
    			<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=categoryList.jsp?catid=222000200">국산</a></li>
    			<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=categoryList.jsp?catid=222000300">수입</a></li>
    			<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=categoryList.jsp?catid=222000400">냉동</a></li>
    			<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=categoryList.jsp?catid=222000500">견과류</a></li>
    		</ul>
    	</li>
    	<li>
    		<a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=categoryList.jsp?catid=333000"><i class="fas fa-fish"></i>수산</a>
    		<ul class ="smallcate">
    			<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=categoryList.jsp?catid=333000100">생선</a></li>
    			<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=categoryList.jsp?catid=333000200">갑각류</a></li>
    			<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=categoryList.jsp?catid=333000300">어패류</a></li>
    			<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=categoryList.jsp?catid=333000400">가공품</a></li>
    			<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=categoryList.jsp?catid=333000500">기타</a></li>
    		</ul>
    	</li>
    	<li>
    		<a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=categoryList.jsp?catid=444000"><i class="fas fa-drumstick-bite"></i>정육</a>
    		<ul class ="smallcate">
    			<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=categoryList.jsp?catid=444000100">소</a></li>
    			<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=categoryList.jsp?catid=444000200">돼지</a></li>
    			<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=categoryList.jsp?catid=444000300">조류</a></li>
    			<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=categoryList.jsp?catid=444000400">양념</a></li>
    			<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=categoryList.jsp?catid=444000500">계란류</a></li>
    		</ul>
    	</li>
    	<li>
    		<a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=categoryList.jsp?catid=555000"><i class="fas fa-box"></i>완제품</a>
    		<ul class ="smallcate">
    			<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=categoryList.jsp?catid=555000100">샐러드/도시락</a></li>
    			<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=categoryList.jsp?catid=555000200">간편식/냉동</a></li>
    			<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=categoryList.jsp?catid=555000300">밥/면/즉석식품</a></li>
    			<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=categoryList.jsp?catid=555000400">만두/튀김</a></li>
    			<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=categoryList.jsp?catid=555000500">선식/씨리얼</a></li>
    		</ul>
    	</li>
    	<li>
    		<a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=categoryList.jsp?catid=666000"><i class="fas fa-wine-bottle"></i>음료/간식</a>
    		<ul class ="smallcate">
    			<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=categoryList.jsp?catid=666000100">생수/음료/쥬스</a></li>
    			<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=categoryList.jsp?catid=666000200">커피/차</a></li>
    			<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=categoryList.jsp?catid=666000300">유제품</a></li>
    			<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=categoryList.jsp?catid=666000400">초콜릿/젤리/캔디</a></li>
    			<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=categoryList.jsp?catid=666000500">간식</a></li>
    		</ul>
    	</li>
    </ul>