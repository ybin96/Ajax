<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		
		$("#add").click(function(){
			var data = $("#insert").serializeArray();
			$.ajax({
				url:"ChatMessage",
				data:data,
				type:"post",
				success:function(){
				}
			});
		});
		
		setInterval(function(){
			$("#chat").empty();
			$.ajax({
				url:"ChatMessage",
				success:function(arr){
					$.each(arr,function(){
						var data = $("<div></div>").html(this.name+" ==> "+this.name);
						$("#chat").append(data);
					});
				}
			});
		}, 2000);
		
	});
</script>
</head>
<body>
	<form id="insert">
		닉네임 <input type="text" name="name"><br>
		메세지 <textarea rows="3" cols="60" name = message></textarea><br>
	</form>
	<button id="add">글쓰기</button>
	<hr>
	<div id="chat"></div>
</body>
</html>