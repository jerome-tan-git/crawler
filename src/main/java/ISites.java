package main.java;

import org.jsoup.nodes.Document;

import edu.uci.ics.crawler4j.crawler.Page;

public interface ISites {
	public String[] getURLSeed();
	public String outputFileName();
	public boolean shouldVisit(String href);
	public String[] getFields(Document doc, Page page);
	public int getCrawlDepth();
	public boolean isParseURL(String url);
	public int crawlerCount();
}
