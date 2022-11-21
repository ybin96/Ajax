package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.sist.vo.DeptVO;

public class DeptDAO {	
	
	private static DeptDAO dao;
	
	public static DeptDAO getInstance() {
		if(dao == null) {
			dao = new DeptDAO();
		}
		return dao;
	}
	
	private DeptDAO() {		
	}
	
	public int deleteDept(int dno) {
		int re = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete dept where dno=?";
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);			
			pstmt.setInt(1, dno);
			re = pstmt.executeUpdate();
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}finally {
			if(pstmt != null) {try{pstmt.close();}catch(Exception e) {}}
			if(conn != null) {try{conn.close();}catch(Exception e) {}}
		}
		return re;
	}
	public int updateDept(DeptVO d) {
		int re = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update dept set dname=?,dloc=? where dno=?";
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, d.getDname());
			pstmt.setString(2, d.getDloc());
			pstmt.setInt(3, d.getDno());
			re = pstmt.executeUpdate();
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}finally {
			if(pstmt != null) {try{pstmt.close();}catch(Exception e) {}}
			if(conn != null) {try{conn.close();}catch(Exception e) {}}
		}
		return re;
	}
	
	
	public DeptVO findByNO(int dno) {
		DeptVO d = null;
		String sql = "select * from dept where dno=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Context context = new InitialContext();
			DataSource ds =(DataSource) context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				d = new DeptVO();
				d.setDno(rs.getInt("dno"));
				d.setDname(rs.getString("dname"));
				d.setDloc(rs.getString("dloc"));
			}		
			 
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}finally {
			if(rs!=null) {try{rs.close();}catch(Exception e) {}}
			if(pstmt!=null) {try{pstmt.close();}catch(Exception e) {}}
			if(conn!=null) {try{conn.close();}catch(Exception e) {}}
		}
		
		return d;
	}
	
	public ArrayList<DeptVO> findAll(){
		ArrayList<DeptVO> list = new ArrayList<DeptVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from dept";
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				DeptVO d = new DeptVO();
				d.setDno(rs.getInt("dno"));
				d.setDname(rs.getString("dname"));
				d.setDloc(rs.getString("dloc"));
				list.add(d);
			}
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}finally {
			if(rs != null) {try{rs.close();}catch(Exception e) {}}
			if(stmt != null) {try{stmt.close();}catch(Exception e) {}}
			if(conn != null) {try{conn.close();}catch(Exception e) {}}
		}
		return list;
	}
	
	
	public int insertDept(DeptVO d) {
		int re = -1;
		String sql = "insert into dept(dno,dname,dloc) values(?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, d.getDno());
			pstmt.setString(2, d.getDname());
			pstmt.setString(3, d.getDloc());
			re = pstmt.executeUpdate();
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}finally {
			if(pstmt != null) {try{pstmt.close();}catch(Exception e) {}}
			if(conn != null) {try{conn.close();}catch(Exception e) {}}
		}
		return re;
	}
	
	
	
}











