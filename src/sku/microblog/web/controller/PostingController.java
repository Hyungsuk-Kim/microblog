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
import javax.servlet.http.HttpSession;

import sku.microblog.business.domain.Member;
import sku.microblog.business.domain.Posting;
import sku.microblog.business.domain.PostingContent;
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
	
	private static final long serialVersionUID = -7621617611088089272L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
        String action = request.getParameter("action");
        try {
	        if (action.equals("read")) {
			    this.readPosting(request, response);
			} else if (action.equals("list")) {
			    this.selectPosting(request, response);
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
			} else if (action.equals("like")) {
			    this.addLikes(request, response);
			} else if (action.equals("cancelLike")) {
			    this.cancelLike(request, response);
			}
        } catch (DataNotFoundException dne) {
        	throw new ServletException(dne);
        }
    }
	
	private void readPosting(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException, DataNotFoundException {
		String num = request.getParameter("num");
		String blogName = request.getParameter("blogName");
		
		PostingService postingService = new PostingServiceImpl();
		Posting posting = postingService.readPosting(blogName, Integer.parseInt(num));
		
		request.setAttribute("posting", posting);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("blog.jsp");
		dispatcher.forward(request, response);
	}
	
	private void selectPosting(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DataNotFoundException {
		/*String searchType = request.getParameter("searchType");
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
		request.setAttribute("totalPageCount", totalPageCount);*/
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/search?action=search");
		dispatcher.include(request, response);
		
		dispatcher = request.getRequestDispatcher("postingList.jsp");
		dispatcher.forward(request, response);
	}
	
	private void writePosting(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, DataNotFoundException {
		HttpSession session = request.getSession(false);
		Member member = (Member) session.getAttribute("loginMember");
		
		String blogName = request.getParameter("blogName"); System.out.println(blogName);
		String textContent = request.getParameter("textContent"); System.out.println(textContent);
		String filePaths = request.getParameter("filePaths");
		String title = request.getParameter("title"); System.out.println(title);
		//String writer = request.getParameter("writer");
		String contentType = request.getParameter("contentType"); System.out.println(contentType);
		String exposure = request.getParameter("exposure"); System.out.println(exposure);
		String tags = request.getParameter("tags"); System.out.println(tags);
		String postingType = request.getParameter("postingType"); System.out.println(postingType);
		String reblogOption = request.getParameter("reblogOption"); System.out.println(reblogOption);
		
		Posting posting = null;
		PostingContent content = null;
		int conType = 0;
		int expose = 0;
		int postType = 0;
		
		// 공개여부 결정
		if (exposure.equals("public_all")) {
			expose = Posting.PUBLIC_ALLOW_BOTH_REPLY_AND_REBLOG; // 답글, 리블로그 모두 허용
		} else if (exposure.equals("")) {
			expose = Posting.PUBLIC_ALLOW_REPLY_AND_NO_REBLOG; // 답글만 허용
		} else if (exposure.equals("")) {
			expose = Posting.PUBLIC_NO_REPLY_AND_ALLOW_REBLOG; // 리블로그만 허용
		} else if (exposure.equals("")) {
			expose = Posting.PUBLIC_NO_REPLY_NO_REBLOG; // 답글, 리블로그 모두 불가능
		} else if (exposure.equals("private")) {
			expose = Posting.PRIVATE; // 비공개 포스팅(노출 안됨)
		}
		
		// 포스팅의 타입 결정
		if (postingType.equals("normal")) {
			postType = Posting.NORMAL_TYPE_POSTING;
		} else if (postingType.equals("reply")) {
			postType = Posting.REPLY_TYPE_POSTING;
		} else if (postingType.equals("qna")) {
			postType = Posting.QNA_TYPE_POSTING;
		}
		
		if (contentType.equals("textContent")) {
			if (textContent != null) {
				conType = PostingContent.TEXT_CONTENT;
				content = new PostingContent(textContent);
				posting = new Posting(title, member.getName(), content, conType, expose, tags, postType, 0);
			}
		} /*else if (contentType.equals("videoFileContent")) {
			if (textContent != null) {
				content = new PostingContent(textContent, filePaths);
			} else {
				content = new PostingContent();
			}
		} else if (contentType.equals("audioFileContent")) {
			if (textContent != null) {
				content = new PostingContent(textContent, filePaths);
			} else {
				content = new PostingContent();
			}
		} else if (contentType.equals("imageFileContent")) {
			if (textContent != null) {
				content = new PostingContent(textContent, filePaths);
			} else {
				content = new PostingContent();
			}
		} else if (contentType.equals("videoLinkContent")) {
			if (textContent != null) {
				content = new PostingContent(textContent, filePaths);
			} else {
				content = new PostingContent();
			}
		} else if (contentType.equals("audioLinkContent")) {
			if (textContent != null) {
				content = new PostingContent(textContent, filePaths);
			} else {
				content = new PostingContent();
			}
		} else if (contentType.equals("imageLinkContent")) {
			if (textContent != null) {
				content = new PostingContent(textContent, filePaths);
			} else {
				content = new PostingContent();
			}
		}*/
		
		PostingService postingService = new PostingServiceImpl();
		postingService.writePosting(blogName, posting);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
	
	private void writePostingForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("writeForm.jsp");
		dispatcher.forward(request, response);
	}
	
	private void updatePosting(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	private void updatePostingForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DataNotFoundException {
		String num = request.getParameter("num");
		String blogName = request.getParameter("blogName");
		
		if(num != null) {
			PostingService postingService = new PostingServiceImpl();
			postingService.findPosting(blogName, Integer.parseInt(num));
		}
	}
	
	private void removePosting(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DataNotFoundException {
		String num = request.getParameter("num");
		String blogName = request.getParameter("blogName");
		
		PostingService postingService = new PostingServiceImpl();
		postingService.removePosting(blogName, Integer.parseInt(num));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(arg0);
		dispatcher.forward(request, response);
	}
	
	private void replyPosting(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	private void replyPostingForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(arg0);
		dispatcher.forward(request, response);
	}
	
	private void addLikes(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
				
	}
	
	private void cancelLike(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
				
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
