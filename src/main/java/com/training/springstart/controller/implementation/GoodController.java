package com.training.springstart.controller.implementation;

import com.training.springstart.model.Client;
import com.training.springstart.model.Good;
import com.training.springstart.service.good.GoodServiceBean;
import com.training.springstart.util.PagingEntity.PagingEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class GoodController {
    private final GoodServiceBean goodServiceBean;

    @PostMapping("/goods")
    @ResponseStatus(HttpStatus.CREATED)
    public Good saveGood(@RequestBody Good good) {
        return goodServiceBean.create(good);
    }

    @GetMapping("/goods")
    @ResponseStatus(HttpStatus.OK)
    public List<Good> getAllGoods() {
        return goodServiceBean.getAll();
    }

    @GetMapping("/goods/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Good getGoodById(@PathVariable("id") Integer id) {
        return goodServiceBean.getById(id);
    }

    @PutMapping("/goods/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Good refreshGood(@PathVariable("id") Integer id, @RequestBody Good good) {
        return goodServiceBean.updateById(id, good);
    }

    @PatchMapping("/goods/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeGoodById(@PathVariable("id") Integer id) {
        goodServiceBean.removeById(id);
    }

    @GetMapping("/get-new-goods")
    @ResponseStatus(HttpStatus.OK)
    public List<Good> getAllRecentlyAddedGoods() {
        return goodServiceBean.getAllRecentlyAddedGoods();
    }

    @GetMapping("/goods/price-range")
    @ResponseStatus(HttpStatus.OK)
    public List<Good> getAllNotDeletedGoodsInPriceRange(@RequestParam(name = "priceFloor") Double priceFloor,
                                                        @RequestParam(defaultValue = "priceCeil") Double priceCeil) {
        return goodServiceBean.getAllGoodsInPriceRange(priceFloor, priceCeil);
    }



    //STREAM API METHODS

    @GetMapping("/get-new-goods-streamAPI")
    @ResponseStatus(HttpStatus.OK)
    public List<Good> getAllRecentlyAddedGoodsStreamAPI() {
        return goodServiceBean.getAllRecentlyAddedGoodsStreamAPI();
    }


    @GetMapping("/goods/price-range-streamAPI")
    @ResponseStatus(HttpStatus.OK)
    public List<Good> getAllNotDeletedGoodsInPriceRangeStreamAPI(@RequestParam(name = "priceFloor") Double priceFloor,
                                                        @RequestParam(defaultValue = "priceCeil") Double priceCeil) {
        return goodServiceBean.getAllGoodsInPriceRangeStreamAPI(priceFloor, priceCeil);
    }


}
