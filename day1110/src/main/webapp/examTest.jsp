<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#insertExam span{
		width: 50px;
		display: inline-block;
		
	}
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		
		$(document).on("click",".exam",function(){
			var td = $(this).find("td");
			$("#no").val($(td[0]).html());
			$("#name").val($(td[1]).html());
			$("#sex").val($(td[2]).html());
			$("#addr").val($(td[3]).html());
			$("#blood").val($(td[4]).html());
		});
		
		$("#delete").click(function(){
			if(confirm("삭제??")){
			var data = $("#f").serializeArray();
			$.ajax({
				url:"DeleteExam",
				data:data,
				success:function(){
					list();
					}
				});
			}
		});
		
		$("#update").click(function(){
			var data = $("#f").serializeArray();
			$.ajax({
				url:"UpdateExam",
				data:data,
				success:function(){
					list();
				}
			});
		});
		
		$("#add").click(function(){
			var data = $("#f").serializeArray();
			$.ajax({
				url:"InsertExam",
				data:data,
				success:function(){
					list();
				}
			});
		});
		
		var list = function(){
			$("#list").empty();
			$.ajax({
				url:"ListExam",
				success:function(arr){
					$.each(arr,function(){
						var tr = $("<tr></tr>").addClass("exam");						
						var td1 = $("<td></td>").html(this.no);
						var td2 = $("<td></td>").html(this.name);
						var td3 = $("<td></td>").html(this.sex);
						var td4 = $("<td></td>").html(this.addr);
						var td5 = $("<td></td>").html(this.blood);
						$(tr).append(td1,td2,td3,td4,td5);
						$("#list").append(tr);
					});
				}
			});
		}
		list();
	});
</script>
</head>
<body>
	<h2>데이터 추가</h2>
	<hr>
	<form id="f">
	<input type="hidden" id="no" name="no">
	<span>이름</span> <input type="text" id="name" name="name"><br>
	<span>성별</span> <input type="text" id="sex" name="sex"><br>
	<span>주소</span> <input type="text" id="addr" name="addr"><br>
	<span>혈액형</span> <input type="text" id="blood" name="blood"><br>
	</form>
	<button id="add">추가</button>
	<button id="update">수정</button>
	<button id="delete">삭제</button>
	<hr>
	<h2>회원목록</h2>
	<table border="1" width="60%">
		<thead>
			<tr>
				<th>no</th>
				<th>이름</th>
				<th>성별</th>
				<th>지역</th>
				<th>혈액형</th>
			</tr>
		</thead>
		<tbody id="list"></tbody>
	</table>
</body>
</html>