package com.example.demo.repository;

import com.example.demo.entity.SimplePokemonEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SimplePokemonRepository extends JpaRepository<SimplePokemonEntity, Integer> {

    @Query(value = "SELECT num_nat, name_kor, name_jap, name_eng FROM pokemon WHERE height >= ?1 AND height <= ?2 AND weight >= ?3 AND weight <= ?4 AND "
            + "JSON_EXTRACT(base_stat, '$.h') >= ?5 AND JSON_EXTRACT(base_stat, '$.h') <= ?6 AND "
            + "JSON_EXTRACT(base_stat, '$.a') >= ?7 AND JSON_EXTRACT(base_stat, '$.a') <= ?8 AND "
            + "JSON_EXTRACT(base_stat, '$.b') >= ?9 AND JSON_EXTRACT(base_stat, '$.b') <= ?10 AND "
            + "JSON_EXTRACT(base_stat, '$.c') >= ?11 AND JSON_EXTRACT(base_stat, '$.c') <= ?12 AND "
            + "JSON_EXTRACT(base_stat, '$.d') >= ?13 AND JSON_EXTRACT(base_stat, '$.d') <= ?14 AND "
            + "JSON_EXTRACT(base_stat, '$.s') >= ?15 AND JSON_EXTRACT(base_stat, '$.s') <= ?16 AND "
            + "JSON_EXTRACT(base_stat, '$.total') >= ?17 AND JSON_EXTRACT(base_stat, '$.total') <= ?18",
            nativeQuery = true)
    List<SimplePokemonEntity> listPokemon0(String minHeight, String maxHeight, String minWeight, String maxWeight,
                                          int minH, int maxH, int minA, int maxA, int minB, int maxB, int minC, int maxC, int minD, int maxD, int minS, int maxS,
                                          int minTotalStat, int maxTotalStat,
                                          Pageable p);

    @Query(value = "SELECT num_nat, name_kor, name_jap, name_eng FROM pokemon WHERE height >= ?1 AND height <= ?2 AND weight >= ?3 AND weight <= ?4 AND "
            + "JSON_EXTRACT(base_stat, '$.h') >= ?5 AND JSON_EXTRACT(base_stat, '$.h') <= ?6 AND "
            + "JSON_EXTRACT(base_stat, '$.a') >= ?7 AND JSON_EXTRACT(base_stat, '$.a') <= ?8 AND "
            + "JSON_EXTRACT(base_stat, '$.b') >= ?9 AND JSON_EXTRACT(base_stat, '$.b') <= ?10 AND "
            + "JSON_EXTRACT(base_stat, '$.c') >= ?11 AND JSON_EXTRACT(base_stat, '$.c') <= ?12 AND "
            + "JSON_EXTRACT(base_stat, '$.d') >= ?13 AND JSON_EXTRACT(base_stat, '$.d') <= ?14 AND "
            + "JSON_EXTRACT(base_stat, '$.s') >= ?15 AND JSON_EXTRACT(base_stat, '$.s') <= ?16 AND "
            + "JSON_EXTRACT(base_stat, '$.total') >= ?17 AND JSON_EXTRACT(base_stat, '$.total') <= ?18 AND "
            + "(JSON_EXTRACT(types, '$.type1') = ?19 OR JSON_EXTRACT(types, '$.type2') = ?19)",
            nativeQuery = true)
    List<SimplePokemonEntity> listPokemon1(String minHeight, String maxHeight, String minWeight, String maxWeight,
                                           int minH, int maxH, int minA, int maxA, int minB, int maxB, int minC, int maxC, int minD, int maxD, int minS, int maxS,
                                           int minTotalStat, int maxTotalStat, String type1,
                                           Pageable p);

    @Query(value = "SELECT num_nat, name_kor, name_jap, name_eng FROM pokemon WHERE height >= ?1 AND height <= ?2 AND weight >= ?3 AND weight <= ?4 AND "
            + "JSON_EXTRACT(base_stat, '$.h') >= ?5 AND JSON_EXTRACT(base_stat, '$.h') <= ?6 AND "
            + "JSON_EXTRACT(base_stat, '$.a') >= ?7 AND JSON_EXTRACT(base_stat, '$.a') <= ?8 AND "
            + "JSON_EXTRACT(base_stat, '$.b') >= ?9 AND JSON_EXTRACT(base_stat, '$.b') <= ?10 AND "
            + "JSON_EXTRACT(base_stat, '$.c') >= ?11 AND JSON_EXTRACT(base_stat, '$.c') <= ?12 AND "
            + "JSON_EXTRACT(base_stat, '$.d') >= ?13 AND JSON_EXTRACT(base_stat, '$.d') <= ?14 AND "
            + "JSON_EXTRACT(base_stat, '$.s') >= ?15 AND JSON_EXTRACT(base_stat, '$.s') <= ?16 AND "
            + "JSON_EXTRACT(base_stat, '$.total') >= ?17 AND JSON_EXTRACT(base_stat, '$.total') <= ?18 AND "
            + "((JSON_EXTRACT(types, '$.type1') = ?19 AND JSON_EXTRACT(types, '$.type2') = ?20) OR (JSON_EXTRACT(types, '$.type1') = ?20 AND JSON_EXTRACT(types, '$.type2') = ?19))",
            nativeQuery = true)
    List<SimplePokemonEntity> listPokemon2(String minHeight, String maxHeight, String minWeight, String maxWeight,
                                           int minH, int maxH, int minA, int maxA, int minB, int maxB, int minC, int maxC, int minD, int maxD, int minS, int maxS,
                                           int minTotalStat, int maxTotalStat, String type1, String type2,
                                           Pageable p);

}

