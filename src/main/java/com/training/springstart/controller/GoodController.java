package com.training.springstart.controller;

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
        Date date = new Date();
        long millisecond = date.getTime();
        Long neededTime = millisecond - 86400000L;
        return goodServiceBean.getAllRecentlyAddedGoods(neededTime);
    }



}
