package com.gtt.pets.util;

import com.google.common.collect.Maps;
import com.gtt.kenshin.cache.CacheKey;
import com.gtt.kenshin.cache.CacheService;
import com.gtt.kenshin.web.util.StaticDecoratorImpl;
import com.gtt.kenshin.web.util.StaticFile;
import com.gtt.pets.bean.CacheKeyHolder;
import com.gtt.pets.dao.common.StaticFileDao;
import com.gtt.pets.entity.common.StaticFileEntity;
import com.gtt.pets.service.GlobalService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @author tiantiangao
 */
public class StaticDecoratorPetsImpl extends StaticDecoratorImpl {

	/**
	 * 句号
	 */
	private static final String COMMA = ".";
	/**
	 * 图片类型后缀
	 */
	private static final String ImageExtension =
			".bmp.jpg.png.tiff.gif.pcx.tga.exif.fpx.svg.psd.cdr.pcd.dxf.ufo.eps.ai.raw.ico.";

	private static final String HTML_SCRIPT_TEMPLATE = "<script src=\"%s\"></script>";

	private static final String HTML_CSS_TEMPLATE = "<link rel=\"stylesheet\" href=\"%s\" type=\"text/css\"/>";

	private static final String HTML_IMG_TEMPLATE = "<img src=\"%s\" />";

	@Autowired
	private GlobalService globalService;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private StaticFileDao staticFileDao;

	@Override
	protected String fillFullPath(String resource) {
		return super.fillFullPath(fillMD5(resource));
	}

	private String fillMD5(String url) {
		String md5Enable = globalService.get("StaticFileMD5Enable");
		if (!md5Enable.equals("true")) {
			return url;
		}

		Map<String, String> staticFileMap = getStaticFileMap();
		if (!staticFileMap.containsKey(url.toLowerCase())) {
			return url;
		}

		String fileMD5 = staticFileMap.get(url.toLowerCase());

		if (StringUtils.isBlank(fileMD5)) {
			return url;
		}

		return addFileMD5ToUrl(url, fileMD5);
	}

	private String addFileMD5ToUrl(String url, String fileMD5) {
		String[] urlSplit = url.split("\\.");
		if (urlSplit != null && urlSplit.length > 0) {
			int length = urlSplit.length;
			urlSplit[length - 1] = fileMD5 + "." + urlSplit[length - 1];
			return StringUtils.join(urlSplit, ".");
		}

		return url.toLowerCase();
	}

	@Override
	protected String getStaticServer(String resource) {
		if (isJs(resource)) {
			return globalService.get("staticServerJs");
		} else if (isCss(resource)) {
			return globalService.get("staticServerCss");
		} else if (isImg(resource)) {
			return globalService.get("staticServerImg");
		} else {
			return globalService.get("staticServer");
		}
	}

	@Override
	protected String decorate(String fullPathResource) {
		if (isJs(fullPathResource)) {
			return String.format(HTML_SCRIPT_TEMPLATE, fullPathResource);
		} else if (isCss(fullPathResource)) {
			return String.format(HTML_CSS_TEMPLATE, fullPathResource);
		} else if (isImg(fullPathResource)) {
			return String.format(HTML_IMG_TEMPLATE, fullPathResource);
		}
		return fullPathResource;
	}

	private boolean isJs(String resource) {
		return isFileType(resource, ".js");
	}

	private boolean isCss(String resource) {
		return isFileType(resource, ".css");
	}

	private boolean isImg(String resource) {
		String extension = StringUtils.substringAfterLast(resource, COMMA);
		if (StringUtils.isBlank(extension)) {
			return false;
		}
		return ImageExtension.contains(COMMA + extension + COMMA);
	}

	private boolean isFileType(String resource, String suffix) {
		return StringUtils.isNotBlank(resource) && resource.endsWith(suffix);
	}

	private Map<String, String> getStaticFileMap() {
		CacheKey cacheKeyMD5 = new CacheKey(CacheKeyHolder.STATIC_FILE);
		Map<String, String> staticFileMap = cacheService.get(cacheKeyMD5);
		if (staticFileMap == null) {
			synchronized (StaticFile.class) {
				staticFileMap = cacheService.get(cacheKeyMD5);
				if (staticFileMap == null) {
					staticFileMap = Maps.newHashMap();
					List<StaticFileEntity> staticFileList = staticFileDao.findStaticFileMD5();
					if (!CollectionUtils.isEmpty(staticFileList)) {
						for (StaticFileEntity staticFile : staticFileList) {
							staticFileMap.put(staticFile.getUrl().toLowerCase(), staticFile.getMd5());
						}
					}
					cacheService.add(cacheKeyMD5, staticFileMap);
				}
			}
		}
		return staticFileMap;
	}

}
