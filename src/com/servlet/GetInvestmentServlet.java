package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.CorpDao;
import com.dao.InvestmentDao;
import com.dao.ShareholderDao;

/**
 * Servlet implementation class GetInvestmentServlet
 */
@WebServlet("/GetInvestmentServlet.com")
public class GetInvestmentServlet extends HttpServlet {
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
		String result = null;

		try {

			CorpDao corpDao = new CorpDao();		
			String name = corpDao.getCorpName(regist_num);

			result = "[{\'name\':\'" + name + "\',\'children\':" + "[";
			
			InvestmentDao iDao = new InvestmentDao();
			ShareholderDao sDao = new ShareholderDao();

			
			String str_1 = iDao.investmentfirm(regist_num);
			String str_2 = sDao.shareholderName(regist_num);

			
			result = result + str_1 + str_2;
			
			result = result.substring(0, result.length() - 1) + "]}]";
			
			result = result + "+" + company_name;
			
//			request.setAttribute("company_name", company_name);
//			
//			request.getRequestDispatcher("tu_2.html").forward(request,response);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		PrintWriter out = response.getWriter();
		out.write(result);
		out.flush();
		out.close();
	}

}
