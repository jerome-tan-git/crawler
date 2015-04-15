package main.java;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import edu.uci.ics.crawler4j.crawler.Page;

public class taobao implements ISites {

	private final static Pattern SESSION = Pattern
			.compile("http://www.chuanke.com/[\\d]+\\-[\\d]+\\.html");
	
	public String[] getURLSeed() {
		//http://www.chuanke.com/course/72351163642544128______2.html?page=1
		String[] pages = new String[374];
		for(int i=1;i<=374;i++)
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
		Elements el = doc.select("h1[class=mb15]");//title
		if(el.size()>0)
		{
			infoArray.add(el.get(0).text());
		}
		else
		{
			infoArray.add("no title");
		}
		
		
		
		el = doc.select("em[class=pri]");//price
		if(el.size()>0)
		{
			infoArray.add(el.get(0).text());
		}
		else
		{
			infoArray.add("no price");
		}
		
		el = doc.select("header[class=hd]");//teacher name
		if(el.size()>0)
		{
			infoArray.add(el.get(0).text());
		}
		else
		{
			infoArray.add("no teacher name");
		}
		
		
		el = doc.select("ul[id=schoolInfo] li:eq(0) img");//teacher_rating
		if(el.size()>0)
		{
			infoArray.add(el.get(0).text());
		}
		else
		{
			infoArray.add("no teacher rating");
		}
		
		el = doc.select("ul[id=schoolInfo]");//teacher desc
		if(el.size()>0)
		{
			infoArray.add(el.get(0).text());
		}
		else
		{
			infoArray.add("no teacher desc");
		}

		infoArray.add("No category path");//category path
		
		el = doc.select("div[class=pic] img");//picture
		if(el.size()>0)
		{
			infoArray.add(el.get(0).attr("src"));
		}
		else
		{
			infoArray.add("no picture");
		}
		
		infoArray.add("No video length");//video length
		infoArray.add("No start date");//start date
		
		
		el = doc.select("span[class=mr30]:contains(结束时间) em");//end date
		if(el.size()>0)
		{
			infoArray.add(el.get(0).text());
		}
		else
		{
			infoArray.add("no end date");
		}
		
		infoArray.add("No video length");//video length
		
		el = doc.select("span[class=mr30]:contains(总课时) em");//course hour
		if(el.size()>0)
		{
			infoArray.add(el.get(0).text());
		}
		else
		{
			infoArray.add("no course hour");
		}
		

		el = doc.select("span:contains(担保期) em");//course hour
		if(el.size()>0)
		{
			infoArray.add(el.get(0).text());
		}
		else
		{
			infoArray.add("no expire date");
		}
		
		infoArray.add("no 3rd platform");//no 3rd platform
		
		el = doc.select("header[class=hd] dd a");//course hour
		if(el.size()>0)
		{
			infoArray.add(el.get(0).attr("href"));
		}
		else
		{
			infoArray.add("no school URL");
		}
		
		
		el = doc.select("header[class=hd]");//school name the same as teacher name
		if(el.size()>0)
		{
			infoArray.add(el.get(0).text());
		}
		else
		{
			infoArray.add("no school name");
		}
		
		
		infoArray.add("no type");//type
		
		el = doc.select("span:contains(学生满意度) em");//rate
		if(el.size()>0)
		{
			infoArray.add(el.get(0).text());
		}
		else
		{
			infoArray.add("no rate");
		}
		
		el = doc.select("span:contains(课程评价数) em");//rate
		if(el.size()>0)
		{
			infoArray.add(el.get(0).text());
		}
		else
		{
			infoArray.add("no comments_count");
		}
		
		el = doc.select("span:contains(已经购买人数) em");//rate
		if(el.size()>0)
		{
			infoArray.add(el.get(0).text());
		}
		else
		{
			infoArray.add("no enrolled_count");
		}
		

		infoArray.add("no desc");//desc ajax by http://www.chuanke.com/?mod=course&act=show&do=brief&sid=1095070&courseid=83606&r=0.9632144784581542
		infoArray.add("no outline");//outline ajax by http://www.chuanke.com/?mod=course&act=show&do=brief&sid=1041791&courseid=90938&r=0.9406228070297693
		infoArray.add(page.getWebURL().getURL());//video URL
		infoArray.add("instruction");//instruction
		
		
		
		el = doc.select("span:contains(已经购买人数) em");//purchased_count
		if(el.size()>0)
		{
			infoArray.add(el.get(0).text());
		}
		else
		{
			infoArray.add("no purchased_count");
		}
		
		

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

	public int crawlerCount() {
		// TODO Auto-generated method stub
		return 10;
	}



}
