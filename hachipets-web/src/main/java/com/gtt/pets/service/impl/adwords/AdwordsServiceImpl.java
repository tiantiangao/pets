package com.gtt.pets.service.impl.adwords;

import com.google.common.base.Preconditions;
import com.gtt.kenshin.cache.CacheKey;
import com.gtt.kenshin.cache.CacheService;
import com.gtt.kenshin.log.KenshinLogger;
import com.gtt.kenshin.log.KenshinLoggerFactory;
import com.gtt.pets.bean.CacheKeyHolder;
import com.gtt.pets.bean.adwords.AdwordsDTO;
import com.gtt.pets.dao.adwords.AdwordsDao;
import com.gtt.pets.entity.adwords.AdwordsEntity;
import com.gtt.pets.service.adwords.AdwordsService;
import com.gtt.pets.util.DTOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: gtt
 * Date: 14-5-10
 * Time: 下午3:58
 * To change this template use File | Settings | File Templates.
 */
@Service
public class AdwordsServiceImpl implements AdwordsService {

    private static KenshinLogger LOGGER = KenshinLoggerFactory.getLogger(AdwordsServiceImpl.class);

    @Autowired
    private AdwordsDao adwordsDao;
    @Autowired
    private CacheService cacheService;

    @Override
    public AdwordsDTO load(int adwordsId, int typeId) {
        try {
            Preconditions.checkArgument(adwordsId > 0);
            Preconditions.checkArgument(typeId > 0);

            // load from cache
            CacheKey cacheKey = new CacheKey(CacheKeyHolder.ADWORDS, adwordsId, typeId);
            AdwordsDTO dto = cacheService.get(cacheKey);
            if (dto != null) {
                return dto;
            }

            // no cache, load from db
            AdwordsEntity adwordsEntity = adwordsDao.load(adwordsId, typeId);
            if (adwordsEntity == null) {
                return null;
            }

            dto = DTOUtils.toDTO(AdwordsDTO.class, adwordsEntity);

            // add cache
            cacheService.add(cacheKey, dto);

            return dto;
        } catch (Exception e) {
            LOGGER.error("load: adwordsId = " + adwordsId + ", typeId = " + typeId, e);
            return null;
        }
    }
}
