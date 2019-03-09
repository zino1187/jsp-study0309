<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	//1단계: 드라이버 로드 
	Class.forName("oracle.jdbc.driver.OracleDriver");

	//2단계: 접속 
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="jsp0309";
	String pass="jsp0309";
	Connection con=DriverManager.getConnection(url, user, pass);
	if(con!=null){
		out.print("접속성공");
	}else{
		out.print("접속실패");
	}
	
	//3단계: 쿼리문 실행 
	//목록에서 전송되어온 파라미터를 ?대신 대입!!
	//http 통신으로 전송되는 데이터는 모두 문자취급한다 
	//따라서 content.jps?board_id=5  라 할지라도 실제적으로 
	//숫자5가 아니라  "5" 와 같은 문자이다
	String board_id=request.getParameter("board_id");
	String sql="select * from where board_id=?";

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
	 $("input[type='button']").click(function(){
			send();	 	
	 });
	 
});//문서가 로드되면...

//서버에 폼양식 전송하는 함수 정의!! 
function send(){
	//alert("나 눌렀어?");
	$("form").attr({
		"method":"post",
		"action":"/board/regist.jsp"
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
    <input type="text" name="writer" placeholder="작성자 입력...">
    <input type="text" name="title" placeholder="제목입력...">
    <textarea id="content" name="content" placeholder="Write something.." style="height:200px"></textarea>
    
    <input type="button" value="Submit">
  </form>
</div>

</body>
</html>


