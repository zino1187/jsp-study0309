<%@page import="com.itbank.model.domain.Board"%>
<%@page import="com.itbank.model.repository.BoardDAO"%>
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
	
	
	//클래스로 대체됨
	BoardDAO boardDAO = new BoardDAO();
	Board board =new Board(); //empty 비어있음
	board.setWriter(writer);
	board.setTitle(title);
	board.setContent(content);
	board.setBoard_id(Integer.parseInt(board_id));
	int result=boardDAO.update(board);
	
	out.print("<script>");
	if(result ==0){
		out.print("alert('수정실패');");
		out.print("history.back();");
	}else{
		out.print("alert('수정성공');");
		out.print("location.href='content.jsp?board_id="+board_id+"';");
	}
	out.print("</script>");

%>






