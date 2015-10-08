package sku.microblog.test;

import org.junit.Before;

import sku.microblog.business.domain.*;
import sku.microblog.business.service.PostingDao;
import sku.microblog.dataaccess.PostingDaoImpl;

public class TestPostingDaoImpl {
	private PostingDao postingDao = null;
	private Posting testPosting1 = null;
	private Posting testPosting2 = null;
	private PostingContent testPostingContent1 = null;
	private PostingContent testPostingContent2 = null;
	
	@Before
	public void init() {
		postingDao = new PostingDaoImpl();
		testPostingContent1 = new PostingContent("test", "This is test PostingContent written by admin.\n", "content1.jpg", "content2,jpg", "content3.png");
		testPostingContent2 = new PostingContent("test", null, "content1.mp4");
		testPosting1 = new Posting("Test posting. Mixed type posting. It contains multiple image files.", "admin", testPostingContent1, PostingContent.MIXED_IMAGE_FILE_CONTENT, new java.util.Date(),
				Posting.PUBLIC_ALLOW_BOTH_REPLY_AND_REBLOG, "#test#testing#admin", Posting.NORMAL_TYPE_POSTING, Posting.ON_UPDATE_AND_DELETE_CASCADE);
		testPosting2 = new Posting("Test posting. Single type posting. It contains a video file.", "admin", testPostingContent2, PostingContent.MIXED_VIDEO_FILE_CONTENT, new java.util.Date(),
				Posting.PUBLIC_ALLOW_BOTH_REPLY_AND_REBLOG, "#test#testing#admin", Posting.NORMAL_TYPE_POSTING, Posting.ON_UPDATE_AND_DELETE_CASCADE);
	}
}
