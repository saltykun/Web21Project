package de.hsrm.mi.web.projekt.spruch;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityReference;


@Entity
public class Spruch {
    @Id
    @GeneratedValue
    private Long Id;

    private Long version;

    private String name;
    
    private String text;
    
    private int anspruch;

    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(cascade = CascadeType.ALL)
    private List<Tag> tags = new ArrayList<>();

    public Spruch(){
        name = "";
        text = "";
        anspruch=0;
    }
    public List<Tag> getTags() {
        return tags;
    }
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
    public void addTag(Tag tag){
        tags.add(tag);
    }

    public Long getId() {
        return Id;
    }
    public Long getVersion() {
        return version;
    }
    public int getAnspruch() {
        return anspruch;
    }
    public String getName() {
        return name;
    }
    public String getText() {
        return text;
    }
    public void setAnspruch(int anspruch) {
        this.anspruch = anspruch;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setText(String text) {
        this.text = text;
    }
    public void setId(Long id) {
        Id = id;
    }
    public void setVersion(Long version) {
        this.version = version;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + anspruch;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((text == null) ? 0 : text.hashCode());
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
        Spruch other = (Spruch) obj;
        if (anspruch != other.anspruch)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (text == null) {
            if (other.text != null)
                return false;
        } else if (!text.equals(other.text))
            return false;
        return true;
    }
    
}
