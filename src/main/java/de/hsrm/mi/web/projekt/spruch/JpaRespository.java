package de.hsrm.mi.web.projekt.spruch;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface JpaRespository extends JpaRepository<Spruch, Long>{
   
    List<Spruch> findByText(String text);
}
