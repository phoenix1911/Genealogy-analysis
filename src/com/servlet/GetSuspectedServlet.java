package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.NodeDao;

/**
 * Servlet implementation class GetSuspectedServlet
 */
@WebServlet("/GetSuspectedServlet.com")
public class GetSuspectedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		HttpSession  hs = request.getSession();		
		String regist_num = (String) hs.getAttribute("regist_num");
		String company_name = (String) hs.getAttribute("company_name");
//		String regist_num = "320000000043078";
		
		String result_n = "";
		String result_n_r = "";
		String result = null;

		try {

			NodeDao nDao = new NodeDao();

			result_n = nDao.getNode(regist_num);
			result_n_r = nDao.getNodeRela(regist_num);

			result = result_n + "+" + result_n_r + "+" + company_name;
//			request.setAttribute("company_name", company_name);
//			
//			request.getRequestDispatcher("tu_4.html").forward(request,response);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}

//		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.write(result);
		out.flush();
		out.close();
	}

}
