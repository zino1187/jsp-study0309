<%@page import="com.itbank.model.domain.Board"%>
<%@page import="com.itbank.model.repository.BoardDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	//파라미터 받기!!
	String board_id=request.getParameter("board_id");

	//클래스로 옮겨감...
	BoardDAO boardDAO = new BoardDAO();
	
	//rs로 받환받지 말고, 대신 Board 로 받는다!!
	Board board=boardDAO.select(Integer.parseInt(board_id));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}

input[type=button] {
  background-color: #4CAF50;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=button]:hover {
  background-color: #45a049;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="//cdn.ckeditor.com/4.11.3/standard/ckeditor.js"></script>
<script>
$(function(){
	 CKEDITOR.replace('content');
	 
	 //버튼을 누르면....
	 //jquery는 css의 선택자를 따른다..따라서 아이디는 동일하게 #접근
	 $("#bt-edit").click(function(){
		if(confirm("수정하실래요?")){
			edit();
		} 	
	 });
	 
	 $("#bt-del").click(function(){
		if(confirm("삭제하실래요?")){
			del();
		} 	
	 });
	 
});//문서가 로드되면...

//서버측에 삭제를 요청한다!!
function del(){
	//alert("나 불럿어?");
	location.href="/board1/delete.jsp?board_id=<%=board.getBoard_id()%>";
}
//서버에 폼양식 전송하는 함수 정의!! 
function edit(){
	//alert("나 눌렀어?");
	$("form").attr({
		"method":"post",
		"action":"/board1/edit.jsp"
	});
	$("form").submit();//전송!!
}
</script>
</head>
<body>

<h3>상세보기 화면</h3>

<div class="container">
  <form>
	  <!-- html문서에서 오직 name 만이 파라미터 전송 역할을 수행한다
	  	즉 id는 불가능하다
	   -->
	 <%out.print("지금 보고있는 글의 id는 "+board.getBoard_id());%>
	<input type="hidden" name="board_id" value="<%=board.getBoard_id()%>">
    <input type="text" name="writer" value="<%=board.getWriter()%>">
    <input type="text" name="title" value="<%=board.getTitle()%>">
    <!-- textarea는 쌍으로 열고 닫는 형식의 태그이므로, 그 사이에 넣어야 한다 -->
    <textarea id="content" name="content" placeholder="Write something.." style="height:200px"><%=rs.getString("content") %></textarea>
    
    <input type="button" id="bt-edit" value="수정">
    <input type="button" id="bt-del" value="삭제">
    <input type="button" id="bt-list" value="목록">
  </form>
</div>

</body>
</html>
<%
	rs.close();
	pstmt.close();
	con.close();
%>












