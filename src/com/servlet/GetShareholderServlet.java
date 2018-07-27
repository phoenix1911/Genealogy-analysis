package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.ShareholderDao;

/**
 * Servlet implementation class GetShareholderServlet
 */
@WebServlet("/GetShareholderServlet.com")
public class GetShareholderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		HttpSession  hs = request.getSession();		
		String regist_num = (String) hs.getAttribute("regist_num");
		String company_name = (String) hs.getAttribute("company_name");
//		String regist_num = "320000000043078";
		System.out.println(regist_num);
		
		String result_1 = "";
		String result_2 = "";;
		String result_3 = "";;
		String result_4 = "";;
		String result = null;

		try {
			
			ShareholderDao sDao = new ShareholderDao();
			
			String result_1_0 = sDao.getShareholderTypeJson();
			String result_1_1 = sDao.getShareholderNameJson(regist_num);
			result_1 = result_1_0 + result_1_1;
			result_2 = sDao.getShareholderTypeValueJson(regist_num);
			result_3 = sDao.getShareholderIndividualValueJson(regist_num);
			result_4 = sDao.getMaxShareholder(regist_num);
		
			result = result_1 + "+" + result_2 + "+" + result_3 + "+" + result_4 + "+" + company_name;
			
//			request.setAttribute("company_name", company_name);
//			
//			request.getRequestDispatcher("tu_1.html").forward(request,response);
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
