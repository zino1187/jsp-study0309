<%@ page contentType="text/html; charset=UTF-8"%>
<%
	//수정 후 상세보기 페이지를 요청할 것이므로, 여기서는 디자인 html이
	//필요없다
	//update board set writer=?, title=? , content=?
	//where board_id=?
	String writer=request.getParameter("writer");		
	String title=request.getParameter("title");		
	String content=request.getParameter("content");		
	String board_id=request.getParameter("board_id");
	out.print("작성자는 "+writer);
	out.print("제목은 "+title);
	out.print("내용은 "+content);
	out.print("board_id "+board_id);
%>






