<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
session.setAttribute("member_id", "나다라");
session.setAttribute("certification", "0");
session.setAttribute("address", "부산광역시_서구");
%>

	<a href="list?pageno=1">게시물 리스트</a>