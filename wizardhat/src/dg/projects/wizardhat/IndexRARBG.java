package dg.projects.wizardhat;

import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.text.ParseException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class IndexRARBG implements WizardIndex {
	private String indexName = "RARBG";
	private String indexDescription = "Check for RSS updates";
	private int refreshDelay;
	private URL rssUrl;
	private ArrayList<Media> media = new ArrayList<Media>();
	
	public IndexRARBG(URL rssUrl, int refreshDelay) {
		this.rssUrl = rssUrl;
		this.refreshDelay = refreshDelay;
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
	
	private Media itemNodeToMedia(Node item) {
		if ((item.getNodeType() != Node.ELEMENT_NODE) || !(item.getNodeName().equals("item")))
			return null;
		MediaType type = MediaType.MAGNET;
		String title = ((Element) item).getElementsByTagName("title").item(0).getFirstChild().getNodeValue();
		String linkEncoded = ((Element) item).getElementsByTagName("link").item(0).getFirstChild().getNodeValue();
		String link = null;
		try {
			link = URLDecoder.decode(linkEncoded, "ASCII");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
    	}
		String dateStr = ((Element) item).getElementsByTagName("pubDate").item(0).getFirstChild().getNodeValue();
		Date date = null;
		try {
			//Tue, 23 Jan 2018 10:05:05 +0100
			date = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss Z").parse(dateStr);
		} catch(ParseException e) {
			e.printStackTrace();
		}
		if ((title == null) || (link == null) || (date == null))
			return null;
		else
			return new Media(type, title, link, date);
	}
	
	public void update() {
		InputStream is = null;
		HttpURLConnection urlConn = null;
		media = new ArrayList<Media>();
		try {
			//urlConn = (HttpURLConnection) rssUrl.openConnection();
		    //urlConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
		    //urlConn.setReadTimeout(30000);
		    //urlConn.setConnectTimeout(30000);
			//is = urlConn.getInputStream();
		    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder builder = factory.newDocumentBuilder();
		    //Document document = builder.parse(is);
		    Document document = builder.parse(new File("C:\\Users\\Lyalya\\Desktop\\test.xml"));
		    NodeList nodeList = document.getDocumentElement().getElementsByTagName("item");
		    for (int i = 0; i < nodeList.getLength(); i++) {
		    	Media item = itemNodeToMedia(nodeList.item(i));
		    	if (item == null)
		    		continue;
		    	media.add(itemNodeToMedia(nodeList.item(i)));
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
			if (urlConn != null)
				urlConn.disconnect();
		}
	}
	
	public ArrayList<Media> getMedia() {
		return this.media;
	}
}
