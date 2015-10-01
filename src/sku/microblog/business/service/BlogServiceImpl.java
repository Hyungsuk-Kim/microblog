package sku.microblog.business.service;

import java.util.Map;

import sku.microblog.business.domain.Blog;
import sku.microblog.business.domain.Member;
import sku.microblog.util.DataDuplicatedException;
import sku.microblog.util.DataNotFoundException;

public class BlogServiceImpl implements BlogService {

	@Override
	public void createBlog(Member member, String blogName)
			throws DataDuplicatedException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBlog(Member member, Blog blog)
			throws DataNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeBlog(Member member, Blog blog)
			throws DataNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public Blog findBlog(String blogName) throws DataNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void following(Member member, String blogName)
			throws DataNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void unfollow(Member member, String blogName)
			throws DataNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public Blog[] getFollowingList(Member member) throws DataNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blog[] getBlogList(Map<String, Object> searchInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getBlogCount(Map<String, Object> searchInfo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
