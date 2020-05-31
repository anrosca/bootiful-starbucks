package com.endava.drinks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrinkPage {
    private List<DrinkDto> drinks;
    private int page;
    private int size;
    private int totalPages;

    public static DrinkPage from(Page<DrinkDto> page) {
        return new DrinkPage(page.getContent(), page.getNumber(), page.getSize(), page.getTotalPages());
    }
}
