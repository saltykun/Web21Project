package de.hsrm.mi.web.projekt.foto;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AdressServiceImpl implements AdressService{
    RestTemplate restTemplate = new RestTemplate();

    @Override
    public Optional<String> findeAdresse(double geoBreite, double geolaenge) {
        String fooResourceUrl = "https://nominatim.openstreetmap.org/reverse?lat="+geoBreite+"&lon="+geolaenge+"&format=json";
        Adresse adresse = restTemplate.getForObject(fooResourceUrl, Adresse.class);
        if(adresse.getDisplay_name() != null){
            return Optional.of(adresse.getDisplay_name());
        }else{
            return Optional.empty();
        }
    }
}
