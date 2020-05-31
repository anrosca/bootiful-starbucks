package com.endava.drinks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DrinkServiceTest {

    private final DrinkRepository drinkRepository = mock(DrinkRepository.class);

    private DrinkService drinkService;

    @BeforeEach
    void setUp() {
        drinkService = new DrinkService(drinkRepository);
    }

    @Test
    public void shouldThrowDrinkNotFound_WhenDrinkWithGivenIdDoesNotExist() {
        when(drinkRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(DrinkNotFoundException.class, () -> drinkService.findById(1L));
    }
}
