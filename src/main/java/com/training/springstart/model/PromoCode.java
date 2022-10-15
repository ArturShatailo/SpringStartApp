package com.training.springstart.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "promos")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PromoCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String value;

    private boolean isActive = Boolean.TRUE;
}
