package sku.microblog.dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import sku.microblog.business.domain.Blog;
import sku.microblog.business.domain.Member;
import sku.microblog.business.service.BlogDao;

public class BlogDaoImpl implements BlogDao{

	private Connection obtainConnection() throws SQLException{
		return DatabaseUtil.getConnection();
	}
	
	@Override
	public void insertBlog(Blog blog) {
		String sql = "INSERT INTO blog (member_name, blog_name, follower_count, background_color, header_image, profile_image, blog_layout) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
		System.out.println("BlogDaoImpl insertBlog() query : " + sql);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = this.obtainConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, blog.getMemberName());
			pstmt.setString(2, blog.getBlogName());
			pstmt.setInt(3, blog.getFollowerCount());
			pstmt.setInt(4, blog.getBackgroundColor());
			pstmt.setString(5, blog.getHeaderImage());
			pstmt.setString(6, blog.getProfileImage());
			pstmt.setInt(7, blog.getBlogLayout());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseUtil.close(connection, pstmt);
		}
	}

	@Override
	public void updateBlog(Blog blog) {
		String sql = "UPDATE blog SET blog_name=?, follower_count=?, background_color=?, header_image, profile_image=?, blog_layout=? WHERE member_name=?";
		System.out.println("BlogDaoImpl updateBlog() query : " + sql);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = this.obtainConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, blog.getBlogName());
			pstmt.setInt(2, blog.getFollowerCount());
			pstmt.setInt(3, blog.getBackgroundColor());
			pstmt.setString(4, blog.getHeaderImage());
			pstmt.setString(5, blog.getProfileImage());
			pstmt.setInt(6, blog.getBlogLayout());
			pstmt.setString(7, blog.getMemberName());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseUtil.close(connection, pstmt);
		}
	}

	@Override
	public void removeBlog(Blog blog) {
		String sql = "DELETE FROM blog WHERE blog_name=?";
		System.out.println("BlogDaoImpl removeBlog() query : " + sql);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = this.obtainConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, blog.getBlogName());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseUtil.close(connection, pstmt);
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
						rs.getInt("background_color"), rs.getString("header_image"), rs.getString("profile_image"), 
						rs.getInt("blog_layout"));
			}
		} catch(SQLException e) {
			
		} finally {
			DatabaseUtil.close(connection, pstmt, rs);
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
							rs.getInt("background_color"), rs.getString("header_image"), rs.getString("profile_image"), 
							rs.getInt("blog_layout"));
					
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
			DatabaseUtil.close(null, pstmt2, rs2);
			DatabaseUtil.close(connection, pstmt, rs);
		}
		
		return bList.toArray(new Blog[0]);
	}

	@Override
	public void addFollowing(Member member, String blogName) {
		String sql = "INSERT INTO following (blog_name, member_name) VALUES (?, ?)";
		String sql2 = "UPDATE blog SET follower_count = follower_count + 1 WHERE blog_name=?";
		
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
			DatabaseUtil.close(connection, pstmt);
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
			DatabaseUtil.close(connection, pstmt);
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
						rs.getInt("background_color"), rs.getString("header_image"), rs.getString("profile_image"), 
						rs.getInt("blog_layout"));
				bList.add(blog);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseUtil.close(connection, pstmt, rs);
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
			DatabaseUtil.close(connection, pstmt, rs);
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
			DatabaseUtil.close(connection, pstmt, rs);
		}
		
		return result;
	}
	
}
