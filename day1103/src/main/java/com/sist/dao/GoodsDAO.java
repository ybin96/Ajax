package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.sist.vo.GoodsVO;

public class GoodsDAO {
	
	//상품번호를 매개변수로 전달받아 해당 상품 하나를 검색하여 반환하는 메소드를 정의
	public GoodsVO findByNo(int no) {
		GoodsVO g = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from goods where no=?";
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				g = new GoodsVO();
				g.setNo(rs.getInt("no"));
				g.setName(rs.getString("name"));
				g.setQty(rs.getInt("qty"));
				g.setPrice(rs.getInt("price"));
				g.setFname(rs.getString("fname"));
			}
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}finally {
			if(rs != null) {try{rs.close();}catch(Exception e) {}}
			if(pstmt != null) {try{pstmt.close();}catch(Exception e) {}}
			if(conn != null) {try{conn.close();}catch(Exception e) {}}
		}
		return g;
	}
	
	
	//모든 상품목록을 반환하는 메소드 정의
	public ArrayList<GoodsVO> findAll(){
		ArrayList<GoodsVO> list = new ArrayList<GoodsVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from goods";
		try {
			Context context = new InitialContext();
			DataSource ds =(DataSource) context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				GoodsVO g = new GoodsVO();
				g.setNo(rs.getInt("no"));
				g.setName(rs.getString("name"));
				g.setQty(rs.getInt("qty"));
				g.setPrice(rs.getInt("price"));
				g.setFname(rs.getString("fname"));
				list.add(g);
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
		
	
	//상품을 등록하는 메소드를 정의
	public int insertGoods(GoodsVO g) {
		int re = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into goods(no,name,qty,price,fname) "
					+ "values(?,?,?,?,?)";
			
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, g.getNo());
			pstmt.setString(2, g.getName());
			pstmt.setInt(3, g.getQty());
			pstmt.setInt(4, g.getPrice());
			pstmt.setString(5, g.getFname());
			re = pstmt.executeUpdate();			
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}finally {
			if(pstmt != null) { try { pstmt.close();}catch(Exception e) {} }
			if(conn != null) { try{conn.close();}catch(Exception e) {}}			
		}
		
		return re;
	}
	
	//상품 수정을 위한 메소드 정의
	public int updateGoods(GoodsVO g) {
		int re = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String sql = "update goods set name=?,qty=?,price=?,fname=? where "
					+ "no=?";
			
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);		
			pstmt.setString(1, g.getName());
			pstmt.setInt(2, g.getQty());
			pstmt.setInt(3, g.getPrice());
			pstmt.setString(4, g.getFname());
			pstmt.setInt(5, g.getNo());
			re = pstmt.executeUpdate();			
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}finally {
			if(pstmt != null) { try { pstmt.close();}catch(Exception e) {} }
			if(conn != null) { try{conn.close();}catch(Exception e) {}}			
		}
		
		return re;
	}
	
	//상품 삭제를 위한 메소드 정의
	public int deleteGoods(int no) {
		int re = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String sql = "delete goods where no=?";
			
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			re = pstmt.executeUpdate();			
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}finally {
			if(pstmt != null) { try { pstmt.close();}catch(Exception e) {} }
			if(conn != null) { try{conn.close();}catch(Exception e) {}}			
		}
		
		return re;
	}
}















