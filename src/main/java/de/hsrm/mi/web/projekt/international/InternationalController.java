package de.hsrm.mi.web.projekt.international;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class InternationalController {
    Logger logger = LoggerFactory.getLogger(InternationalController.class);


    @GetMapping("/international")
    public String getInternational(Locale locale, Model m) { 
        logger.info("Hallo");
        m.addAttribute("sprache", locale.getDisplayLanguage());
        return "international/international"; 

    }

}
