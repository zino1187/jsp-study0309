package com.itbank.model.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//이 클래스는 오직 데이터베이스와 관련된 CRUD만을 수행하기 위한 전담객체로 정의한다
//이러한 목적으로 정의되는 객체를 가리켜 어플리케이션 설계분야에서는 DAO라한다.
//DAO (Data Access Object )

public class BoardDAO {

	//글 등록 메서드 정의!!
	public void insert() {
		try {
			//1단계) 드라이버 로드 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로드 성공");
			
			//2단계) 접속 
			String url="jdbc:oracle:thin:@localhost:1521:XE";
			String user="jsp0309";
			String pass="jsp0309";
			Connection con=DriverManager.getConnection(url, user, pass);
			if(con ==null) {
				System.out.println("접속 실패");
			}else {
				System.out.println("접속 성공");
			}
			
			String sql="insert into board(board_id,writer,title,content)";
			sql+=" values(seq_board.nextval,?,?,?)";
			PreparedStatement pstmt=con.prepareStatement(sql);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		//3단계) 쿼리문 수행
		//4단계) 닫기 
		
	}
}








