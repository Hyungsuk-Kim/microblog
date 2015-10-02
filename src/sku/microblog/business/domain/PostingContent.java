package sku.microblog.business.domain;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class PostingContent implements Serializable{
	// Variables
	// Instance Variables
	private static final long serialVersionUID = -3440708136003859673L;
	
	private String blogName;
	private int postingNum;
	private int contentType;
	private String textContent;
	private List<File> fileContent;
	
	// Class Variables
	// available content types
	public static final int TEXT_CONTENT = 10;
	public static final int MIXED_CONTENT = 11;
	public static final int IMAGE_FILE_CONTENT = 20;
	public static final int IMAGE_LINK_CONTENT = 21;
	public static final int AUDIO_FILE_CONTENT = 30;
	public static final int AUDIO_LINK_CONTENT = 31;
	public static final int VIDEO_FILE_CONTENT = 40;
	public static final int VIDEO_LINK_CONTENT = 41;
	public static final int LINK_CONTENT = 50;
	
	// Constructors
	public PostingContent(String blogName, int postingNum, int contentType) {
		this.blogName = blogName;
		this.postingNum = postingNum;
		this.contentType = contentType;
	}
	
	// for Text Contents
	public PostingContent(String blogName, int postingNum, int contentType, String textContent) {
		this(blogName, postingNum, contentType);
		this.textContent = textContent;
	}
	
	// for Single Contents (Media content)
	public PostingContent(String blogName, int postingNum, int contentType, File... files) {
		this(blogName, postingNum, contentType);
		if (files.length != 0) {
			this.fileContent = new ArrayList<File>();
			for (File file : files) {
				fileContent.add(file);
			}
		}
	}
	
	// for Mixed Contents
	public PostingContent(String blogName, int postingNum, int contentType, String textContent, File... files) {
		this(blogName, postingNum, contentType, files);
		this.textContent = textContent;
	}

	// Methods
	@Override
	public String toString() {
		return "PostingContent [blogName=" + blogName + ", postingNum=" + postingNum + ", contentType=" + contentType
				+ ", textContent=" + textContent + ", fileContent=" + fileContent + "]";
	}

	// Getters
	public String getBlogName() {
		return blogName;
	}

	public int getPostingNum() {
		return postingNum;
	}

	public int getContentType() {
		return contentType;
	}

	public String getTextContent() {
		return textContent;
	}

	public List<File> getFileContent() {
		return fileContent;
	}

	// Setters
	public void setBlogName(String blogName) {
		this.blogName = blogName;
	}

	public void setPostingNum(int postingNum) {
		this.postingNum = postingNum;
	}

	public void setContentType(int contentType) {
		this.contentType = contentType;
	}

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}

	public void setFileContent(List<File> fileContent) {
		this.fileContent = fileContent;
	}
	
}
