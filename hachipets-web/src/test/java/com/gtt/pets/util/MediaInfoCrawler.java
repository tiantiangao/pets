package com.gtt.pets.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gtt
 * Date: 13-9-29
 * Time: 下午11:01
 * To change this template use File | Settings | File Templates.
 */
public class MediaInfoCrawler {

    public static void main(String[] args) throws Exception {
        String id = "1294998";
        crawlerMovieInfo(id);
    }

    private static void crawlerMovieInfo(String id) {
        try {
            Movie movie = new Movie();
            movie.id = id;
            String url = "http://movie.douban.com/subject/" + id;
            Document doc = Jsoup.connect(url).get();
            Elements content = doc.select("#content");

            // get title
            Elements title = content.select("h1 span[property=v:itemreviewed]");
            movie.title = title.text();

            Elements infos = content.select(".article .indent .subjectwrap .subject #info");

            // get director
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

        } catch (Exception e) {
            System.err.println("failed to get movie info: " + id);
            e.printStackTrace();
        }
    }

    private static void handleInfo(Movie movie, String temp) {
        System.out.println(temp);
    }

    static class Movie {
        protected String id;
        protected String title;
    }

}
