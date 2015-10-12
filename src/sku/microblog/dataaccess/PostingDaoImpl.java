package sku.microblog.dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import sku.microblog.business.domain.Member;
import sku.microblog.business.domain.Posting;
import sku.microblog.business.domain.PostingContent;
import sku.microblog.business.service.PostingDao;
import sku.microblog.util.ConvertDateType;

public class PostingDaoImpl implements PostingDao {
	
	private Connection obtainConnection() throws SQLException {
    	return DatabaseUtil_old.getConnection();
    	//return DatabaseUtil.getConnection();
    }
	
	private void closeResources(Connection connection, Statement stmt, ResultSet rs){
		DatabaseUtil_old.close(connection, stmt, rs);
		//DatabaseUtil.close(connection, stmt, rs);
	}
	
	private void closeResources(Connection connection, Statement stmt){
		this.closeResources(connection, stmt, null);
	}
	
	private String getContentTable(Posting posting){
		String contentTable = null;
		
		int contentType = posting.getContentType();
		if (contentType / 100 == PostingContent.MIXED_TYPE_CONTENT) {
			contentTable = "mixed_contents";
		} else if (contentType / 100 == PostingContent.SINGLE_TYPE_CONTENT) {
			contentTable = "single_type_contents";
		} else if (contentType == PostingContent.TEXT_CONTENT) {
			contentTable = "text_contents";
		}
		return contentTable;
	}
	
	private String getContentTable(int contentType){
		String contentTable = null;
		if (contentType / 100 == PostingContent.MIXED_TYPE_CONTENT) {
			contentTable = "mixed_contents";
		} else if (contentType / 100 == PostingContent.SINGLE_TYPE_CONTENT) {
			contentTable = "single_type_contents";
		} else if (contentType == PostingContent.TEXT_CONTENT) {
			contentTable = "text_contents";
		}
		return contentTable;
	}
	
	private String getFilePaths(PostingContent pContent) {
		String filePaths = "";
		String[] files = pContent.getFilePaths();
		for (String file : files) {
			filePaths += "@" + file;
		}
		return filePaths;
	}
	
	private boolean checkValidContentType(int typeOfContent) {
		boolean result = false;
		
		switch (typeOfContent) {
		case PostingContent.MIXED_AUDIO_FILE_CONTENT:
		case PostingContent.MIXED_AUDIO_LINK_CONTENT:
		case PostingContent.MIXED_IMAGE_FILE_CONTENT:
		case PostingContent.MIXED_IMAGE_LINK_CONTENT:
		case PostingContent.MIXED_VIDEO_FILE_CONTENT:
		case PostingContent.MIXED_VIDEO_LINK_CONTENT:
		case PostingContent.SINGLE_AUDIO_FILE_CONTENT:
		case PostingContent.SINGLE_AUDIO_LINK_CONTENT:
		case PostingContent.SINGLE_IMAGE_FILE_CONTENT:
		case PostingContent.SINGLE_IMAGE_LINK_CONTENT:
		case PostingContent.SINGLE_VIDEO_FILE_CONTENT:
		case PostingContent.SINGLE_VIDEO_LINK_CONTENT:
		case PostingContent.TEXT_CONTENT:
			result = true;
			break;

		default:
			break;
		}
		
		return result;
	}

