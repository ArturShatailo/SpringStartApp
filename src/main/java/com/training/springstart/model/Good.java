package com.training.springstart.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "goods")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String description;

    private Double price;

    private Integer amount;

    private String article;

    private String comment;

    private boolean deleted = Boolean.FALSE;

    private Long added_date_time = new Date().getTime();
}
