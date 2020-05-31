package com.endava.drinks;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class DrinkDto implements Serializable {
    @Pattern(regexp = "\\d+")
    private String id;

    @NotNull
    private String name;

    @ValidDrinkType(message = "incorrect drinkType value")
    private String drinkType;

    public Drink toDrink() {
        return Drink.builder()
                .name(name)
                .drinkType(DrinkType.valueOf(drinkType))
                .build();
    }

    public static DrinkDto from(Drink drink) {
        return new DrinkDto(drink.getId().toString(), drink.getName(), drink.getDrinkType().name());
    }
}
