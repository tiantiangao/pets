package com.gtt.pets.util;

import com.gtt.kenshin.web.util.StaticDecoratorImpl;
import com.gtt.pets.service.GlobalService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

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
}
