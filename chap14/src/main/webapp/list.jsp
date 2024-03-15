<%@page import="guestbook.MessageListView"%>
<%@page import="guestbook.GetMessageListService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<%
String pageNumberStr = request.getParameter("page");
int pageNumber = 1;
if (pageNumberStr != null) {
	pageNumber = Integer.parseInt(pageNumberStr);
}
GetMessageListService messageListService = GetMessageListService.getInstance();
MessageListView viewData = messageListService.getMessageList(pageNumber);
%>
<c:set var="viewData" value="<%=viewData%>" />
<html>
<head>
<meta charset="UTF-8">
<title>방명록 메세지 목록</title>
</head>
<body>
	<form action="writeMessage.jsp" method="post">
		이름: <input type="text" name="guestName"><br> 암호: <input
			type="password" name="password"><br> 메세지:
		<textarea rows="3" cols="30" name="message"></textarea>
		<br> <input type="submit" value="메시지 남기기" />
	</form>
	<hr>
	<c:if test="${viewData.isEmpty()}">
	등록된 메시지가 없습니다.
</c:if>
	<c:if test="${!viewData.isEmpty()}">

	</c:if>


</body>
</html>