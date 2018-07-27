package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.ChangeDao;
import com.dao.CorpDao;
import com.dao.CourtDao;
import com.dao.InvestmentDao;
import com.dao.PersonnelDao;
import com.dao.RefereeDao;
import com.dao.ShareholderDao;

/**
 * Servlet implementation class GetGenealogyServlet
 */
@WebServlet("/GetGenealogyServlet.com")
public class GetGenealogyServlet extends HttpServlet {
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
		
		String result = null;

		try {

			CorpDao corpDao = new CorpDao();		
			String name = corpDao.getCorpName(regist_num);

			result = "[{\"name\":\"" + name + "\",\"children\":" + "[";
			
			InvestmentDao iDao = new InvestmentDao();
			ShareholderDao sDao = new ShareholderDao();
			PersonnelDao pDao = new PersonnelDao();
			ChangeDao chDao = new ChangeDao();
			RefereeDao rDao = new RefereeDao();
			CourtDao coDao = new CourtDao();
			
			String str_1 = iDao.investmentfirm(regist_num);
			String str_2 = sDao.shareholderName(regist_num);
			String str_3 = pDao.personnelName(regist_num);
			String str_4 = chDao.getHistoryShareholder(regist_num);
			String str_5 = chDao.getHistoryLegal(regist_num);
			String str_6 = rDao.refereeName(regist_num);
			String str_7 = coDao.courtName(regist_num);
			
			result = result + str_1 + str_2 + str_3 + str_4 + str_5 + str_6 + str_7;
			
			result = result.substring(0, result.length() - 1) + "]}]";
			
			result = result + "+" + company_name;
			
//			request.setAttribute("company_name", company_name);
//			
//			request.getRequestDispatcher("tu_3.html").forward(request,response);
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
