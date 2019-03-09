<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 100%;
	border: 1px solid #ddd;
}

th, td {
	text-align: left;
	padding: 16px;
}

tr:nth-child(even) {
	background-color: #f2f2f2
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
$(function(){
	$("button").click(function(){
		location.href="/board/write.jsp";//글쓰기 폼 요청 
	});//버튼을 클릭하면...
	
});//문서가 로드되면

</script>
</head>
<body>

	<h2>Zebra Striped Table</h2>
	<p>For zebra-striped tables, use the nth-child() selector and add a
		background-color to all even (or odd) table rows:</p>

	<table>
		<tr>
			<th width="5%">No</th>
			<th width="70%">제목</th>
			<th width="10%">작성자</th>
			<th width="10%">작성일</th>
			<th width="5%">조회수</th>
		</tr>

		<tr>
			<td>Jill</td>
			<td>Smith</td>
			<td>50</td>
			<td>50</td>
			<td>50</td>
		</tr>
		
		<tr>
			<td colspan="5">
				<button>글등록</button>
			</td>
		</tr>

	</table>

</body>
</html>






