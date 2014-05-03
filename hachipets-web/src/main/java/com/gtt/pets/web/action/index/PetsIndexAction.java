/**
 * Project: bag
 *
 * File Created at 2013-6-14
 * $Id$
 *
 * Copyright 2010 dianping.com.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Dianping Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with dianping.com.
 */
package com.gtt.pets.web.action.index;

import com.google.common.base.Function;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.gtt.pets.bean.baike.PetsTypeDTO;
import com.gtt.pets.bean.media.PetsMovieDTO;
import com.gtt.pets.constants.ChannelType;
import com.gtt.pets.service.GlobalService;
import com.gtt.pets.service.baike.PetsTypeService;
import com.gtt.pets.service.media.PetsMediaService;
import com.gtt.pets.web.action.BaseAction;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 宠物首页
 *
 * @author tiantiangao
 */

public class PetsIndexAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private PetsTypeService petsTypeService;
	@Autowired
	private PetsMediaService petsMediaService;
	@Autowired
	private GlobalService globalService;

	private List<PetsTypeDTO> baikeList;
	private List<PetsMovieDTO> movieList;

	@Override
	public String doExecute() throws Exception {
		setChannel(ChannelType.CHANNEL_INDEX);

		prepareBaikeList();
		prepareMediaList();

		return SUCCESS;
	}

	private void prepareBaikeList() {
		baikeList = Lists.newArrayList();

		// load from config
		final String indexBaikeList = globalService.get("indexBaikeList");
		if (StringUtils.isBlank(indexBaikeList)) {
			return;
		}

		// split
		Iterable<String> baikeIdStrList = Splitter.on(",").split(indexBaikeList);
		List<Integer> baikeIdList =
				Lists.transform(ImmutableList.copyOf(baikeIdStrList), new Function<String, Integer>() {
					@Override
					public Integer apply(String input) {
						try {
							return Integer.parseInt(input);
						} catch (Exception e) {
							return 0;
						}
					}
				});

		// load detail
		baikeList = petsTypeService.findByTypeIDList(baikeIdList);
	}

	private void prepareMediaList() {

		// load from config
		String indexMovieList = globalService.get("indexMovieList");
		if (StringUtils.isBlank(indexMovieList)) {
			movieList = Lists.newArrayList();
			return;
		}

		// split
		Iterable<String> movieIdStrList = Splitter.on(",").split(indexMovieList);
		List<Integer> movideIdList =
				Lists.transform(ImmutableList.copyOf(movieIdStrList), new Function<String, Integer>() {
					@Override
					public Integer apply(String input) {
						try {
							return Integer.parseInt(input);
						} catch (Exception e) {
							return 0;
						}
					}
				});

		// load Detail
		movieList = petsMediaService.findByMovieIDList(movideIdList);
	}

	public List<PetsTypeDTO> getBaikeList() {
		return baikeList;
	}

	public List<PetsMovieDTO> getMovieList() {
		return movieList;
	}
}
