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
			} else if (action.equals("trend")) {
				this.trendPosting(request, response);
			} else if (action.equals("video")) {
				this.videoPosting(request, response);
			} else if (action.equals("image")) {
				this.imagePosting(request, response);
			} else if (action.equals("audio")) {
				this.audioPosting(request, response);
			} else if (action.equals("text")) {
				this.trendPosting(request, response);
			} else if (action.equals("blogBoundary")) {
				this.blogBoundary(request, response);
			} else if (action.equals("memberList")) {
				this.selectMemberList(request, response);
			} else if (action.equals("postingList")) {
				this.selectPostingList(request, response);
			} else if (action.equals("blogList")) {
				this.selectPostingList(request, response);
			} else if (action.equals("blogPosting")) {
				this.blogListUp(request, response);
			}
		} catch (DataNotFoundException dne) {
			throw new ServletException(dne);
		}
	}
	
	private void videoPosting(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DataNotFoundException {
		Map<String, Object> searchInfo = new HashMap<String, Object>();
		searchInfo.put("target", "posting");
		searchInfo.put("contentType", PostingContent.MIXED_VIDEO_FILE_CONTENT);
		
		PostingService postingService = new PostingServiceImpl();
		Posting[] postingList = postingService.getPostingList(searchInfo);
		
		request.setAttribute("postingList", postingList);
	}

	private void imagePosting(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DataNotFoundException {
		Map<String, Object> searchInfo = new HashMap<String, Object>();
		searchInfo.put("target", "posting");
		searchInfo.put("contentType", PostingContent.MIXED_IMAGE_FILE_CONTENT);
		
		PostingService postingService = new PostingServiceImpl();
		Posting[] postingList = postingService.getPostingList(searchInfo);
		
		request.setAttribute("postingList", postingList);
	}

	private void audioPosting(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DataNotFoundException {
		Map<String, Object> searchInfo = new HashMap<String, Object>();
		searchInfo.put("target", "posting");
		searchInfo.put("contentType", PostingContent.MIXED_AUDIO_FILE_CONTENT);
		
		PostingService postingService = new PostingServiceImpl();
		Posting[] postingList = postingService.getPostingList(searchInfo);
		
		request.setAttribute("postingList", postingList);
	}

	private void blogBoundary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DataNotFoundException {
		String blogName = request.getParameter("blogName");
		String searchType = request.getParameter("searchType");
		String searchText = request.getParameter("searchText");
		String startRow = request.getParameter("startRow");
		String endRow = request.getParameter("endRow");
		
		Map<String, Object> searchInfo = new HashMap<String, Object>();
		searchInfo.put("target", "posting");
		searchInfo.put("blogName", blogName);
		if (searchType != null) {
			searchInfo.put("searchType", searchType);
		}
		if (searchText != null) {
			searchInfo.put("searchText", searchText);
		}
		if (startRow != null) {
			searchInfo.put("startRow", Integer.parseInt(startRow));
		}
		if (endRow != null) {
			searchInfo.put("endRow", Integer.parseInt(endRow));
		}
		
		PostingService postingService = new PostingServiceImpl();
		Posting[] postingList = postingService.getPostingList(searchInfo);
		
		request.setAttribute("postingList", postingList);
	}

	private void selectMemberList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchType = request.getParameter("searchType");
		String searchText = request.getParameter("searchText");
		String startRow = request.getParameter("startRow");
		String endRow = request.getParameter("endRow");
		
		Map<String, Object> searchInfo = new HashMap<String, Object>();
		searchInfo.put("target", "member");
		searchInfo.put("contentType", PostingContent.MIXED_AUDIO_FILE_CONTENT);
		if (searchType != null) {
			searchInfo.put("searchType", searchType);
		}
		if (searchText != null) {
			searchInfo.put("searchText", searchText);
		}
		if (startRow != null) {
			searchInfo.put("startRow", Integer.parseInt(startRow));
		}
		if (endRow != null) {
			searchInfo.put("endRow", Integer.parseInt(endRow));
		}
		MemberService memberService = new MemberServiceImpl();
		Member[] memberList = memberService.getMemberList(searchInfo);
		
		request.setAttribute("memberList", memberList);
	}

	private void selectPostingList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DataNotFoundException {
		String searchType = request.getParameter("searchType");
		String searchText = request.getParameter("searchText");
		String startRow = request.getParameter("startRow");
		String endRow = request.getParameter("endRow");
		
		Map<String, Object> searchInfo = new HashMap<String, Object>();
		searchInfo.put("target", "posting");
		searchInfo.put("contentType", PostingContent.MIXED_AUDIO_FILE_CONTENT);
		if (searchType != null) {
			searchInfo.put("searchType", searchType);
		}
		if (searchText != null) {
			searchInfo.put("searchText", searchText);
		}
		if (startRow != null) {
			searchInfo.put("startRow", Integer.parseInt(startRow));
		}
		if (endRow != null) {
			searchInfo.put("endRow", Integer.parseInt(endRow));
		}
		PostingService postingService = new PostingServiceImpl();
		Posting[] postingList = postingService.getPostingList(searchInfo);
		
		request.setAttribute("postingList", postingList);
	}

	private void trendPosting(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DataNotFoundException {
		String searchType = request.getParameter("searchType");
		String searchText = request.getParameter("searchText");
		String startRow = request.getParameter("startRow");
		String endRow = request.getParameter("endRow");
		
		PostingService postingService = new PostingServiceImpl();
		
		Map<String, Object> searchInfo = new HashMap<String, Object>();
		searchInfo.put("sortOption", "popularity");
		searchInfo.put("target", "posting");
		searchInfo.put("searchType", "all");
		if (searchText != null) {
			searchInfo.put("searchText", searchText);
		}
		if (searchType != null) {
			searchInfo.put("searchText", searchText);
		}
		if (startRow != null) {
			searchInfo.put("startRow", Integer.parseInt(startRow));
		}
		if (endRow != null) {
			searchInfo.put("endRow", Integer.parseInt(endRow));
		}
		searchInfo.put("startRow", 1);
		searchInfo.put("endRow", 4);
		
		Posting[] postingList = postingService.getPostingList(searchInfo);
		/*Posting[] postingList = new Posting[2];
		
		PostingContent testPostingContent1 = new PostingContent("blog_table_208", 1, "This is test PostingContent written by admin.\n", "@content1.jpg",);
		PostingContent testPostingContent2 = new PostingContent(null, "@content1.mp4");
		
		Posting testPosting1 = new Posting(1, "Test posting. Mixed type posting. It contains multiple image files.", "admin", testPostingContent1, PostingContent.MIXED_IMAGE_FILE_CONTENT,
				Posting.PUBLIC_ALLOW_BOTH_REPLY_AND_REBLOG, "#test#testing#admin", Posting.NORMAL_TYPE_POSTING, Posting.ON_UPDATE_AND_DELETE_CASCADE);
		Posting testPosting2 = new Posting(2, "Test posting. Single type posting. It contains a video file.", "admin", testPostingContent2, PostingContent.SINGLE_VIDEO_FILE_CONTENT,
				Posting.PUBLIC_ALLOW_BOTH_REPLY_AND_REBLOG, "#test#testing#admin", Posting.NORMAL_TYPE_POSTING, Posting.ON_UPDATE_AND_DELETE_CASCADE);*/
		
		
		request.setAttribute("postingList", postingList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/blogMain.jsp");
		dispatcher.forward(request, response);
	}

	private void goHomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DataNotFoundException {
		HttpSession session = request.getSession(false); 
		Member member = (Member) session.getAttribute("loginMember");
		BlogService blogService = new BlogServiceImpl();
		Blog[] blogs = blogService.getMyBlogs(member);
		
		if (member != null) {
			if (blogs.length == 0) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("blogMain.jsp");
				dispatcher.forward(request, response);
				return;
			} else if (blogs.length > 0) {
				request.setAttribute("blogs", blogs);
				RequestDispatcher dispatcher = request.getRequestDispatcher("blog.jsp");
				dispatcher.forward(request, response);
				return;
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void blogListUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DataNotFoundException {
		String blogName = request.getParameter("blogName");
		String startRow = request.getParameter("startRow");
		String endRow = request.getParameter("endRow");
		
		PostingService postingService = new PostingServiceImpl();
		Map<String, Object> searchInfo = new HashMap<String, Object>();
		searchInfo.put("target", "posting");
		searchInfo.put("blogName", blogName);
		if (startRow != null) {
			searchInfo.put("startRow", Integer.parseInt(startRow));
		}
		if (endRow != null) {
			searchInfo.put("endRow", Integer.parseInt(endRow));
		}
		Posting[] postingList = postingService.getPostingList(searchInfo);
		request.setAttribute("postingList", postingList);
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
