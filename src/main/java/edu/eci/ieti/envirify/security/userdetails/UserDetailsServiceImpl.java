package edu.eci.ieti.envirify.services.impl;

import edu.eci.ieti.envirify.model.User;
import edu.eci.ieti.envirify.persistence.repositories.UserRepository;
import edu.eci.ieti.envirify.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service that provides details of a user
 * @author Error 418
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    /**
     * Get a user by username
     * @param username username of the user
     * @return the user that has the username
     * @throws UsernameNotFoundException in case that the app didn't found the username
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userRepository.findByEmail(username);
        }catch(UsernameNotFoundException e){
            throw new UsernameNotFoundException(e.getMessage());
        }
        return UserDetailsImpl.build(user);
    }
}