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
@SessionAttributes({"loggedinusername"})
public class LoginController{
    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @ModelAttribute("loggedinuserename")
        public void intiLoggesinusername(Model m){
        m.addAttribute("loggedinuserename", "");
    }

    @GetMapping("/login")
    public String login_get(Model m){
        m.addAttribute("loggedinusername", "");
        return "login";
    }
    
    @PostMapping("/login")
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
            return "redirect:/sichtung/meine";
        }else{
            benutzername ="";
            String ausgabe = "Für "+username+" sollte das Passwort "+rightpassword+" sein.";
            m.addAttribute("loggedinusername", "");
            m.addAttribute("Fehlermeldung", ausgabe);
        }
        }
        return"login";
    }
    


}