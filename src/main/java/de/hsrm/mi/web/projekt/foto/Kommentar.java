package de.hsrm.mi.web.projekt.foto;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(
generator = ObjectIdGenerators.PropertyGenerator.class, 
property = "id")
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

    
    /*
    public Kommentar(){
        
    }
    */
    public Kommentar(){
        this.autor = "";
        this.text = "";
        this.zeitpunkt = LocalDateTime.now();
    }

    public Kommentar(String autor, String text){
        this.autor = autor;
        this.text = text;
        zeitpunkt = LocalDateTime.now();
    }

    /*
    public boolean equals(Kommentar kommentar){
        if (kommentar.getId() == this.id){
            return true;
        }
        return false;
    }
*/
   
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Kommentar other = (Kommentar) obj;
        if (id != other.id)
            return false;
        return true;
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
