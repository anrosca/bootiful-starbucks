package com.endava.drinks;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidDrinkTypeConstraintValidator implements ConstraintValidator<ValidDrinkType, String> {
   public void initialize(ValidDrinkType constraint) {
   }

   public boolean isValid(String value, ConstraintValidatorContext context) {
      try {
         DrinkType.valueOf(value);
         return true;
      } catch (Exception e) {
         context.buildConstraintViolationWithTemplate("Unexpected drink type: " + value);
         return false;
      }
   }
}
