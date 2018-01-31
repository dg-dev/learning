package dg.projects.wizardhat;

import java.util.ArrayList;
import java.util.Properties;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
	private static WizardIndex createIndex(String suffix, Properties config) {
		if ((suffix == null) || (config == null))
			return null;
		String indexName = config.getProperty("indexname" + suffix, "");
		String indexDelayStr = config.getProperty("indexdelay" + suffix, "-1");
		Integer indexDelay = null;
		try {
			indexDelay = Integer.valueOf(indexDelayStr);
		} catch (NumberFormatException e) {
			indexDelay = new Integer("-1");
		}
		String indexLinkStr = config.getProperty("indexlink" + suffix, "");
		URL indexLink = null;
		try {
			indexLink = new URL(indexLinkStr);
		} catch (MalformedURLException e) {
		}
		if ((indexName == "") || (indexDelay.intValue() < 0) || (indexLink == null))
			return null;
		WizardIndex index = null;
		switch (indexName.toLowerCase()) {
			case "rarbg":
				index = new IndexRARBG(indexLink, indexDelay);
				break;
			default:
				break;
		}
		return index;
	}
	
	public static void main(String[] args) {
		ArrayList<WizardIndex> indexes = new ArrayList<WizardIndex>();
		File configPath = new File(System.getProperty("user.home") + File.separator + ".wizardhat" + File.separator + "wizardhat.cfg");
		Properties config = new Properties();
		BufferedInputStream is = null;
		try {
			is = new BufferedInputStream(new FileInputStream(configPath));
			config.load(is);
			for (String key : config.stringPropertyNames()) {
				if (!(key.startsWith("indexname")))
					continue;
				if (key.length() <= "indexname".length())
					continue;
				WizardIndex index = createIndex(key.substring("indexname".length()), config);
				if (index == null)
					continue;
				indexes.add(index);
			}
		} catch (FileNotFoundException e) {
			System.err.println("ERROR: missing " + configPath.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Wizard gandalf = new Wizard(indexes);
		gandalf.start();
	}
}
