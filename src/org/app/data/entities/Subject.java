package org.app.data.entities;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;

public class Subject extends Entity implements Serializable{

	private static final long serialVersionUID = 1L;
	private String parent;
	private String title;
	private String type;
	private String author;
	private ArrayList<Comment> comments;
	private String content;
	private String creationDate;
	private int likesCount;
	private int hatesCount;
	
	public Subject() {
		comments = new ArrayList<Comment>();
		likesCount=0;
		hatesCount=0;
		creationDate = new Date().toString();
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getTitle() {
		return title;
	}
	
	public String getUrl(){
		try {
			return URLEncoder.encode(title, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		if(type.equals("Link") || type.equals("Image") || type.equals("Text")){
			this.type=type;
		}
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public ArrayList<Comment> getComments() {
		return comments;
	}

	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate.toString();
	}

	public int getLikesCount() {
		return likesCount;
	}

	public void setLikesCount(int likesCount) {
		this.likesCount = likesCount;
	}

	public int getHatesCount() {
		return hatesCount;
	}

	public void setHatesCount(int hatesCount) {
		this.hatesCount = hatesCount;
	}
	
}
