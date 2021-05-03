package de.hsrm.mi.web.projekt.sichtung;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@SessionAttributes(names = {"meinesichtung"})
public class SichtungController{
    Logger logger = LoggerFactory.getLogger(SichtungController.class);
    

    @ModelAttribute("meinesichtung")
    public void initListe(Model m){
        m.addAttribute("meinesichtung", new ArrayList<Sichtung>());
    }
    
    @GetMapping("/sichtungen/meine")
    public String sichtungMeineListe_post(@ModelAttribute("meinesichtung") ArrayList<Sichtung> list){
        //list.add(new Sichtung("abx", "frankfur", LocalDate.now(), "17"));      
        
        return "sichtungen/meine/liste";
    }

    @PostMapping("/sichtungen/meine")
    public String sichtungMeineListe_post(Model m) {
        
        return "sichtungen/meine/liste";
    }
    
    @GetMapping("/sichtungen/meine/neu")
    public String sichtungMeineNeu_get(Model m){      
        m.addAttribute("meinesichtungform", new Sichtung());
        return "sichtungen/meine/bearbeiten";
    }

    @PostMapping("/sichtungen/meine/neu")
    public String sichtungMeineNeu_post(@ModelAttribute("meinesichtungform")
    Sichtung sichtung,
    @ModelAttribute("meinesichtung") ArrayList<Sichtung> list, 
    Model m) {
        list.add(sichtung);

        return "sichtungen/meine/liste";
    }

    @GetMapping("/sichtungen/meine/{zahl}/del")
    public String del_get(@PathVariable int zahl,
    @ModelAttribute("meinesichtung") ArrayList<Sichtung> list, 
    Model m) {
        logger.info("delete Zahl:"+zahl);
        list.remove(zahl);
        
        return  "sichtungen/meine/liste";
    }    
}