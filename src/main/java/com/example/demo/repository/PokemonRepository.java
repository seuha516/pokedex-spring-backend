package com.example.demo.repository;

import com.example.demo.entity.PokemonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonRepository extends JpaRepository<PokemonEntity, Integer> {

    @Query(value = "select * from pokemon where num_nat = :num_nat", nativeQuery=true)
    List<PokemonEntity> readPokemonRepo(@Param("num_nat") int num_nat);

}

