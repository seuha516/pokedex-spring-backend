package com.example.demo.controller;

import com.example.demo.entity.PokemonEntity;
import com.example.demo.entity.ReturnPokemon;
import com.example.demo.entity.SimplePokemonEntity;
import com.example.demo.repository.PokemonRepository;
import com.example.demo.repository.SimplePokemonRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = {"https://pokedex-seuha516.netlify.app/", "http://localhost:3000/"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class PokemonController {

    private final PokemonRepository pokemonRepository;
    private final SimplePokemonRepository simplePokemonRepository;

    @GetMapping("/list")
    public List<SimplePokemonEntity> listPokemon(@RequestParam(value = "firstSort1", defaultValue = "num_nat") String firstSort1,
                                                 @RequestParam(value = "firstSort2", defaultValue = "ASC") String firstSort2,
                                                 @RequestParam(value = "secondSort1", defaultValue = "") String secondSort1,
                                                 @RequestParam(value = "secondSort2", defaultValue = "") String secondSort2,

                                                 @RequestParam(value = "type1", defaultValue = "") String type1,
                                                 @RequestParam(value = "type2", defaultValue = "") String type2,

                                                 @RequestParam(value = "minHeight", defaultValue = "-1.0") String minHeight,
                                                 @RequestParam(value = "maxHeight", defaultValue = "9999.9") String maxHeight,
                                                 @RequestParam(value = "minWeight", defaultValue = "-1.0") String minWeight,
                                                 @RequestParam(value = "maxWeight", defaultValue = "9999.9") String maxWeight,

                                                 @RequestParam(value = "minH", defaultValue = "-1") int minH,
                                                 @RequestParam(value = "maxH", defaultValue = "9999") int maxH,
                                                 @RequestParam(value = "minA", defaultValue = "-1") int minA,
                                                 @RequestParam(value = "maxA", defaultValue = "9999") int maxA,
                                                 @RequestParam(value = "minB", defaultValue = "-1") int minB,
                                                 @RequestParam(value = "maxB", defaultValue = "9999") int maxB,
                                                 @RequestParam(value = "minC", defaultValue = "-1") int minC,
                                                 @RequestParam(value = "maxC", defaultValue = "9999") int maxC,
                                                 @RequestParam(value = "minD", defaultValue = "-1") int minD,
                                                 @RequestParam(value = "maxD", defaultValue = "9999") int maxD,
                                                 @RequestParam(value = "minS", defaultValue = "-1") int minS,
                                                 @RequestParam(value = "maxS", defaultValue = "9999") int maxS,
                                                 @RequestParam(value = "minTotal", defaultValue = "-1") int minTotal,
                                                 @RequestParam(value = "maxTotal", defaultValue = "9999") int maxTotal) {

        Pageable pageable;
        if(secondSort1.equals("")) {
            pageable = PageRequest.of(0,1000,firstSort2.equals("ASC") ? Sort.by(firstSort1) : Sort.by(firstSort1).descending());
        }else if(secondSort1.equals("height") || secondSort1.equals("weight")) {
            pageable = PageRequest.of(0,1000,
                    (secondSort2.equals("ASC") ? Sort.by(secondSort1) : Sort.by(secondSort1).descending()).and(
                            firstSort2.equals("ASC") ? Sort.by(firstSort1) : Sort.by(firstSort1).descending()
                        )
                    );
        }else if((secondSort1.equals("male_rate") && secondSort2.equals("ASC")) || (secondSort1.equals("female_rate") && secondSort2.equals("DESC"))) {
            pageable = PageRequest.of(0,1000,
                    (JpaSort.unsafe("IF(male_rate=-1, 999, male_rate)")).and(
                            firstSort2.equals("ASC") ? Sort.by(firstSort1) : Sort.by(firstSort1).descending()
                    )
            );
        }else if((secondSort1.equals("male_rate") && secondSort2.equals("DESC")) || (secondSort1.equals("female_rate") && secondSort2.equals("ASC"))) {
            pageable = PageRequest.of(0,1000,
                    (JpaSort.unsafe("IF(male_rate=-1, 999, 1-male_rate)")).and(
                            firstSort2.equals("ASC") ? Sort.by(firstSort1) : Sort.by(firstSort1).descending()
                    )
            );
        }else {
            pageable = PageRequest.of(0,1000,
                    (JpaSort.unsafe((secondSort2.equals("ASC") ? "1 * " : "-1 * ") + "base_stat -> '$." + secondSort1 + "'")).and(
                            firstSort2.equals("ASC") ? Sort.by(firstSort1) : Sort.by(firstSort1).descending()
                    )
            );
        }

        if (type1.equals("")) {
            return simplePokemonRepository.listPokemon0(minHeight, maxHeight, minWeight, maxWeight,
                    minH, maxH, minA, maxA, minB, maxB, minC, maxC, minD, maxD, minS, maxS, minTotal, maxTotal, pageable);
        }else if (type2.equals("")) {
            return simplePokemonRepository.listPokemon1(minHeight, maxHeight, minWeight, maxWeight,
                    minH, maxH, minA, maxA, minB, maxB, minC, maxC, minD, maxD, minS, maxS, minTotal, maxTotal, type1, pageable);
        }else{
            return simplePokemonRepository.listPokemon2(minHeight, maxHeight, minWeight, maxWeight,
                    minH, maxH, minA, maxA, minB, maxB, minC, maxC, minD, maxD, minS, maxS, minTotal, maxTotal, type1, type2, pageable);
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

    @GetMapping(value="/image", produces= MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody
    byte[] getImage(@RequestParam("value") String value) throws IOException {

        FileInputStream fis = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String fileDir = "./././././images/";
        if(value.substring(0, 4).equals("icon")) {
            fileDir += ("/icons/" + value + ".jpg");
        }else if(value.substring(0, 7).equals("picture")) {
            fileDir += ("/pictures/" + value + ".jpg");
        }

        try{
            fis = new FileInputStream(fileDir);
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }

        int readCount = 0;
        byte[] buffer = new byte[1024];
        byte[] fileArray = null;

        try{
            while((readCount = fis.read(buffer)) != -1){
                baos.write(buffer, 0, readCount);
            }
            fileArray = baos.toByteArray();
            fis.close();
            baos.close();
        } catch(IOException e){
            throw new RuntimeException("File Error");
        }
        return fileArray;
    }

}