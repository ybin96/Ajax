package com.sist.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sist.dao.DeptDAO;
import com.sist.vo.DeptVO;
import com.sist.vo.JsonResult;

/**
 * Servlet implementation class EditDept
 */
@WebServlet("/EditDept")
public class EditDept extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditDept() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oper = request.getParameter("oper");
		int dno = Integer.parseInt(request.getParameter("dno"));
		String dname = "";
		String dloc = "";
		DeptVO d = null;
		if(oper.equals("del")) {
			dname = request.getParameter("dname");
			dloc = request.getParameter("dloc");
			d = new DeptVO();
			d.setDno(dno);
			d.setDname(dname);
			d.setDloc(dloc);
		}
		
		DeptDAO dao = DeptDAO.getInstance();
		int re = 0;
		switch(oper) {
		case "add":re = dao.insertDept(d);break;
		case "edit":re = dao.updateDept(d);break;
		case "del":re = dao.deleteDept(dno);break;
		}
		JsonResult jr = new JsonResult();
		jr.setResult("success");
		if(re<=0) {
			jr.setResult("fail");
		}
		Gson gson = new Gson();
		String str = gson.toJson(jr);
		
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(str);
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
