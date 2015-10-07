package sku.microblog.dataaccess;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

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

	@Override
	public void insertPosting(String blogName, Posting posting) {
		String contentTable = "";
		String contents = "";
		
		PostingContent pContent = posting.getContents();
		
		if (pContent != null) {
			int contentType = pContent.getContentType();
			if (contentType / 100 == PostingContent.SINGLE_TYPE_CONTENT) {
				contentTable = "mixed_contents";
				contents = "?, ?, ?";
			} else if (contentType / 100 == PostingContent.MIXED_TYPE_CONTENT) {
				contentTable = "single_type_contents";
				contents = "?, ?";
			} else if (contentType == PostingContent.TEXT_CONTENT) {
				contentTable = "text_contents";
				contents = "?";
			}
		}
		
		String sql = "INSERT INTO " + blogName + " (num, title, writer, content_type, reg_date, exposure, tags, ref, posting_type, reblog_option) "
				+ " VALUES (" + blogName + "_num_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, " + blogName + "_num_seq.CURRVAL, ?, ?)";
		String sql2= "INSERT INTO " + contentTable + " VALUES (?, ?, " + contents + ")";
		System.out.println("PostingDaoImpl insertPosting() : " + sql);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = this.obtainConnection();
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, posting.getTitle());
			pstmt.setString(2, posting.getWriter());
			pstmt.setInt(3, posting.getContents().getContentType());
			pstmt.setDate(4, ConvertDateType.ConvertDateUtilToSql(new java.util.Date()));
			pstmt.setInt(5, posting.getExposure());
			pstmt.setString(6, posting.getTags());
			pstmt.close();
			
			pstmt = connection.prepareStatement(sql2);
			pstmt.setString(1, pContent.getBlogName());
			pstmt.setInt(2, pContent.getPostingNum());
			
			if (pContent != null) {
				int contentType = pContent.getContentType();
				if (contentType / 100 == PostingContent.MIXED_TYPE_CONTENT) {
					File[] files = pContent.getFileContent().toArray(new File[0]);
					String filePaths = "";
					for (File file : files) {
						filePaths += "@" + file.getPath();
					}
					
					pstmt.setString(3, pContent.getTextContent());
					pstmt.setString(4, filePaths);
					pstmt.setInt(5, pContent.getContentType());
				} else if (contentType / 100 == PostingContent.SINGLE_TYPE_CONTENT) {
					File[] files = pContent.getFileContent().toArray(new File[0]);
					String filePaths = "";
					for (File file : files) {
						filePaths += "@" + file.getPath();
					}
					
					pstmt.setString(3, filePaths);
					pstmt.setInt(4, pContent.getContentType());
				} else if (contentType == PostingContent.TEXT_CONTENT) {
					pstmt.setString(3, pContent.getTextContent());
				}
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
	public void updatePosting(String blogName, Posting posting) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePosting(String blogName, int postingNum) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Posting selectPosting(String blogName, int postingNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Posting[] selectAllPostings(String blogName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Posting[] selectAllPostings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Posting> getPostingList(Map<String, Object> searchInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPostingCount(Map<String, Object> searchInfo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean PostingExists(String blogName, Posting posting) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addReadCount(String blogName, int postingNum) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addLikes(String blogName, int postingNum) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelLikes(String blogName, int postingNum) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PostingContent getContents(String blogName, int postingNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setContents(String blogName, int postingNum) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reblog(String originBlogName, int postingNum, String targetBlogName) {
		// TODO Auto-generated method stub
		
	}
}
