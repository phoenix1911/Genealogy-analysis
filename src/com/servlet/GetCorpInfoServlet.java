package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class GetCorpServlet
 */
@WebServlet("/GetCorpInfoServlet.com")
public class GetCorpInfoServlet extends HttpServlet {
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
			String regist_num = cDao.getCorpRegistNum(company_name);
			List<Corp> list_corp = new ArrayList<Corp>();
			list_corp = cDao.corpinfo(regist_num);
//			System.out.println(list_corp);
		
			List<Shareholder> list_shareholder = new ArrayList<Shareholder>();
			ShareholderDao sDao = new ShareholderDao();
			list_shareholder = sDao.shareholderinfo(regist_num);
//			System.out.println(list_shareholder);
			
			List<Investment> list_investment = new ArrayList<Investment>();
			InvestmentDao iDao = new InvestmentDao();
			list_investment = iDao.investmentinfo(regist_num);
//			System.out.println(list_investment);
			
			List<Personnel> list_personnel = new ArrayList<Personnel>();
			PersonnelDao pDao = new PersonnelDao();
			list_personnel = pDao.personnelinfo(regist_num);
//			System.out.println(list_personnel);
			
			List<Branch> list_branch = new ArrayList<Branch>();
			BranchDao bDao = new BranchDao();
			list_branch = bDao.branchinfo(regist_num);
//			System.out.println(list_branch);
			
			List<Change> list_change = new ArrayList<Change>();
			ChangeDao hDao = new ChangeDao();
			list_change = hDao.changeinfo(regist_num);
//			System.out.println(list_change);
			
			request.setAttribute("list_corp", list_corp);
			request.setAttribute("list_shareholder", list_shareholder);
			request.setAttribute("list_investment", list_investment);
			request.setAttribute("list_personnel", list_personnel);
			request.setAttribute("list_branch", list_branch);
			request.setAttribute("list_change", list_change);
			
			request.getRequestDispatcher("showinfo.jsp").forward(request,response);
//			request.getRequestDispatcher("/GetGenealogy.com").forward(request,response);
			
			HttpSession  hs = request.getSession();
	    	hs.setAttribute("regist_num", regist_num);
	    	hs.setAttribute("company_name", company_name);
			

	    	
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
