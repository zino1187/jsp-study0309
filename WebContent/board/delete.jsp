<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	//파라미터를 넘겨받아, 레코드 1건을 삭제한다!!
	String board_id=request.getParameter("board_id");
	String sql="delete from board where board_id=?";
	out.print(sql);
	
	//1단계: 드라이버 로드 
	Class.forName("oracle.jdbc.driver.OracleDriver");
	
	//2단계: 접속 
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
	PreparedStatement pstmt=con.prepareStatement(sql);
	int result=pstmt.executeUpdate(); //delete 는 DML이므로... 
	
	//4단계: 닫기 
	pstmt.close();
	con.close();
	
	if(result ==0){
		out.print("삭제 실패");
	}else{
		out.print("삭제 성공");
	}
%>




