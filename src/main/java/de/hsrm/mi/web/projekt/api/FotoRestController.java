package de.hsrm.mi.web.projekt.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import de.hsrm.mi.web.projekt.foto.Foto;
//import de.hsrm.mi.web.projekt.foto.FotoRepository;
import de.hsrm.mi.web.projekt.foto.FotoService;
import de.hsrm.mi.web.projekt.foto.Kommentar;
import javaxt.json.JSONException;
//import javaxt.json.JSONObject;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/foto")
public class FotoRestController {
    
    private static final Logger logger = LoggerFactory.getLogger(FotoRestController.class);

    @Autowired
    FotoService fService;

    @GetMapping(value="/api/foto",produces=MediaType.APPLICATION_JSON_VALUE) 
    public String getAllFotos()throws JSONException {
        logger.info("hallo---------------------------");
        //return fService.alleFotosNachZeitstempelSortiert();
        return "hallo";
    }

    @DeleteMapping("/api/foto/{id}")
    public void delFotoByID(@PathVariable long id){
        fService.loescheFoto(id);
    }

    @GetMapping(value="/api/foto/{id}/kommentar", produces=MediaType.APPLICATION_JSON_VALUE)
    public List<Kommentar> getAllKommentsFromFotowithID(@PathVariable long id){
        
        return fService.alleKommentareFuerFoto(id);
    }

    @DeleteMapping("/api/foto/{id}/kommentar/{kid}")
    public void delKommentarFromFotoIDwithKID(@PathVariable long id,
    @PathVariable long kid){
        fService.fotoKommentarLoeschen(id, kid);
    }

}
