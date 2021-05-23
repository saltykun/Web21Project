package de.hsrm.mi.web.projekt.foto;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Kommentar {
    @Id
    @GeneratedValue
    private long id;
    private long version;
    
    
    private String autor;
    @NotEmpty
    private String text;
    
    private LocalDateTime zeitpunkt;

    public Kommentar(){
        
    }

    public Kommentar(String autor, String text){
        this.autor = autor;
        this.text = text;
        zeitpunkt = LocalDateTime.now();
    }

    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }
    public long getVersion() {
        return version;
    }

    public String getAutor() {
        return autor;
    }
    public String getText() {
        return text;
    }
    public LocalDateTime getZeitpunkt() {
        return zeitpunkt;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public void setText(String text) {
        this.text = text;
    }
    public void setZeitpunkt(LocalDateTime zeitpunkt) {
        this.zeitpunkt = zeitpunkt;
    }

}
