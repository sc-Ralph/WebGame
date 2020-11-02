<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="game.GameBean" %>
<%@ page import="game.User" %>
<%@ page import="game.Enemy" %>
<%@ page import="game.MessageLog" %>
<%
//リクエストスコープからのデータの取得
User player = (User)session.getAttribute("player");
Enemy enemy = (Enemy)session.getAttribute("enemy");
MessageLog log = (MessageLog)session.getAttribute("log");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>
	<p><%=enemy.getName() %> HP:<%=enemy.getHp() %>/<%=enemy.getMaxHp() %></p>

	<p><%=player.getName() %> HP:<%=player.getHp() %>/<%=player.getMaxHp() %></p>

	<p><%=log.getMessage() %></p>



	<%
	if(player.getHp()>0){
	for(int i = 0; i<4 ;i++){
		%>
	<p>
	<form action="play" method="post">

	<input type="hidden" name="choice" value="<%=i %>" />
	<input type="submit" value="<%=GameBean.choices[i] %>" />

	</form>
	</p>
	<%} %>
<%} %>

</body>
</html>