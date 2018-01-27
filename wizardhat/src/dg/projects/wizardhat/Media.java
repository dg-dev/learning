package dg.projects.wizardhat;

import java.util.Date;

public class Media {
	private MediaType type;
	private String title;
	private String link;
	private Date date;
	
	public Media(MediaType type, String title, String link, Date date) {
		this.type = type;
		this.title = title;
		this.link = link;
		this.date = date;
	}
	
	public MediaType getType() {
		return type;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getLink() {
		return link;
	}
	
	public Date getDate() {
		return date;
	}
	
	public String toString() {
		return this.title + " (" + this.link + ")";
	}
}
