package com.itbank.db.pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/*
 * JNDI�� ����ϴ� �ڵ带 ���뼺�� ���̱� ���� �� Ŭ������
 * �ۼ��Ѵ�
 * */
public class PoolManager {
	private static PoolManager instance; //�����ڸ� ��������, ����ȭ��
	//getter�� ���� �ν��Ͻ��� ����������!!
	
	InitialContext initx;//�˻� ��ü
	DataSource ds;//Ŀ�ؼ� ��ü�� �����ϴ� ��ü
	
	public static PoolManager getInstance() {
		if(instance==null) {//null�̸� ���⼭ �ø���!!
			instance = new PoolManager();
		}
		return instance;
	}
	//�������� ���������ڸ� private ���� �����Ѵ� ��?? new�� ���� ���ϰ�
	//�ҷ���..
	private PoolManager() {
		try {
			initx=new InitialContext();//�˻� ��ü ����
			ds=(DataSource)initx.lookup("java:comp/env/jdbc/oracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}	
	//Ŭ���̾�Ʈ�� Ŀ�ؼ��� �� �� �ְԲ� ��������!!
	public Connection getConnection() {
		Connection con=null;
		try {
			con=ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;//ȣ���ڿ��� Connection ��ȯ!!(Ʃ�� �����ֱ�)
	}
	
	//Ŀ�ؼ��� �ݳ��ϴ� �޼��� ����
	//����ڰ� ������ �������� ������ ���� �ݳ� ������ �����ε�����!!
	public void release(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//insert ,update ,delete
	public void release(Connection con, PreparedStatement pstmt) {
		try {
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//select
	public void release(Connection con, PreparedStatement pstmt,ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}







