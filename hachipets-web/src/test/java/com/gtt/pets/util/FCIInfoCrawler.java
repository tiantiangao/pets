package com.gtt.pets.util;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created with IntelliJ IDEA. User: gtt Date: 13-9-29 Time: 下午11:01 To change
 * this template use File | Settings | File Templates.
 */
public class FCIInfoCrawler {

    public static void main(String[] args) throws Exception {
        Document doc = getPage("http://baike.goumin.com/%E7%8A%AC%E7%A7%8D%E7%BE%A4");
        Elements dogs = doc.select("#bodyContent a");
        boolean start = false;
        int sectionId = 1;
        for (Element dog : dogs) {
            String name = dog.attr("name");
            if (StringUtils.isNotBlank(name) && name.contains("_Group_1_")) {
                start = true;
            }

            if (!start) {
                continue;
            }
            String title = dog.attr("title");
            String href = dog.attr("href");
            if (StringUtils.isNotBlank(title) && title.contains("Category:犬种概述")) {
                start = false;
                continue;
            }
            if (StringUtils.isNotBlank(title) && StringUtils.isNotBlank(href) && title.startsWith("犬种群")) {
                crawDetail(sectionId, href, title);
                sectionId++;
            }
        }
    }

    private static void crawDetail(int sectionId, String href, String title) {
//        System.out.println(sectionId + " >> " + title + " >> http://baike.goumin.com" + href);
        String link = "http://baike.goumin.com" + href;
        Document page = getPage(link);
        Elements table = page.select(".prettytable");
        Elements trs = table.select("tr");
        for (int index = 0; index < trs.size(); index++) {
            if (index == 0) {
                continue;
            }
            Element tr = trs.get(index);

            Elements tds = tr.select("td");
            String name = tds.get(0).text();
            String enName = tds.get(1).text();
            String origin = tds.get(2).text();
            String fciNo = tds.get(3).text();
            String pic = "http://baike.goumin.com" + tds.get(4).select("img").attr("src");
//            System.out.println(sectionId + " -- " + name + " -- " + enName + " -- " + origin + " -- " + fciNo + " -- " + pic);

            String sql = "";
            sql = "insert into Pets_Baike_FCI_Section_List(SectionID, PetID, Name, EnName, Origin, FCINo, PicUrl) ";
            sql += "value(" + sectionId + ",1,'" + name + "',\"" + enName + "\",'" + origin + "','" + fciNo + "','" + pic + "');";
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
