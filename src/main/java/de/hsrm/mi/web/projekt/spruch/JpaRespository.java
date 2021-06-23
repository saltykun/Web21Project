package de.hsrm.mi.web.projekt.spruch;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRespository extends JpaRepository<Spruch, Long>{
   
    List<Spruch> findByText(String text);
}
