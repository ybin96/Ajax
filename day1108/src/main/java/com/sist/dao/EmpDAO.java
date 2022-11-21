package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.sist.vo.EmpVO;

public class EmpDAO {
	private EmpDAO() {		
	}
	private static EmpDAO dao;
	public static EmpDAO getInstance() {
		if(dao == null) {
			dao = new EmpDAO();
		}
		return dao;
	}
	
	
	public EmpVO findByEno(int eno) {
		EmpVO e = null;
		String sql = "select * from emp where eno=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Context context = new InitialContext();
			DataSource ds= (DataSource) context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, eno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				e  = new EmpVO();
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
				e.setEmail(rs.getString("email"));
				e.setJumin(rs.getString("jumin"));
			}
			
		}catch (Exception ex) {
			System.out.println("예외발생:"+ex.getMessage());
		}finally {
			if(rs != null) {try {
				rs.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}}
			if(pstmt != null) {try {
				pstmt.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}}
			if(conn != null) {try {
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}}
		}
		return e;
	}
	
	
	public ArrayList<EmpVO> findByDno(int dno){
		ArrayList<EmpVO> list = new ArrayList<EmpVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Context context = new InitialContext();
			DataSource ds =(DataSource) context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs= stmt.executeQuery("select * from emp where dno="+dno);
			while(rs.next()) {
				EmpVO e = new EmpVO();
				e.setEno(rs.getInt("eno"));
				e.setEname(rs.getString("ename"));
				e.setDno(rs.getInt("dno"));
				e.setSalary(rs.getInt("salary"));
				list.add(e);
			}
			
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}finally {
			if(rs != null) {try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
			if(stmt != null) {try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
			if(conn != null) {try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		}
		
		return list;
	}
	public ArrayList<EmpVO> findAll(){
		ArrayList<EmpVO> list = new ArrayList<EmpVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Context context = new InitialContext();
			DataSource ds =(DataSource) context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs= stmt.executeQuery("select * from emp");
			while(rs.next()) {
				EmpVO e = new EmpVO();
				e.setEno(rs.getInt("eno"));
				e.setEname(rs.getString("ename"));
				e.setDno(rs.getInt("dno"));
				e.setSalary(rs.getInt("salary"));
				list.add(e);
			}
			
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}finally {
			if(rs != null) {try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
			if(stmt != null) {try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
			if(conn != null) {try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		}
		
		return list;
	}
}	


















