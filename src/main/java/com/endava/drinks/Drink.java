package com.endava.drinks;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Drink {
    @SequenceGenerator(name = "drink_sequence", sequenceName = "drink_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(generator = "drink_sequence")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "drink_type")
    @Enumerated(EnumType.STRING)
    private DrinkType drinkType;
}
