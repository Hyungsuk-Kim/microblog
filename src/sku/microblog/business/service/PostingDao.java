package sku.microblog.business.service;

import java.util.List;
import java.util.Map;

import sku.microblog.business.domain.Member;
import sku.microblog.business.domain.Posting;
import sku.microblog.business.domain.PostingContent;

public interface PostingDao {
	public abstract int insertPosting(String blogName, Posting posting);
	public abstract void updatePosting(String blogName, Posting posting);
	public abstract void deletePosting(String blogName, Posting posting);
	public abstract Posting selectPosting(String blogName, int postingNum);
	public abstract List<Posting> selectAllPostings(String blogName);
	public abstract List<Posting> selectAllPostings();
	public abstract List<Posting> getPostingList(Map<String, Object> searchInfo);
	public abstract int getPostingCount(Map<String, Object> searchInfo);
	public abstract boolean postingExists(String blogName, int postingNum);
	public abstract void addReadCount(String blogName, int postingNum);
	public abstract void addLikes(Member member, String blogName, int postingNum);
	public abstract void cancelLikes(Member member, String blogName, int postingNum);
	public abstract PostingContent getContents(String blogName, int postingNum);
	public abstract void setContents(String blogName, int postingNum, PostingContent pContent);
	public abstract void reblog(Member member, String originBlogName, int postingNum, String targetBlogName);
}