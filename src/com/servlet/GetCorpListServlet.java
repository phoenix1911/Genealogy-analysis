package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.Branch;
import com.bean.Change;
import com.bean.Corp;
import com.bean.Investment;
import com.bean.Personnel;
import com.bean.Shareholder;
import com.dao.BranchDao;
import com.dao.ChangeDao;
import com.dao.CorpDao;
import com.dao.InvestmentDao;
import com.dao.PersonnelDao;
import com.dao.ShareholderDao;

/**
 * Servlet implementation class GetCorpListServlet
 */
@WebServlet("/GetCorpListServlet.com")
public class GetCorpListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String company_name = request.getParameter("company_name");
		
		try {
			CorpDao cDao = new CorpDao();
			List<Corp> list_corp = new ArrayList<Corp>();
			list_corp = cDao.corplist(company_name);
//			System.out.println(list_corp);
			
			request.setAttribute("list_corp", list_corp);
			
			request.getRequestDispatcher("listinfo.jsp").forward(request,response);
//			request.getRequestDispatcher("/GetGenealogy.com").forward(request,response);
	    	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
