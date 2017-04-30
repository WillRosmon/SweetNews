package com.sn.serv;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sn.create.bean.RetrieveArticlesBean;
import com.sn.create.bean.RetrieveSourcesBean;
import com.sn.database.objects.Article;

/**
 * Servlet implementation class MainServlet
 */
//@WebServlet("/Main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Entered doGet()");
		RetrieveArticlesBean listBean = new RetrieveArticlesBean();
		
		RetrieveSourcesBean sourcesBean = new RetrieveSourcesBean();
		System.out.println("Initializing Sources");
		sourcesBean.initialiseSources();
		
		System.out.println("Initializing Articles");
		listBean.initaliseArticles();
		List<Article> articles = listBean.getMainPageArticles();
		request.setAttribute("articles", articles);
		String url = "/index.jsp";
		System.out.println("Returning " + articles.size() + " to the page");
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
