package com.gtt.pets.util;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: gtt Date: 13-9-29 Time: 下午11:01 To change
 * this template use File | Settings | File Templates.
 */
public class MediaInfoCrawler {

	public static void main(String[] args) throws Exception {
		String ids = "1294998 " + ",1305531" + ",2011513" + ",1292907" + ",1298432" + ",1298900" + ",2133429"
				+ ",2983281" + ",3008666" + ",1300787 " + ",1308676 " + ",1473562" + ",1769033" + ",2271607"
				+ ",1298258" + ",1465465" + ",2133466" + ",3898722 " + ",10580637" + ",2297275" + ",19931543"
				+ ",6094945 " + ",1420083 " + ",20561603" + ",6974439" + ",1959195" + ",4030531" + ",4196245"
				+ ",2997094" + ",1946651" + ",1296455" + ",1294754" + ",1298499" + ",1304159" + ",3600774"
				+ ",10600880" + ",3095375" + ",2357302" + ",1302355" + ",2141508 " + ",3431802" + ",3126194 "
				+ ",3134345 " + ",3092010" + ",3092011" + ",4219765" + ",4907956" + ",10833925" + ",1301469"
				+ ",2134285" + ",11610693" + ",1793912 " + ",4206436 " + ",3274505 " + ",20429432" + ",1304554 "
				+ ",1309150 " + ",4031065 " + ",3041483 " + ",3161705 " + ",3126607 " + ",1369747 " + ",3002542 "
				+ ",21331055 " + ",1794547 " + ",1306804 " + ",1304348 " + ",1308776" + ",1760104 " + ",1830941 "
				+ ",1291578 " + ",1437342" + ",2357162" + ",4914468" + ",1428178" + ",3011581" + ",5152678 "
				+ ",1870681 " + ",5948613 " + ",1291573";
		String[] movies = ids.split(",");
		for (String id : movies) {
			crawlerMovieInfo(id.trim());
			Thread.sleep(2000);
		}
	}

	private static void crawlerMovieInfo(String id) {
		try {
			Movie movie = new Movie();
			movie.id = id;
			String url = "http://movie.douban.com/subject/" + id;
			Document doc = getPage(url);
			Elements content = doc.select("#content");

			// get title
			Elements title = content.select("h1 span[property=v:itemreviewed]");
			movie.title = title.text();

			Elements infos = content.select(".article .indent .subjectwrap .subject #info");

			// get info
			Element info = infos.get(0);
			List<TextNode> textNodes = info.textNodes();
			Elements children = info.children();
			String temp = "";
			for (Element e : children) {
				if (!e.tagName().equals("br")) {
					temp += e.text();
					if (temp.contains("制片国家")) {
						Node node = e.nextSibling();
						if (node instanceof TextNode) {
							temp += ((TextNode) node).text();
						}
					}
				} else {
					handleInfo(movie, temp);
					temp = "";
				}
			}

			Elements desc = content.select("#link-report");
			if (desc != null) {
				movie.desc = desc.text();
				if (movie.desc.contains("©豆瓣")) {
					movie.desc.replace("©豆瓣", "");
				}
			}

			// output
			System.out.println(movie.toString());

		} catch (Exception e) {
			System.err.println("failed to get movie info: " + id);
			e.printStackTrace();
		}
	}

	private static Document getPage(String url) {
		try {
			return Jsoup.connect(url).get();
		} catch (Exception e) {
			return getPage(url);
		}
	}

	private static void handleInfo(Movie movie, String temp) {
		if (temp.contains("导演")) {
			movie.director = temp.replace("导演: ", "").trim();
		}
		if (temp.contains("主演")) {
			movie.actor = temp.replace("主演: ", "").trim();
		}
		if (temp.contains("制片国家")) {
			movie.country = temp.replace("制片国家/地区:", "").trim();
		}
		if (temp.contains("上映日期")) {
			movie.showtime = temp.replace("上映日期:", "").trim();
			if (movie.showtime.indexOf("(") != -1) {
				movie.showtime = movie.showtime.substring(0, movie.showtime.indexOf("("));
			}
		}
		if (temp.contains("片长")) {
			movie.length = temp.replace("片长:", "").trim();
			movie.length = movie.length.replace("分钟", "").trim();
		}

	}

	static class Movie {
		public String director;
		public String actor;
		public String country;
		public String showtime;
		public String length;
		public String desc;
		protected String id;
		protected String title;

		@Override
		public String toString() {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (showtime == null) {
				showtime = "aaaaaaaaaaaaaaaaa";
			}
			try {
				sdf.parse(showtime);
			} catch (ParseException e) {
				System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + id + " -> " + showtime);
				if (showtime.length() == 4) {
					showtime += "-01-01";
				} else if (showtime.length() == 7) {
					showtime += "-01";
				} else {
					showtime = "1900-01-01";
				}
			}

			StringBuilder sb = new StringBuilder();
			sb.append("insert into `pets_movie` ");
			sb.append("(`name`, `desc`, `pic`, `director`, `actor`, `region`, `length`, `release`, `year`, `addTime`) ");
			sb.append("values('" + title + "','" + desc + "','','" + director + "','" + actor + "','" + country + "','"
					+ length + "','" + showtime + " 00:00:00','" + getYear() + "',NOW());");
			return sb.toString();
		}

		protected String getYear() {
			if (StringUtils.isNotBlank(showtime)) {
				return showtime.substring(0, showtime.indexOf("-"));
			}
			return "0";
		}
	}

}
