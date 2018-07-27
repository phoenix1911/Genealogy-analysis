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

import com.dao.CorpDao;

/**
 * Servlet implementation class GetCorpNameServlet
 */
@WebServlet("/GetCorpNameServlet.com")
public class GetCorpNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String result = "[";
		
		try {
			CorpDao cDao = new CorpDao();
			List<String> list = new ArrayList<String>();
			list = cDao.getAllCorpName();
			for (int i = 0; i < list.size(); i++) {
				result = result + "\"" + list.get(i) + "\",";
			}
			result = result.substring(0, result.length() - 1) + "]";	
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
