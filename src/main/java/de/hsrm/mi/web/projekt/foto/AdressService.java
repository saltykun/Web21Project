package de.hsrm.mi.web.projekt.foto;

import java.util.Optional;

public interface AdressService {
    Optional<String> findeAdresse(double geoBreite, double geolaenge);
}
