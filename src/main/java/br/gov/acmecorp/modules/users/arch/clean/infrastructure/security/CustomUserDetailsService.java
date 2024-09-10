package br.gov.acmecorp.modules.users.arch.clean.infrastructure.security;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.gov.acmecorp.modules.users.arch.clean.infrastructure.entity.user.UsersEntity;
import br.gov.acmecorp.modules.users.arch.clean.infrastructure.repository.UsersRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    
    private final UsersRepository repository;

    public CustomUserDetailsService(UsersRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersEntity user = this.repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
