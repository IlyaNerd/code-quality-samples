package com.itechart.error.handling.mvc.validation;

import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameNotStartsWithIValidator implements ConstraintValidator<NameNotStartsWithI, String> {
   public void initialize(NameNotStartsWithI constraint) {
   }

   public boolean isValid(String obj, ConstraintValidatorContext context) {
      if (obj == null) {
         return true;
      }
      boolean valid = !obj.toLowerCase().startsWith("i");
      if (!valid) {
         HibernateConstraintValidatorContext validatorContext = context.unwrap(HibernateConstraintValidatorContext.class);
         validatorContext.addMessageParameter("name", obj);
      }
      return valid;
   }
}
