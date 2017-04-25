package com.sn.serv;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sn.api.util.APIConstants;
import com.sn.create.bean.RetrieveSourcesBean;
import com.sn.database.accessors.SourceAccessor;
import com.sn.database.objects.Source;
import com.sn.list.beans.CreateSourceListBean;

/**
 * Servlet implementation class LoadSourcesServlet
 */
@WebServlet("/LoadSourcesServlet")
public class LoadSourcesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadSourcesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		RetrieveSourcesBean sourcesBean = new RetrieveSourcesBean();
		sourcesBean.loadSources();
		
		List<Source> sourcesList = null;
		CreateSourceListBean sourceListBean = new CreateSourceListBean();
		sourcesList = sourceListBean.getSources();
		request.setAttribute(APIConstants.SOURCES, sourcesList);
		
		for(Source s : sourcesList) {
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
