package com.training.springstart.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ClientChangePassDTO {

    private String email;

    private String password;

}
