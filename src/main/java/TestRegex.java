package main.java;

import java.util.regex.Pattern;

public class TestRegex {
	public static void main(String[] args)
	{
		Pattern SESSION = Pattern
				.compile("http://www.fenby.com/courses/[\\w\\-]+/");
		System.out.println(SESSION.matcher("http://www.fenby.com/courses/htmlke-cheng/").matches());
	}
}
