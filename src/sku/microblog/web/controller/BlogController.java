package sku.microblog.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sku.microblog.business.domain.Blog;
import sku.microblog.business.domain.Member;
import sku.microblog.business.service.BlogService;
import sku.microblog.business.service.BlogServiceImpl;
import sku.microblog.util.DataDuplicatedException;
import sku.microblog.util.DataNotFoundException;
import sku.microblog.util.IllegalDataException;

/**
 * Servlet implementation class BlogController
 */
public class BlogController extends HttpServlet {
    private static final long serialVersionUID = 5549809211454526999L;

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException,
            IllegalDataException {
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
            } else if (action.equals("following")) {
                this.following(request, response);
            } else if (action.equals("unfollow")) {
                this.unfollow(request, response);
            } else if (action.equals("theme")) {
                this.modifyTheme(request, response);
            } else if (action.equals("getFollowingList")) {
                this.getFollowingList(request, response);
            } else if (action.equals("getBlogList")) {
                this.getBlogList(request, response);
            } else if (action.equals("getBlogCount")) {
                this.getBlogCount(request, response);
            } else if (action.equals("changeBlogName")) {
                this.changeBlogName(request, response);
            } else if (action.equals("visitBlog")) {
                this.visitBlog(request, response);
            }

        } catch (DataNotFoundException dne) {
            throw new ServletException(dne);
        } catch (DataDuplicatedException dde) {
            throw new ServletException(dde);
        }
    }

    private void visitBlog(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException,
            DataNotFoundException {

        String email = request.getParameter("memberName");
        String password = request.getParameter("password");

        Member member = new Member(email, password);

        String blogName = request.getParameter("blogName");

        BlogService blogService = new BlogServiceImpl();
        blogService.visitBlog(member, blogName);

        RequestDispatcher dispatcher = request.getRequestDispatcher("");
        dispatcher.forward(request, response);

    }

    private void changeBlogName(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException,
            DataNotFoundException, DataDuplicatedException,
            IllegalDataException {

        String email = request.getParameter("memberName");
        String password = request.getParameter("password");

        Member member = new Member(email, password);

        String originBlogName = request.getParameter("originBlogName");
        String newBlogName = request.getParameter("newBlogName");

        BlogService blogService = new BlogServiceImpl();
        blogService.changeBlogName(member, originBlogName, newBlogName);

        RequestDispatcher dispatcher = request.getRequestDispatcher("");
        dispatcher.forward(request, response);

    }

    private void getBlogCount(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        
        String searchType = request.getParameter("searchType");
        
               
         Map<String,Object> searchInfo = new HashMap<String,Object>();
         searchInfo.put("searchType",searchType);
         
        
         BlogService blogService = new BlogServiceImpl();
         blogService.getBlogCount(searchInfo);
        
         RequestDispatcher dispatcher = request.getRequestDispatcher("");
         dispatcher.forward(request, response);

    }

    private void getBlogList(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    
        String searchType = request.getParameter("searchType");
        
        
        Map<String,Object> searchInfo = new HashMap<String,Object>();
        searchInfo.put("searchType",searchType);
        
       
        BlogService blogService = new BlogServiceImpl();
        blogService.getBlogList(searchInfo);
       
        RequestDispatcher dispatcher = request.getRequestDispatcher("");
        dispatcher.forward(request, response);

    }

    private void getFollowingList(HttpServletRequest request,
            HttpServletResponse response) throws DataNotFoundException,
            ServletException, IOException {

        String email = request.getParameter("memberName");
        String password = request.getParameter("password");

        Member member = new Member(email, password);

        BlogService blogService = new BlogServiceImpl();
        blogService.getFollowingList(member);

        RequestDispatcher dispatcher = request.getRequestDispatcher("");
        dispatcher.forward(request, response);

    }

    private void createBlog(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException,
            DataDuplicatedException, DataNotFoundException {

        String email = request.getParameter("memberName");
        String password = request.getParameter("password");

        Member member = new Member(email, password);

        // String memberName = request.getParameter("memberName");
        String blogName = request.getParameter("blogName");
        // String backgroundColor = request.getParameter("backgroundColor");
        // String headerImage = request.getParameter("headerImage");
        // String profileImage = request.getParameter("profileImage");
        // String blogLayout = request.getParameter("blogLayout");

        // Blog blog = new Blog(memberName, blogName,
        // Integer.parseInt(backgroundColor), headerImage, profileImage,
        // Integer.parseInt(blogLayout));

        BlogService blogService = new BlogServiceImpl();
        blogService.createBlog(member, blogName);

        RequestDispatcher dispatcher = request.getRequestDispatcher("");
        dispatcher.forward(request, response);

    }

    private void selectBlog(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException,
            DataNotFoundException {

        String blogName = request.getParameter("blogName");

        BlogService blogService = new BlogServiceImpl();
        blogService.findBlog(blogName);

        RequestDispatcher dispatcher = request.getRequestDispatcher("");
        dispatcher.forward(request, response);

    }

    private void updateBlog(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException,
            DataNotFoundException {

        String email = request.getParameter("memberName");
        String password = request.getParameter("password");

        Member member = new Member(email, password);

        String memberName = request.getParameter("memberName");
        String blogName = request.getParameter("blogName");
        String backgroundColor = request.getParameter("backgroundColor");
        String headerImage = request.getParameter("headerImage");
        String profileImage = request.getParameter("profileImage");
        String blogLayout = request.getParameter("blogLayout");

        Blog blog = new Blog(memberName, blogName,
                Integer.parseInt(backgroundColor), headerImage, profileImage,
                Integer.parseInt(blogLayout));

        BlogService blogService = new BlogServiceImpl();
        blogService.updateBlog(member, blog);

        RequestDispatcher dispatcher = request.getRequestDispatcher("");
        dispatcher.forward(request, response);

    }

    private void removeBlog(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException,
            DataNotFoundException {

        String email = request.getParameter("memberName");
        String password = request.getParameter("password");

        Member member = new Member(email, password);

        String memberName = request.getParameter("memberName");
        String blogName = request.getParameter("blogName");
        String backgroundColor = request.getParameter("backgroundColor");
        String headerImage = request.getParameter("headerImage");
        String profileImage = request.getParameter("profileImage");
        String blogLayout = request.getParameter("blogLayout");

        Blog blog = new Blog(memberName, blogName,
                Integer.parseInt(backgroundColor), headerImage, profileImage,
                Integer.parseInt(blogLayout));

        BlogService blogService = new BlogServiceImpl();
        blogService.removeBlog(member, blog);

        RequestDispatcher dispatcher = request.getRequestDispatcher("");
        dispatcher.forward(request, response);

    }

    private void following(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException,
            DataNotFoundException {

        String email = request.getParameter("memberName");
        String password = request.getParameter("password");

        Member member = new Member(email, password);

        String blogName = request.getParameter("blogName");

        BlogService blogService = new BlogServiceImpl();
        blogService.following(member, blogName);

        RequestDispatcher dispatcher = request.getRequestDispatcher("");
        dispatcher.forward(request, response);

    }

    private void unfollow(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException,
            DataNotFoundException {
     String email = request.getParameter("memberName");
        String password = request.getParameter("password");

        Member member = new Member(email, password);

        String blogName = request.getParameter("blogName");

        BlogService blogService = new BlogServiceImpl();
        blogService.unfollow(member, blogName);

        RequestDispatcher dispatcher = request.getRequestDispatcher("");
        dispatcher.forward(request, response);

    }

    private void modifyTheme(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ")
                .append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}