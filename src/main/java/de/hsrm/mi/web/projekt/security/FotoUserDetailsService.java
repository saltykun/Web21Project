package de.hsrm.mi.web.projekt.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class FotoUserDetailsService implements UserDetailsService{
    @Autowired(required = true) private FotoUserRespository fUserRespository;
    @Autowired PasswordEncoder encoder;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        FotoUser user = fUserRespository.findById(username).get();
        if (user == null) { throw new UsernameNotFoundException(username); }
        // Schritt 2: Spring 'User'-Objekt mit relevanten Daten für 'username' zurückgeben
        return org.springframework.security.core.userdetails.User 
        .withUsername(username)
        .password(encoder.encode(user.getPassword())) // falls in DB encoded gespeichert 
        .roles(user.getRole()) // Rolle könnte auch aus DB kommen
        .build();
    }
    
}
