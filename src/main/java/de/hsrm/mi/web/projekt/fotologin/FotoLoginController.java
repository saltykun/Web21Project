package de.hsrm.mi.web.projekt.fotologin;

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
@SessionAttributes({"loggedinusername"})
public class FotoLoginController{
    Logger logger = LoggerFactory.getLogger(FotoLoginController.class);

    @ModelAttribute("loggedinuserename")
        public void intiLoggesinusername(Model m){
        m.addAttribute("loggedinuserename", "");
    }

    @GetMapping("/fotologin")
    public String login_get(Model m){
        m.addAttribute("loggedinusername", "");
        return "foto/login";
    }
    
    @PostMapping("/fotologin")
    public String login_post(Model m, 
    @RequestParam String username,
    @RequestParam String password
    ){
        String benutzername;
        if(username != ""){
        int lenght = username.length();
        String rightpassword = username + lenght;

        if(password.equals(rightpassword)){
            benutzername = username;
            m.addAttribute("loggedinusername", benutzername);
            return "redirect:/foto";
        }else{
            benutzername ="";
            String ausgabe = "Für "+username+" sollte das Passwort "+rightpassword+" sein.";
            m.addAttribute("loggedinusername", "");
            m.addAttribute("Fehlermeldung", ausgabe);
        }
        }
        return"foto/login";
    }
    


}