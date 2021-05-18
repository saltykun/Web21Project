package de.hsrm.mi.web.projekt.foto;

//import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;



public interface FotoService {
    public Foto fotoAbspeichern(Foto foto);
    public Optional<Foto> fotoAbfragenNachId(Long id);
    public List<Foto> alleFotosNachZeitstempelSortiert(); 
    //public List<Foto> alleFotosNachZeitstempelSortiert(LocalDateTime time); 
    public void loescheFoto(Long id);
}
