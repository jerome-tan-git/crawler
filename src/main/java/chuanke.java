package main.java;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;

import edu.uci.ics.crawler4j.crawler.Page;

public class chuanke implements ISites {

	private final static Pattern SESSION = Pattern
			.compile("http://www.chuanke.com/[\\d]+\\-[\\d]+\\.html");
	
	public String[] getURLSeed() {
		//http://www.chuanke.com/course/72351163642544128______2.html?page=1
		String[] pages = new String[1];
		for(int i=1;i<=1;i++)
		{
			pages[i-1] = "http://www.chuanke.com/course/72351163642544128______2.html?page=" + i;
		}
		// TODO Auto-generated method stub
		return pages;
	}

	public String outputFileName() {
		// TODO Auto-generated method stub
		return "./chuanke.csv";
	}
	
	public boolean shouldVisit(String href) {
		// TODO Auto-generated method stub
		return SESSION.matcher(href).matches();
	}

	public String[] getFields(Document doc, Page page) {
		// TODO Auto-generated method stub
		System.out.println(page.getWebURL().getURL() + " | " + page.getWebURL().getParentUrl());
//		String[] fields = new String[4];
		ArrayList<String> infoArray = new ArrayList<String>();
		infoArray.add(doc.select("h1[class=mb15]").get(0).text());//title
		infoArray.add(doc.select("em[class=pri]").get(0).text());//price
		infoArray.add(doc.select("header[class=hd]").get(0).text());//teacher name
		infoArray.add(doc.select("ul[id=schoolInfo] li:eq(0) img").get(0).outerHtml());//teacher_rating
		
//		fields[0] = doc.select("h1[class=mb15]").get(0).text();//title
//		fields[1] = doc.select("em[class=pri]").get(0).text();//price
//		fields[2] = doc.select("header[class=hd]").get(0).text();//teacher name
//		fields[3] = doc.select("div[class=title]").get(0).text();//teacher_rating
//		fields[4] = doc.select("div[class=about]").get(0).text();//teacher desc
//		fields[6] = "No category path";
//		fields[7] = doc.select("div[class=col-sm-5 picture] img").get(0).attr("src");// picture
//		fields[8] = "No video length";
//		fields[9] = "No start date";
//		fields[10] = "No end date";
//		fields[11] = "No expire date";
//		fields[12] = "no 3rd platform";
//		fields[13] = "no teacher rating";
//		fields[14] = "school URL";
//		fields[15] = "school Name";
//		fields[16] = "type";
//		fields[17] = doc.select("span[class^=stars-]").get(0).attr("class");//rate
//		fields[18] = doc.select("span[class=stat-item] span").get(2).text();//comments_count
//		fields[19] = doc.select("span[class=member-num]").get(0).text();//enrolled_count
//		if(doc.select("div[class=panel panel-default sub-articles]:contains(¿Î³Ì±³¾°)").size()==0)
//		{
//			fields[20] = "no desc";//desc
//		}
//		else
//		{
//			fields[20] = doc.select("div[class=panel panel-default sub-articles]:contains(¿Î³Ì±³¾°)").get(0).text()+"";//desc
//		}
//		if(doc.select("div[class=panel panel-default sub-articles]:contains(¿Î³Ì´ó¸Ù)").size()==0)
//		{
//			fields[21] ="no outline";//outline
//		}
//		else
//		{
//			fields[21] = doc.select("div[class=panel panel-default sub-articles]:contains(¿Î³Ì´ó¸Ù)").get(0).text()+"";//outline
//		}
//		fields[22] = page.getWebURL().getURL();//course_video
//		fields[23] = "instruction";
//		fields[24] = "collected_count";
//		fields[25] = "purchased_count";
		String[] result = new String[infoArray.size()];
		for(int i=0;i<infoArray.size();i++)
		{
			result[i] = infoArray.get(i);
		}
				
		return result;
	}

	public int getCrawlDepth() {
		// TODO Auto-generated method stub
		return 2;
	}

	public boolean isParseURL(String url) {
		// TODO Auto-generated method stub
		return SESSION.matcher(url).matches();
	}



}
