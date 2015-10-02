package sku.microblog.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sku.microblog.util.DataDuplicatedException;
import sku.microblog.util.DataNotFoundException;

/**
 * Servlet implementation class BlogController
 */
public class BlogController extends HttpServlet {
	private static final long serialVersionUID = 5549809211454526999L;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		try {
			if (action.equals("create")) {
				this.createBlog(request, response);
			} else if (action.equals("select")) {
				this.selectBlog(request, response);
			} else if (action.equals("update")) {
				this.updateBlog(request, response);
			} else if (action.equals("remove")) {
				this.removeBlog(request, response);
			} else if (action.equals("followingList")) {
				this.followingList(request, response);
			} else if (action.equals("unfollow")) {
				this.unfollow(request, response);
			} else if (action.equals("theme")) {
				this.modifyTheme(request, response);
			}
		} catch (DataNotFoundException dne) {
			throw new ServletException(dne);
		} catch (DataDuplicatedException dde) {
			throw new ServletException(dde);
		}
	}

	private void createBlog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DataDuplicatedException {
		// TODO Auto-generated method stub
		
	}

	private void selectBlog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DataNotFoundException {
		// TODO Auto-generated method stub
		
	}

	private void updateBlog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DataNotFoundException {
		// TODO Auto-generated method stub
		
	}

	private void removeBlog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DataNotFoundException {
		// TODO Auto-generated method stub
		
	}

	private void followingList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DataNotFoundException {
		// TODO Auto-generated method stub
		
	}

	private void unfollow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DataNotFoundException {
		// TODO Auto-generated method stub
		
	}

	private void modifyTheme(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
