package de.hsrm.mi.web.projekt.foto;


import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class FotoController {
    public static final String UPLOADDIR = "/tmp";
    
    @Autowired 
    FotoService fService;

    @ModelAttribute("fotos")
    public void initListe(Model m){
        m.addAttribute("fotos", new ArrayList<Foto>());
    }



    @PostMapping("/foto")
    public String fotoPost(Model m, @RequestParam("datei") MultipartFile file, @ModelAttribute("fotos") List<Foto> flist){
        Foto foto = new Foto();
        foto.setDateiname(file.getOriginalFilename());
        try {
            foto.setFotodaten(file.getBytes());
            Path zielpfad = Paths.get(UPLOADDIR, foto.getDateiname());
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
    public String fotoGet(Model m, @ModelAttribute("fotos") List<Foto> flist){
        m.addAttribute("fotos", fService.alleFotosNachZeitstempelSortiert());
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
    
    
}
