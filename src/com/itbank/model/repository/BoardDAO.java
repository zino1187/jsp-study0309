package com.itbank.model.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.itbank.model.domain.Board;

//�� Ŭ������ ���� �����ͺ��̽��� ���õ� CRUD���� �����ϱ� ���� ���㰴ü�� �����Ѵ�
//�̷��� �������� ���ǵǴ� ��ü�� ������ ���ø����̼� ����о߿����� DAO���Ѵ�.
//DAO (Data Access Object )

public class BoardDAO {

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








