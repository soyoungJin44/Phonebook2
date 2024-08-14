<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- 임포트 수동으로 해줘야함 --%>
<%@ page import="java.util.List"  %>
<%@ page import="com.javaex.vo.PersonVo"  %>


<% 	
	List<PersonVo> personList = (List<PersonVo>)request.getAttribute("personList"); 
	System.out.println("여기는 jsp");
	System.out.println(personList);
%>



<!DOCTYPE html>
<html lang="ko">
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	</head>
	
	<body>
	
		<h1>전화번호부</h1>
		
		<h2>전화번호-리스트</h2>
		
		<p>등록된 전화번호 리스트 입니다!!</p>
		
		<%for(int i=0; i<personList.size();i++){ %>
		<table border=1>
			<tbody>
				<tr>
					<th>이름(name)</th>
					<td><%= personList.get(i).getName() %></td>
				</tr>
				<tr>
					<th>핸드폰(hp)</th>
					<td><%= personList.get(i).getHp() %></td>
				</tr>
				<tr>
					<th>회사(company)</th>
					<td><%= personList.get(i).getCompany() %></td>
				</tr>
				<tr>
					<td><a href="http://localhost:8088/phonebook2/pbcontroller?action=editform&no=<%= personList.get(i).getPersonId() %>">[ 수정폼으로 이동 ]<a></td>
					<td><a href="http://localhost:8088/phonebook2/pbcontroller?action=delete&no=<%= personList.get(i).getPersonId() %>">[ 삭제 ]</a></td>
				</tr>
			</tbody>
		</table>
		<br>
		<%} %>
		
		<br>
		<a href="http://localhost:8088/phonebook2/pbcontroller?action=writeform">등록폼으로 이동</a>
	</body>
	
</html>