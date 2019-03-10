package com.itbank.model.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.itbank.model.domain.Board;

//이 클래스는 오직 데이터베이스와 관련된 CRUD만을 수행하기 위한 전담객체로 정의한다
//이러한 목적으로 정의되는 객체를 가리켜 어플리케이션 설계분야에서는 DAO라한다.
//DAO (Data Access Object )

public class BoardDAO {

	//글 등록 메서드 정의!!
	public int insert(Board board) {
		int result=0; //실행 결과가 담긴 변수
		Connection con=null;//지역변수는 반드시 초기화 해야 한다!!
		PreparedStatement pstmt=null;
		
		try {
			//1단계) 드라이버 로드 
			Class.forName("oracle.jdbc.drive.OracleDriver");
			System.out.println("드라이버 로드 성공");
			
			//2단계) 접속 
			String url="jdbc:oracle:thin:@localhost:1521:XE";
			String user="jsp0309";
			String pass="jsp0309";
			con=DriverManager.getConnection(url, user, pass);
			if(con ==null) {
				System.out.println("접속 실패");
			}else {
				System.out.println("접속 성공");
			}
			
			//3단계) 쿼리문 수행
			String sql="insert into board(board_id,writer,title,content)";
			sql+=" values(seq_board.nextval,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, board.getWriter());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			result=pstmt.executeUpdate();//쿼리문 수행!!!
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			//try문과 catch 문 모두~~이 영역은 피할 수 없다!!!
			//따라서 이 영역이 db 닫는 최적의 장소이다..
			//왜?? db는 언제난 닫아야 하므로..
			if(pstmt !=null) { //메모리에 존재할때만..
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con !=null) { //메모리에 존재할때만..
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		return result;
		
		
		//4단계) 닫기 
		
	}
}








