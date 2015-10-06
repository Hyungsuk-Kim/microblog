package sku.microblog.web.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sku.microblog.business.domain.Posting;
import sku.microblog.business.service.PostingService;
import sku.microblog.business.service.PostingServiceImpl;
import sku.microblog.util.DataDuplicatedException;
import sku.microblog.util.DataNotFoundException;
import sku.microblog.util.IllegalDataException;
import sku.microblog.util.PageHandler;

/**
 * Servlet implementation class PostingController
 */
public class PostingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 protected void processRequest(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException,
	            IllegalDataException, DataNotFoundException, DataDuplicatedException {
		 
	        String action = request.getParameter("action");
	        
	        if (action.equals("read")) {
			    this.readPosting(request, response);
			} else if (action.equals("find")) {
			    this.findPosting(request, response);
			} else if (action.equals("update")) {
			    this.updatePosting(request, response);
			} else if (action.equals("updateForm")) {
			    this.updatePostingForm(request, response);
			} else if (action.equals("write")) {
			    this.writePosting(request, response);
			} else if (action.equals("writeForm")) {
			    this.writePostingForm(request, response);
			} else if (action.equals("remove")) {
			    this.removePosting(request, response);
			}  else if (action.equals("reply")) {
			    this.replyPosting(request, response);
			} else if (action.equals("replyForm")) {
			    this.replyPostingForm(request, response);
			} else if (action.equals("getPostingCount")) {
			    this.getPostingCount(request, response);
			} else if (action.equals("getPostingList")) {
			    this.getPostingList(request, response);
			} else if (action.equals("addLikes")) {
			    this.addLikes(request, response);
			} else if (action.equals("cancelLike")) {
			    this.cancelLike(request, response);
			} else if (action.equals("getContentsType")) {
				this.getContentType(request, response);
			}
	    }
	
	private void readPosting(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, NumberFormatException, DataNotFoundException {
				String num = request.getParameter("num");
				String blogName = request.getParameter("blogName");
				
				PostingService postingService = new PostingServiceImpl();
				Posting posting = postingService.readPosting(blogName, Integer.parseInt(num));
				
				request.setAttribute("posting", posting);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("blog.jsp");
				dispatcher.forward(request, response);
	}
	
	private void findPosting(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
				String searchType = request.getParameter("searchType");
				String searchText = request.getParameter("searchText");
				
				String pageNumber = request.getParameter("pageNumber");
				
				int currentPageNumber = 1;
				if (pageNumber != null && pageNumber.length() != 0) {
					currentPageNumber = Integer.parseInt(pageNumber);
				}
				
				Map<String, Object> searchInfo = new HashMap<String, Object>();
				searchInfo.put("searchType", searchType);
				searchInfo.put("searchText", searchText);
				
				PostingService postingService = new PostingServiceImpl();
				
				int totalBoardCount = postingService.getPostingCount(searchInfo);
				
				int totalPageCount = PageHandler.getTotalPageCount(totalBoardCount);
				
				int startPageNumber = PageHandler.getStartPageNumber(currentPageNumber);
				
				int endPageNumber = PageHandler.getEndPageNumber(currentPageNumber, totalBoardCount);
				
				int startRow = PageHandler.getStartRow(currentPageNumber);
				
				int endRow = PageHandler.getEndRow(currentPageNumber);
				
				searchInfo.put("startRow", startRow);
				searchInfo.put("endRow", endRow);
				
				Posting[] postingList = postingService.getPostingList(searchInfo);
				
				request.setAttribute("postingList", postingList);
				
				request.setAttribute("startPageNumber", startPageNumber);
				request.setAttribute("endPageNumber", endPageNumber);
				request.setAttribute("currentPageNumber", currentPageNumber);
				request.setAttribute("totalPageCount", totalPageCount);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("postingList.jsp");
				dispatcher.forward(request, response);
	}
	
	private void writePosting(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
				String title = request.getParameter("title");
				String writer = request.getParameter("writer");
				String contents = request.getParameter("contents");
				String ip = request.getRemoteAddr();
				String exposure = request.getParameter("exposure");
				String tags = request.getParameter("tags");
				
			//	Posting posting = new Posting(title, writer, contents, ip, regDate, exposure, tags)
	}
	
	private void writePostingForm(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	private void updatePosting(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
				String num =  request.getParameter("num");
				String writer = request.getParameter("writer");
				String title = request.getParameter("title");
				String contents = request.getParameter("contents");
				String ip = request.getRemoteAddr();
				String exposure = request.getParameter("exposure"); //여기다 값을 입력받나?
	}
	
	private void updatePostingForm(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
				
	}
	
	private void removePosting(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
				
	}
	
	private void replyPosting(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
				
	}
	
	private void replyPostingForm(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
				
	}
	
	private int getPostingCount(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
				return 0;
				
	}
	
	private Posting[] getPostingList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
				return null;
				
	}
	
	private void addLikes(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
				
	}
	
	private void cancelLike(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
				
	}
	
	private void getContentType(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
				
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
