<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
	<head>
	<meta charset="UTF-8">
	<title>writeForm</title>
	</head>
	
	<body>
		
		<h1>전화번호부</h1>
		
		<h2>전화번호-등록폼</h2>
		
		<p>아래의 항목을 입력한 후 등록버튼을 눌러주세요!_!</p>
		
		<form action="/phonebook2/pbcontroller" method="get">
			
			<div>
				<label for="txt-name">이름(name):</label>
				<input id="txt-name" type="text" name="name" value="" placeholder="이름">
			</div>
			<div>
				<label for="txt-hp">핸드폰(hp):</label>
				<input id="txt-hp" type="text" name="hp" value="" placeholder="핸드폰">
			</div>
				
			<div>
				<label for="txt-company">회사(company):</label>
				<input id="txt-company" type="text" name="company" value="" placeholder="회사">
			</div>
			
			<%-- action에 insert가 있어야 전송이 되기때문에 hidden으로 숨겨서 적용해준다. --%>
			<input type="text" name="action" value="insert">
			<br>
			<button type="submit">등록</button>
		
		</form>
		<br><br>
		<a href="">리스트로 가기</a>
		
	</body>
	
</html>