package dg.projects.wizardhat;

import java.util.ArrayList;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
	public static void main(String[] args) {
		ArrayList<WizardIndex> indexes = new ArrayList<WizardIndex>();
		try {
			indexes.add(new IndexRARBG(new URL("https://www.reddit.com/r/news/.rss"), 600));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		Wizard gandalf = new Wizard(indexes);
		gandalf.start();
	}
}
