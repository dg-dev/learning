package dg.projects.wizardhat;

import java.util.ArrayList;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class IndexRARBG implements WizardIndex {
	private String indexName = "RARBG";
	private String indexDescription = "Check for RSS updates";
	private int refreshDelay;
	private URL rssUrl;
	private ArrayList<Media> media;
	
	public IndexRARBG(URL rssUrl) throws MalformedURLException {
		this(rssUrl, 900);
	}
	
	public IndexRARBG(URL rssUrl, int refreshDelay) throws MalformedURLException {
		this.media = new ArrayList<Media>();
		this.refreshDelay = refreshDelay;
		this.rssUrl = rssUrl;
	}
	
	public int getRefreshDelay() {
		return this.refreshDelay;
	}
	
	public URL getUrl() {
		return this.rssUrl;
	}
	
	public String getIndexName() {
		return this.indexName;
	}
	
	public String getIndexDescription() {
		return this.indexDescription;
	}
	
	private String urlToString(URL link) {
		BufferedReader is = null;
		String buffer = null;
		StringBuilder data = new StringBuilder();
		try {
		    URLConnection url = rssUrl.openConnection();
		    url.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
			url.connect();
		    is = new BufferedReader(new InputStreamReader(url.getInputStream()));
			while ((buffer = is.readLine()) != null) {
				data.append(buffer);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try { 
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return data.toString();
	}
	
	public void update() {
		String data = urlToString(rssUrl);
	}
	
	public ArrayList<Media> getMedia() {
		return this.media;
	}
}
