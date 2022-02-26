package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "pokemon")
public class PokemonEntity {

    @Id
    private int num_nat;
    private String name_kor;
    private String name_jap;
    private String name_eng;
    private String base_stat;
    private String types;
    private String classification;
    private String color;
    private double height;
    private double weight;
    private double male_rate;
    private String explanation;

}

