package com.itbank.model.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.itbank.model.domain.Board;

//�� Ŭ������ ���� �����ͺ��̽��� ���õ� CRUD���� �����ϱ� ���� ���㰴ü�� �����Ѵ�
//�̷��� �������� ���ǵǴ� ��ü�� ������ ���ø����̼� ����о߿����� DAO���Ѵ�.
//DAO (Data Access Object )

//DAO�� �޼��帶�� Ŀ�ؼ��� �����ϴ� �ڵ带 �־�θ�, ��û�ø��� �����
//���� �ݺ��Ǵµ�, ���ɿ� ������ �ʷ�...�����ڰ� �������� ��Ʈ��ũ �� �޸�
//��������.... �ذ�å) ��û�� ���������� �������õ����� ����, �̸� ����� 
//Ŀ�ؼ��� Ȯ���� ���� ��û�� �������� �� Ȯ���� Connection ��ü �� �ϳ���
//�뿩������!! �� ����ϰ� �� �Ŀ�??? �ٽ� Connection ���� �ִ� Pool
//�� ����������!! �̷��� Ŀ�ؼ� ���� ����� ������ Connection Pooling...
//Ŀ�ؼ� Ǯ���� �����ϴ� ��� 
//1) �����ڰ� ���������� �ۼ��ϴ� ��(�����ڸ��� �Ƿ����� ���� õ������)
//2) �� ������� ���̺귯���� �̿��ϴ� ��(Apache DBCP)
//3) Tomcat �� ���� �������������̹� �����ϴ� Ǯ���� ���� ��
//    Ư�� �������� �����ϴ� Ŀ�ؼ�Ǯ���� JNDI�� �̿��Ѵ�!!
// JNDI(Java Naming Directory Interface �� ����)
// Ŭ���� �ڵ�ȿ� �����ͺ��̽� ���� ������ ���� ����, xml�� ���� �ܺ� ����
// ���Ͽ� ������ �ε�, �̸��� �ٿ� �ʿ��Ҷ����� �̸����� �����ϴ� ���
public class BoardDAO {
	
	//�Ѱ� ����
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
				System.out.println("���� ����");
			}else {
				System.out.println("���� ����");
			}
			//�������� 
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
	
	
	//�Ѱ� ���� 
	public int delete(int board_id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0;//���� ���� ���θ� ��� �ִ� ����
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url="jdbc:oracle:thin:@localhost:1521:XE";
			String user="jsp0309";
			String pass="jsp0309";
			con=DriverManager.getConnection(url, user, pass);
			if(con ==null) {
				System.out.println("���� ����");
			}else {
				System.out.println("���� ����");
			}
			//������ ���� 
			String sql="delete from board where board_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, board_id);//���ε� ������ ����
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
		return result; //��� ��ȯ
	}
	
	//�Ѱ� ���ڵ� ��������!!
	public Board select(int board_id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Board board=null; //��ȯ�Ǿ�� �ϹǷ�, ���⿡ ����..
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url="jdbc:oracle:thin:@localhost:1521:XE";
			String user="jsp0309";
			String pass="jsp0309";
			con=DriverManager.getConnection(url, user, pass);
			if(con ==null) {
				System.out.println("���� ����");
			}else {
				System.out.println("���� ����");
			}
			
			String sql="select * from board where board_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, board_id);//���ε� ���� �� ����
			rs=pstmt.executeQuery();//select�� ����
			
			//rs�� �ݾƾߵǹǷ�, rs�� ���� ��ȯ���� ���� ���ڵ� 1����
			//ǥ���ϴ� �� ���Ǵ� ��ü�� DTO�� �̿�����!!
			if(rs.next()) { //���ڵ尡 �ִٸ�...
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
	
	//�� ��� �޼��� ����!!
	public int insert(Board board) {
		int result=0; //���� ����� ��� ����
		Connection con=null;//���������� �ݵ�� �ʱ�ȭ �ؾ� �Ѵ�!!
		PreparedStatement pstmt=null;
		
		try {
			//1�ܰ�) ����̹� �ε� 
			Class.forName("oracle.jdbc.drive.OracleDriver");
			System.out.println("����̹� �ε� ����");
			
			//2�ܰ�) ���� 
			String url="jdbc:oracle:thin:@localhost:1521:XE";
			String user="jsp0309";
			String pass="jsp0309";
			con=DriverManager.getConnection(url, user, pass);
			if(con ==null) {
				System.out.println("���� ����");
			}else {
				System.out.println("���� ����");
			}
			
			//3�ܰ�) ������ ����
			String sql="insert into board(board_id,writer,title,content)";
			sql+=" values(seq_board.nextval,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, board.getWriter());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			result=pstmt.executeUpdate();//������ ����!!!
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			//try���� catch �� ���~~�� ������ ���� �� ����!!!
			//���� �� ������ db �ݴ� ������ ����̴�..
			//��?? db�� ������ �ݾƾ� �ϹǷ�..
			if(pstmt !=null) { //�޸𸮿� �����Ҷ���..
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con !=null) { //�޸𸮿� �����Ҷ���..
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		return result;
		
		
		//4�ܰ�) �ݱ� 
		
	}
}








