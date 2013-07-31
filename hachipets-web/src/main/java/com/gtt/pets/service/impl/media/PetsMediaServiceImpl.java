package com.gtt.pets.service.impl.media;

import com.gtt.kenshin.dao.model.PageModel;
import com.gtt.pets.bean.media.PetsMovieDTO;
import com.gtt.pets.dao.movie.PetsMovieDao;
import com.gtt.pets.dao.movie.PetsMovieHotDao;
import com.gtt.pets.dao.movie.PetsMovieRecommendDao;
import com.gtt.pets.entity.movie.PetsMovie;
import com.gtt.pets.service.media.PetsMediaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gtt
 * Date: 13-7-28
 * Time: 下午3:19
 * To change this template use File | Settings | File Templates.
 */
@Service
public class PetsMediaServiceImpl implements PetsMediaService {

    @Autowired
    private PetsMovieDao petsMovieDao;

    @Autowired
    private PetsMovieRecommendDao petsMovieRecommendDao;

    @Autowired
    private PetsMovieHotDao petsMovieHotDao;

    @Override
    public PetsMovieDTO loadByMovieID(int movieId) {
        if (movieId < 1) {
            return null;
        }

        PetsMovie petsMovie = petsMovieDao.loadById(movieId);
        PetsMovieDTO dto = new PetsMovieDTO();
        BeanUtils.copyProperties(petsMovie, dto);
        return dto;
    }

    @Override
    public List<PetsMovieDTO> findByMovieIDList(List<Integer> movieIdList) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PageModel findMovieList(String region, int year, String sortBy, boolean asc, int page, int max) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<PetsMovieDTO> findHotMovieList() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<PetsMovieDTO> findNewMovieList() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
