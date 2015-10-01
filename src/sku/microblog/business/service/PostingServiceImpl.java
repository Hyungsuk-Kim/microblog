package sku.microblog.business.service;

import java.util.Map;

import sku.microblog.business.domain.Member;
import sku.microblog.business.domain.Posting;
import sku.microblog.util.DataNotFoundException;

public class PostingServiceImpl implements PostingService {

	@Override
	public Posting readPosting(String blogName, int postingNum)
			throws DataNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Posting findPosting(String blogName, int postingNum)
			throws DataNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removePosting(String blogName, int postingNum)
			throws DataNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void writePosting(String blogName, Posting posting) {
		// TODO Auto-generated method stub

	}

	@Override
	public void writePosting(Map<String, Object> contentsInfo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updatePosting(String blogName, Posting posting)
			throws DataNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void replyPosting(String blogName, Posting posting)
			throws DataNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getPostingCount(Map<String, Object> searchInfo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Posting[] getPostingList(Map<String, Object> searchInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addLikes(Member member, String blogName, int postingNum)
			throws DataNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancelLikes(Member member, String blogName, int postingNum)
			throws DataNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void reblog(String fromBlogName, int postingNum, String toBlogName)
			throws DataNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getContentType(String blogName, int postingNum)
			throws DataNotFoundException {
		// TODO Auto-generated method stub
		return 0;
	}

}
