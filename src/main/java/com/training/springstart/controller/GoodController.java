package com.training.springstart.controller;

import com.training.springstart.model.Good;
import com.training.springstart.service.CrudService;
import com.training.springstart.service.good.GoodService;
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

    private final CrudService<Good> crudService;

    private final GoodService goodService;

    @PostMapping("/goods")
    @ResponseStatus(HttpStatus.CREATED)
    public Good saveGood(@RequestBody Good good) {
        return crudService.create(good);
    }

    @GetMapping("/goods")
    @ResponseStatus(HttpStatus.OK)
    public List<Good> getAllGoods() {
        return crudService.getAll();
    }

    @GetMapping("/goods/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Good getGoodById(@PathVariable("id") Integer id) {
        return crudService.getById(id);
    }

    @PutMapping("/goods/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Good refreshGood(@PathVariable("id") Integer id, @RequestBody Good good) {
        return crudService.updateById(id, good);
    }

    @PatchMapping("/goods/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeGoodById(@PathVariable("id") Integer id) {
        crudService.removeById(id);
    }

    @GetMapping("/get-new-goods")
    @ResponseStatus(HttpStatus.OK)
    public List<Good> getAllRecentlyAddedGoods() {
        Date date = new Date();
        long millisecond = date.getTime();
        Long neededTime = millisecond - 86400000L;
        return goodService.getAllRecentlyAddedGoods(neededTime);
    }

}
