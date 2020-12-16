<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<div id = "headerTop">
		<div id = "toptop">
			<ul>
				<li><a href="main.jsp?spage="><i class="fas fa-user-plus"></i></a></li>
				<li> | </li>
				<li><a href="main.jsp?spage="><i class="fas fa-sign-in-alt"></i></a></li>
				<li> | </li>
				<li><a href="main.jsp?spage="><i class="fas fa-headset"></i></a></li>
			</ul>
			<img src = "<%=request.getContextPath() %>/images/mainimage.png" id="mainimg">
	  	</div>
	  	<div id = top2>
		  	<ul>
				<li><a href="main.jsp?spage=">전체 카테고리</a></li>
				<li> | </li>
				<li><a href="main.jsp?spage=">신상품</a></li>
				<li> | </li>
				<li><a href="main.jsp?spage=">베스트상품</a></li>
				<li> | </li>
				<li><a href="main.jsp?spage=">할인상품</a></li>
				<li> | </li>
				<li><a href="main.jsp?spage=">이벤트</a></li>
				<li><input type="text" value ="검색어를 입력하세요"></li>
				<li><a href="main.jsp?spage="><i class="fas fa-search"></i></a></li>
				<li><a href="main.jsp?spage="><i class="fas fa-heart"></i></a></li>
				<li><a href="main.jsp?spage="><i class="fas fa-shopping-cart"></i></a></li>
			</ul>
		</div>
	</div>