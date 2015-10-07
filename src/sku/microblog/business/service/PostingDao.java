package sku.microblog.business.service;

import java.util.List;
import java.util.Map;

import sku.microblog.business.domain.Posting;
import sku.microblog.business.domain.PostingContent;

public interface PostingDao {
	public abstract void insertPosting(String blogName, Posting posting);
	public abstract void updatePosting(String blogName, Posting posting);
	public abstract void deletePosting(String blogName, int postingNum);
	public abstract Posting selectPosting(String blogName, int postingNum);
	public abstract Posting[] selectAllPostings(String blogName);
	public abstract Posting[] selectAllPostings();
	public abstract List<Posting> getPostingList(Map<String, Object> searchInfo);
	public abstract int getPostingCount(Map<String, Object> searchInfo);
	public abstract boolean PostingExists(String blogName, Posting posting);
	public abstract void addReadCount(String blogName, int postingNum);
	public abstract void addLikes(String blogName, int postingNum);
	public abstract void cancelLikes(String blogName, int postingNum);
	public abstract PostingContent getContents(String blogName, int postingNum);
	public abstract void setContents(String blogName, int postingNum);
	public abstract void reblog(String originBlogName, int postingNum, String targetBlogName);
}