package com.training.springstart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "cards")
@AllArgsConstructor
@Getter
@Setter
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String number;

    private String cvv;

    private String expirationDate;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="owner_id", nullable=false)
    private Client owner;

    public Card() {}

}
