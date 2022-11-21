<%@page import="com.google.gson.Gson"%>
<%@page import="com.sist.vo.BookVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sist.dao.BookDAO"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BookDAO dao = BookDAO.getInstance();
	ArrayList<BookVO> list = dao.findAll();
	String str = "";
	Gson gson = new Gson();
	str = gson.toJson(list);
	 
%>
<%= str %>

