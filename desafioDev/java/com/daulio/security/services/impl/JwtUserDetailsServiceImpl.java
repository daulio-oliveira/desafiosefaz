package com.daulio.security.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.daulio.entity.Usuario;
import com.daulio.repository.UsuarioRepository;
import com.daulio.security.JwtUserFactory;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByNome(username);

        if (usuario.isPresent()) {
            return JwtUserFactory.create(usuario.get());
        }

        throw new UsernameNotFoundException("Usuario não encontrado.");
    }

}
