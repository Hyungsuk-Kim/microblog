package sku.microblog.dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import sku.microblog.business.domain.Blog;
import sku.microblog.business.domain.Member;
import sku.microblog.business.domain.Posting;
import sku.microblog.business.service.BlogDao;

public class BlogDaoImpl implements BlogDao{

	private Connection obtainConnection() throws SQLException{
		return DatabaseUtil.getConnection();
	}
	
	private void closeResources(Connection connection, Statement stmt, ResultSet rs){
		DatabaseUtil.close(connection, stmt, rs);
	}
	
	private void closeResources(Connection connection, Statement stmt){
		DatabaseUtil.close(connection, stmt);
	}
	
	@Override
	public void insertBlog(Blog blog) {
		String sql = "INSERT INTO blog (blog_name, member_name, follower_count, visit_count, background_color, header_image, profile_image, blog_layout) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		String sql2 = "CREATE TABLE " + blog.getBlogName() + "("
						+ "num INTEGER,"
						+ "title VARCHAR(255),"
						+ "writer VARCHAR(60),"
						+ "contents INTEGER,"
						+ "ip VARCHAR(20),"
						+ "read_count NUMBER(10),"
						+ "reg_date DATE,"
						+ "likes NUMBER(10),"
						+ "exposure NUMBER(1) CHECK(exposure IN("
							+ Posting.PUBLIC_ALLOW_BOTH_REPLY_AND_REBLOG + ", "
							+ Posting.PUBLIC_ALLOW_REPLY_AND_NO_REBLOG + ", "
							+ Posting.PUBLIC_NO_REPLY_AND_ALLOW_REBLOG + ", "
							+ Posting.PUBLIC_NO_REPLY_NO_REBLOG + ", "
							+ Posting.PRIVATE + ")"
						+ "),"
						+ "tags VARCHAR(255),"
						+ "ref INTEGER NOT NULL,"
						+ "reply_step NUMBER(5),"
						+ "reply_depth NUMBER(5),"
						+ "reply_count NUMBER(5),"
						+ "posting_type NUMBER(1) CHECK(posting_type IN("
							+ Posting.NORMAL_TYPE_POSTING + ", "
							+ Posting.REPLY_TYPE_POSTING + ", "
							+ Posting.QNA_TYPE_POSTING + ")"
						+ "),"
						+ "reblog_count NUMBER(5),"
						+ "reblog_option NUMBER(1) CHECK(reblog_option IN("
							+ Posting.ON_UPDATE_AND_DELETE_CASCADE + ", "
							+ Posting.ON_UPDATE_CASCADE + ", "
							+ Posting.ON_DELETE_CASCADE + ", "
							+ Posting.SET_NULL + ")"
						+ "),"
						+ "PRIMARY KEY(num),"
						//+ "FOREIGN KEY(num) REFERENCES reblog(origin_num)"
					+")";
		System.out.println("BlogDaoImpl insertBlog() query : " + sql);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = this.obtainConnection();
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, blog.getBlogName());
			pstmt.setString(2, blog.getMemberName());
			pstmt.setInt(3, blog.getFollowerCount());
			pstmt.setInt(4, blog.getVisitCount());
			pstmt.setInt(5, blog.getBackgroundColor());
			pstmt.setString(6, blog.getHeaderImage());
			pstmt.setString(7, blog.getProfileImage());
			pstmt.setInt(8, blog.getBlogLayout());
			pstmt.executeUpdate();
			pstmt.close();
			
			pstmt = connection.prepareStatement(sql2);
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
	public void updateBlog(Blog blog) {
		//String sql = "UPDATE blog SET blog_name=?, follower_count=?, visit_count=?, background_color=?, header_image, profile_image=?, blog_layout=? WHERE member_name=?";
		String sql = "UPDATE blog SET background_color=?, header_image=?, profile_image=?, blog_layout=? WHERE blog_name=?";
		System.out.println("BlogDaoImpl updateBlog() query : " + sql);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = this.obtainConnection();
			pstmt = connection.prepareStatement(sql);
			/*pstmt.setInt(2, blog.getFollowerCount());
			pstmt.setInt(3, blog.getVisitCount());*/
			pstmt.setInt(1, blog.getBackgroundColor());
			pstmt.setString(2, blog.getHeaderImage());
			pstmt.setString(3, blog.getProfileImage());
			pstmt.setInt(4, blog.getBlogLayout());
			pstmt.setString(5, blog.getBlogName());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeResources(connection, pstmt);
		}
	}

	@Override
	public void removeBlog(Blog blog) {
		String sql = "DELETE FROM blog WHERE blog_name=?";
		String sql2 ="DROP TABLE ?";
		System.out.println("BlogDaoImpl removeBlog() query : " + sql);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = this.obtainConnection();
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, blog.getBlogName());
			pstmt.executeUpdate();
			pstmt.close();
			
