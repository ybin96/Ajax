package com.sist.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.sist.vo.GoodsVO;

public class GoodsDAO {
	
	public ArrayList<GoodsVO> findAll(){
		ArrayList<GoodsVO> list = new ArrayList<GoodsVO>();
		String sql = "select * from goods";
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
				GoodsVO g = new GoodsVO();
				g.setNo(rs.getInt("no"));
				g.setName(rs.getString("name"));
				g.setPrice(rs.getInt("price"));
				g.setQty(rs.getInt("qty"));
				g.setFname(rs.getString("fname"));
				list.add(g);
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
