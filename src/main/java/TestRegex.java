package main.java;

import java.util.regex.Pattern;

public class TestRegex {
	public static void main(String[] args)
	{
		Pattern SESSION = Pattern
				.compile("http://www.chuanke.com/[\\d]+\\-[\\d]+\\.html");
		System.out.println(SESSION.matcher("http://www.chuanke.com/2915731-133074.html").matches());
	}
}
