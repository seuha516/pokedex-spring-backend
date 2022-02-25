package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity
public class SimplePokemonEntity {

    @Id
    private int num_nat;
    private String name_kor;

}
