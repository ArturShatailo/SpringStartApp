package com.training.springstart.util.PagingEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PagingEntity {

    private Integer page;
    private Integer size;
    private String sort;

}
