package de.hsrm.mi.web.projekt.sichtung.validierung;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME) // zur Laufzeit vorhanden
@Constraint(validatedBy = SiebzehnValidator.class)
@Documented

public @interface Siebzehnhaft {
    String message() default "es muss eine Siebzehn oder 17 enthalten sein";
    // optimale Zusatzinfos 
    Class<? extends Payload>[] payload() default { };
    // Zuordnung zu Validierungsgruppen?
    Class<?>[] groups() default { };
}
