<%@page import="com.fasterxml.jackson.databind.util.JSONPObject"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>
<%@ page import="kr.or.connect.connectdb.dao.TodoDao"%>
<%@ page import = "kr.or.connect.connectdb.dto.TodoDto" %>
<%@ page import = "java.util.List" %>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/Todo/static/main.css">
</head>
<body>


<div class = "top">
	<div class = "title"><h1>나의 해야할 일들</h1></div>
		<a href="/Todo/todo" target="_self">
			<div class = "new-todo">
				새로운 TODO 등록
			</div>
		</a>
</div>
<div class = "out-box">
	<div class = "box">
		<div class ="todo">
			<b>TODO</b>
		</div>
		<ul class = 'todo-ul'>
		</ul>
	</div>
	<div class = "box">
		<div class= "doing">
			<b>DOING</b>
		</div>
		<ul class = "doing-ul">
		</ul>
	</div>
	<div class = "box">
		<div class = "done">
			<b>DONE</b>
		</div>
		<ul class = 'done-ul'>
		</ul>
	</div>
</div>


<script>

function readTodoList(){
	var jsonStr = '${jsonStr}'
	var todoList = JSON.parse(jsonStr);
	for (i of todoList){
		var type = i['type'];
		var id = i['id'];
		if(type === 'TODO'){
			
			var title = i['title'];
			var name = i['name'];
			var regDate = i['regDate'];
			var sequence = i['sequence'];
			var strset = "<li><b>"+title+"</b><br>"+name+", 등록날짜:"+regDate+", 우선순위: "+sequence+"<button type = 'button' onclick=\"ajax(\'"+type+"','"+id+"');\">→</button></li>";
			document.querySelector('.todo-ul').innerHTML += strset;
			

		
		}else if(type === 'DOING'){
			
			var title = i['title'];
			var name = i['name'];
			var regDate = i['regDate'];
			var sequence = i['sequence'];
			var strset = "<li><b>"+title+"</b><br>"+name+", 등록날짜:"+regDate+", 우선순위: "+sequence+"<button type = 'button' onclick=\"ajax(\'"+type+"','"+id+"');\">→</button></li>";
			document.querySelector('.doing-ul').innerHTML += strset;

			
			
		}else{
			
			var title = i['title'];
			var name = i['name'];
			var regDate = i['regDate'];
			var sequence = i['sequence'];
			var strset = "<li><b>"+title+"</b><br>"+name+", 등록날짜:"+regDate+", 우선순위: "+sequence+"</li>";
			document.querySelector('.done-ul').innerHTML += strset;

		}
		
	}
}

function clearTodoList(){
	document.querySelector('.todo-ul').innerHTML = null;
	document.querySelector('.doing-ul').innerHTML = null;
	document.querySelector('.done-ul').innerHTML = null;
}
function ajax(type, id){
	var oReq = new XMLHttpRequest();
	oReq.addEventListener("load",function(){
		var ourData = oReq.responseText;
		document.close();
		document.write(ourData);
		

		
		
	})
	oReq.open("POST", "http://localhost:8080/Todo/main/"+type+"/"+id);
	oReq.send();
}
readTodoList();


</script>
</body>
</html>