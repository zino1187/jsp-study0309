<%@page import="com.itbank.model.domain.Board"%>
<%@page import="com.itbank.model.repository.BoardDAO"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("utf-8"); //전송되는 파라미터 인코딩 처리
	
	//파라미터값들을 넘겨받아 , db에 넣어주는 역할만 수행하므로
	//이 jsp는 html과 같은 디자인 요소는 필요없다
	String writer=request.getParameter("writer");//작성자
	String title=request.getParameter("title");//제목
	String content=request.getParameter("content");//내용
	
	out.print("작성자는 "+writer);
	out.print("제목은 "+title);
	out.print("내용은 "+content);
	/* jsp 의 개발방식 
	1) 스크립트 방식 : jsp만을 이용하여 로직을 작성하는 방식
	 asp, php
	 
    2) 모델1 방식: jsp코드에서 재사용할 로직을 자바클래스로 분리
                        하여 개발하는 방식 mybatis 
                        
    3) 모델2 방식 : jsp, 자바클래스, 서블릿 각각 MVC패턴으로
                       나누어서 개발하는 방식 
	*/
	
	/*먼저 스크립트 방식으로 코드를 완료 진후, 로직을 다시 
	자바로 분리시켜 개발할 예정..*/
	
	//클래스로 옮겨감...
	BoardDAO boardDAO=new BoardDAO();//CURD 담당클래스
	Board board=new Board();//인스턴스 생성 empty
	board.setWriter(writer);//작성자 주입
	board.setTitle(title);//제목 주입
	board.setContent(content);//내용주입
	int result=boardDAO.insert(board);

	if(result==1){
		out.print("등록성공");
	}else{
		out.print("등록실패");
	}
	
	//목록페이지를 재 요청 
	response.sendRedirect("/board1/list.jsp");
%>













