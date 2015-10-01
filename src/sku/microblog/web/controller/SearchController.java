package sku.microblog.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sku.microblog.business.service.*;

/**
 * Servlet implementation class SearchController
 */
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = -5410439819005540004L;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchType = request.getParameter("searchType"); // 검색할 대상의 타입 (None(=default) - all, 나머지는 아래 참조)
		String searchText = request.getParameter("searchText"); // 검색 키워드(검색어)
		String searchOption = request.getParameter("searchOption"); // 좋아요, 최신 등록일, 조회수, 정확도 등의 순서로 검색하기 위한 옵션
		String blogName = (String) request.getAttribute("blogName"); // 블로그 내 검색 기능을 위해 검색 대상이 될 블로그 명을 속성으로 받음
		
		Map<String, Object> searchInfo = new HashMap<String, Object>();
		
		// 키워드 결정
		if (searchText != null && searchText.trim().length() != 0) {
			searchInfo.put("searchText", searchText);
		}
		
		// 검색 옵션 결정
		if (searchOption != null && searchOption.trim().length() != 0) {
			if (searchOption.equals("likes")) {
				if (searchType.equals("postingTitle") || searchType.equals("postingWriter") || searchType.equals("postingContents") || searchType.equals("tags")){
					// 포스팅 관련 타입일 때만 property로 추가함
					searchInfo.put("searchOption", searchOption);
				}
			} else if (searchOption.equals("readCount")) {
				if (searchType.equals("postingTitle") || searchType.equals("postingWriter") || searchType.equals("postingContents") || searchType.equals("tags")){
					// 포스팅 관련 타입일 때만 property로 추가함
					searchInfo.put("searchOption", searchOption);
				}
			} else if (searchOption.equals("followers")) {
				if (searchType.equals("blog")) {
					// 블로그 타입일 때만 property로 추가함
					searchInfo.put("searchOption", searchOption);
				}
			}
		}
		
		// 블로그 내 검색여부 및 검색할 블로그 결정
		if (blogName != null && blogName.trim().length() != 0) {
			searchInfo.put("blogName", blogName);
		}
		
		// 검색 대상 결정
		if (searchType == null || searchType.trim().length() == 0) {
			// 모든 타입에 대한 검색
			searchInfo.put("searchType", "all");
			
			// 해당하는(member, posting, blog) 서비스 객체에게 Map객체(searchInfo)를 매개변수로 넘겨주며 getXXXList 메서드 호출
			MemberService memberService = new MemberServiceImpl();
			memberService.getMemberList(searchInfo);
			
			PostingService postingService = new PostingServiceImpl();
			postingService.getPostingList(searchInfo);
			
			BlogService blogService = new BlogServiceImpl();
			blogService.getBlogList(searchInfo);
			
			return;
		} else if (searchType != null && searchType.trim().length() != 0) {
			// 결과 내 검색 기능(해당 타입에 대하여 재검색)
			if (searchType.equals("member")) { 
				// 입력된 키워드를 포함한 회원을 검색
				searchInfo.put("searchType", "member");
				
				// member 관련 서비스 객체에게 Map객체(searchInfo)를 매개변수로 넘겨주며 getXXXList 메서드 호출
				MemberService memberService = new MemberServiceImpl();
				memberService.getMemberList(searchInfo);
				return;
			} else if (searchType.equals("blog")) { 
				// 입력된 키워드를 포함한 블로그를 검색
				searchInfo.put("searchType", "blog");
				
				// blog 관련 서비스 객체에게 Map객체(searchInfo)를 매개변수로 넘겨주며 getXXXList 메서드 호출
				BlogService blogService = new BlogServiceImpl();
				blogService.getBlogList(searchInfo);
				return;
			} else if (searchType.equals("postingTitle")) {
				// 입력된 키워드를 포함한 포스팅을 포스팅 제목으로 검색
				searchInfo.put("searchType", "postingTitle");
			} else if (searchType.equals("postingWriter")) {
				// 입력된 키워드를 포함한 포스팅을 포스팅 작성자로 검색
				searchInfo.put("searchType", "postingWriter");
			} else if (searchType.equals("postingContents")) {
				// 입력된 키워드를 포함한 포스팅을 포스팅 내용으로 검색
				searchInfo.put("searchType", "postingContents");
			} else if (searchType.equals("tags")) {
				// 입력된 키워드를 포함한 포스팅을 태그로 검색
				searchInfo.put("searchType", "tags");
			}
			// posting 관련 서비스 객체에게 Map객체(searchInfo)를 매개변수로 넘겨주며 getXXXList 메서드 호출
			PostingService postingService = new PostingServiceImpl();
			postingService.getPostingList(searchInfo);
			return;
		}
		
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
