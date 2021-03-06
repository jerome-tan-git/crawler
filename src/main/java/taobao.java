package main.java;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import edu.uci.ics.crawler4j.crawler.Page;

public class taobao implements ISites {

	private final static Pattern SESSION = Pattern
			.compile("http://i\\.xue\\.taobao\\.com/detail\\.htm\\?courseid=[0-9]+",Pattern.CASE_INSENSITIVE);
	
	public String[] getURLSeed() {
		//http://www.chuanke.com/course/72351163642544128______2.html?page=1
		String[] pages = new String[243];
		for(int i=1;i<=243;i++)
		{
			pages[i-1] = "http://i.xue.taobao.com/list.htm?page="+i+"&firstCat=52302004";
		}
		// TODO Auto-generated method stub
		return pages;
	}

	public String outputFileName() {
		// TODO Auto-generated method stub
		return "./taobao.tsv";
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
		Elements el = doc.select("div[class=tb-title]");//title
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
		el = doc.select("strong[class=tb-rmb-num]");//price
		if(el.size()>0)
		{
			infoArray.add(el.get(0).text());
		}
		else
		{
			infoArray.add("no price");
		}
//		
		el = doc.select("div[class=teacher-info] p");//teacher name
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
		el = doc.select("ul[id=schoolInfo] li:eq(0) img");//teacher_rating
		if(el.size()>0)
		{
			infoArray.add(el.get(0).text());
		}
		else
		{
			infoArray.add("no teacher rating");
		}
//		
		el = doc.select("ul[id=schoolInfo]");//teacher desc
		if(el.size()>0)
		{
			infoArray.add(el.get(0).text());
		}
		else
		{
			infoArray.add("no teacher desc");
		}
//
		el = doc.select("p[id=breadcrumbs]");//category path
		if(el.size()>0)
		{
			infoArray.add(el.get(0).text());
		}
		else
		{
			infoArray.add("no category path");
		}
//		
		el = doc.select("div[class=slides-container] img");//picture
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
		el = doc.select("span[class=mr30]:contains(结束时间) em");//end date
		if(el.size()>0)
		{
			infoArray.add(el.get(0).text());
		}
		else
		{
			infoArray.add("no end date");
		}
//		
		el = doc.select("p[class=tb-record-course-len]");//video length same as course hour
		if(el.size()>0)
		{
			infoArray.add(el.get(0).text());
		}
		else
		{
			infoArray.add("no video length");
		}
//		
		el = doc.select("p[class=tb-record-course-len]");//course hour
		if(el.size()>0)
		{
			infoArray.add(el.get(0).text());
		}
		else
		{
			infoArray.add("no course hour");
		}
//		
//
		el = doc.select("span:contains(担保期) em");//course hour
		if(el.size()>0)
		{
			infoArray.add(el.get(0).text());
		}
		else
		{
			infoArray.add("no expire date");
		}
//		
		infoArray.add("no 3rd platform");//no 3rd platform
//		
		el = doc.select("a[class=store-link]");//school url
		if(el.size()>0)
		{
			infoArray.add(el.get(0).attr("href"));
		}
		else
		{
			infoArray.add("no school URL");
		}
//		
//		
		el = doc.select("div[class=teacher-info] p");//school name the same as teacher name
		if(el.size()>0)
		{
			infoArray.add(el.get(0).text());
		}
		else
		{
			infoArray.add("no school name");
		}
//		
//		
		el = doc.select("div[class=tb-property-cont course-time]");//type
		if(el.size()>0)
		{
			infoArray.add(el.get(0).text());
		}
		else
		{
			infoArray.add("no type");
		}
//		
		el = doc.select("strong[class=J_RateScore]");//rate ajax 后加载
		if(el.size()>0)
		{
			infoArray.add(el.get(0).text());
		}
		else
		{
			infoArray.add("no rate");
		}
//		
		el = doc.select("strong[class=J_CommentCount]");//comments count ajax 后加载
		if(el.size()>0)
		{
			infoArray.add(el.get(0).text());
		}
		else
		{
			infoArray.add("no comments_count");
		}
//		
		el = doc.select("a[class=login-link] strong em");//enrolled_count
		if(el.size()>0)
		{
			infoArray.add(el.get(0).text());
		}
		else
		{
			infoArray.add("no enrolled_count");
		}
		

		infoArray.add("no desc");//desc ajax by http://www.chuanke.com/?mod=course&act=show&do=brief&sid=1095070&courseid=83606&r=0.9632144784581542
		infoArray.add("no outline");//outline ajax by http://i.xue.taobao.com/asynCourseResourse.do?courseId=20631&pageSize=20&pageNum=0&_ksTS=1429064673817_189&callback=jsonp190
		infoArray.add(page.getWebURL().getURL());//video URL
		infoArray.add("instruction");//instruction
		
		
		
		el = doc.select("a[class=login-link] strong em");//purchased_count same as enrolled_count
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
