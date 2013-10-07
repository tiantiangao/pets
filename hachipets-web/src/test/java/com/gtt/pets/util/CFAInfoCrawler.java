package com.gtt.pets.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created with IntelliJ IDEA. User: gtt Date: 13-9-29 Time: 下午11:01 To change
 * this template use File | Settings | File Templates.
 */
public class CFAInfoCrawler {

    public static void main(String[] args) throws Exception {
        Document doc = getPage("http://www.cfainc.org/Breeds.aspx");
        Elements cats = doc.select("#dnn_ctr1348_HtmlModule_lblContent table a");
        System.out.println(cats.size());
        for (Element cat : cats) {
            String text = cat.text();
            String link = "http://www.cfainc.org" + cat.attr("href");
            String sql = "";
            sql = "insert into Pets_Baike_CFA_List(name, enName, cfalink, petId) ";
            sql += "value('" + text + "','" + text + "','" + link + "',0);";
            System.out.println(sql);
        }
    }

    private static Document getPage(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (Exception e) {
            return getPage(url);
        }
    }

}
