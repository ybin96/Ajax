<%@page import="com.sist.vo.BookVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sist.dao.BookDAO"%>
<%@ page language="java" contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BookDAO dao = BookDAO.getInstance();
	ArrayList<BookVO> list = dao.findAll();
	String str = "[";
	for(BookVO b:list){
		str += "{\"bookid\":\""+b.getBookid()+"\",\"bookname\":\""+b.getBookname()+"\",\"publisher\":\""+b.getPublisher()+"\",\"price\":\""+b.getPrice()+"\"},";
	}
	
	str = str.substring(0, str.length()-1);
	str += "]";
%>
<%= str %>

