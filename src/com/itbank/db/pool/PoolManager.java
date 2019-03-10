package com.itbank.db.pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/*
 * JNDI를 사용하는 코드를 재사용성을 높이기 위해 이 클래스를
 * 작성한다
 * */
public class PoolManager {
	InitialContext initx;//검색 객체
	DataSource ds;//커넥션 객체를 관리하는 객체
	
	public PoolManager() {
		try {
			initx=new InitialContext();//검색 객체 생성
			ds=(DataSource)initx.lookup("java:comp/env/jdbc/oracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}	
	//클라이언트가 커넥션을 얻어갈 수 있게끔 제공하자!!
	public Connection getConnection() {
		Connection con=null;
		try {
			con=ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;//호출자에게 Connection 반환!!(튜브 빌려주기)
	}
	
	//커넥션을 반납하는 메서드 정의
	//사용자가 실행한 쿼리문의 유형에 따라 반납 유형도 오버로딩하자!!
	public void release(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
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








