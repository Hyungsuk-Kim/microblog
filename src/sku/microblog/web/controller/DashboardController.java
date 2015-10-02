package sku.microblog.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DashboardController
 */
public class DashboardController extends HttpServlet {
	private static final long serialVersionUID = -1291979765729275166L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if (action.equals("main")) {
			this.myPosting(request, response);
		} else if (action.equals("newPosting")) {
			this.newPosting(request, response);
		} else if (action.equals("readPosting")) {
			this.readPosting(request, response);
		} else if (action.equals("updatePosting")) {
			this.updatePosting(request, response);
		} else if (action.equals("removePosting")) {
			this.removePosting(request, response);
		} else if (action.equals("reblog")) {
			this.rebloging(request, response);
		} else if (action.equals("addLike")) {
			this.addLike(request, response);
		} else if (action.equals("cancelLike")) {
			this.cancelLike(request, response);
		} else if (action.equals("following")) {
			this.following(request, response);
		} else if (action.equals("unfollowing")) {
			this.unfollowing(request, response);
		} else if (action.equals("visitBlog")) {
			this.visitBlog(request, response);
		} else if (action.equals("searchInBlog")) {
			this.searchInBlog(request, response);
		}
	}
	private void myPosting(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	private void newPosting(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	private void readPosting(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	private void updatePosting(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	private void removePosting(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	private void rebloging(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	private void addLike(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	private void cancelLike(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	private void following(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	private void unfollowing(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	private void visitBlog(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	private void searchInBlog(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.processRequest(request, response);
	}

}
