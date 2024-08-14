<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.javaex.vo.PersonVo" %>

<% 
	PersonVo personVo = (PersonVo)request.getAttribute("personVo");

	System.out.println("------------------");
	System.out.println(personVo.getName());
%>

<!DOCTYPE html>
<html lang="ko">
	<head>
	<meta charset="UTF-8">
	<title>editForm</title>
	</head>
	
	<body>
		
		<h1>전화번호부</h1>
		
		<h2>전화번호-수정폼</h2>
		
		<p>수정할 항목을 입력한 후 수정버튼을 눌러주세요!_!</p>
		
		<form action="" method="">
			
			<div>
				<label for="txt-name">이름(name):</label>
				<input id="txt-name" type="text" name="name" value="<%= personVo.getName()%>" placeholder="이름">
			</div>
			<div>
				<label for="txt-hp">핸드폰(hp):</label>
				<input id="txt-hp" type="text" name="hp" value="<%= personVo.getHp() %>" placeholder="핸드폰">
			</div>
				
			<div>
				<label for="txt-company">회사(company):</label>
				<input id="txt-company" type="text" name="company" value="<%= personVo.getCompany() %>" placeholder="회사">
			</div>
			
			
			<input type="text" name="action" value="update">
			<input type="text" name="no" value="<%= personVo.getPersonId() %>">
			<br>
			<button type="submit">수정</button>
		
		</form>
		<br><br>
		<a href="">리스트로 가기</a>
		
	</body>
	
</html>