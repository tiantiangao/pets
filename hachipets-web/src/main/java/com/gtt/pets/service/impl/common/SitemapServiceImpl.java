package com.gtt.pets.service.impl.common;

import com.google.common.base.Preconditions;
import com.gtt.kenshin.dao.model.PageModel;
import com.gtt.kenshin.dao.util.PageModelUtil;
import com.gtt.pets.bean.common.SitemapDTO;
import com.gtt.pets.dao.common.SitemapDao;
import com.gtt.pets.entity.common.SitemapEntity;
import com.gtt.pets.service.common.SitemapService;
import com.gtt.pets.util.DTOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tiantiangao
 */
@Service
public class SitemapServiceImpl implements SitemapService {

	@Autowired
	private SitemapDao sitemapDao;

	@Override
	public PageModel<SitemapDTO> pageSitemap(int page, int max) {
		try {
			Preconditions.checkArgument(page > 0);
			Preconditions.checkArgument(max > 0);

			if (page == 0) {
				page = 1;
			}

			PageModel<SitemapEntity> pageModel = sitemapDao.pageSiteMap(page, max);

			PageModel<SitemapDTO> model = PageModelUtil.transfer(pageModel);
			model.setRecords(DTOUtils.toDTOList(SitemapDTO.class, pageModel.getRecords()));
			return model;
		} catch (Exception e) {
			return new PageModel<SitemapDTO>();
		}
	}
}
