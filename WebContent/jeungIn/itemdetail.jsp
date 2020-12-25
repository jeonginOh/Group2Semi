<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	 <jsp:include page="reviewlist.jsp">
	 	<jsp:param value="${param.itemid }" name="itemid"/>
	 	<jsp:param value="${param.pageNum }" name="pageNum"/>
	 	<jsp:param value="${param.code }" name="code"/>
	 </jsp:include>

	
			
