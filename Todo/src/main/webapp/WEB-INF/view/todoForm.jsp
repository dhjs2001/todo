<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel ="stylesheet" type="text/css" href="/Todo/static/todoForm.css">
</head>
<body>
<h1>할일 등록</h1>
<div>
<form action="/Todo/main/Todo" accept-charset="UTF-8" name = "todo_info" method="post" >
어떤 일인가요?<br>
<input class = "job" type="text" name = "title" placeholder="swift 공부하기(24자까지)" maxlength="24" required autofocus >
<br><br>

누가 할 일인가요?<br>
<input class="who" type="text" name = "name" placeholder="홍길동" maxlength="7" required>
<br><br>

우선 순위를 선택하세요<br>
<label><input type="radio" name = "sequence" value = "1" required>1순위</label>
<label><input type="radio" name = "sequence" value = "2" required>2순위</label>
<label><input type="radio" name = "sequence" value = "3" required>3순위</label>
<br><br>
<button class = "btn1" type="button" onclick="history.back()"><span style = "text-decoration : underline">&lt;이전</span></button>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<button class = "btn2" type="submit" onclick="">제출</button>
<button class = "btn2" type="reset" onclick="">내용지우기</button>

</form>
</div>
</body>
</html>