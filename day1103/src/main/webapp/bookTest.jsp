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
		$("#btnRead").click(function(){
			$.ajax("listBook.jsp",{success:function(data){
				var arr = eval("("+data+")");
				$.each(arr, function(){
					var tr = $("<tr></tr>");
					var td1 = $("<td></td>").html(this.bookid);
					var td2 = $("<td></td>").html(this.bookname);
					var td3 = $("<td></td>").html(this.publisher);
					var td4 = $("<td></td>").html(this.price);
					$(tr).append(td1,td2,td3,td4);
					$("#list").append(tr);
				});
			}});
		});
	});
</script>
</head>
<body>
	<button id="btnRead">도서목록 읽어오기</button>
	<table border="1" width="80%">
		<thead>
			<tr>
				<td>도서번호</td>
				<td>도서명</td>
				<td>출판사</td>
				<td>가격</td>
			</tr>
		</thead>
		<tbody id="list"></tbody>
	</table>
</body>
</html>