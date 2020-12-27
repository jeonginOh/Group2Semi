<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<div id = "headerTop">
		<div id = "toptop">
			<ul>
				<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=/member/join.jsp"><i class="fas fa-user-plus"></i></a></li>
				<li> | </li>
				<c:choose>
					<c:when test="${not empty sessionScope.memid }">
				<li><span title="로그아웃"><a href="<%=request.getContextPath() %>/auth/logout.do"><i class="fas fa-sign-in-alt"></i></a></span></li>		
					</c:when>
					<c:otherwise>
				<li><span title="로그인"><a href="<%=request.getContextPath() %>/auth/login.jsp"><i class="fas fa-sign-in-alt"></i></a></span></li>
					</c:otherwise>
				</c:choose>
				<li> | </li>
				<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=/ask_list"><i class="fas fa-headset"></i></a></li>
			</ul>
			<a href="<%=request.getContextPath() %>/jeungIn/main.jsp" ><img src = "<%=request.getContextPath() %>/images/mainimage2.png" id="mainimg"></a>
	  	</div>
	  	<div id = top2>
		  	<ul>
				<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=/jeungIn/cardlist.jsp">전체 카테고리</a></li>
				<li> | </li>
				<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=">신상품</a></li>
				<li> | </li>
				<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=">베스트상품</a></li>
				<li> | </li>
				<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=">할인상품</a></li>
				<li> | </li>
				<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=">이벤트</a></li>
<!-- 				<li><input type="text" value ="검색어를 입력하세요"></li> -->
				<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=/search_item/search_list.jsp"><i class="fas fa-search"></i></a></li>
				<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=/yang/dibsListpage.jsp"><i class="fas fa-heart"></i></a></li>
				<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=/yang/basketListpage.jsp"><i class="fas fa-shopping-cart"></i></a></li>
				<li><a href="<%=request.getContextPath() %>/jeungIn/main.jsp?spage=/logistic.do"><i class="fas fa-shipping-fast"></i></a></li>
			</ul>
		</div>
	</div>