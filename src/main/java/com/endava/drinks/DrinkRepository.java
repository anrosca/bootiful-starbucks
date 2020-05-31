package com.endava.drinks;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DrinkRepository extends PagingAndSortingRepository<Drink, Long> {

    @Query("select d from Drink d where d.name = :name")
    List<Drink> findAllByName(@Param("name") String name);
}
