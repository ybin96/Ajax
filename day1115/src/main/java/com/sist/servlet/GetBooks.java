package com.sist.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.BookDAO;
import com.sist.vo.BookVO;

/**
 * Servlet implementation class GetBooks
 */
@WebServlet("/GetBooks")
public class GetBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBooks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = 1;
		int rows = 10;
		String searchField = null;
		String searchOper = null;
		String searchString = null;
		
		boolean _search = Boolean.parseBoolean(request.getParameter("_search"));
		
		if(_search) {
			searchField = request.getParameter("searchField");
			searchOper = request.getParameter("searchOper");
			searchString = request.getParameter("searchString");
		}
		  
		if(request.getParameter("page")!= null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		if(request.getParameter("rows")!= null) {
			rows = Integer.parseInt(request.getParameter("rows"));
		}
		
		BookDAO dao = BookDAO.getInstance();
		ArrayList<BookVO> list = dao.findAll(rows,page,searchField,searchOper,searchString);
		String str = "<rows>";
		str += "<page>"+page+"</page>";
		str += "<total>"+BookDAO.totalPage+"</total>";
		str += "<records>"+BookDAO.totalRecord+"</records>";
		for(BookVO b:list) {
			str += "<row>";
			str += "<cell>"+b.getBookid()+"</cell>";
			str += "<cell>"+b.getBookname()+"</cell>";
			str += "<cell>"+b.getPublisher()+"</cell>";
			str += "<cell>"+b.getPrice()+"</cell>";
			str += "</row>";
		}
		str += "</rows>";
		
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(str);
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
