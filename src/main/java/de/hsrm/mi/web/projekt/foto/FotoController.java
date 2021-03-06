package de.hsrm.mi.web.projekt.foto;


import java.io.IOException;
//mport java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

@Controller
@SessionAttributes({"loggedinusername"})
public class FotoController {
    public static final String UPLOADDIR = "/tmp";
    Logger logger = LoggerFactory.getLogger(FotoController.class);
    @Autowired 
    FotoService fService;

    @ModelAttribute("fotos")
    public void initFotoListe(Model m){
        m.addAttribute("fotos", new ArrayList<Foto>());
    }
    @ModelAttribute("kommentare")
    public void initKomentarListe(Model m){
        m.addAttribute("kommentare", new ArrayList<Kommentar>());
    }



    @PostMapping("/foto")
    public String fotoPost(Model m, @RequestParam("datei") MultipartFile file, @ModelAttribute("fotos") List<Foto> flist){
        Foto foto = new Foto();
        foto.setDateiname(file.getOriginalFilename());
        try {
            foto.setFotodaten(file.getBytes());
            Paths.get(UPLOADDIR, foto.getDateiname());
        } catch (IOException e) {
            e.printStackTrace();
        }
        foto.setMimetype(file.getContentType());
        if(foto.getFotodaten().length >= 17){
            fService.fotoAbspeichern(foto);
        }
        
        m.addAttribute("fotos", fService.alleFotosNachZeitstempelSortiert());
        return"foto/liste";
    }
    @GetMapping("/foto")
    public String fotoGet(Model m, @ModelAttribute("fotos") List<Foto> flist, Principal prinz
    //@ModelAttribute("loggedinusername") String username
    ){
        String loginname = prinz.getName();
        logger.info("-------Benutzername-----"+ loginname);
        m.addAttribute("benutzername", loginname);
        m.addAttribute("fotos", fService.alleFotosNachZeitstempelSortiert());
        //if(username == null){
         //   return "foto/liste";
       // }
        return"foto/liste";
    }
    @GetMapping("/foto/{id}")
    public ResponseEntity<byte[]>fotoIdGet(Model m, @PathVariable Long id){
        Foto foto = fService.fotoAbfragenNachId(id).get();
        return ResponseEntity.ok()
            .header("Content-Type", foto.getMimetype()) 
            .body(foto.getFotodaten());
        
    }
    @GetMapping("/foto/{id}/del")
    public String fotoDelGet(Model m, @PathVariable Long id){
        fService.loescheFoto(id);
        return"redirect:/foto";
    }  
    
    @GetMapping("/foto/{id}/kommentar")
    public String getfotoIDKommentar(Model m, @PathVariable Long id, 
    @ModelAttribute("foto") Foto foto, @ModelAttribute("kommentare") List<Kommentar> kommentare
    ){
        if(id == null){
            return "foto/liste";
        }
        m.addAttribute("kommentare", fService.fotoAbfragenNachId(id).get().getKommentare());
        m.addAttribute("foto", fService.fotoAbfragenNachId(id).get());
        return"foto/kommentare";
    }  
    @PostMapping("/foto/{id}/kommentar")
    public String postfotoIDKommentar(Model m, @PathVariable Long id, 
    @RequestParam("kommentar") String kommentar, Principal prinz
    //@ModelAttribute("kommentare") List<Kommentar> kommentare
    ){
        Object o = m.getAttribute("loggedinusername");
        String benutzername = prinz.getName();
        if(o!= null){
            String username = o.toString();
            if(!username.equals("") && username != null){
            
                fService.fotoKommentieren(id, username, kommentar);
            
            }else{
                logger.info("Username" + benutzername);
                fService.fotoKommentieren(id, benutzername, kommentar);
            }
            
        }else{
            
            logger.info("Username" + benutzername);
            fService.fotoKommentieren(id, benutzername, kommentar);

        }
        return"redirect:/foto/"+id+"/kommentar";

    }

}
