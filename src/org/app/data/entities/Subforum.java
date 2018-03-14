package org.app.data.entities;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Subforum extends Entity implements Serializable{

	private static final long serialVersionUID = 1L;
	private String title;
	private String description;
	private String image;
	private String ruleList;
	private String responsibleMod;
	private ArrayList<String> moderators;
	
	public Subforum() {
		moderators = new ArrayList<String>();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getRuleList() {
		return ruleList;
	}

	public void setRuleList(String ruleList) {
		this.ruleList = ruleList;
	}

	public String getResponsibleMod() {
		return responsibleMod;
	}

	public void setResponsibleMod(String responsibleMod) {
		this.responsibleMod = responsibleMod;
	}

	public ArrayList<String> getModerators() {
		return moderators;
	}

	public void setModerators(ArrayList<String> moderators) {
		this.moderators = moderators;
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
}
