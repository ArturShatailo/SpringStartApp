package com.training.springstart.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "promo_codes")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PromoCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String owner_email;

    private String value;

    private boolean status;
}
