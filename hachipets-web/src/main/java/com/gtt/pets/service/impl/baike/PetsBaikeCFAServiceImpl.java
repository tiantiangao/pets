package com.gtt.pets.service.impl.baike;

import com.gtt.kenshin.cache.CacheKey;
import com.gtt.kenshin.cache.CacheService;
import com.gtt.kenshin.log.KenshinLogger;
import com.gtt.kenshin.log.KenshinLoggerFactory;
import com.gtt.pets.bean.CacheKeyHolder;
import com.gtt.pets.bean.baike.PetsCFACatDTO;
import com.gtt.pets.dao.baike.PetsCFACatDao;
import com.gtt.pets.entity.baike.PetsCFACat;
import com.gtt.pets.service.baike.PetsBaikeCFAService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gtt
 * Date: 13-10-7
 * Time: 下午4:05
 * To change this template use File | Settings | File Templates.
 */
@Service
public class PetsBaikeCFAServiceImpl implements PetsBaikeCFAService {

    private static final KenshinLogger LOGGER = KenshinLoggerFactory.getLogger(PetsBaikeCFAServiceImpl.class);

    @Autowired
    private PetsCFACatDao petsCFACatDao;
    @Autowired
    private CacheService cacheService;

    @Override
    public List<PetsCFACatDTO> findAll() {
        try {
            // load from cache
            CacheKey cacheKey = new CacheKey(CacheKeyHolder.BAIKE_CFA_CAT_LIST);
            List<PetsCFACatDTO> dtoList = cacheService.get(cacheKey);
            if (dtoList != null) {
                return dtoList;
            }

            // no cache, load from db
            List<PetsCFACat> catList = petsCFACatDao.findAll();
            if (CollectionUtils.isEmpty(catList)) {
                dtoList = new ArrayList<PetsCFACatDTO>();
            } else {
                dtoList = toCatDTOList(catList);
            }

            // add cache
            cacheService.add(cacheKey, dtoList);

            return dtoList;
        } catch (Exception e) {
            LOGGER.error("find all cfa cat failed", e);
            return new ArrayList<PetsCFACatDTO>();
        }
    }

    private List<PetsCFACatDTO> toCatDTOList(List<PetsCFACat> catList) {
        List<PetsCFACatDTO> result = new ArrayList<PetsCFACatDTO>();
        for (PetsCFACat cat : catList) {
            PetsCFACatDTO dto = new PetsCFACatDTO();
            BeanUtils.copyProperties(cat, dto);
            result.add(dto);
        }
        return result;
    }
}
