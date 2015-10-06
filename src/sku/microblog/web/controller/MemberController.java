package sku.microblog.web.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sku.microblog.business.domain.Member;
import sku.microblog.business.service.MemberService;
import sku.microblog.business.service.MemberServiceImpl;
import sku.microblog.business.service.PostingService;
import sku.microblog.business.service.PostingServiceImpl;
import sku.microblog.util.DataDuplicatedException;
import sku.microblog.util.DataNotFoundException;

/**
 * Servlet implementation class MemberController
 */
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 4412561148870850058L;

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		try {
			if (action.equals("register")) {
				this.registerMember(request, response);
			} else if (action.equals("deregister")) {
				this.deregisterMember(request, response);
			} else if (action.equals("select")) {
				this.selectMember(request, response);
			} else if (action.equals("login")) {
				this.login(request, response);
			} else if (action.equals("logout")) {
				this.logout(request, response);
			} else if (action.equals("update")) {
				this.updateMember(request, response);
			} else if (action.equals("likeList")) {
				this.likeList(request, response);
			} else if (action.equals("cancelLike")) {
				this.cancelLike(request, response);
			} else if (action.equals("removeLike")) {
				this.removeLike(request, response);
			} else if (action.equals("checkName")){
				this.availableName(request, response);
			}
		} catch (DataNotFoundException dne) {
			throw new ServletException(dne);
		} catch (DataDuplicatedException dde) {
			throw new ServletException(dde);
		}
	}


	/**
	 * 입력된 회원정보로 회원을 등록시키는 메서드
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws DataDuplicatedException
	 */
	private void registerMember(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			DataDuplicatedException {
		// 요청 파라미터를 통해 HTML 등 데이터를 얻어낸다.
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String password = request.getParameter("password");

		// 폼 데이터가 유효한 지 검증한다.
		List<String> errorMsgs = new ArrayList<String>();

		if (email == null || email.length() == 0) {
			errorMsgs.add("이메일을 입력해주세요.");
		}

		if (name == null || name.length() == 0) {
			errorMsgs.add("이름을 입력해주세요.");
		}

		if (password == null || password.length() == 0) {
			errorMsgs.add("비밀번호를 입력해주세요.");
		}

		// 유효하지 않은 데이터가 있으면 에러 페이지를 출력한다.
		if (!errorMsgs.isEmpty()) {
			request.setAttribute("errorMsgs", errorMsgs);

			RequestDispatcher dispatcher = request
					.getRequestDispatcher("userError.jsp");
			dispatcher.forward(request, response);

			return;
		}

		// 데이터가 유효하면 새로운 회원을 등록하는 처리를 한다. 적절한 데이터를 가진 member 객체를 생성한다.
		Member member = new Member(email, name, password);

		// memberservice 객체에 위임하여 회원을 등록한다.
		MemberService memberService = new MemberServiceImpl();
		memberService.registerMember(member);

		request.setAttribute("member", member);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("success.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * 현재 로그인 되어있는 회원을 회원 탈퇴시키는 메서드
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws DataNotFoundException
	 */
	private void deregisterMember(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			DataNotFoundException {
	    HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "로그인이 필요합니다.");
            return;
        }

        Member member = (Member) session.getAttribute("loginMember");
        if (member == null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "로그인이 필요합니다.");
            return;
        }

		MemberService memberService = new MemberServiceImpl();
		memberService.removeMember(member);

		session.removeAttribute("loginMember");
		session.invalidate();

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * 한 명의 회원 상세 정보를 조회하는 요청을 처리하는 메서드
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws DataNotFoundException
	 */
	private void selectMember(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			DataNotFoundException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);

		if (session == null) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
					"로그인이 필요합니다.");
			return;
		}

		Member member = (Member) session.getAttribute("loginMember");

		if (member == null) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
					"로그인이 필요합니다.");
			return;
		}

		String name = member.getName();

		MemberService memberService = new MemberServiceImpl();

		Member selectedMember = memberService.findMember(name);

		request.setAttribute("updateMember", selectedMember);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("updateMember.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * form data로 받은 회원 정보로 로그인 요청을 처리하는 메서드
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */

	private void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		MemberService memberService = new MemberServiceImpl();
		Member member = memberService.loginCheck(email, password);

		if (member.getCheck() == Member.VALID_MEMBER) {
			HttpSession session = request.getSession(true);
			session.setAttribute("loginMember", member);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
			return;
		} else {
			String loginErrorMsg = null;

			if (member.getCheck() == Member.INVALID_EMAIL) {
				loginErrorMsg = "이메일이 존재하지 않습니다.";
			} else if (member.getCheck() == Member.INVALID_PASSWORD) {
				loginErrorMsg = "비밀번호가 일치하지 않습니다.";
			}

			request.setAttribute("loginErrorMsg", loginErrorMsg);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}

	}

	/**
	 * 현재 로그인 되어있는 회원을 로그아웃 시키는 메서드
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if (session != null) {
			session.removeAttribute("loginMember");
			session.invalidate();
		}

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * form data로 받은 수정된 회원 정보로 회원을 갱신시키는 메서드
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws DataNotFoundException
	 */
	private void updateMember(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			DataNotFoundException {
		HttpSession session = request.getSession(false);

		if (session == null) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
					"로그인이 필요합니다.");
			return;
		}

		Member member = (Member) session.getAttribute("loginMember");

		if (member == null) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
					"로그인이 필요합니다.");
			return;
		}

		String email = member.getEmail();
		String name = member.getName();
		String password = member.getPassword();

		member = new Member(email, name, password);
		MemberService memberService = new MemberServiceImpl();
		memberService.updateMember(member);

		session.setAttribute("loginMember", member);

		request.setAttribute("member", member);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("success.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * 현재 로그인 되어있는 회원의 좋아요 목록을 조회하는 요청을 처리하는 메서드
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void likeList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
					"로그인이 필요합니다.");
			return;
		}

		Member member = (Member) session.getAttribute("loginMember");

		if (member == null) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
					"로그인이 필요합니다.");
			return;
		}
		
		//종아요 리스트 부분 구현.

	}

	/**
	 * 현재 로그인 되어있는 회원이 추가했던 좋아요를 취소하는 메서드
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws DataNotFoundException
	 */
	private void cancelLike(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			DataNotFoundException {
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
					"로그인이 필요합니다.");
			return;
		}

		Member member = (Member) session.getAttribute("loginMember");

		if (member == null) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
					"로그인이 필요합니다.");
			return;
		}

		String blogName = request.getParameter("blogName");
		int postingNum = Integer.parseInt(request.getParameter("postingNum"));

		PostingService postingService = new PostingServiceImpl();
		postingService.cancelLikes(member, blogName, postingNum);

		// like.jsp는 멤버 정보의 좋아요 목록에서 좋아요를 취소하는 부분.
		/*
		 * RequestDispatcher dispatcher =
		 * request.getRequestDispatcher(".likeList");
		 * dispatcher.forward(request, response);
		 */

	}

	private void removeLike(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(".cancelLike");
		dispatcher.include(request, response);
		
		dispatcher = request.getRequestDispatcher(".likeList");
		dispatcher.forward(request, response);
	}
	
	private boolean availableName(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		return false;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.processRequest(request, response);
	}

}
