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
		width:250px;
		height:300px;
		border: solid 2px pink;
		border-radius: 15px;
		margin: 30px;
		padding: 10px;
	}
	
	ul{
		overflow: hidden;
	}
	
	li{
		float: left;
		list-style: none;
		padding: 10px;
		cursor: pointer;
	}
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		
		// 최초에 Ajax통신을 하여 서버로부터 읽어온 모든 상품을 배열에 저장해 둔다.
		var list;
		var pageSize = 4;		// 한 화면에 보여줄 상품의 수
		var totalRecord = 0;	// 전체 상품의 수
		var totalPage = 1;		// 전체 페이지의 수
		
		// 전체 페이지를 출력하는 함수
		function printPage(){
			for(i=1;i<=totalPage;i++){
				var li = $("<li></li>").html(i);
				$(".paging_button").append(li);
			}
		}
		
		// 현재 페이지에 따른 상품을 출력하는 함수
		function printItem(pageNum){
			// 현재 페이지에 출력할 시작 인덱스와 마지막 인덱스를 구한다
			var start = (pageNum-1)*pageSize;
			var end = start + pageSize -1;
			
			for(i=start;i<=end;i++){
				var row = list[i];
				var div = $("<div></div>").addClass("goods");
				var img = $("<img/>").attr({
					src:"images/"+row.fname,
					width:"200",
					height:"200"
				});
				var no_name = $("<div></div>").html("<b>"+row.no+" : " +row.name+"</b>");
				var price = $("<div></div>").html("가격 : "+row.price);
				var qty = $("<div></div>").html("수량 : "+row.qty);
				$(div).append(img,no_name,price,qty);
				$("#list").append(div);
			}
		}
		
		$.ajax({
			url:"ListGoods",
			success:function(arr){
				list = arr;
				totalRecord = arr.length;
				totalPage = Math.ceil(totalRecord / pageSize);
				
				printItem(1);	// Ajax통신하자마자 1페이지를 보여준다
				printPage();	// 전체 페이지를 출력
			}
		});
		
		// 클릭 이번트 등록하기
		$(document).on("click","li",function(){
			var pageNum = $(this).html();
			$("#list").empty();
			printItem(pageNum);
		});
		
	});
</script>
</head>
<body>
	<ul class="paging_button"></ul>
	<div id="list"></div>
	<ul class="paging_button"></ul>
</body>
</html>