package com.gtt.pets.web.action.common;

import com.google.common.collect.Lists;
import com.gtt.kenshin.dao.model.PageModel;
import com.gtt.kenshin.oauth.impl.util.CollectionUtils;
import com.gtt.pets.bean.common.SitemapDTO;
import com.gtt.pets.service.common.SitemapService;
import com.gtt.pets.web.action.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author tiantiangao
 */
public class SitemapAction extends BaseAction {

	private static final int PAGE_SIZE = 20;
	@Autowired
	private SitemapService sitemapService;

	private List<SitemapDTO> urlList;

	@Override
	protected String doExecute() throws Exception {
		int page = 1;
		urlList = Lists.newArrayList();
		boolean hasNext;
		do {
			PageModel<SitemapDTO> model = sitemapService.pageSitemap(page, PAGE_SIZE);
			if (!CollectionUtils.isEmpty(model.getRecords())) {
				urlList.addAll(model.getRecords());
			}
			hasNext = model.getPageCount() > page;
			page++;
		} while (hasNext);

		resp.setContentType("application/xml");

		return SUCCESS;
	}

	public static void main(String[] args) {
		int type = 1;
		for (int index = type; index <= 114; index++) {
			System.out.println(
					"INSERT INTO `Pets_Sitemap` (`Url`, `AddTime`, `UpdateTime`) VALUES ( 'http://www.hachipets.com/media/" +
							index + "', NOW(), NOW());"
			);
		}
	}

	public List<SitemapDTO> getUrlList() {
		return urlList;
	}
}
