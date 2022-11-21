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
import com.sist.vo.EmpVO;

public class EmpDAO {

	private static EmpDAO dao;
	
	public static EmpDAO getInstance() {
		if(dao == null) {
			dao = new EmpDAO();
		}
		return dao;
	}
	
	private EmpDAO() {
		
	}
	
	// 사원추가를 위한 메소드
	public int insertEmp(EmpVO e) {
		int re = -1;
		String sql = "update into emp(eno,ename,dno,salary,comm,hiredate,phone,addr,mgr,job,email,jumin) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Context context = new InitialContext();
			DataSource ds =(DataSource) context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, e.getEno());
			pstmt.setString(2, e.getEname());
			pstmt.setInt(3, e.getDno());
			pstmt.setInt(4, e.getSalary());
			pstmt.setInt(5, e.getComm());
			pstmt.setDate(6, e.getHiredate());
			pstmt.setString(7, e.getPhone());
			pstmt.setString(8, e.getAddr());
			pstmt.setInt(9, e.getMgr());
			pstmt.setString(10, e.getJob());
			pstmt.setString(11, e.getEmail());
			pstmt.setString(12, e.getJumin());
			re = pstmt.executeUpdate();
		} catch (Exception e2) {
			System.out.println("예외발생:"+e2.getMessage());
		}finally {
			if(pstmt!=null) {try{pstmt.close();}catch(Exception e2) {}}
			if(conn!=null) {try{conn.close();}catch(Exception e2) {}}
		}
		
		return re;
	}
	
	// 사원 상세보기를 위한 메소드
	public EmpVO findByEno(int eno) {
		EmpVO e = null;
		String sql = "select * from emp where eno=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Context context = new InitialContext();
			DataSource ds =(DataSource) context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, eno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				e = new EmpVO();
				e.setEno(rs.getInt("eno"));
				e.setEname(rs.getString("ename"));
				e.setDno(rs.getInt("dno"));
				e.setSalary(rs.getInt("salary"));
				e.setComm(rs.getInt("comm"));
				e.setHiredate(rs.getDate("hiredate"));
				e.setPhone(rs.getString("phone"));
				e.setAddr(rs.getString("addr"));
				e.setMgr(rs.getInt("mgr"));
				e.setJob(rs.getString("job"));
				e.setJumin(rs.getString("jumin"));
				e.setEmail(rs.getString("email"));
			}		
			 
		}catch (Exception e2) {
			System.out.println("예외발생:"+e2.getMessage());
		}finally {
			if(rs!=null) {try{rs.close();}catch(Exception e2) {}}
			if(pstmt!=null) {try{pstmt.close();}catch(Exception e2) {}}
			if(conn!=null) {try{conn.close();}catch(Exception e2) {}}
		}
		
		
		return e;
	}
	
	// 사원목록을 보여주는 메소드
	public ArrayList<EmpVO> findAll(){
		ArrayList<EmpVO> list = new ArrayList<EmpVO>();
		String sql = "select * from emp";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				EmpVO e = new EmpVO();
				e.setEno(rs.getInt("eno"));
				e.setEname(rs.getString("ename"));
				e.setDno(rs.getInt("dno"));
				e.setSalary(rs.getInt("salary"));
				e.setComm(rs.getInt("comm"));
				e.setHiredate(rs.getDate("hiredate"));
				e.setPhone(rs.getString("phone"));
				e.setAddr(rs.getString("addr"));
				e.setMgr(rs.getInt("mgr"));
				e.setJob(rs.getString("job"));
				e.setJumin(rs.getString("jumin"));
				e.setEmail(rs.getString("email"));
				list.add(e);
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
}
