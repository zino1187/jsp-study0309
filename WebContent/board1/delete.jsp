<%@page import="com.itbank.model.repository.BoardDAO"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	//파라미터를 넘겨받아, 레코드 1건을 삭제한다!!
	String board_id=request.getParameter("board_id");

	//클래스로 대체됨
	BoardDAO boardDAO = new BoardDAO();
	int result=boardDAO.delete(Integer.parseInt(board_id));

	out.print("<script>");
	if(result ==0){
		out.print("alert('삭제실패');");
		out.print("history.back();");
	}else{
		out.print("alert('삭제성공');");
		out.print("location.href='list.jsp';");
	}
	out.print("</script>");
	
%>




