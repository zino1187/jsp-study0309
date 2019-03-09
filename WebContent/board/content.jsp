<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
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
	String sql="select * from board where board_id=?";
	
	//쿼리 수행객체 준비
	PreparedStatement pstmt=con.prepareStatement(sql);
	//물음표인 바인드 변수값 지정 
	//자바기본자료형 byte, short ,int ,long ,float, double
	//기본자료형 마다 1:1 대응되는 wrapper 클래스가 잇다..
	//예)  int - Integer , short  - Short 
	// 왜 지원하나?? 자바에서는 기본자료형과 객체자료형간의 형변환을 가능하게 하기 위한
	//레퍼클래스가 지원된다....
	//Integer.parseInt() 문자열이었던 객체를 정수로 변환
	pstmt.setInt(1, Integer.parseInt(board_id));
	ResultSet rs=pstmt.executeQuery();
	//rs에는 여러건이 아닌, 단 한건짜리 레코드가 들어있다, 여전히 커서는 위로 올라가있음
	//따라서 사용전에 next() 내려야 한다!!
	rs.next(); //한칸 전진!!
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
	location.href="/board/delete.jsp?board_id=<%=rs.getInt("board_id")%>";
}
//서버에 폼양식 전송하는 함수 정의!! 
function edit(){
	//alert("나 눌렀어?");
	$("form").attr({
		"method":"post",
		"action":"/board/edit.jsp"
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
	 <%out.print("지금 보고있는 글의 id는 "+rs.getInt("board_id"));%>
	<input type="hidden" name="board_id" value="<%=rs.getInt("board_id")%>">
    <input type="text" name="writer" value="<%=rs.getString("writer")%>">
    <input type="text" name="title" value="<%=rs.getString("title")%>">
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












