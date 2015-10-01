package sku.microblog.business.service;

import java.util.List;
import java.util.Map;

import sku.microblog.business.domain.Blog;
import sku.microblog.business.domain.Member;
import sku.microblog.dataaccess.BlogDaoImpl;
import sku.microblog.dataaccess.MemberDaoImpl;
import sku.microblog.util.DataDuplicatedException;
import sku.microblog.util.DataNotFoundException;

public class BlogServiceImpl implements BlogService {

	@Override
	public void createBlog(Member member, String blogName) throws DataDuplicatedException, DataNotFoundException {
		MemberDao memberDao = new MemberDaoImpl();
		BlogDao blogDao = null;
		Blog blog = null;
		
		if (memberDao.memberNameExists(member.getName())) {
			blogDao = new BlogDaoImpl();
			if (blogDao.blogExists(blogName)) {
				blog = new Blog(blogName, member.getName());
				blogDao.insertBlog(blog);
			} else {
				throw new DataDuplicatedException("이미 존재하는 블로그 이름입니다. [" + blogName + "]");
			}
		} else {
			throw new DataNotFoundException("존재하지 않는 회원정보입니다. [" + member.getName() + "]");
		}
	}

	@Override
	public void updateBlog(Member member, Blog blog) throws DataNotFoundException {
		MemberDao memberDao = new MemberDaoImpl();
		BlogDao blogDao = null;
		
		if (memberDao.memberNameExists(member.getName())) {
			blogDao = new BlogDaoImpl();
			if (blogDao.blogExists(blog.getBlogName())) {
				throw new DataNotFoundException("존재하지 않는 블로그 입니다. [" + blog.getBlogName() + "]");
			}
			blogDao.updateBlog(blog);
		} else {
			throw new DataNotFoundException("존재하지 않는 회원정보입니다. [" + member.getName() + "]");
		}
	}

	@Override
	public void removeBlog(Member member, Blog blog) throws DataNotFoundException {
		MemberDao memberDao = new MemberDaoImpl();
		BlogDao blogDao = null;
		
		if (memberDao.memberNameExists(member.getName())) {
			blogDao = new BlogDaoImpl();
			if (blogDao.blogExists(blog.getBlogName())) {
				throw new DataNotFoundException("존재하지 않는 블로그 입니다. [" + blog.getBlogName() + "]");
			}
			blogDao.removeBlog(blog);
		} else {
			throw new DataNotFoundException("존재하지 않는 회원정보입니다. [" + member.getName() + "]");
		}
	}

	@Override
	public Blog findBlog(String blogName) throws DataNotFoundException {
		BlogDao blogDao = new BlogDaoImpl();
		if (blogDao.blogExists(blogName)) {
			return blogDao.selectBlog(blogName);
		} else {
			throw new DataNotFoundException("존재하지 않는 블로그 입니다. [" + blogName + "]");
		}
	}

	@Override
	public void following(Member member, String blogName) throws DataNotFoundException {
		MemberDao memberDao = new MemberDaoImpl();
		BlogDao blogDao = null;
		if (memberDao.memberNameExists(member.getName())) {
			blogDao = new BlogDaoImpl();
			if (blogDao.blogExists(blogName)) {
				blogDao.addFollowing(member, blogName);
			} else {
				throw new DataNotFoundException("존재하지 않는 블로그 입니다. [" + blogName + "]");
			}
		} else {
			throw new DataNotFoundException("존재하지 않는 회원정보입니다. [" + member.getName() + "]");
		}
	}

	@Override
	public void unfollow(Member member, String blogName) throws DataNotFoundException {
		MemberDao memberDao = new MemberDaoImpl();
		BlogDao blogDao = null;
		if (memberDao.memberNameExists(member.getName())) {
			blogDao = new BlogDaoImpl();
			if (blogDao.blogExists(blogName)) {
				blogDao.cancelFollowing(member, blogName);
			} else {
				throw new DataNotFoundException("존재하지 않는 블로그 입니다. [" + blogName + "]");
			}
		} else {
			throw new DataNotFoundException("존재하지 않는 회원정보입니다. [" + member.getName() + "]");
		}
	}

	@Override
	public Blog[] getFollowingList(Member member) throws DataNotFoundException {
		MemberDao memberDao = new MemberDaoImpl();
		if (memberDao.memberNameExists(member.getName())) {
			return new BlogDaoImpl().selectFollowedBlogs(member);
		} else {
			throw new DataNotFoundException("존재하지 않는 회원정보입니다. [" + member.getName() + "]");
		}
	}

	@Override
	public Blog[] getBlogList(Map<String, Object> searchInfo) {
		List<Blog> bList = new BlogDaoImpl().selectBlogList(searchInfo);
		return bList.toArray(new Blog[0]);
	}

	@Override
	public int getBlogCount(Map<String, Object> searchInfo) {
		return new BlogDaoImpl().selectBlogCount(searchInfo);
	}

}
