package org.app.search.advanced;

public class AdvancedSearchQuery {

	private String forumQuery;
	private boolean forumTitle;
	private boolean forumDescription;
	private boolean forumMod;
	
	private String subjQuery;
	private boolean subjTitle;
	private boolean subjContent;
	private boolean subjAuthor;
	private boolean subjParent;
	
	private String userQuery;
	
	public AdvancedSearchQuery(){}

	public String getForumQuery() {
		return forumQuery;
	}

	public void setForumQuery(String forumQuery) {
		this.forumQuery = forumQuery;
	}

	public boolean isForumTitle() {
		return forumTitle;
	}

	public void setForumTitle(boolean forumTitle) {
		this.forumTitle = forumTitle;
	}

	public boolean isForumDescription() {
		return forumDescription;
	}

	public void setForumDescription(boolean forumDescription) {
		this.forumDescription = forumDescription;
	}

	public boolean isForumMod() {
		return forumMod;
	}

	public void setForumMod(boolean forumMod) {
		this.forumMod = forumMod;
	}

	public String getSubjQuery() {
		return subjQuery;
	}

	public void setSubjQuery(String subjQuery) {
		this.subjQuery = subjQuery;
	}

	public boolean isSubjTitle() {
		return subjTitle;
	}

	public void setSubjTitle(boolean subjTitle) {
		this.subjTitle = subjTitle;
	}

	public boolean isSubjContent() {
		return subjContent;
	}

	public void setSubjContent(boolean subjContent) {
		this.subjContent = subjContent;
	}

	public boolean isSubjAuthor() {
		return subjAuthor;
	}

	public void setSubjAuthor(boolean subjAuthor) {
		this.subjAuthor = subjAuthor;
	}

	public boolean isSubjParent() {
		return subjParent;
	}

	public void setSubjParent(boolean subjParent) {
		this.subjParent = subjParent;
	}

	public String getUserQuery() {
		return userQuery;
	}

	public void setUserQuery(String userQuery) {
		this.userQuery = userQuery;
	}
	
}
