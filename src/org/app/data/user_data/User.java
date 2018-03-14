package org.app.data.user_data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import org.app.data.entities.Comment;
import org.app.data.entities.Subforum;
import org.app.data.entities.Subject;

public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String name;
	private String surname;
	private String type;
	private String phoneNum;
	private String email;
	private String registrationDate;
	
	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	private ArrayList<Subforum> followedForums;
	private ArrayList<Subject> savedSubjects;
	private ArrayList<Comment> savedComments;
	private ArrayList<Message> messages;
	private ArrayList<Message> savedMessages;
	
	private ArrayList<Subject> likeSubj;
	private ArrayList<Subject> hateSubj;
	private ArrayList<Comment> likeComm;
	private ArrayList<Comment> hateComm;

	public User() {
		super();
		type = "Regular";
		followedForums = new ArrayList<Subforum>();
		savedSubjects = new ArrayList<Subject>();
		savedComments = new ArrayList<Comment>();
		messages = new ArrayList<Message>();
		savedMessages = new ArrayList<Message>();
		registrationDate = new Date().toString();
		
		likeSubj = new ArrayList<>();
		likeComm = new ArrayList<>();
		hateComm = new ArrayList<>();
		hateSubj = new ArrayList<>();
	}
	
	public ArrayList<Subject> getLikeSubj() {
		return likeSubj;
	}
	
	public void setLikeSubj(ArrayList<Subject> likeSubj) {
		this.likeSubj = likeSubj;
	}

	public ArrayList<Subject> getHateSubj() {
		return hateSubj;
	}

	public void setHateSubj(ArrayList<Subject> hateSubj) {
		this.hateSubj = hateSubj;
	}

	public ArrayList<Comment> getLikeComm() {
		return likeComm;
	}

	public void setLikeComm(ArrayList<Comment> likeComm) {
		this.likeComm = likeComm;
	}

	public ArrayList<Comment> getHateComm() {
		return hateComm;
	}

	public void setHateComm(ArrayList<Comment> hateComm) {
		this.hateComm = hateComm;
	}

	public ArrayList<Message> getMessages() {
		return messages;
	}

	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		if(type.equals("Administrator") || type.equals("Moderator") || type.equals("Regular")){
			this.type=type;
		}
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public ArrayList<Subforum> getFollowedForums() {
		return followedForums;
	}
	public void setFollowedForums(ArrayList<Subforum> followedForums) {
		this.followedForums = followedForums;
	}
	public ArrayList<Subject> getSavedSubjects() {
		return savedSubjects;
	}
	public void setSavedSubjects(ArrayList<Subject> savedSubjects) {
		this.savedSubjects = savedSubjects;
	}
	public ArrayList<Comment> getSavedComments() {
		return savedComments;
	}
	public void setSavedComments(ArrayList<Comment> savedComments) {
		this.savedComments = savedComments;
	}
	
	public ArrayList<Message> getSavedMessages() {
		return savedMessages;
	}

	public void setSavedMessages(ArrayList<Message> savedMessages) {
		this.savedMessages = savedMessages;
	}

}
