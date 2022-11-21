<%@page import="com.sist.vo.DeptVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sist.dao.DeptDAO"%>
<%@ page language="java" contentType="text/plaine; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String str = "[";
	DeptDAO dao = DeptDAO.getInstance();
	ArrayList<DeptVO> list = dao.findAll();
	for(DeptVO d :list){
		str += "{\"dno\":\""+d.getDno()+"\",\"dname\":\""+d.getDname()+"\",\"dloc\":\""+d.getDloc()+"\"},";
	}
	str = str.substring(0, str.length()-1);
	str += "]";
%>    
<%=str%>