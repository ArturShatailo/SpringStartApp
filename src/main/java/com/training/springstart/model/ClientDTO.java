package com.training.springstart.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ClientDTO {

    private String name;

    private String surname;

    private String email;

    private String phone_number;

}
