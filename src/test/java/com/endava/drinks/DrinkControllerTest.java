package com.endava.drinks;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class DrinkControllerTest {

    @MockBean
    private DrinkService drinkService;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void findById_WhenDrinkWasNotFound_ReturnsErrorResponse() throws Exception {
        when(drinkService.findById(1L)).thenThrow(new DrinkNotFoundException("Drink with id 1 was not found"));

        mockMvc.perform(MockMvcRequestBuilders.get("/drinks/{id}", "1"))
                .andExpect(status().is(404))
                .andExpect(jsonPath("error").value("Drink not found"))
                .andExpect(jsonPath("message").value("Drink with id 1 was not found"));
    }

    @Test
    public void create_WhenSuccessful_ReturnsLocationHeader() throws Exception {
        Drink drink = Drink.builder().id(2L).name("Tucano Cappuccino").drinkType(DrinkType.HOT_COFFEES).build();
        DrinkDto drinkDto = DrinkDto.from(drink);
        when(drinkService.create(any(DrinkDto.class))).thenReturn(drinkDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/drinks")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(drinkDto)))
                .andExpect(status().is(201));
    }
}