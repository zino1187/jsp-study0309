<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	//수정 후 상세보기 페이지를 요청할 것이므로, 여기서는 디자인 html이
	//필요없다
	//update board set writer=?, title=? , content=?
	//where board_id=?
	//파라미터를 받기전에 인코딩 세팅을 해야함 
	request.setCharacterEncoding("utf-8");

	String writer=request.getParameter("writer");		
	String title=request.getParameter("title");		
	String content=request.getParameter("content");		
	String board_id=request.getParameter("board_id");
	out.print("작성자는 "+writer);
	out.print("제목은 "+title);
	out.print("내용은 "+content);
	out.print("board_id "+board_id);
	
	//1단계) 드라이버 로드 
	Class.forName("oracle.jdbc.driver.OracleDriver");
	
	//2단계) 접속 
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="jsp0309";
	String pass="jsp0309";
	Connection con=DriverManager.getConnection(url, user, pass);
	if(con!=null){
		out.print("접속성공");
	}else{
		out.print("접속실패");
	}

	//3단계) 쿼리문수행
	String sql="update board  set  writer=?, title=?, content=?";
	sql+=" where board_id=?";
	PreparedStatement pstmt=con.prepareStatement(sql);
	pstmt.setString(1, writer);
	pstmt.setString(2, title);
	pstmt.setString(3, content);
	pstmt.setInt(4, Integer.parseInt(board_id));//문자를 숫자로 변환하여 처리
	
	int result=pstmt.executeUpdate();//쿼리문 수행!!

	out.print("<script>");
	if(result ==0){
		out.print("alert('수정실패');");
		out.print("history.back();");
	}else{
		out.print("alert('수정성공');");
		out.print("location.href='content.jsp?board_id="+board_id+"';");
	}
	out.print("</script>");
	
	//4단계) 닫기 
	pstmt.close();
	con.close();
%>