			pstmt = connection.prepareStatement(sql2);
			pstmt.setString(1, blog.getBlogName());
			pstmt.executeQuery();
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
	public Blog selectBlog(String blogName) {
		Blog blog = null;
		
		String sql = "SELECT * FROM blog WHERE blog_name=?";
		System.out.println("BlogDaoImpl selectBlog() query : " + sql);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = this.obtainConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, blogName);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				blog = new Blog(rs.getString("blog_name"), rs.getString("member_name"), rs.getInt("follower_count"), 
						rs.getInt("visit_count"), rs.getInt("background_color"), rs.getString("header_image"), 
						rs.getString("profile_image"), rs.getInt("blog_layout"));
			}
		} catch(SQLException e) {
			
		} finally {
			this.closeResources(connection, pstmt, rs);
		}
		
		return blog;
	}

	@Override
	public Blog[] selectFollowedBlogs(Member member) {
		List<Blog> bList = new ArrayList<Blog>();
		Blog blog = null;
		
		String sql ="SELECT * FROM following WHERE member_name=?";
		String sql2 = "SELECT * FROM blog WHERE blog_name=?";
		System.out.println("BlogDaoImpl selectFollowedBlogs() query : " + sql);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		
		try{
			connection = this.obtainConnection();
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, member.getName());
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				System.out.println("BlogDaoImpl selectFollowedBlogs() query : " + sql2);
				String blogName = rs.getString("blog_name");
				
				pstmt2 = connection.prepareStatement(sql2);
				pstmt2.setString(1, blogName);
				rs2 = pstmt2.executeQuery();
				
				while (rs2.next()) {
					blog = new Blog(rs.getString("blog_name"), rs.getString("member_name"), rs.getInt("follower_count"), 
							rs.getInt("visit_count"), rs.getInt("background_color"), rs.getString("header_image"), 
							rs.getString("profile_image"), rs.getInt("blog_layout"));
					
					bList.add(blog);
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
		
		return bList.toArray(new Blog[0]);
	}

	@Override
	public void addFollowing(Member member, String blogName) {
		String sql = "INSERT INTO following (blog_name, member_name) VALUES (?, ?)";
		String sql2 = "UPDATE blog SET follower_count = follower_count + 1 WHERE blog_name=?";
		System.out.println("BlogDaoImpl addFollowing() query : " + sql);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = this.obtainConnection();
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, blogName);
			pstmt.setString(2, member.getName());
			pstmt.executeUpdate();
			pstmt.close();
			
			System.out.println("BlogDaoImpl addFollowing() query : " + sql2);
			pstmt = connection.prepareStatement(sql2);
			pstmt.setString(1, blogName);
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
	public void cancelFollowing(Member member, String blogName) {
		String sql = "DELETE FROM following WHERE member_name=? AND blog_name=?";
		String sql2 = "UPDATE blog SET follower_count = follower_count - 1 WHERE blog_name=?";
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = this.obtainConnection();
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, blogName);
			pstmt.setString(2, member.getName());
			pstmt.executeUpdate();
			pstmt.close();
			
			pstmt = connection.prepareStatement(sql2);
			pstmt.setString(1, blogName);
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
	public List<Blog> selectBlogList(Map<String, Object> searchInfo) {
		List<Blog> bList = new ArrayList<Blog>();
		Blog blog = null;
		
		String searchType = null;
		String searchText = null;
		String searchTextKeyword = null;
		int startRow = 0;
		int endRow = 0;
		
		String whereSyntax ="";
		
		if (searchInfo != null) {
			if (!(searchInfo.isEmpty())) {
				String target = (String) searchInfo.get("target");
				if (target.equals("blog") || target.equals("all")) {
					searchType = (String) searchInfo.get("searchType");
					searchText = (String) searchInfo.get("searchText");
					searchTextKeyword = null;
					startRow = (Integer) searchInfo.get("startRow");
					endRow = (Integer) searchInfo.get("endRow");
					
					if (searchType.equals("all")) {
						whereSyntax = " WHERE member_name LIKE ? OR blog_name LIKE ? ESCAPE '@'";
					} else if (searchType.equals("memberName")) {
						whereSyntax = " WHERE name LIKE ? ESCAPE '@'";
					} else if (searchType.equals("blogName")) {
						whereSyntax = " WHERE blog_name LIKE ? ESCAPE '@'";
					}
					
					if (searchText != null) {
						searchText.trim();
						String searchTextTemp1 = searchText.replace("@", "@@");
						String searchTextTemp2 = searchTextTemp1.replace("_", "@_");
						String searchTextTemp3 = searchTextTemp2.replace("%", "@%");
						searchTextKeyword = "%" + searchTextTemp3.replace(' ', '%') + "%";
					}
				}
			}
		}
		
		String sql = "SELECT * FROM (SELECT ROWNUM AS row_num, * FROM (SELECT * FROM blog " + whereSyntax + ")) WHERE row_num BETWEEN ? AND ?";
		System.out.println("BlogDaoImpl selectBlogList() query: " + sql);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = this.obtainConnection();
			pstmt = connection.prepareStatement(sql);
			
			if (searchType == null || searchType.trim().length() == 0) {
            	pstmt.setInt(1, startRow);
            	pstmt.setInt(2, endRow);
            } else if (searchType.equals("all")) {
				pstmt.setString(1, searchTextKeyword);
				pstmt.setString(2, searchTextKeyword);
				pstmt.setInt(3, startRow);
				pstmt.setInt(4, endRow);
			} else if (searchType.equals("memberName")) {
				pstmt.setString(1, searchTextKeyword);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
			} else if (searchType.equals("memberEmail")) {
				pstmt.setString(1, searchTextKeyword);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
			}
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				blog = new Blog(rs.getString("blog_name"), rs.getString("member_name"), rs.getInt("follower_count"), 
						rs.getInt("visit_count"), rs.getInt("background_color"), rs.getString("header_image"), 
						rs.getString("profile_image"), rs.getInt("blog_layout"));
				bList.add(blog);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeResources(connection, pstmt, rs);
		}
		
		return bList;
	}

	@Override
	public int selectBlogCount(Map<String, Object> searchInfo) {
		int count = 0;
		
		String searchType = null;
		String searchText = null;
		String searchTextKeyword = null;
		
		String whereSyntax ="";
		
		if (searchInfo != null) {
			if (!(searchInfo.isEmpty())) {
				String target = (String) searchInfo.get("target");
				if (target.equals("blog") || target.equals("all")) {
					searchType = (String) searchInfo.get("searchType");
					searchText = (String) searchInfo.get("searchText");
					searchTextKeyword = null;
					
					if (searchType.equals("all")) {
						whereSyntax = " WHERE member_name LIKE ? OR blog_name LIKE ? ESCAPE '@'";
					} else if (searchType.equals("memberName")) {
						whereSyntax = " WHERE name LIKE ? ESCAPE '@'";
					} else if (searchType.equals("blogName")) {
						whereSyntax = " WHERE blog_name LIKE ? ESCAPE '@'";
					}
					
					if (searchText != null) {
						searchText.trim();
						String searchTextTemp1 = searchText.replace("@", "@@");
						String searchTextTemp2 = searchTextTemp1.replace("_", "@_");
						String searchTextTemp3 = searchTextTemp2.replace("%", "@%");
						searchTextKeyword = "%" + searchTextTemp3.replace(' ', '%') + "%";
					}
				}
			}
		}
		
		
		String sql = "SELECT count(blog_name) FROM blog " + whereSyntax;
		System.out.println("MemberDaoImpl selectBlogCount() query : " + sql);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = this.obtainConnection();
			pstmt = connection.prepareStatement(sql);
			
			if (searchType == null || searchType.trim().length() == 0) {
            } else if (searchType.equals("all")) {
				pstmt.setString(1, searchTextKeyword);
				pstmt.setString(2, searchTextKeyword);
			} else if (searchType.equals("memberName")) {
				pstmt.setString(1, searchTextKeyword);
			} else if (searchType.equals("blogName")) {
				pstmt.setString(1, searchTextKeyword);
			}
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeResources(connection, pstmt, rs);
		}
		
		return count;
	}

	@Override
	public boolean blogExists(String blogName) {
		boolean result = false;
		
		String sql = "SELECT blog_name FROM blog WHERE blog_name=?";
		System.out.println("BlogDaoImpl blogExists() query : " + sql);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = this.obtainConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, blogName);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeResources(connection, pstmt, rs);
		}
		
		return result;
	}

	@Override
	public void addVisitCount(String blogName) {
		String sql = "UPDATE blog SET visit_count = visit_count + 1 WHERE blog_name=?";
		System.out.println("BlogDaoImpl addVisitCount() query : " + sql);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = this.obtainConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, blogName);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeResources(connection, pstmt);
		}
	}

	@Override
	public void updateBlogName(String originBlogName, String newBlogName) {
		String sql = "UPDATE blog SET blogName=? WHERE blogName=?";
		String sql2 = "RENAME ? TO ?";
		System.out.println("BlogDaoImpl updateBlogName() query : " + sql);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = this.obtainConnection();
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, newBlogName);
			pstmt.setString(2, originBlogName);
			pstmt.executeUpdate();
			pstmt.close();
			
			pstmt = connection.prepareStatement(sql2);
			pstmt.setString(1, originBlogName);
			pstmt.setString(2, newBlogName);
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
	
}
