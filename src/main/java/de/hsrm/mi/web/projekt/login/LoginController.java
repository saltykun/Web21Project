package de.hsrm.mi.web.projekt.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@SessionAttributes({"loggedinuserename"})
public class LoginController{
    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @ModelAttribute("loggedinuserename")
    public void intiLoggesinusername(Model m){
        m.addAttribute("loggedinuserename", "");
    }

    @GetMapping("/login")
    public String login_get(){
        return "/login";
    }
    
    @PostMapping("/login")
    public String login_post(Model m, 
    @RequestParam String username,
    @RequestParam String passwort,
    @ModelAttribute("loggedinuserename") String benutzername
    ){
        int lenght = username.length();
        String rightpasswort = username+lenght;

        if(passwort.equals(rightpasswort)){
            benutzername = username;
            return "redirect:" + "sichtungen/meine";
        }else{
            benutzername ="";
            String ausgabe = "Für "+username+" sollte das Passwort "+rightpasswort+" sein.";
            m.addAttribute("Fehlermeldung", ausgabe);
        }
       
        return"/login";
    }
    


}