<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
no : ${bag.bbsShareNo}<br>
request : ${bag.bbsShareRequest}<br>
title : ${bag.bbsShareTitle}<br>
content : ${bag.bbsShareContent}<br>
writer : ${bag.bbsShareWriter}<br>
date : ${bag.bbsShareDate}<br>
category : ${bag.bbsShareCategory}<br>
view : ${bag.bbsShareView}<br>
interest : ${bag.bbsShareInterest}<br>
success : ${bag.bbsShareSuccess}<form action="success" method="get">
	<input type="hidden" name ="bbsShareNo" value="${bag.bbsShareNo}">
	<button type="submit">완료처리</button>
</form><br>
image : ${bag.bbsShareImage}<br>
</body>
</html>