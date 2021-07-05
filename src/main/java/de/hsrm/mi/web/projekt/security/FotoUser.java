package de.hsrm.mi.web.projekt.security;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class FotoUser {
    @Id
    private String username;
    private String password;
    private String role;

    public FotoUser(String username, String password, String role){
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public String getPassword() {
        return password;
    }
    public String getRole() {
        return role;
    }
    public String getUsername() {
        return username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public void setUsername(String username) {
        this.username = username;
    }


}
