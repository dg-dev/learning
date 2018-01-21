package dg.projects.wizardhat;

public class Media {
	private MediaType type;
	private String title;
	private String link;
	
	public Media(MediaType type, String title, String link) {
		this.type = type;
		this.title = title;
		this.link = link;
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
	
	public String toString() {
		return this.title;
	}
}
