package de.hsrm.mi.web.projekt.foto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

//import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.projekt.messaging.FotoMessage;
import de.hsrm.mi.web.projekt.utils.FotoBearbeitungService;

@Service
public class FotoServiceImpl implements FotoService{
    Logger logger = LoggerFactory.getLogger(FotoServiceImpl.class);

   @Autowired FotoBearbeitungService fbservice;
   @Autowired FotoRepository fRepository;
   @Autowired SimpMessagingTemplate broker;
   
    
    @Override
    public Foto fotoAbspeichern(Foto foto) {
        fbservice.aktualisiereMetadaten(foto);
        fbservice.orientiereFoto(foto);
        foto.setOrt(new AdressServiceImpl().findeAdresse(foto.getGeobreite(), foto.getGeolaenge()).get());
        fRepository.save(foto);
        
        broker.convertAndSend("/topic/foto" , new FotoMessage(FotoMessage.FOTO_GESPEICHERT, foto.getId()));
        return fRepository.getOne(foto.getId());

    }

    @Override
    public Optional<Foto> fotoAbfragenNachId(Long id) {
    
        return Optional.of(fRepository.findById(id).get());
    }

    @Override
    public List<Foto> alleFotosNachZeitstempelSortiert() {

        return fRepository.findAll();
    }
    public List<Foto> alleFotosNachZeitstempelSortiert(LocalDateTime time) {

        //return fRepository.findBylocalDateOrderByTimeAsc(time);
        return fRepository.findAll(Sort.by("zeitstempel"));
    }

    
    @Override
    public void loescheFoto(Long id) {
        fRepository.deleteById(id);
        broker.convertAndSend("/topic/foto", new FotoMessage(FotoMessage.FOTO_GELOESCHT, id));
    }

    @Override
    public void fotoKommentieren(long id, String autor, String kommentar)throws NoSuchElementException {
        Foto foto = fRepository.findById(id).get();
        Kommentar neuesKommentar = new Kommentar(autor,kommentar);
        foto.addKommentar(neuesKommentar);
        fRepository.save(foto);
    }

    @Override
    public List<Kommentar> alleKommentareFuerFoto(long fotoid) throws NoSuchElementException {

        return fRepository.getOne(fotoid).getKommentare();
    }

    @Override
    public void fotoKommentarLoeschen(long fotoid, long kid) throws NoSuchElementException{
        
        Foto foto = fRepository.findById(fotoid).get(); 
    
        if(foto.findKommentarById(kid) == null){
            throw new NoSuchElementException();
        }else{
            foto.removeKommentar(foto.findKommentarById(kid));
        } 

        
        
        
        //fRepository.getOne(fotoid).getKommentare().remove((int)kid);
        
    }


}
