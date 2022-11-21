package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.sist.vo.BookVO;
import com.sist.vo.CustomerOrderVO;

public class BookDAO {
	
	private static BookDAO dao;
	public static BookDAO getInstance() {
		if( dao == null) {
			dao = new BookDAO();
		}
		return dao;
	}
	private BookDAO() {	
	}
	
	public ArrayList<CustomerOrderVO> infoAll(int bookid){
		ArrayList<CustomerOrderVO> list = new ArrayList<CustomerOrderVO>();
		String sql = "select c.custid,name,address,phone,orderid,saleprice,orderdate"
				   + " from customer c, orders o, book b"
				   + " where c.custid = o.custid and b.bookid = o.bookid and b.bookid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Context context = new InitialContext();
			DataSource ds =(DataSource) context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CustomerOrderVO c = new CustomerOrderVO();
				c.setCustid(rs.getInt("custid"));
				c.setName(rs.getString("name"));
				c.setAddress(rs.getString("address"));
				c.setPhone(rs.getString("phone"));
				c.setOrderid(rs.getInt("orderid"));
				c.setSaleprice(rs.getInt("saleprice"));
				c.setOrderdate(rs.getDate("orderdate"));
				list.add(c);
			}
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}finally {
			if(conn != null)try {conn.close();} catch (Exception e2) {}
			if(pstmt != null)try {pstmt.close();} catch (Exception e2) {}
			if(rs != null)try {rs.close();} catch (Exception e2) {}
		}
		
		return list;
	}
	
	// 출판사를 전달받아 도서목록을 반환하는 메소드를 정의
	public ArrayList<BookVO> findBypublisher(String publisher){
		ArrayList<BookVO> list = new ArrayList<BookVO>();
		String sql = "select * from book where publisher=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Context context = new InitialContext();
			DataSource ds =(DataSource) context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, publisher);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BookVO b = new BookVO();
				b.setBookid(rs.getInt("bookid"));
				b.setBookname(rs.getString("bookname"));
				b.setPublisher(rs.getString("publisher"));
				b.setPrice(rs.getInt("price"));
				list.add(b);
			}
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}finally {
			if(conn != null)try {conn.close();} catch (Exception e2) {}
			if(pstmt != null)try {pstmt.close();} catch (Exception e2) {}
			if(rs != null)try {rs.close();} catch (Exception e2) {}
		}
		return list;
	}
	
	// 모든 출판사 목록을 반환하는 메소드를 정의
	public ArrayList<String> findPublisher() {
		ArrayList<String> list = new ArrayList<String>();
		String sql = "select distinct publisher from book";
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
				 list.add(rs.getString(1));
			}
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}finally {
			if(conn != null)try {conn.close();} catch (Exception e2) {}
			if(stmt != null)try {stmt.close();} catch (Exception e2) {}
			if(rs != null)try {rs.close();} catch (Exception e2) {}
		}
		return list;
	}
	
	// 새로운 도서번호를 발행하는 메소드를 정의
	public int getNextBookid() {
		int bookid = 0;
		String sql = "select nvl(max(bookid),0) +1 from book";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Context context = new InitialContext();
			DataSource ds =(DataSource) context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bookid = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}finally {
			if(conn != null)try {conn.close();} catch (Exception e2) {}
			if(pstmt != null)try {pstmt.close();} catch (Exception e2) {}
			if(rs != null)try {rs.close();} catch (Exception e2) {}
		}
		
		return bookid;
	}
	
	// 도서 삭제를 위한 메소드를 정의
	public int deleteBook(int bookid) {
		int re = -1;
		String sql = "delete book where bookid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Context context = new InitialContext();
			DataSource ds =(DataSource) context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookid);
			re = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}finally {
			if(conn != null)try {conn.close();} catch (Exception e2) {}
			if(pstmt != null)try {pstmt.close();} catch (Exception e2) {}
		}
		return re;
	}
	
	// 도서를 등록하는 메소드를 정의
	public int insertBook(BookVO b) {
		int re = -1;
		String sql = "insert into book(bookid,bookname,publisher,price) values(?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Context context = new InitialContext();
			DataSource ds =(DataSource) context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getBookid());
			pstmt.setString(2, b.getBookname());
			pstmt.setString(3, b.getPublisher());
			pstmt.setInt(4, b.getPrice());
			re = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}finally {
			if(conn != null)try {conn.close();} catch (Exception e2) {}
			if(pstmt != null)try {pstmt.close();} catch (Exception e2) {}
		}
		return re;
	}
	
	// 수정할 도서의 정보를 매개변수로 전달받아 해당도서의 정보를 수정하는 메소드를 정의
	public int updateBook(BookVO b) {
		int re = -1;
		String sql = "update book set bookname=?,publisher=?,price=? where bookid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Context context = new InitialContext();
			DataSource ds =(DataSource) context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getBookname());
			pstmt.setString(2, b.getPublisher());
			pstmt.setInt(3, b.getPrice());
			pstmt.setInt(4, b.getBookid());
			re = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}finally {
			if(conn != null)try {conn.close();} catch (Exception e2) {}
			if(pstmt != null)try {pstmt.close();} catch (Exception e2) {}
		}
		
		return re;
	}
	
	// 도서번호를 매개변수로 전달받아 해당 도서의 정보를 반환하는 메소드
	public BookVO findByNo(int bookid) {
		BookVO b = null;
		String sql = "select * from book where bookid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Context context = new InitialContext();
			DataSource ds =(DataSource) context.lookup("java:/comp/env/mydb");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				b = new BookVO();
				b.setBookid(rs.getInt("bookid"));
				b.setBookname(rs.getString("bookname"));
				b.setPublisher(rs.getString("publisher"));
				b.setPrice(rs.getInt("price"));
			}
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}finally {
			if(conn != null)try {conn.close();} catch (Exception e2) {}
			if(pstmt != null)try {pstmt.close();} catch (Exception e2) {}
			if(rs != null)try {rs.close();} catch (Exception e2) {}
		}
		
		return b;
	}
	
	// 모든 도서목록을 반환하는 메소드
	public ArrayList<BookVO> findAll(){
		ArrayList<BookVO> list = new ArrayList<BookVO>();
		String sql = "select * from book";
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
				BookVO b = new BookVO();
				b.setBookid(rs.getInt("bookid"));
				b.setBookname(rs.getString("bookname"));
				b.setPublisher(rs.getString("publisher"));
				b.setPrice(rs.getInt("price"));
				list.add(b);
			}
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}finally {
			if(conn != null)try {conn.close();} catch (Exception e2) {}
			if(stmt != null)try {stmt.close();} catch (Exception e2) {}
			if(rs != null)try {rs.close();} catch (Exception e2) {}
		}
		return list;
	}
	
}
