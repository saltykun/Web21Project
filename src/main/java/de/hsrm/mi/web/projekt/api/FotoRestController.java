package de.hsrm.mi.web.projekt.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.hsrm.mi.web.projekt.foto.Foto;
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
@RequestMapping("/api/foto")
public class FotoRestController {
    
    private static final Logger logger = LoggerFactory.getLogger(FotoRestController.class);

    @Autowired
    FotoService fService;

    @GetMapping(value="",produces=MediaType.APPLICATION_JSON_VALUE) 
    public List<Foto> getAllFotos()throws JSONException {
        logger.info("");
        return fService.alleFotosNachZeitstempelSortiert();
    }

    @DeleteMapping("/{id}")
    public void delFotoByID(@PathVariable long id)throws JSONException{
        fService.loescheFoto(id);
    }

    @GetMapping(value="/{id}/kommentar", produces=MediaType.APPLICATION_JSON_VALUE)
    public List<Kommentar> getAllKommentsFromFotowithID(@PathVariable long id)throws JSONException {
        
        return fService.alleKommentareFuerFoto(id);
    }

    @DeleteMapping("/{id}/kommentar/{kid}")
    public void delKommentarFromFotoIDwithKID(@PathVariable long id,
    @PathVariable long kid)throws JSONException{
        fService.fotoKommentarLoeschen(id, kid);
    }

}
