package org.app.data.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import org.app.data.database.comments.DatabaseCommentService;

public class Comment extends Entity implements Serializable{

	private static final long serialVersionUID = 1L;
	private String parent;
	private String grandparent;
	private String author;
	private String commentDate;
	private long parentComment;
	private ArrayList<Comment> children;
	private String content;
	private int likesCount;
	private int hatesCount;
	private boolean isDirty;
	private boolean deleted;
	private long commentId;
	
	public long getCommentId() {
		return commentId;
	}

	public void setCommentId() {
		this.commentId = DatabaseCommentService.getCommentId(grandparent, parent);
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getGrandparent() {
		return grandparent;
	}

	public void setGrandparent(String grandparent) {
		this.grandparent = grandparent;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Comment() {
		children = new ArrayList<Comment>();
		isDirty = false;
		likesCount=0;
		hatesCount=0;
		commentDate = new Date().toString();
		deleted=false;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate.toString();
	}

	public long getParentComment() {
		return parentComment;
	}

	public void setParentComment(long parentComment) {
		this.parentComment = parentComment;
	}

	public ArrayList<Comment> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<Comment> children) {
		this.children = children;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public boolean isDirty() {
		return isDirty;
	}

	public void setDirty(boolean isDirty) {
		this.isDirty = isDirty;
	}

	
}
