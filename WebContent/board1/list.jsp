<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	//board 테이블의 모든 레코드 가져오기!!
	
	//1단계) 드라이버 로드 
	Class.forName("oracle.jdbc.driver.OracleDriver");
	
	//2단계)오라클 접속!!
	//DriverManager는 접속을시도하는 객체임
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="jsp0309";
	String pass="jsp0309";
	Connection con=DriverManager.getConnection(url, user, pass);
	if(con!=null){
		out.print("접속성공");
	}else{
		out.print("접속실패");
	}
	

	//3단계) 쿼리문 수행 (내림차순으로 가져와 최근글을 먼저 보이게)
	String sql="select * from board order by board_id desc";
	PreparedStatement pstmt=con.prepareStatement(sql);
	//select문일 때는 executeQuery() 사용해야 함
	//select문의 수행결과로 ResultSet 객체가 메모리상에 테이블을 올려놓고
	//접근하여 사용한다!!
	ResultSet rs=pstmt.executeQuery();

	
	
%>
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
		location.href="/board1/write.jsp";//글쓰기 폼 요청 
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
		<% 
			while(rs.next()){//커서 한칸 내리기
		%>
		<tr>
			<td>Jill</td>
			<td><a href="/board1/content.jsp?board_id=<%=rs.getInt("board_id")%>"><%=rs.getString("title")%></a></td>
			<td><%=rs.getString("writer") %></td>
			<td><%=rs.getString("regdate") %></td>
			<td><%=rs.getInt("hit") %></td>
		</tr>
		<%} %>
		
		<tr>
			<td colspan="5">
				<button>글등록</button>
			</td>
		</tr>

	</table>

</body>
</html>
<% 
	rs.close();
	pstmt.close();
	con.close();
%>














