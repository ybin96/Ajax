<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="jquery-ui-1.13.1.custom/jquery-ui.min.css">
<style type="text/css">
	body{
		font-size: 100%;
	}
	h1{
		text-align: center;
	}
	input[type=text],lavel{
		margin-bottom: 12px;
		padding: .4em;
		width: 95%
	}
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script type="text/javascript" src="jquery-ui-1.13.1.custom/jquery-ui.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#dialog").dialog({
			autoOpen:false,
			modal:true,
			buttons:{
				submit:function(){},
				reset:function(){},
				cancel:function(){}
			}
		});
		$("#event_date").datepicker({
			dateFormat:"yyyy/mm/dd",
			numberOfMon ths:3
		});
	});
</script>
</head>
<body>
	<div id="dialog">
		<h1>Hello Huiju</h1>
		<label for="evnet_name">일정이름</label> <input type="text" id="event_name">
		<label for="evnet_date">날짜</label> <input type="text" id="event_date">
	</div>
</body>
</html>