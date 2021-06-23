package de.hsrm.mi.web.projekt.sichtung.validierung;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SiebzehnValidator implements ConstraintValidator<Siebzehnhaft, String>{
  

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // String siebzehn = "{siebzehn.zahl}";
        if(value == null) return false;

        if(value.toLowerCase().contains("17") || value.toLowerCase().contains("siebzehn") || value.toLowerCase().contains("seventeen"))return true;

        return false;
    }

}