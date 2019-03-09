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
	
	//1단계) 드라이버 로드 
	Class.forName("oracle.jdbc.driver.OracleDriver");
	
	//2단계)오라클 접속!!
	//DriverManager는 접속을시도하는 객체임
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="jsp0309";
	String pass="jsp0309";
	
	//접속시도 후 성공여부를 알기 위해서는 반환객체인 Connection 객체가
	//null이 아닌지 확인한다!!
	Connection con=DriverManager.getConnection(url, user,pass);//접속시도
	if(con !=null){
		out.print("접속 성공");
	}else{
		out.print("접속 실패");
	}
	
	//3단계) 접속이 성공되면 쿼리문을 수행한다 
	//         쿼리문을 담당하는 객체가 PreparedStatement 이다!!
	PreparedStatement pstmt=null;
	String sql="insert into board(board_id,writer,title,content)";
	sql=sql+" values(seq_board.nextval,?,?,?)";
	pstmt=con.prepareStatement(sql);
	
	//물음표로 표현되는 바인드 변수의 값을 결정짓고, 쿼리문 수행해야 한다!!!
	//물음표 순서대로 컬럼과 매칭 , 첫번째 물음표 =  writer 의미 
	pstmt.setString(1, writer);
	pstmt.setString(2, title);
	pstmt.setString(3, content);
	
	//쿼리수행
	//DML 이란?? 데이터 조작어 Data Manipulation Language
	// 					insert(입력), update(수정), delete(삭제)		
	int result=pstmt.executeUpdate(); //DML 문장은 executeUpdate(), 
										//select 문장은 executeQuery() 를 수행하면 됨
	if(result==1){
		out.print("등록성공");
	}else{
		out.print("등록실패");
	}
	//모든 작업이 끝나면 데이터베이스 관련된 객체들은 다시 닫아야 함!!
	pstmt.close();
	con.close();
	
	//목록페이지를 재 요청 
	response.sendRedirect("/board/list.jsp");
%>













