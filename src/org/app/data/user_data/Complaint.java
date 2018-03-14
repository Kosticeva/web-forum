package org.app.data.user_data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import org.app.data.entities.Entity;

public class Complaint implements Serializable {

	private static final long serialVersionUID = 1L;
	private String author;
	private String date;
	private Entity target;
	private String content;
	private ArrayList<User> authorities;
	
	public Complaint(){
		setAuthorities(new ArrayList<User>());
		date = new Date().toString();
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Entity getTarget() {
		return target;
	}

	public void setTarget(Entity target) {
		this.target = target;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ArrayList<User> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(ArrayList<User> authorities) {
		this.authorities = authorities;
	}
	
}
