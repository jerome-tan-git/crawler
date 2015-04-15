package main.java;

import java.util.regex.Pattern;

import org.jsoup.nodes.Document;

import edu.uci.ics.crawler4j.crawler.Page;

public class osforceSite implements ISites {

	private final static Pattern SESSION = Pattern
			.compile("http://www.osforce.cn/course/[\\d]+");
	
	public String[] getURLSeed() {
		// TODO Auto-generated method stub
		return new String[]{"http://www.osforce.cn/course/explore"};
	}

	public String outputFileName() {
		// TODO Auto-generated method stub
		return "./osforce.csv";
	}
	
	public boolean shouldVisit(String href) {
		// TODO Auto-generated method stub
		return SESSION.matcher(href).matches() || href
				.startsWith("http://www.osforce.cn/course/explore");
	}

	public String[] getFields(Document doc, Page page) {
		// TODO Auto-generated method stub
		System.out.println(page.getWebURL().getURL());
		String[] fields = new String[26];
		fields[0] = doc.select("h1").get(0).text();//title
		fields[1] = doc.select("span[class=money-num]").get(0).text();//price
		if (doc.select("div[class=nickname]").size()==0)
		{
			fields[2] = "no teacher";
		}
		else
		{
			fields[2] = doc.select("div[class=nickname]").get(0).text();//teacher name
		}
		if(doc.select("div[class=title]").size()==0)
		{
			fields[3] = "no teacher_rating";
		}
		else
		{
			fields[3] = doc.select("div[class=title]").get(0).text();//teacher_rating
		}
		if(doc.select("div[class=about]").size() == 0)
		{
			fields[4] = "no teacher desc";
		}
		else
		{
		fields[4] = doc.select("div[class=about]").get(0).text();//teacher desc
		}
		fields[6] = "No category path";
		if(doc.select("div[class=col-sm-5 picture] img").size() == 0)
		{
			fields[7] = "no description";
		}
		else
		{
			fields[7] = doc.select("div[class=col-sm-5 picture] img").get(0).attr("src");// picture
		}
		fields[8] = "No video length";
		fields[9] = "No start date";
		fields[10] = "No end date";
		fields[11] = "No expire date";
		fields[12] = "no 3rd platform";
		fields[13] = "no teacher rating";
		fields[14] = "school URL";
		fields[15] = "school Name";
		fields[16] = "type";
		if(doc.select("span[class^=stars-]").size()==0)
		{
			fields[17] = "no rate";//rate
		}
		else
		{
			fields[17] = doc.select("span[class^=stars-]").get(0).attr("class");//rate
		}
		if (doc.select("span[class=stat-item] span").size()<3)
		{
			fields[18] = "no comments count";
		}
		else
		{
			fields[18] = doc.select("span[class=stat-item] span").get(2).text();//comments_count
		}	
		if (doc.select("span[class=member-num]").size() ==0)
		{
			fields[19] = "no enrolled_count";
		}
		else
		{
			fields[19] = doc.select("span[class=member-num]").get(0).text();//enrolled_count
		}
		if(doc.select("div[class=panel panel-default sub-articles]:contains(¿Î³Ì±³¾°)").size()==0)
		{
			fields[20] = "no desc";//desc
		}
		else
		{
			fields[20] = doc.select("div[class=panel panel-default sub-articles]:contains(¿Î³Ì±³¾°)").get(0).text()+"";//desc
		}
		if(doc.select("div[class=panel panel-default sub-articles]:contains(¿Î³Ì´ó¸Ù)").size()==0)
		{
			fields[21] ="no outline";//outline
		}
		else
		{
			fields[21] = doc.select("div[class=panel panel-default sub-articles]:contains(¿Î³Ì´ó¸Ù)").get(0).text()+"";//outline
		}
		fields[22] = page.getWebURL().getURL();//course_video
		fields[23] = "instruction";
		fields[24] = "collected_count";
		fields[25] = "purchased_count";
		return fields;
	}

	public int getCrawlDepth() {
		// TODO Auto-generated method stub
		return -1;
	}

	public boolean isParseURL(String url) {
		// TODO Auto-generated method stub
		return SESSION.matcher(url).matches();
	}

	public int crawlerCount() {
		// TODO Auto-generated method stub
		return 3;
	}



}
