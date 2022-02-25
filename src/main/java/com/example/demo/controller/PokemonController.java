package com.example.demo.controller;

import com.example.demo.entity.PokemonEntity;
import com.example.demo.entity.ReturnPokemon;
import com.example.demo.entity.SimplePokemonEntity;
import com.example.demo.repository.PokemonRepository;
import com.example.demo.repository.SimplePokemonRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://pokedex-seuha516.netlify.app/, http://localhost:3000/")
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class PokemonController {

    private final PokemonRepository pokemonRepository;
    private final SimplePokemonRepository simplePokemonRepository;

    @GetMapping("/list")
    public List<SimplePokemonEntity> listPokemon(@RequestParam(value = "sort1", defaultValue = "num_nat") String sort1,
                                                 @RequestParam(value = "sort2", defaultValue = "ASC") String sort2,
                                                 @RequestParam(value = "type1", defaultValue = "") String type1,
                                                 @RequestParam(value = "type2", defaultValue = "") String type2,
                                                 @RequestParam(value = "minHeight", defaultValue = "0.0") String minHeight,
                                                 @RequestParam(value = "maxHeight", defaultValue = "9999.9") String maxHeight,
                                                 @RequestParam(value = "minWeight", defaultValue = "0.0") String minWeight,
                                                 @RequestParam(value = "maxWeight", defaultValue = "9999.9") String maxWeight,
                                                 @RequestParam(value = "minH", defaultValue = "0") int minH,
                                                 @RequestParam(value = "maxH", defaultValue = "999") int maxH,
                                                 @RequestParam(value = "minA", defaultValue = "0") int minA,
                                                 @RequestParam(value = "maxA", defaultValue = "999") int maxA,
                                                 @RequestParam(value = "minB", defaultValue = "0") int minB,
                                                 @RequestParam(value = "maxB", defaultValue = "999") int maxB,
                                                 @RequestParam(value = "minC", defaultValue = "0") int minC,
                                                 @RequestParam(value = "maxC", defaultValue = "999") int maxC,
                                                 @RequestParam(value = "minD", defaultValue = "0") int minD,
                                                 @RequestParam(value = "maxD", defaultValue = "999") int maxD,
                                                 @RequestParam(value = "minS", defaultValue = "0") int minS,
                                                 @RequestParam(value = "maxS", defaultValue = "999") int maxS,
                                                 @RequestParam(value = "minTotalStat", defaultValue = "0") int minTotalStat,
                                                 @RequestParam(value = "maxTotalStat", defaultValue = "999") int maxTotalStat) {

        if (type1.equals("")) {
            return simplePokemonRepository.listPokemon0(minHeight, maxHeight, minWeight, maxWeight,
                    minH, maxH, minA, maxA, minB, maxB, minC, maxC, minD, maxD, minS, maxS, minTotalStat, maxTotalStat,
                    PageRequest.of(0,1000,sort2.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, sort1));
        }else if (type2.equals("")) {
            return simplePokemonRepository.listPokemon1(minHeight, maxHeight, minWeight, maxWeight,
                    minH, maxH, minA, maxA, minB, maxB, minC, maxC, minD, maxD, minS, maxS, minTotalStat, maxTotalStat, type1,
                    PageRequest.of(0,1000,sort2.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, sort1));
        }else{
            return simplePokemonRepository.listPokemon2(minHeight, maxHeight, minWeight, maxWeight,
                    minH, maxH, minA, maxA, minB, maxB, minC, maxC, minD, maxD, minS, maxS, minTotalStat, maxTotalStat, type1, type2,
                    PageRequest.of(0,1000,sort2.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, sort1));
        }

    }

    @GetMapping("read/{num_nat}")
    public ReturnPokemon readPokemon(@PathVariable int num_nat) throws ParseException {
        List<PokemonEntity> result = pokemonRepository.readPokemonRepo(num_nat);
        if (result.isEmpty()) {
            return null;
        }else {
            return new ReturnPokemon(result.get(0));
        }
    }

}