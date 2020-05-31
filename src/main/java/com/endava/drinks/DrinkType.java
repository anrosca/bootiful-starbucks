package com.endava.drinks;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum DrinkType {
    HOT_COFFEES("Hot Coffees", true),
    HOT_TEAS("Hot teas", true),
    HOT_DRINKS("Hot drinks", true),
    COLD_COFFEES("Cold coffees", false),
    ICED_TEAS("Iced teas", false);

    private final String prettyName;

    private final boolean isHot;
}
