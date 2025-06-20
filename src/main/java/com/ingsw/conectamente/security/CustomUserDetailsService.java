package com.ingsw.conectamente.security;

import com.ingsw.conectamente.model.entity.Usuario;
import com.ingsw.conectamente.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().getName().name() );

        return new UserPrincipal(
                usuario.getIdUsuario(),
                usuario.getEmail(),
                usuario.getPassword(),
                Collections.singletonList(authority),
                usuario
        );
    }
}
