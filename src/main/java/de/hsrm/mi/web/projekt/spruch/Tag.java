package de.hsrm.mi.web.projekt.spruch;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tag {
    
    @Id
    @GeneratedValue
    private long id;

    private long version;

    private String name;

    public Tag(){
        name = "";
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public long getVersion() {
        return version;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setVersion(long version) {
        this.version = version;
    }
    public void setName(String name) {
        this.name = name;
    }


}
