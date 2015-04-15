package main.java;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import edu.uci.ics.crawler4j.crawler.Page;

public class boolan implements ISites {

	private final static Pattern SESSION = Pattern
			.compile("http://boolan.com/course/[0-9]+",Pattern.CASE_INSENSITIVE);
	
	public String[] getURLSeed() {
		//http://www.chuanke.com/course/72351163642544128______2.html?page=1
		int pageLen = 1;
		String[] pages = new String[pageLen];
		for(int i=1;i<=pageLen;i++)
		{
			pages[i-1] = "http://boolan.com/course";
		}
		// TODO Auto-generated method stub
		return pages;
	}

	public String outputFileName() {
		// TODO Auto-generated method stub
		return "./boolan.tsv";
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
		Elements el = doc.select("div[id=courseTitle]");//title
		if(el.size()>0)
		{
			infoArray.add(el.get(0).text());
		}
		else
		{
			infoArray.add("no title");
		}
//		
//		
//		
		el = doc.select("div[id=scourse-price]");//price
		if(el.size()>0)
		{
			infoArray.add(el.get(0).text());
		}
		else
		{
			infoArray.add("no price");
		}
//		
		el = doc.select("div[id=teacherInformationName]");//teacher name
		if(el.size()>0)
		{
			infoArray.add(el.get(0).text());
		}
		else
		{
			infoArray.add("no teacher name");
		}
//		
//		
//		el = doc.select("ul[id=schoolInfo] li:eq(0) img");//teacher_rating
//		if(el.size()>0)
//		{
//			infoArray.add(el.get(0).text());
//		}
//		else
		{
			infoArray.add("no teacher rating");
		}
//		
		el = doc.select("div[id=teacherInformationDescription]");//teacher desc
		if(el.size()>0)
		{
			infoArray.add(el.get(0).text());
		}
		else
		{
			infoArray.add("no teacher desc");
		}
//
		el = doc.select("span[class=underline]");//category path
		if(el.size()>0)
		{
			infoArray.add(el.get(0).text());
		}
		else
		{
			infoArray.add("no category path");
		}
//		
		el = doc.select("img[id=scourse-thumb]");//picture
		if(el.size()>0)
		{
			infoArray.add(el.get(0).attr("src"));
		}
		else
		{
			infoArray.add("no picture");
		}
//		

		infoArray.add("No start date");//start date
//		
//		
//		el = doc.select("span[class=mr30]:contains(结束时间) em");//end date
//		if(el.size()>0)
//		{
//			infoArray.add(el.get(0).text());
//		}
//		else
		{
			infoArray.add("no end date");
		}
//		
//		el = doc.select("p[class=tb-record-course-len]");//video length same as course hour
//		if(el.size()>0)
//		{
//			infoArray.add(el.get(0).text());
//		}
//		else
		{
			infoArray.add("no video length");
		}
//		
//		el = doc.select("p[class=tb-record-course-len]");//course hour
//		if(el.size()>0)
//		{
//			infoArray.add(el.get(0).text());
//		}
//		else
		{
			infoArray.add("no course hour");
		}
//		
//
//		el = doc.select("span:contains(担保期) em");//expire date
//		if(el.size()>0)
//		{
//			infoArray.add(el.get(0).text());
//		}
//		else
		{
			infoArray.add("no expire date");
		}
//		
		infoArray.add("no 3rd platform");//no 3rd platform
//		
//		el = doc.select("a[class=store-link]");//school url
//		if(el.size()>0)
//		{
//			infoArray.add(el.get(0).attr("href"));
//		}
//		else
		{
			infoArray.add("no school URL");
		}
//		
//		
//		el = doc.select("div[class=teacher-info] p");//school name the same as teacher name
//		if(el.size()>0)
//		{
//			infoArray.add(el.get(0).text());
//		}
//		else
		{
			infoArray.add("no school name");
		}
//		
//		
//		el = doc.select("div[class=tb-property-cont course-time]");//type
//		if(el.size()>0)
//		{
//			infoArray.add(el.get(0).text());
//		}
//		else
		{
			infoArray.add("no type");
		}
//		
//		el = doc.select("strong[class=J_RateScore]");//rate ajax 后加载
//		if(el.size()>0)
//		{
//			infoArray.add(el.get(0).text());
//		}
//		else
		{
			infoArray.add("no rate");
		}
//		
//		el = doc.select("strong[class=J_CommentCount]");//comments count ajax 后加载
//		if(el.size()>0)
//		{
//			infoArray.add(el.get(0).text());
//		}
//		else
		{
			infoArray.add("no comments_count");
		}
//		
//		el = doc.select("a[class=login-link] strong em");//enrolled_count
//		if(el.size()>0)
//		{
//			infoArray.add(el.get(0).text());
//		}
//		else
		{
			infoArray.add("no enrolled_count");
		}
		//detailContentLeft

		infoArray.add("no desc");//desc ajax by http://www.chuanke.com/?mod=course&act=show&do=brief&sid=1095070&courseid=83606&r=0.9632144784581542

		el = doc.select("div[id=detailContentLeft]");//outline
		if(el.size()>0)
		{
			infoArray.add(el.get(0).text());
		}
		else
		{
			infoArray.add("no outline");
		}
		
		
		infoArray.add(page.getWebURL().getURL());//video URL
		//
		el = doc.select("div[id=courseIntroductionContent]");//intro
		if(el.size()>0)
		{
			infoArray.add(el.get(0).text());
		}
		else
		{
			infoArray.add("no intro");
		}
		
		
		
		
		el = doc.select("div[id=enrollInformationNumber]");//purchased_count
		if(el.size()>0)
		{
			infoArray.add(el.get(0).text());
		}
		else
		{
			infoArray.add("no purchased_count");
		}
		
		
		System.out.println(infoArray);
		String[] result = new String[infoArray.size()];
		for(int i=0;i<infoArray.size();i++)
		{
			result[i] = infoArray.get(i);
		}
				
		return result;
	}

	public int getCrawlDepth() {
		// TODO Auto-generated method stub
		return 1;
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
