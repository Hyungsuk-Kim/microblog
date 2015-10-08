package sku.microblog.test;

import org.junit.Before;

import sku.microblog.business.domain.*;
import sku.microblog.business.service.PostingDao;
import sku.microblog.dataaccess.PostingDaoImpl;

public class TestPostingDaoImpl {
	private PostingDao postingDao = null;
	private Posting testPosting = null;
	private PostingContent testPostingContent = null;
	
	@Before
	public void init() {
		postingDao = new PostingDaoImpl();
		testPostingContent = new PostingContent("test", "This is test PostingContent written by admin", "");
	}
}
