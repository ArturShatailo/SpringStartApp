package com.training.springstart.service.good;

import com.training.springstart.model.Good;
import com.training.springstart.repository.GoodRepository;
import com.training.springstart.service.CrudService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@org.springframework.stereotype.Service
public class GoodServiceBean implements CrudService<Good>, GoodService {

    private final GoodRepository goodRepository;

    @Override
    public Good create(Good good) {
        return goodRepository.save(good);
    }

    @Override
    public List<Good> getAll() {
        return goodRepository.findAll();
    }

    @Override
    public Good getById(Integer id) {
        Good good = goodRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Good not found with id = " + id));
        //.orElseThrow(ResourceNotFoundException::new);
        if (good.isDeleted()) {
            throw new EntityNotFoundException("Good was deleted with id = " + id);
        }
        return good;
    }

    @Override
    public Good updateById(Integer id, Good good) {
        return goodRepository.findById(id).map(
                entity -> {
                    entity.setTitle(good.getTitle());
                    entity.setDescription(good.getDescription());
                    entity.setPrice(good.getPrice());
                    entity.setArticle(good.getArticle());
                    return goodRepository.save(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Good not found with id = " + id));
    }

    @Override
    public void removeById(Integer id) {
        Good good = goodRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Good not found with id = " + id));
        good.setDeleted(true);
        goodRepository.save(good);
    }

    @Override
    public List<Good> getAllRecentlyAddedGoods() {
        Long date = getMonthPeriod();
        List<Good> goods = goodRepository.findRecentlyAddedGoods(date);
        return goods;
    }

    private Long getMonthPeriod() {
        Date date = new Date();
        long millisecond = date.getTime();
        return millisecond - 86400000L;
    }

    public List<Good> getAllGoodsInPriceRange(Double priceFloor, Double priceCeil) {
        return goodRepository.findGoodsInPriceRange(priceFloor, priceCeil);
    }




    //STREAM API METHODS
    public List<Good> getAllRecentlyAddedGoodsStreamAPI() {
        Long date = getMonthPeriod();
        List<Good> goods = getAll();
        return goods.stream()
                .filter(g -> !g.isDeleted())
                .filter(g -> g.getAdded_date_time() >= date)
                .collect(Collectors.toList());
    }

    public List<Good> getAllGoodsInPriceRangeStreamAPI(Double priceFloor, Double priceCeil) {
        List<Good> goods = getAll();
        return goods.stream()
                .filter(g -> !g.isDeleted())
                .filter(g -> g.getPrice() >= priceFloor)
                .filter(g -> g.getPrice() <= priceCeil)
                .collect(Collectors.toList());
    }



}
