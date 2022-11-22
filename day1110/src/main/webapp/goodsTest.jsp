<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.goods{
		display: inline-block;
		width:150px;
		height:150px;
		border: solid 2px pink;
		border-radius: 15px;
		margin: 10px;
		padding: 10px;
	}
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$.ajax({
			url:"ListGoods",
			success:function(arr){
				$.each(arr,function(){
					var div = $("<div></div>").addClass("goods");
					var img = $("<img/>").attr({
						src:"images/"+this.fname,
						width:"50",
						height:"50"
					});
					$(div).append(img);
					$("#list").append(div);
				});
			}
		});
	});
</script>
</head>
<body>
	<div id="list"></div>
</body>
</html>