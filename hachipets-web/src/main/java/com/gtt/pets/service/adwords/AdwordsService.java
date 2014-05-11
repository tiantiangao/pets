package com.gtt.pets.service.adwords;

import com.gtt.pets.bean.adwords.AdwordsDTO;

/**
 * Created with IntelliJ IDEA.
 * User: gtt
 * Date: 14-5-10
 * Time: 下午3:34
 * To change this template use File | Settings | File Templates.
 */
public interface AdwordsService {

    AdwordsDTO load(int adwordsId, int typeId);

}
