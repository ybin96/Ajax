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
		$("#open_button").click(function(){
			$("#dialog").dialog("open");
		});
		
		$("#dialog").dialog({
			autoOpen:false,
			modal:true,
			buttons:{
				submit:function(){
					var eventName = $("#event_name").val();
					var eventDate = $("#event_date").val();
					$("#output").append($("<h1></h1>").html(eventName+":"+eventDate));
					$("#event_name").val("");
					$("#event_Date").val("");
					$("#dialog").dialog("close");
				},
				reset:function(){
					$("#event_name").val("");
					$("#event_Date").val("");
				},
				cancel:function(){
					$("#event_name").val("");
					$("#event_Date").val("");
					$("#dialog").dialog("close");
				}
			}
		});
		$("#event_date").datepicker({
			dateFormat:"yy/mm/dd",
			numberOfMonths:3
		});
	});
</script>
</head>
<body>
	<div>
		<h1></h1>
		<p></p>
	</div>
</body>
</html>