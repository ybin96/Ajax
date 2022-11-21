<%@page import="com.sist.vo.DeptVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sist.dao.DeptDAO"%>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String str = "<list>";
	DeptDAO dao = DeptDAO.getInstance();
	ArrayList<DeptVO> list = dao.findAll();
	for(DeptVO d:list){
		str += "<dept>";
		str += "<dno>"+d.getDno()+"</dno>";
		str += "<dname>"+d.getDname()+"</dname>";
		str += "<dloc>"+d.getDloc()+"</dloc>";
		str += "</dept>";
	}	
	str += "</list>";
%>    
<%=str%>