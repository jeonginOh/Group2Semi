<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<style>
	ul{list-style:none;}
</style>

<div align="center">
	<ul>
		<li><a href="<%=request.getContextPath()%>/admin_jeungin/adminFrame.jsp?spage=/admin_jeungin/storageinfo.jsp">물품조회</a></li>
		<li><a href="<%=request.getContextPath()%>/admin_jeungin/adminFrame.jsp?spage=/admin_jeungin/storageInsert.jsp">물품등록</a></li>
		<li><a href="<%=request.getContextPath()%>/admLogistic.do">배송관리</a></li>
		<li><a href="<%=request.getContextPath()%>/admin_jeungin/adminFrame.jsp?spage=/yang_admin/admin_board.jsp">매출관리</a></li>
		<li><a href="<%=request.getContextPath()%>/admin_jeungin/adminFrame.jsp?spage=/admin_review/adminlist2.jsp">리뷰조회</a></li>
	</ul>
</div>