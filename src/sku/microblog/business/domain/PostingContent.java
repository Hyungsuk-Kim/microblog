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
	public static final int SINGLE_TYPE_CONTENT = 1;
	public static final int MIXED_TYPE_CONTENT = 2;
	public static final int TEXT_CONTENT = 110;
	public static final int SINGLE_IMAGE_FILE_CONTENT = 120;
	public static final int SINGLE_IMAGE_LINK_CONTENT = 121;
	public static final int MIXED_IMAGE_FILE_CONTENT = 220;
	public static final int MIXED_IMAGE_LINK_CONTENT = 221;
	public static final int SINGLE_AUDIO_FILE_CONTENT = 130;
	public static final int SINGLE_AUDIO_LINK_CONTENT = 131;
	public static final int MIXED_AUDIO_FILE_CONTENT = 230;
	public static final int MIXED_AUDIO_LINK_CONTENT = 231;
	public static final int SINGLE_VIDEO_FILE_CONTENT = 140;
	public static final int SINGLE_VIDEO_LINK_CONTENT = 141;
	public static final int MIXED_VIDEO_FILE_CONTENT = 240;
	public static final int MIXED_VIDEO_LINK_CONTENT = 241;
	
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
