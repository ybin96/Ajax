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

public class ChatMessageDAO {
	
	public static ChatMessageDAO dao;
	private ChatMessageDAO() {		
	}
	
	public static ChatMessageDAO getInstance() {
		if(dao == null) {
			dao = new ChatMessageDAO();
		}
		return dao;
	}
	
	public int insert(ChatMessageVO c){
		int re = -1;
		String sql = "insert into chatmessage(id,name,message) values(seq_chatmessage.nextval,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Context context = new InitialContext();
			DataSource ds =(DataSource) context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getName());
			pstmt.setString(2, c.getMessage());
			re = pstmt.executeUpdate();
		} catch (Exception e2) {
			System.out.println("예외발생:"+e2.getMessage());
		}finally {
			if(pstmt != null) { try{pstmt.close();}catch(Exception e2) {} }
			if(conn != null) { try{conn.close();}catch(Exception e2) {} }
		}
		
		return re;
	}
	
	
	public ArrayList<ChatMessageVO> findAll(){
		ArrayList<ChatMessageVO> list = new ArrayList<ChatMessageVO>();
		String sql = "select * from chatmessage";
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
				ChatMessageVO c = new ChatMessageVO();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setMessage(rs.getString("message"));
				list.add(c);
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
