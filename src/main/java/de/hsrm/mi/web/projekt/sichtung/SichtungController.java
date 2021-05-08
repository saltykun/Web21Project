package de.hsrm.mi.web.projekt.sichtung;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@SessionAttributes(names = {"meinesichtungen"})
public class SichtungController{
    Logger logger = LoggerFactory.getLogger(SichtungController.class);
    

    @ModelAttribute("meinesichtungen")
    public void initListe(Model m){
        m.addAttribute("meinesichtungen", new ArrayList<Sichtung>());
    }
    
    @GetMapping("/sichtung/meine")
    public String sichtungMeineListe_post(Model m){
        //list.add(new Sichtung("abx", "frankfur", LocalDate.now(), "17"));      
        
        return "sichtung/meine/liste";
    }
    
    @GetMapping("/sichtung/meine/neu")
    public String sichtungMeineNeu_get(Model m){      
        m.addAttribute("meinesichtungform", new Sichtung());
        return "sichtung/meine/bearbeiten";
    }

    @PostMapping("/sichtung/meine/neu")
    public String sichtungMeineNeu_post(Model m, @ModelAttribute("meinesichtungform")
    Sichtung sichtung,@ModelAttribute("meinesichtungen") List<Sichtung> list) {
        list.add(sichtung);
        m.addAttribute("meinesichtungform", list);
        return "redirect:/sichtung/meine";
    }
    @RequestMapping(params = "cancel", method = RequestMethod.POST)
    public String cancelUpdateUser(HttpServletRequest request) {
    return "redirect:/meine/liste";
}

    @GetMapping("/sichtung/meine/{zahl}/del")
    public String del_get(@PathVariable int zahl,
    @ModelAttribute("meinesichtungen") List<Sichtung> list, 
    Model m) {
        logger.info("delete Zahl:"+zahl);
        list.remove(zahl);
        
        return  "redirect:/sichtung/meine";
    }
    @GetMapping("/sichtung/meine/{zahl}")
    public String edit_get(@PathVariable int zahl,
    @ModelAttribute("meinesichtungen") List<Sichtung> list, 
    Model m) {
        m.addAttribute("meinesichtungform", list.get(zahl));
        list.remove(zahl);
        return "sichtung/meine/bearbeiten";
    }
}