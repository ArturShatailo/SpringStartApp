package com.training.springstart.controller;

import com.training.springstart.entity.Good;
import com.training.springstart.service.CrudService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class GoodController {

    private final CrudService<Good> crudService;

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
}
