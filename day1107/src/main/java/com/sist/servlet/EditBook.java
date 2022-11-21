package com.sist.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.BookDAO;
import com.sist.vo.BookVO;

/**
 * Servlet implementation class EditBook
 */
@WebServlet("/EditBook")
public class EditBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int re = 0;
		String oper = request.getParameter("oper");
		int bookid = Integer.parseInt(request.getParameter("bookid"));
		int price = 0;
		String bookname = "";
		String publisher = "";
		BookVO b = null;
		if(!oper.equals("delete")) {
			price = Integer.parseInt(request.getParameter("price"));
			bookname = request.getParameter("bookname");
			publisher = request.getParameter("publisher");
			b = new BookVO();
			b.setBookid(bookid);
			b.setBookname(bookname);
			b.setPublisher(publisher);
			b.setPrice(price);
		}
		BookDAO dao = BookDAO.getInstance();
		switch(oper) {
			case "add":dao.insertBook(b);break;
			case "update":dao.updateBook(b);break;
			case "delete":dao.deleteBook(bookid);break;
		}
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		out.print(re);
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
