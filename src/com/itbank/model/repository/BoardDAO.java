package com.itbank.model.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//�� Ŭ������ ���� �����ͺ��̽��� ���õ� CRUD���� �����ϱ� ���� ���㰴ü�� �����Ѵ�
//�̷��� �������� ���ǵǴ� ��ü�� ������ ���ø����̼� ����о߿����� DAO���Ѵ�.
//DAO (Data Access Object )

public class BoardDAO {

	//�� ��� �޼��� ����!!
	public void insert() {
		try {
			//1�ܰ�) ����̹� �ε� 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("����̹� �ε� ����");
			
			//2�ܰ�) ���� 
			String url="jdbc:oracle:thin:@localhost:1521:XE";
			String user="jsp0309";
			String pass="jsp0309";
			Connection con=DriverManager.getConnection(url, user, pass);
			if(con ==null) {
				System.out.println("���� ����");
			}else {
				System.out.println("���� ����");
			}
			
			String sql="insert into board(board_id,writer,title,content)";
			sql+=" values(seq_board.nextval,?,?,?)";
			PreparedStatement pstmt=con.prepareStatement(sql);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		//3�ܰ�) ������ ����
		//4�ܰ�) �ݱ� 
		
	}
}








