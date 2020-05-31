package com.endava.drinks;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DrinkService {

    private final DrinkRepository drinkRepository;

    @Cacheable("drinkCache")
    @Transactional(readOnly = true)
    public Page<DrinkDto> findAll(Pageable pageable) {
        log.debug("Finding all drinks from database");
        return drinkRepository.findAll(pageable)
                .map(DrinkDto::from);
    }

    @Cacheable("drinkCache")
    @Transactional(readOnly = true)
    public DrinkDto findById(long id) {
        log.debug("Fetching drink from database");
        return drinkRepository.findById(id)
                .map(DrinkDto::from)
                .orElseThrow(() -> new DrinkNotFoundException("No drink with id " + id + " was found"));
    }

    @CacheEvict(value = "drinkCache", allEntries = true)
    @Transactional
    public DrinkDto create(DrinkDto drink) {
        log.debug("Creating new drink");
        return DrinkDto.from(drinkRepository.save(drink.toDrink()));
    }

    @CacheEvict(value = "drinkCache", allEntries = true)
    @Transactional
    public void deleteById(long id) {
        log.debug("Deleting drink: {}", id);
        drinkRepository.deleteById(id);
    }
}
