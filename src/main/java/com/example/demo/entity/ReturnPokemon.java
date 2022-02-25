package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Getter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ReturnPokemon {

    private final int num_nat;
    private final String name_kor;
    private final String name_jap;
    private final String name_eng;
    private final JSONObject base_stat;
    private final JSONObject types;
    private final String classification;
    private final double height;
    private final double weight;
    private final double male_rate;

    public ReturnPokemon(PokemonEntity p) throws ParseException {
        this.num_nat = p.getNum_nat();
        this.name_kor = p.getName_kor();
        this.name_jap = p.getName_jap();
        this.name_eng = p.getName_eng();
        this.classification = p.getClassification();
        this.height = p.getHeight();
        this.weight = p.getWeight();
        this.male_rate = p.getMale_rate();

        JSONParser parser = new JSONParser();
        this.base_stat = (JSONObject) parser.parse(p.getBase_stat());
        this.types = (JSONObject) parser.parse(p.getTypes());
    }

}