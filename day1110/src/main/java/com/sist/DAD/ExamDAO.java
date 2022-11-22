package com.sist.DAD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.sist.VO.ExamVO;

public class ExamDAO {
	
	public int deleteExam(int no) {
		int re = -1;
		String sql = "delete exam where no=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Context context = new InitialContext();
			DataSource ds =(DataSource) context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			re = pstmt.executeUpdate();
		} catch (Exception e2) {
			System.out.println("예외발생:"+e2.getMessage());
		}finally {
			if(pstmt != null) { try{pstmt.close();}catch(Exception e2) {} }
			if(conn != null) { try{conn.close();}catch(Exception e2) {} }
		}
		
		return re;
	}
	
	public int updateExam(ExamVO e) {
		int re = -1;
		String sql = "update exam set name=?,sex=?,addr=?,blood=? where no=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Context context = new InitialContext();
			DataSource ds =(DataSource) context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, e.getName());
			pstmt.setString(2, e.getSex());
			pstmt.setString(3, e.getAddr());
			pstmt.setString(4, e.getBlood());
			pstmt.setInt(5, e.getNo());
			re = pstmt.executeUpdate();
		} catch (Exception e2) {
			System.out.println("예외발생:"+e2.getMessage());
		}finally {
			if(pstmt != null) { try{pstmt.close();}catch(Exception e2) {} }
			if(conn != null) { try{conn.close();}catch(Exception e2) {} }
		}
		
		return re;
	}
	
	public int insertExam(ExamVO e){
		int re = -1;
		String sql = "insert into exam(no,name,sex,addr,blood) values(seq_member.nextval,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Context context = new InitialContext();
			DataSource ds =(DataSource) context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, e.getName());
			pstmt.setString(2, e.getSex());
			pstmt.setString(3, e.getAddr());
			pstmt.setString(4, e.getBlood());
			re = pstmt.executeUpdate();
		} catch (Exception e2) {
			System.out.println("예외발생:"+e2.getMessage());
		}finally {
			if(pstmt != null) { try{pstmt.close();}catch(Exception e2) {} }
			if(conn != null) { try{conn.close();}catch(Exception e2) {} }
		}
		
		return re;
	}
	
	public ArrayList<ExamVO> findAll(){
		ArrayList<ExamVO> list = new ArrayList<ExamVO>();
		String sql = "select * from exam";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Context context = new InitialContext();
			DataSource ds =(DataSource) context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				ExamVO e = new ExamVO();
				e.setNo(rs.getInt("no"));
				e.setName(rs.getString("name"));
				e.setSex(rs.getString("sex"));
				e.setAddr(rs.getString("addr"));
				e.setBlood(rs.getString("blood"));
				list.add(e);
			}
			
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}finally {
			if(rs != null) { try{rs.close();}catch(Exception e) {} }
			if(stmt != null) { try{stmt.close();}catch(Exception e) {} }
			if(conn != null) { try{conn.close();}catch(Exception e) {} }
		}
		
		return list;
	}
}
