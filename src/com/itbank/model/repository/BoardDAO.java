package com.itbank.model.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.itbank.model.domain.Board;

//이 클래스는 오직 데이터베이스와 관련된 CRUD만을 수행하기 위한 전담객체로 정의한다
//이러한 목적으로 정의되는 객체를 가리켜 어플리케이션 설계분야에서는 DAO라한다.
//DAO (Data Access Object )

public class BoardDAO {
	
	//한건 수정
	public int update(Board board) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url="jdbc:oracle:thin:@localhost:1521:XE";
			String user="jsp0309";
			String pass="jsp0309";
			con=DriverManager.getConnection(url, user, pass);
			if(con ==null) {
				System.out.println("접속 실패");
			}else {
				System.out.println("접속 성공");
			}
			//쿼리수행 
			String sql="update board set writer=?, title=?,content=?";
			sql+=" where board_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, board.getWriter());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			pstmt.setInt(4, board.getBoard_id());
			result=pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}				
			
		}
		return result;
	}
	
	
	//한건 삭제 
	public int delete(int board_id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0;//삭제 성공 여부를 담고 있는 변수
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url="jdbc:oracle:thin:@localhost:1521:XE";
			String user="jsp0309";
			String pass="jsp0309";
			con=DriverManager.getConnection(url, user, pass);
			if(con ==null) {
				System.out.println("접속 실패");
			}else {
				System.out.println("접속 성공");
			}
			//쿼리문 수행 
			String sql="delete from board where board_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, board_id);//바인드 변수값 지정
			result=pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}			
		}
		return result; //결과 반환
	}
	
	//한건 레코드 가져오기!!
	public Board select(int board_id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Board board=null; //반환되어야 하므로, 여기에 선언..
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url="jdbc:oracle:thin:@localhost:1521:XE";
			String user="jsp0309";
			String pass="jsp0309";
			con=DriverManager.getConnection(url, user, pass);
			if(con ==null) {
				System.out.println("접속 실패");
			}else {
				System.out.println("접속 성공");
			}
			
			String sql="select * from board where board_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, board_id);//바인드 변수 값 지정
			rs=pstmt.executeQuery();//select문 실행
			
			//rs는 닫아야되므로, rs를 직접 반환하지 말고 레코드 1건을
			//표현하는 데 사용되는 객체인 DTO를 이용하자!!
			if(rs.next()) { //레코드가 있다면...
				board = new Board();
				
				board.setBoard_id(rs.getInt("board_id"));
				board.setWriter(rs.getString("writer"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setRegdate(rs.getString("regdate"));
				board.setHit(rs.getInt("hit"));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return board;
	}
	
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








