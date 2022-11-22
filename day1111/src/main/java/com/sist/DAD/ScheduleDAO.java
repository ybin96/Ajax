package com.sist.DAD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.sist.VO.ChatMessageVO;
import com.sist.VO.ExamVO;
import com.sist.VO.GoodsVO;
import com.sist.VO.ScheduleVO;

public class ScheduleDAO {
	
	public static ScheduleDAO dao;
	private ScheduleDAO() {		
	}
	
	public static ScheduleDAO getInstance() {
		if(dao == null) {
			dao = new ScheduleDAO();
		}
		return dao;
	}
	
	public int insert(ScheduleVO s){
		int re = -1;
		String sql = "insert into schedule(no,event_name,event_date) values(seq_schedule.nextval,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Context context = new InitialContext();
			DataSource ds =(DataSource) context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s.getEvent_name());
			pstmt.setString(2, s.getEvent_date());
			re = pstmt.executeUpdate();
		} catch (Exception e2) {
			System.out.println("예외발생:"+e2.getMessage());
		}finally {
			if(pstmt != null) { try{pstmt.close();}catch(Exception e2) {} }
			if(conn != null) { try{conn.close();}catch(Exception e2) {} }
		}
		
		return re;
	}
	
	
	public ArrayList<ScheduleVO> findAll(){
		ArrayList<ScheduleVO> list = new ArrayList<ScheduleVO>();
		String sql = "select * from schedule";
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
				ScheduleVO s = new ScheduleVO();
				s.setNo(rs.getInt("no"));
				s.setEvent_name(rs.getString("event_name"));
				s.setEvent_date(rs.getString("event_date"));
				list.add(s);
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
