package sku.microblog.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sku.microblog.business.domain.Blog;
import sku.microblog.business.domain.Member;
import sku.microblog.business.domain.Posting;
import sku.microblog.business.domain.PostingContent;
import sku.microblog.business.service.*;
import sku.microblog.util.DataNotFoundException;

/**
 * Servlet implementation class SearchController
 */
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = -5410439819005540004L;
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		try {
			if (action.equals("home")) {
				this.goHomePage(request, response);
			} else if (action.equals("search")) {
				this.searching(request, response);
			}
		} catch (DataNotFoundException dne) {
			throw new ServletException(dne);
		}
	}
	
	private void goHomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DataNotFoundException {
		HttpSession session = request.getSession(false); 
		Member member = (Member) session.getAttribute("loginMember");

		
		if (member != null) {
			BlogService blogService = new BlogServiceImpl();
			Blog[] blogs = blogService.getMyBlogs(member);
			
			if (blogs.length == 0) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("blogMain.jsp");
				dispatcher.forward(request, response);
				return;
			} else {
				request.setAttribute("blogs", blogs);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/blog/blog.jsp");
				dispatcher.forward(request, response);
				return;
			}
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	private void searching(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DataNotFoundException {
		MemberService memberService = new MemberServiceImpl();
		BlogService blogService = new BlogServiceImpl();
		PostingService postingService = new PostingServiceImpl();
		
		String searchType = request.getParameter("searchType"); // 검색할 대상의 타입 (None(=default) - all, 나머지는 아래 참조)
		String searchText = request.getParameter("searchText"); // 검색 키워드(검색어)
		String sortingOption = request.getParameter("sortingOption"); // 좋아요, 최신 등록일, 조회수, 정확도 등의 순서로 검색하기 위한 옵션
		String blogName = request.getParameter("blogName"); // 블로그 내 검색 기능을 위해 검색 대상이 될 블로그 명을 속성으로 받음
		String target = request.getParameter("target");
		int contentType = Integer.parseInt(request.getParameter("contentType"));
		int startRow = Integer.parseInt(request.getParameter("startRow"));
		int endRow = Integer.parseInt(request.getParameter("endRow"));
		
		Map<String, Object> searchInfo = new HashMap<String, Object>();
		
		if (target != null && target.trim().length() != 0) {
			if (target.equals("all") || target.equals("posting")) {
				if (sortingOption != null || sortingOption.trim().length() != 0) {
					searchInfo.put("sortingOption", sortingOption);
				}
				if (blogName != null || blogName.trim().length() != 0) {
					searchInfo.put("blogName", blogName);
				}
				if (contentType == PostingContent.MIXED_AUDIO_FILE_CONTENT ||
						contentType == PostingContent.MIXED_AUDIO_LINK_CONTENT ||
						contentType == PostingContent.MIXED_VIDEO_FILE_CONTENT ||
						contentType == PostingContent.MIXED_VIDEO_LINK_CONTENT ||
						contentType == PostingContent.MIXED_IMAGE_FILE_CONTENT ||
						contentType == PostingContent.MIXED_IMAGE_LINK_CONTENT ||
						contentType == PostingContent.TEXT_CONTENT ||
						contentType == PostingContent.SINGLE_AUDIO_FILE_CONTENT ||
						contentType == PostingContent.SINGLE_AUDIO_LINK_CONTENT ||
						contentType == PostingContent.SINGLE_VIDEO_FILE_CONTENT ||
						contentType == PostingContent.SINGLE_VIDEO_LINK_CONTENT ||
						contentType == PostingContent.SINGLE_IMAGE_FILE_CONTENT ||
						contentType == PostingContent.SINGLE_IMAGE_LINK_CONTENT) {
					searchInfo.put("contentType", contentType);
				}
			}
			searchInfo.put("target", target);
		} else if (searchType != null && searchType.trim().length() != 0) {
			searchInfo.put("searchType", searchType);
		} else if (searchText != null && searchText.trim().length() != 0) {
			searchInfo.put("searchText", searchText);
		} else if (startRow != 0) {
			searchInfo.put("startRow", startRow);
		} else if (endRow != 0) {
			searchInfo.put("endRow", endRow);
		}
		
		Member[] memberList = null;
		Blog[] blogList = null;
		Posting[] postingList = null;
		
		if (target != null || target.trim().length() != 0) {
			if (target.equals("all")) {
				memberList = memberService.getMemberList(searchInfo);
				blogList = blogService.getBlogList(searchInfo);
				postingList = postingService.getPostingList(searchInfo);
				
				request.setAttribute("memberList", memberList);
				request.setAttribute("blogList", blogList);
				request.setAttribute("postingList", postingList);
				
			} else if (target.equals("posting")) {
				postingList = postingService.getPostingList(searchInfo);
				request.setAttribute("postingList", postingList);
			} else if (target.equals("blog")) {
				blogList = blogService.getBlogList(searchInfo);
				request.setAttribute("blogList", blogList);
			} else if (target.equals("member")) {
				memberList = memberService.getMemberList(searchInfo);
				request.setAttribute("memberList", memberList);
			}
		}
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