	@Override
	public int insertPosting(String blogName, Posting posting) {
		int affectedRow = 0;
		String contentTable = this.getContentTable(posting);
		String contents = "";
		PostingContent pContent = posting.getContents();
		
		if (contentTable.equals("mixed_contents")) {
			contents = "?, ?";
		} else if (contentTable.equals("single_type_contents")) {
			contents = "?";
		} else if (contentTable.equals("text_contents")) {
			contents = "?";
		}
		int seqNum = 0;
		String query = "SELECT " + blogName + "_num_seq.NEXTVAL FROM dual";
		String sql = "INSERT INTO " + blogName + " (num, title, writer, content_type, reg_date, exposure, tags, ref, posting_type, reblog_option) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String sql2= "INSERT INTO " + contentTable + " VALUES (?, ?, " + contents + ")";
		System.out.println("PostingDaoImpl insertPosting() : " + sql);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = this.obtainConnection();
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				seqNum = rs.getInt(1);
			} else {
				rs.close();
				return affectedRow;
			}
			rs.close();
			pstmt.close();
			
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, seqNum);
			pstmt.setString(2, posting.getTitle());
			pstmt.setString(3, posting.getWriter());
			pstmt.setInt(4, posting.getContentType());
			pstmt.setDate(5, ConvertDateType.ConvertDateUtilToSql(new java.util.Date()));
			pstmt.setInt(6, posting.getExposure());
			pstmt.setString(7, posting.getTags());
			pstmt.setInt(8, seqNum);
			pstmt.setInt(9, posting.getPostingType());
			pstmt.setInt(10, posting.getReblogOption());
			pstmt.executeUpdate();
			pstmt.close();
			
			System.out.println("PostingDaoImpl insertPosting() : " + sql2);
			pstmt = connection.prepareStatement(sql2);
			pstmt.setString(1, blogName);
			pstmt.setInt(2, seqNum);
			if (contentTable.equals("mixed_contents")) {
				pstmt.setString(3, pContent.getTextContent());
				pstmt.setString(4, this.getFilePaths(pContent));
			} else if (contentTable.equals("single_type_contents")) {
				pstmt.setString(3, this.getFilePaths(pContent));
			} else if (contentTable.equals("text_contents")) {
				pstmt.setString(3, pContent.getTextContent());
			}
			affectedRow = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			this.closeResources(connection, pstmt);
		}
		
		return affectedRow;
	}

	@Override
	public void updatePosting(String blogName, Posting posting) {
		PostingContent pContent = posting.getContents();
		
		String contentTable = this.getContentTable(posting);
		String updateComponents = "";
		
		if (contentTable.equals("mixed_contents")) {
			updateComponents = "text_contents=?, file_path=?";
		} else if (contentTable.equals("single_type_contents")) {
			updateComponents = "file_path=?";
		} else if (contentTable.equals("text_contents")) {
			updateComponents = "text_contents=?";
		}
		
		String sql = "UPDATE " + blogName + " SET title=?, content_type=?, exposure=?, tags=? WHERE num=?";
		String sql2 = "UPDATE " + contentTable + " SET " + updateComponents + " WHERE blog_name=? AND posting_num=?";
		System.out.println("PostingDaoImpl updatePosting() : " + sql);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = this.obtainConnection();
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.setString(1, posting.getTitle());
			pstmt.setInt(2, posting.getContentType());
			pstmt.setInt(3, posting.getExposure());
			pstmt.setString(4, posting.getTags());
			pstmt.setInt(5, posting.getNum());
			pstmt.close();
			
			System.out.println("PostingDaoImpl updatePosting() : " + sql2);
			pstmt = connection.prepareStatement(sql2);
		
			if (contentTable.equals("mixed_contents")) {
				pstmt.setString(1, pContent.getTextContent());
				pstmt.setString(2, this.getFilePaths(pContent));
				pstmt.setString(3, blogName);
				pstmt.setInt(5, posting.getNum());
			} else if (contentTable.equals("single_type_contents")) {
				pstmt.setString(1, this.getFilePaths(pContent));
				pstmt.setString(2, blogName);
				pstmt.setInt(3, posting.getNum());
			} else if (contentTable.equals("text_contents")) {
				pstmt.setString(1, pContent.getTextContent());
				pstmt.setString(2, blogName);
				pstmt.setInt(3, posting.getNum());
			}
			pstmt.executeUpdate();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			this.closeResources(connection, pstmt);
		}
	}

	@Override
	public void deletePosting(String blogName, Posting posting) {
		String contentTable = this.getContentTable(posting);
		
		String sql = "DELETE FROM " + blogName + " WHERE num=?";
		String sql2 = "DELETE FROM " + contentTable + " WHERE blog_name=? AND posting_num=?";
		System.out.println("PostingDaoImpl deletePosting() : " + sql);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = this.obtainConnection();
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, posting.getNum());
			pstmt.executeUpdate();
			pstmt.close();
			
			pstmt = connection.prepareStatement(sql2);
			pstmt.setString(1, blogName);
			pstmt.setInt(2, posting.getContents().getPostingNum());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			this.closeResources(connection, pstmt);
		}
	}

	@Override
	public Posting selectPosting(String blogName, int postingNum) {
		Posting selectedPosting = null;
		PostingContent pContent = null;
		String contentTable = null;
		
		String sql = "SELECT * FROM " + blogName + " WHERE num=?";
		System.out.println("PostingDaoImpl selectPosting() : " + sql);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		
		try {
			connection = this.obtainConnection();
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, postingNum);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				int num = rs.getInt("num");
				String title = rs.getString("title");
				String writer = rs.getString("writer");
				int contentType = rs.getInt("content_type");
				int readCount = rs.getInt("read_count");
				java.util.Date regDate = ConvertDateType.ConvertDateSqlToUtil(rs.getDate("reg_date"));
				int likes = rs.getInt("likes");
				int exposure = rs.getInt("exposure");
				String tags = rs.getString("tags");
				int ref = rs.getInt("ref");
				int replyStep = rs.getInt("reply_step");
				int replyDepth = rs.getInt("reply_depth");
				int replyCount = rs.getInt("reply_count");
				int postingType = rs.getInt("posting_type");
				int reblogCount = rs.getInt("reblog_count");
				int reblogOption = rs.getInt("reblog_option");
				
				contentTable = this.getContentTable(contentType);
				String sql2 = "SELECT * FROM " + contentTable + " WHERE blog_name=? AND posting_num=?";
				System.out.println("PostingDaoImpl selectPosting() : " + sql2);
				
				pstmt2 = connection.prepareStatement(sql2);
				pstmt2.setString(1, blogName);
				pstmt2.setInt(2, num);
				rs2 = pstmt2.executeQuery();
				
				if (rs2.next()) {
					String bName = rs2.getString("blog_name");
					int pNum = rs2.getInt("posting_num");
					if (contentTable.equals("mixed_contents")) {
						String textContents = rs2.getString("text_contents");
						String filePaths = rs2.getString("file_path");
						
						StringTokenizer tokenizer = new StringTokenizer(filePaths, "@");
						List<String> paths = new ArrayList<String>();
						while (tokenizer.hasMoreTokens()) {
							paths.add("@" + tokenizer.nextToken());
						}
						pContent = new PostingContent(bName, pNum, textContents, paths.toArray(new String[0]));
						
					} else if (contentTable.equals("single_type_contents")) {
						String filePaths = rs2.getString("file_path");
						
						StringTokenizer tokenizer = new StringTokenizer(filePaths, "@");
						List<String> paths = new ArrayList<String>();
						while (tokenizer.hasMoreTokens()) {
							paths.add("@" + tokenizer.nextToken());
						}
						pContent = new PostingContent(bName, pNum, paths.toArray(new String[0]));
						
					} else if (contentTable.equals("text_contents")) {
						String textContent = rs2.getString("text_contents");
						
						pContent = new PostingContent(bName, pNum, textContent);
					}
					selectedPosting = new Posting(num, title, writer, pContent, contentType,
							readCount, regDate, likes, exposure, tags, ref, replyStep, 
							replyDepth, replyCount, postingType, reblogCount, reblogOption);
				}
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			this.closeResources(null, pstmt2, rs2);
			this.closeResources(connection, pstmt, rs);
		}
		
		return selectedPosting;
	}

	@Override
	public List<Posting> selectAllPostings(String blogName) {
		List<Posting> pList = new ArrayList<Posting>();
		Posting selectedPosting = null;
		PostingContent pContent = null;
		String contentTable = null;
		
		String sql = "SELECT * FROM " + blogName;
		System.out.println("PostingDaoImpl selectAllPostings() : " + sql);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		
		try {
			connection = this.obtainConnection();
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int num = rs.getInt("num");
				String title = rs.getString("title");
				String writer = rs.getString("writer");
				int contentType = rs.getInt("content_type");
				int readCount = rs.getInt("read_count");
				java.util.Date regDate = ConvertDateType.ConvertDateSqlToUtil(rs.getDate("reg_date"));
				int likes = rs.getInt("likes");
				int exposure = rs.getInt("exposure");
				String tags = rs.getString("tags");
				int ref = rs.getInt("ref");
				int replyStep = rs.getInt("reply_step");
				int replyDepth = rs.getInt("reply_depth");
				int replyCount = rs.getInt("reply_count");
				int postingType = rs.getInt("posting_type");
				int reblogCount = rs.getInt("reblog_count");
				int reblogOption = rs.getInt("reblog_option");
				
				contentTable = this.getContentTable(contentType);
				String sql2 = "SELECT * FROM " + contentTable + " WHERE blog_name=? AND posting_num=?";
				System.out.println("PostingDaoImpl selectAllPostings() : " + sql2);
				pstmt2 = connection.prepareStatement(sql2);
				pstmt2.setString(1, blogName);
				pstmt2.setInt(2, num);
				rs2 = pstmt2.executeQuery();
				
				while (rs2.next()) {
					String bName = rs2.getString("blog_name");
					int pNum = rs2.getInt("posting_num");
					if (contentTable.equals("mixed_contents")) {
						String textContents = rs2.getString("text_contents");
						String filePaths = rs2.getString("file_path");
						
						StringTokenizer tokenizer = new StringTokenizer(filePaths, "@");
						List<String> paths = new ArrayList<String>();
						while (tokenizer.hasMoreTokens()) {
							paths.add("@" + tokenizer.nextToken());
						}
						
						pContent = new PostingContent(bName, pNum, textContents, paths.toArray(new String[0]));
						
					} else if (contentTable.equals("single_type_contents")) {
						String filePaths = rs2.getString("file_path");
						
						StringTokenizer tokenizer = new StringTokenizer(filePaths, "@");
						List<String> paths = new ArrayList<String>();
						while (tokenizer.hasMoreTokens()) {
							paths.add("@" + tokenizer.nextToken());
						}
						
						pContent = new PostingContent(bName, pNum, paths.toArray(new String[0]));
						
					} else if (contentTable.equals("text_contents")) {
						String textContent = rs2.getString("text_contents");
						
						pContent = new PostingContent(bName, pNum, textContent);
					}
					selectedPosting = new Posting(num, title, writer, pContent, contentType,
							readCount, regDate, likes, exposure, tags, ref, replyStep, 
							replyDepth, replyCount, postingType, reblogCount, reblogOption);
					
					pList.add(selectedPosting);
				}
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			this.closeResources(null, pstmt2, rs2);
			this.closeResources(connection, pstmt, rs);
		}
		return pList;
	}

	@Override
	public List<Posting> selectAllPostings() {
		List<Posting> pList = new ArrayList<Posting>();
		String blogName = null;
		Posting selectedPosting = null;
		PostingContent pContent = null;
		String contentTable = null;
		
		String sql = "SELECT table_name FROM tabs WHERE table_name NOT IN ('BLOG', 'MEMBER', 'MIXED_CONTENTS', 'TEXT_CONTENTS', 'SINGLE_TYPE_CONTENTS', 'QNA', 'KITSCH', 'LIKES', 'LIKE', 'REBLOG', 'REBLOGS', 'FOLLOW')";
		System.out.println("PostingDaoImpl selectAllPostings() : first query :" + sql);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		
		try {
			connection = this.obtainConnection();
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				blogName = rs.getString(1);
				
				String sql2 = "SELECT * FROM " + blogName;
				System.out.println("PostingDaoImpl selectAllPostings() second query : " + sql2);
				pstmt2 = connection.prepareStatement(sql2);
				rs2 = pstmt2.executeQuery();
				while (rs2.next()) {
					int num = rs2.getInt("num");
					String title = rs2.getString("title");
					String writer = rs2.getString("writer");
					int contentType = rs2.getInt("content_type");
					int readCount = rs2.getInt("read_count");
					java.util.Date regDate = ConvertDateType.ConvertDateSqlToUtil(rs2.getDate("reg_date"));
					int likes = rs2.getInt("likes");
					int exposure = rs2.getInt("exposure");
					String tags = rs2.getString("tags");
					int ref = rs2.getInt("ref");
					int replyStep = rs2.getInt("reply_step");
					int replyDepth = rs2.getInt("reply_depth");
					int replyCount = rs2.getInt("reply_count");
					int postingType = rs2.getInt("posting_type");
					int reblogCount = rs2.getInt("reblog_count");
					int reblogOption = rs2.getInt("reblog_option");
					
					contentTable = this.getContentTable(contentType);
					String sql3 = "SELECT * FROM " + contentTable + " WHERE blog_name=? AND posting_num=?";
					System.out.println("PostingDaoImpl selectAllPostings() third query : " + sql3);
					pstmt3 = connection.prepareStatement(sql3);
					pstmt3.setString(1, blogName.toLowerCase());
					pstmt3.setInt(2, num);
					rs3 = pstmt3.executeQuery();
					
					while (rs3.next()) {
						String bName = rs3.getString("blog_name");
						int pNum = rs3.getInt("posting_num");
						if (contentTable.equals("mixed_contents")) {
							String textContents = rs3.getString("text_contents");
							String filePaths = rs3.getString("file_path");
							
							StringTokenizer tokenizer = new StringTokenizer(filePaths, "@");
							List<String> paths = new ArrayList<String>();
							while (tokenizer.hasMoreTokens()) {
								paths.add("@" + tokenizer.nextToken());
							}
							pContent = new PostingContent(bName, pNum, textContents, paths.toArray(new String[0]));
						} else if (contentTable.equals("single_type_contents")) {
							String filePaths = rs3.getString("file_path");
							
							StringTokenizer tokenizer = new StringTokenizer(filePaths, "@");
							List<String> paths = new ArrayList<String>();
							while (tokenizer.hasMoreTokens()) {
								paths.add("@" + tokenizer.nextToken());
							}
							pContent = new PostingContent(bName, pNum, paths.toArray(new String[0]));
						} else if (contentTable.equals("text_contents")) {
							String textContent = rs3.getString("text_contents");
							
							pContent = new PostingContent(bName, pNum, textContent);
						}
						selectedPosting = new Posting(num, title, writer, pContent, contentType,
								readCount, regDate, likes, exposure, tags, ref, replyStep, 
								replyDepth, replyCount, postingType, reblogCount, reblogOption);
						
						pList.add(selectedPosting);
					}
				}
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			this.closeResources(null, pstmt3, rs3);
			this.closeResources(null, pstmt2, rs2);
			this.closeResources(connection, pstmt, rs);
		}
		return pList;
	}

	@Override
	public List<Posting> selectPostingList(Map<String, Object> searchInfo) {
		List<Posting> pList = new ArrayList<Posting>();
		Posting selectedPosting = null;
		PostingContent pContent = null;;
		
		String orderBySyntax = "";
		String whereSyntax = "";
		String specifyBlog = "";
		String blogTable = "";
		String contentTable = "";
		
		if(searchInfo == null) {
			return pList;
		}
		
		String target = (String) searchInfo.get("target");
		String searchType = (String) searchInfo.get("searchType");
		String searchText = (String) searchInfo.get("searchText");
		String blogName = (String) searchInfo.get("blogName");
		String sortingOption = null;
		int startRow = (Integer) searchInfo.get("startRow");
		int endRow = (Integer) searchInfo.get("endRow");
		int typeOfContent = 0;
		String searchTextKeyword = "";
		boolean selectAsContentType = false;
		
		if (searchInfo.containsKey("sortingOption")) {
			sortingOption = (String) searchInfo.get("sortingOption");
			if (sortingOption != null && sortingOption.trim().length() != 0) {
				if (sortingOption.equals("recently")) {
					orderBySyntax = " ORDER BY reg_date DESC, reblog DESC, likes DESC, read_count DESC";
				} else if (sortingOption.equals("correctly")) {
					System.out.println("#################################################################");
					System.out.println("This function is not implimented yet. PostingDaoImpl at 648 line.");
					System.out.println("#################################################################");
					orderBySyntax = " ORDER BY reblog DESC, likes DESC, read_count DESC, reg_date DESC";
				} else if (sortingOption.equals("popularity")) {
					orderBySyntax = " ORDER BY reblog DESC, likes DESC, read_count DESC, reg_date DESC";
				} else if (sortingOption.equals("inSpecifyBlog")) {
					orderBySyntax = " ORDER BY reg_date DESC, num DESC";
				}
			}
		}
		
		if (searchInfo.containsKey("contentType")) {
			typeOfContent = (Integer) searchInfo.get("contentType");
			selectAsContentType = this.checkValidContentType(typeOfContent);
		}
		
		if (searchText != null) {
			searchText.trim();
			String searchTextTemp1 = searchText.replace("@", "@@");
			String searchTextTemp2 = searchTextTemp1.replace("_", "@_");
			String searchTextTemp3 = searchTextTemp2.replace("%", "@%");
			searchTextKeyword = "%" + searchTextTemp3.replace(' ', '%') + "%";
			searchTextKeyword = searchTextKeyword.toUpperCase();
		}
		
		if (target != null && target.trim().length() != 0) {
			if (target.equals("all") || target.equals("posting")) {
				if (selectAsContentType) {
					whereSyntax = " WHERE content_type=?";
				} else if (searchType != null && searchType.trim().length() != 0) {
					if (searchInfo.containsKey("blogName") && blogName != null && blogName.trim().length() != 0) {
						specifyBlog = " AND table_name=?";
					}
					if (searchType.equals("all")) {
						String subQuery = "";
						if (contentTable.equals("mixed_contents") || contentTable.equals("text_contents")) {
							subQuery = " num IN (SELECT num FROM " + contentTable + " WHERE blog_name=? AND UPPER(text_contents) LIKE ? ESCAPE '@') OR ";
						}
						whereSyntax = " WHERE " + subQuery + " UPPER(title) LIKE ? OR UPPER(writer) LIKE ? OR UPPER(tags) LIKE ? ESCAPE '@'";
					} else if (searchType.equals("title")) {
						whereSyntax = " WHERE UPPER(title) LIKE ? ESCAPE '@'";
					} else if (searchType.equals("writer")) {
						whereSyntax = " WHERE UPPER(writer) LIKE ? ESCAPE '@'";
					} else if (searchType.equals("contents")) {
						whereSyntax = " WHERE num IN (SELECT num FROM " + contentTable + " WHERE blog_name=? AND UPPER(text_contents) LIKE ? ESCAPE '@')";
					} else if (searchType.equals("tags")) {
						whereSyntax = " WHERE UPPER(tags) LIKE ? ESCAPE '@'";
					} else { /*return pList;*/ }
				} else { /*return pList;*/ }
			} else { /*return pList;*/ }
		} else { /*return pList;*/ }
		
		String sql = "SELECT table_name FROM tabs WHERE table_name NOT IN ('BLOG', 'MEMBER', 'MIXED_CONTENTS', 'TEXT_CONTENTS', 'SINGLE_TYPE_CONTENTS', 'QNA', 'KITSCH', 'LIKES', 'LIKE', 'REBLOG', 'REBLOGS', 'FOLLOW') " + specifyBlog;
		System.out.println("PostingDaoImpl getPostingList() first query : " + sql);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		
		try {
			connection = this.obtainConnection();
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement(sql);
			
			if (searchInfo.containsKey("blogName") && blogName != null && blogName.trim().length() != 0) {
				pstmt.setString(1, blogName);
			}
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				blogTable = rs.getString(1);
				String sql2 = "SELECT * FROM ("
						+ "SELECT ROWNUM AS row_num, num, title, writer, content_type, read_count, reg_date, "
							+ "likes, exposure, tags, ref, reply_step, reply_depth, reply_count, "
							+ "posting_type, reblog_count, reblog_option FROM "
								+ "(SELECT * FROM " + blogTable + whereSyntax + orderBySyntax + "))"
						+ " WHERE row_num BETWEEN ? AND ?";
				System.out.println("PostingDaoImpl getPostingList() second query : " + sql2);
				
				pstmt2 = connection.prepareStatement(sql2);
				
				if (selectAsContentType){
					pstmt2.setInt(1, typeOfContent);
					pstmt2.setInt(2, startRow);
					pstmt2.setInt(3, endRow);
				} else if (searchType.equals("all")) {
					if (contentTable.equals("mixed_contents") || contentTable.equals("text_contents")) {
						pstmt2.setString(1, blogTable);
						pstmt2.setString(2, searchTextKeyword);
						pstmt2.setString(3, searchTextKeyword);
						pstmt2.setString(4, searchTextKeyword);
						pstmt2.setString(5, searchTextKeyword);
						pstmt2.setInt(6, startRow);
						pstmt2.setInt(7, endRow);
					} else {
						pstmt2.setString(1, searchTextKeyword);
						pstmt2.setString(2, searchTextKeyword);
						pstmt2.setString(3, searchTextKeyword);
						pstmt2.setInt(4, startRow);
						pstmt2.setInt(5, endRow);
					}
				} else if (searchType.equals("title")) {
					pstmt2.setString(1, searchTextKeyword);
					pstmt2.setInt(2, startRow);
					pstmt2.setInt(3, endRow);
				} else if (searchType.equals("writer")) {
					pstmt2.setString(1, searchTextKeyword);
					pstmt2.setInt(2, startRow);
					pstmt2.setInt(3, endRow);
				} else if (searchType.equals("contents")) {
					pstmt2.setString(1, blogTable);
					pstmt2.setString(2, searchTextKeyword);
					pstmt2.setInt(3, startRow);
					pstmt2.setInt(4, endRow);
				} else if (searchType.equals("tags")) {
					pstmt2.setString(1, searchTextKeyword);
					pstmt2.setInt(2, startRow);
					pstmt2.setInt(3, endRow);
				}
				
				rs2 = pstmt2.executeQuery();
				
				while (rs2.next()) {
					int num = rs2.getInt("num");
					String title = rs2.getString("title");
					String writer = rs2.getString("writer");
					int contentType = rs2.getInt("content_type");
					int readCount = rs2.getInt("read_count");
					java.util.Date regDate = ConvertDateType.ConvertDateSqlToUtil(rs2.getDate("reg_date"));
					int likes = rs2.getInt("likes");
					int exposure = rs2.getInt("exposure");
					String tags = rs2.getString("tags");
					int ref = rs2.getInt("ref");
					int replyStep = rs2.getInt("reply_step");
					int replyDepth = rs2.getInt("reply_depth");
					int replyCount = rs2.getInt("reply_count");
					int postingType = rs2.getInt("posting_type");
					int reblogCount = rs2.getInt("reblog_count");
					int reblogOption = rs2.getInt("reblog_option");
					
					contentTable = this.getContentTable(contentType);
					String sql3 = "SELECT * FROM " + contentTable + " WHERE blog_name=? AND posting_num=?";
					System.out.println("PostingDaoImpl getPostingList() third query : " + sql3);
					pstmt3 = connection.prepareStatement(sql3);
					pstmt3.setString(1, blogTable.toLowerCase());
					pstmt3.setInt(2, num);
					rs3 = pstmt3.executeQuery();
					
					while (rs3.next()) {
						String bName = rs3.getString("blog_name");
						int pNum = rs3.getInt("posting_num");
						if (contentTable.equals("mixed_contents")) {
							String textContents = rs3.getString("text_contents");
							String filePaths = rs3.getString("file_path");
							
							StringTokenizer tokenizer = new StringTokenizer(filePaths, "@");
							List<String> paths = new ArrayList<String>();
							while (tokenizer.hasMoreTokens()) {
								paths.add("@" + tokenizer.nextToken());
							}
							pContent = new PostingContent(bName, pNum, textContents, paths.toArray(new String[0]));
						} else if (contentTable.equals("single_type_contents")) {
							String filePaths = rs3.getString("file_path");
							
							StringTokenizer tokenizer = new StringTokenizer(filePaths, "@");
							List<String> paths = new ArrayList<String>();
							while (tokenizer.hasMoreTokens()) {
								paths.add("@" + tokenizer.nextToken());
							}
							pContent = new PostingContent(bName, pNum, paths.toArray(new String[0]));
						} else if (contentTable.equals("text_contents")) {
							String textContent = rs3.getString("text_contents");
							
							pContent = new PostingContent(bName, pNum, textContent);
						}
						selectedPosting = new Posting(num, title, writer, pContent, contentType,
								readCount, regDate, likes, exposure, tags, ref, replyStep, 
								replyDepth, replyCount, postingType, reblogCount, reblogOption);
						
						pList.add(selectedPosting);
					}
				}
			}
			
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			this.closeResources(null, pstmt3, rs3);
			this.closeResources(null, pstmt2, rs2);
			this.closeResources(connection, pstmt, rs);
		}
		
		return pList;
	}

	@Override
	public int selectPostingCount(Map<String, Object> searchInfo) {
		int selectedCount = 0;
		
		String whereSyntax = "";
		String specifyBlog = "";
		String blogTable = "";
		String contentTable = "";
		
		if(searchInfo == null) {
			return selectedCount;
		}
		
		String target = (String) searchInfo.get("target");
		String searchType = (String) searchInfo.get("searchType");
		String searchText = (String) searchInfo.get("searchText");
		String blogName = (String) searchInfo.get("blogName");
		int typeOfContent = 0;
		String searchTextKeyword = "";
		
		boolean selectAsContentType = false;
		
		if (searchInfo.containsKey("contentType")) {
			typeOfContent = (Integer) searchInfo.get("contentType");
			selectAsContentType = this.checkValidContentType(typeOfContent);
		}
		
		if (searchText != null) {
			searchText.trim();
			String searchTextTemp1 = searchText.replace("@", "@@");
			String searchTextTemp2 = searchTextTemp1.replace("_", "@_");
			String searchTextTemp3 = searchTextTemp2.replace("%", "@%");
			searchTextKeyword = "%" + searchTextTemp3.replace(' ', '%') + "%";
			searchTextKeyword = searchTextKeyword.toUpperCase();
		}
		
		if (target != null && target.trim().length() != 0) {
			if (target.equals("all") || target.equals("posting")) {
				if (selectAsContentType) {
						whereSyntax = " WHERE content_type=?";
				} else if (searchType != null && searchType.trim().length() != 0) {
					if (searchInfo.containsKey("blogName") && blogName != null && blogName.trim().length() != 0) {
						specifyBlog = " AND table_name=?";
					}
					if (searchType.equals("all")) {
						String subQuery = "";
						if (contentTable.equals("mixed_contents") || contentTable.equals("text_contents")) {
							subQuery = " num IN (SELECT num FROM " + contentTable + " WHERE blog_name=? AND UPPER(text_contents) LIKE ? ESCAPE '@') OR ";
						}
						whereSyntax = " WHERE " + subQuery + " UPPER(title) LIKE ? OR UPPER(writer) LIKE ? OR UPPER(tags) LIKE ? ESCAPE '@'";
					} else if (searchType.equals("title")) {
						whereSyntax = " WHERE UPPER(title) LIKE ? ESCAPE '@'";
					} else if (searchType.equals("writer")) {
						whereSyntax = " WHERE UPPER(writer) LIKE ? ESCAPE '@'";
					} else if (searchType.equals("contents")) {
						whereSyntax = " WHERE num IN (SELECT num FROM " + contentTable + " WHERE blog_name=? AND UPPER(text_contents) LIKE ? ESCAPE '@')";
					} else if (searchType.equals("tags")) {
						whereSyntax = " WHERE UPPER(tags) LIKE ? ESCAPE '@'";
					} else { /*return selectedCount;*/ }
				} else { /*return selectedCount;*/ }
			} else { /*return selectedCount;*/ }
		} else { /*return selectedCount;*/ }
		
		
		String sql = "SELECT table_name FROM tabs WHERE table_name NOT IN ('BLOG', 'MEMBER', 'MIXED_CONTENTS', 'TEXT_CONTENTS', 'SINGLE_TYPE_CONTENTS', 'QNA', 'KITSCH', 'LIKES', 'LIKE', 'REBLOG', 'REBLOGS', 'FOLLOW') " + specifyBlog;
		System.out.println("PostingDaoImpl getPostingCount() first query : " + sql);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		
		try {
			connection = this.obtainConnection();
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement(sql);
			
			if (searchInfo.containsKey("blogName") && blogName != null && blogName.trim().length() != 0) {
				pstmt.setString(1, blogName);
			}
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				blogTable = rs.getString(1);
				String sql2 = "SELECT COUNT(*) FROM " + blogTable + whereSyntax;
				System.out.println("PostingDaoImpl getPostingCount() second query : " + sql2);
				
				pstmt2 = connection.prepareStatement(sql2);
				
				if (selectAsContentType) {
						pstmt2.setInt(1, typeOfContent);
				} else if (searchType.equals("all")) {
					if (contentTable.equals("mixed_contents") || contentTable.equals("text_contents")) {
						pstmt2.setString(1, blogTable);
						pstmt2.setString(2, searchTextKeyword);
						pstmt2.setString(3, searchTextKeyword);
						pstmt2.setString(4, searchTextKeyword);
						pstmt2.setString(5, searchTextKeyword);
					} else {
						pstmt2.setString(1, searchTextKeyword);
						pstmt2.setString(2, searchTextKeyword);
						pstmt2.setString(3, searchTextKeyword);
					}
				} else if (searchType.equals("title")) {
					pstmt2.setString(1, searchTextKeyword);
				} else if (searchType.equals("writer")) {
					pstmt2.setString(1, searchTextKeyword);
				} else if (searchType.equals("contents")) {
					pstmt2.setString(1, blogTable.toLowerCase());
					pstmt2.setString(2, searchTextKeyword);
				} else if (searchType.equals("tags")) {
					pstmt2.setString(1, searchTextKeyword);
				}
				
				rs2 = pstmt2.executeQuery();
				
				if (rs2.next()) {
					selectedCount += rs2.getInt(1);
				}
			}
			
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			this.closeResources(null, pstmt2, rs2);
			this.closeResources(connection, pstmt, rs);
		}
		
		return selectedCount;
	}

	@Override
	public boolean postingExists(String blogName, int postingNum) {
		boolean result = false;
		
		String sql = "SELECT num FROM " + blogName + " WHERE num=?";
		System.out.println("PostingDaoImpl PostingExists() : " + sql);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = this.obtainConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, postingNum);
			rs = pstmt.executeQuery();
			result = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeResources(connection, pstmt, rs);
		}
		
		return result;
	}

	@Override
	public int addReadCount(String blogName, int postingNum) {
		int result = 0;
		String sql ="UPDATE " + blogName + " SET read_count=read_count+1 WHERE num=?";
		System.out.println("PostingDaoImpl addReadCount() : " + sql);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = this.obtainConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, postingNum);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeResources(connection, pstmt);
		}
		return result;
	}

	@Override
	public void addLikes(Member member, String blogName, int postingNum) {
		String sql = "UPDATE " + blogName + " SET likes=likes+1 WHERE num=?";
		String sql2 = "INSERT INTO likes (email, blog_name, posting_num) VALUES (?, ?, ?)";
		System.out.println("PostingDaoImpl addLikes() : " + sql);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = this.obtainConnection();
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, postingNum);
			pstmt.executeUpdate();
			pstmt.close();
			
			pstmt = connection.prepareStatement(sql2);
			System.out.println("PostingDaoImpl addLikes() : " + sql2);
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, blogName);
			pstmt.setInt(3, postingNum);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			this.closeResources(connection, pstmt);
		}
	}

	@Override
	public void cancelLikes(Member member, String blogName, int postingNum) {
		String sql = "UPDATE " + blogName + " SET likes=likes-1 WHERE num=?";
		String sql2 = "DELETE FROM likes WHERE email=? AND blog_name=? AND posting_num=?";
		System.out.println("PostingDaoImpl cancelLikes() : " + sql);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = this.obtainConnection();
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, postingNum);
			pstmt.executeUpdate();
			pstmt.close();
			
			pstmt = connection.prepareStatement(sql2);
			System.out.println("PostingDaoImpl cancelLikes() : " + sql2);
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, blogName);
			pstmt.setInt(3, postingNum);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			this.closeResources(connection, pstmt);
		}
	}

	@Override
	public PostingContent getContents(String blogName, int postingNum) {
		PostingContent pContent = null;
		String contentTable = this.getContentTable(this.selectPosting(blogName, postingNum));
		
		String sql = "SELECT * FROM " + contentTable + " WHERE blog_name=? AND posting_num=?";
		System.out.println("PostingDaoImpl getContents() : " + sql);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = this.obtainConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, blogName);
			pstmt.setInt(2, postingNum);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				if (contentTable.equals("mixed_contents")) {
					String filePaths = rs.getString("file_path");
					
					StringTokenizer tokenizer = new StringTokenizer(filePaths, "@");
					List<String> paths = new ArrayList<String>();
					while (tokenizer.hasMoreTokens()) {
						paths.add(tokenizer.nextToken());
					}
					pContent = new PostingContent(rs.getString("blog_name"), rs.getInt("posting_num"), rs.getString("text_contents"), paths.toArray(new String[0]));
				} else if (contentTable.equals("single_type_contents")) {
					String filePaths = rs.getString("file_path");
					
					StringTokenizer tokenizer = new StringTokenizer(filePaths, "@");
					List<String> paths = new ArrayList<String>();
					while (tokenizer.hasMoreTokens()) {
						paths.add("@" + tokenizer.nextToken());
					}
					pContent = new PostingContent(rs.getString("blog_name"), rs.getInt("posting_num"), paths.toArray(new String[0]));
				} else if (contentTable.equals("text_contents")) {
					pContent = new PostingContent(rs.getString("blog_name"), rs.getInt("posting_num"), rs.getString("text_contents"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeResources(connection, pstmt, rs);
		}
		
		return pContent;
	}

	@Override
	public void setContents(String blogName, int postingNum, PostingContent pContent) {
		String contentTable = this.getContentTable(this.selectPosting(blogName, postingNum));
		String contents = null;
		
		if (contentTable.equals("mixed_contents")) {
			contents = "text_content=?, file_path=?";
		} else if (contentTable.equals("single_type_contents")) {
			contents = "file_path=?";
		} else if (contentTable.equals("text_contents")) {
			contents = "text_contents=?";
		}
		
		String sql = "UPDATE " + contentTable + " SET " + contents + " WHERE blog_name=? AND posting_num=?";
		System.out.println("PostingDaoImpl setContents() : " + sql);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = this.obtainConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, blogName);
			pstmt.setInt(2, postingNum);
			
			StringBuilder filePaths = null;
			if (pContent.getFilePaths() != null || pContent.getFilePaths().length != 0) {
				filePaths = new StringBuilder();
				String[] allPaths = pContent.getFilePaths();
				for (String path : allPaths) {
					filePaths.append("@" + path);
				}
			}
			
			if (contentTable.equals("mixed_contents")) {
				pstmt.setString(1, pContent.getTextContent());
				pstmt.setString(2, filePaths.toString());
				pstmt.setString(3, blogName);
				pstmt.setInt(4, postingNum);
			} else if (contentTable.equals("single_type_contents")) {
				pstmt.setString(1, filePaths.toString());
				pstmt.setString(2, blogName);
				pstmt.setInt(3, postingNum);
			} else if (contentTable.equals("text_contents")) {
				pstmt.setString(1, pContent.getTextContent());
				pstmt.setString(2, blogName);
				pstmt.setInt(3, postingNum);
			}
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeResources(connection, pstmt);
		}
	}

	@Override
	public void reblog(Member member, String originBlogName, int postingNum, String targetBlogName) {
		Posting originPosting = this.selectPosting(originBlogName, postingNum);
		originPosting.setWriter(originPosting.getWriter() + " >> " + member.getName());
		this.insertPosting(targetBlogName, originPosting);
		
		String sql = "INSERT INTO reblog (member_eamil, origin_blog_name, target_blog_name, origin_posting_num) VALUES (?, ?, ?, ?)";
		System.out.println("PostingDaoImpl reblog() : " + sql);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = this.obtainConnection();
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, originBlogName);
			pstmt.setString(3, targetBlogName);
			pstmt.setInt(4, postingNum);
			pstmt.executeUpdate();
			if (this.insertPosting(targetBlogName, originPosting) == 0) {
				throw new SQLException();
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			this.closeResources(connection, pstmt);
		}
	}

	@Override
	public List<Posting> selectLikedPostings(Member member) {
		String sql = "SELECT * FROM likes WHERE member_name=?";
		System.out.println("PostingDaoImpl selectLikedPosting() query : " + sql);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			connection = this.obtainConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, member.getName());
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String blogName = rs.getString("blog_name");
				String sql2 = "SELECT * FROM " + blogName + " WHERE num=?";
				sssssssssssss;
			}
		}
		
		
		return null;
	}

	@Override
	public List<Posting> selectReplyPostings(String tableName, int postingNum) {
		// TODO Auto-generated method stub
		return null;
	}

}
