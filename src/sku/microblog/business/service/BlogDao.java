package sku.microblog.business.service;

import java.util.List;
import java.util.Map;

import sku.microblog.business.domain.Blog;
import sku.microblog.business.domain.Member;

public interface BlogDao {
	public abstract void insertBlog(Blog blog);
	public abstract void updateBlog(Blog blog);
	public abstract void removeBlog(Blog blog);
	public abstract Blog selectBlog(String blogName);
	public abstract Blog[] selectFollowedBlogs(Member member);
	public abstract void addFollowing(Member member, String blogName);
	public abstract void cancelFollowing(Member member, String blogName);
	public abstract List<Blog> selectBlogList(Map<String, Object> searchInfo);
	public abstract int selectBlogCount(Map<String, Object> searchInfo);
}
