package com.training.springstart.service.good;

import com.training.springstart.model.Good;

import java.util.List;

public interface GoodService {

    List<Good> getAllRecentlyAddedGoods(Long date);

}
