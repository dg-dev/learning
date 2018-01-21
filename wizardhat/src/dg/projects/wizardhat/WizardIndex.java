package dg.projects.wizardhat;

import java.util.ArrayList;

public interface WizardIndex {
	public String getIndexName();
	public String getIndexDescription();
	public int getRefreshDelay();
	public void update();
	public ArrayList<Media> getMedia();
}
