package com.gtt.pets.service.common;

import com.gtt.kenshin.dao.model.PageModel;
import com.gtt.pets.bean.common.SitemapDTO;

/**
 * @author tiantiangao
 */
public interface SitemapService {

	PageModel<SitemapDTO> pageSitemap(int page, int max);

}
