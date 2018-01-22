package dg.projects.wizardhat;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.io.IOException;
import java.io.InputStream;

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
	
	public void update() {
		InputStream is = null;
		try {
			URLConnection urlConn = rssUrl.openConnection();
		    urlConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
		    is = urlConn.getInputStream();
		    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder builder = factory.newDocumentBuilder();
		    Document document = builder.parse(is);
		    NodeList nodeList = document.getDocumentElement().getChildNodes();
		    for (int i = 0; i < nodeList.getLength(); i++) {
		    	Node node = nodeList.item(i);
			    System.out.println(i + " - " + node.getNodeName() + " (" + node.getNodeType() + ")");
		    }
		} catch (Exception e) {
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
	}
	
	public ArrayList<Media> getMedia() {
		return this.media;
	}
}
