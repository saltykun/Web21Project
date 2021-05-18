package de.hsrm.mi.web.projekt.foto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.projekt.utils.FotoBearbeitungService;

@Service
public class FotoServiceImpl implements FotoService{
   @Autowired FotoBearbeitungService fbservice;
   @Autowired FotoRepository fRepository;

    @Override
    public Foto fotoAbspeichern(Foto foto) {
        fbservice.aktualisiereMetadaten(foto);
        fbservice.orientiereFoto(foto);
        fRepository.save(foto);
        return fRepository.getOne(foto.getId());
    }

    @Override
    public Optional<Foto> fotoAbfragenNachId(Long id) {
    
        return Optional.of(fRepository.getOne(id));
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
    }


}
