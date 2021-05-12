package de.hsrm.mi.web.projekt.sichtung.validierung;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class SiebzehnValidator implements ConstraintValidator<Siebzehnhaft, String>{
   
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null) return false;

        if(value.toLowerCase().contains("17") || value.toLowerCase().contains("siebzehn"))return true;

        return false;
    }

}