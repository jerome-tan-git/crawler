package main.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class BasicCrawlController {
	private static Logger logger = LoggerFactory
			.getLogger(BasicCrawlController.class);
	public static final ISites site = new ibeifeng();
	public static int rowCount = 0;
//	public static final ISites site = new osforceSite();
	public static PrintWriter pw = null;
	
	public static FileWriter fw = null;
	
	public synchronized static void    writeDate(String line)
	{
		pw.println(line);
		pw.flush();
	}
	public static void main(String[] args) throws Exception {


		/*
		 * crawlStorageFolder is a folder where intermediate crawl data is
		 * stored.
		 */
		File oldFile = new File(BasicCrawlController.site.outputFileName());
		if(oldFile.exists())
		{
			oldFile.delete();
		}
		try {
			
			BasicCrawlController.fw = new FileWriter(BasicCrawlController.site.outputFileName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(BasicCrawlController.fw!=null)
		{
			BasicCrawlController.pw = new PrintWriter(fw);
		}
		String crawlStorageFolder = "./tmp";
		
		/*
		 * numberOfCrawlers shows the number of concurrent threads that should
		 * be initiated for crawling.
		 */
		int numberOfCrawlers = BasicCrawlController.site.crawlerCount();

		CrawlConfig config = new CrawlConfig();

		config.setCrawlStorageFolder(crawlStorageFolder);
		config.setUserAgentString("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36");
		/*
		 * Be polite: Make sure that we don't send more than 1 request per
		 * second (1000 milliseconds between requests).
		 */
		config.setPolitenessDelay(1000);

		/*
		 * You can set the maximum crawl depth here. The default value is -1 for
		 * unlimited depth
		 */
		config.setMaxDepthOfCrawling(BasicCrawlController.site.getCrawlDepth());

		/*
		 * You can set the maximum number of pages to crawl. The default value
		 * is -1 for unlimited number of pages
		 */
		// config.setMaxPagesToFetch(1000);

		/**
		 * Do you want crawler4j to crawl also binary data ? example: the
		 * contents of pdf, or the metadata of images etc
		 */
		config.setIncludeBinaryContentInCrawling(false);

		/*
		 * Do you need to set a proxy? If so, you can use:
		 * config.setProxyHost("proxyserver.example.com");
		 * config.setProxyPort(8080);
		 * 
		 * If your proxy also needs authentication:
		 * config.setProxyUsername(username); config.getProxyPassword(password);
		 */

		/*
		 * This config parameter can be used to set your crawl to be resumable
		 * (meaning that you can resume the crawl from a previously
		 * interrupted/crashed crawl). Note: if you enable resuming feature and
		 * want to start a fresh crawl, you need to delete the contents of
		 * rootFolder manually.
		 */
		config.setResumableCrawling(false);
//		config.

		/*
		 * Instantiate the controller for this crawl.
		 */
		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig,
				pageFetcher);
		CrawlController controller = new CrawlController(config, pageFetcher,
				robotstxtServer);

		/*
		 * For each crawl, you need to add some seed urls. These are the first
		 * URLs that are fetched and then the crawler starts following links
		 * which are found in these pages
		 */
		for (String url : site.getURLSeed()) {
			controller.addSeed(url);
		}
		// controller.addSeed("http://www.ics.uci.edu/~lopes/");
		// controller.addSeed("http://www.ics.uci.edu/~welling/");

		/*
		 * Start the crawl. This is a blocking operation, meaning that your code
		 * will reach the line after this only when crawling is finished.
		 */
		controller.start(BasicCrawler.class, numberOfCrawlers);
	}
}
