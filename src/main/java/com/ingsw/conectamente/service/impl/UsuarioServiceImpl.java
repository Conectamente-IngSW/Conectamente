package com.ingsw.conectamente.service.impl;

import com.ingsw.conectamente.dto.AuthResponseDTO;
import com.ingsw.conectamente.dto.LoginDTO;
import com.ingsw.conectamente.dto.UsuarioPerfilDTO;
import com.ingsw.conectamente.dto.UsuarioRegistroDTO;
import com.ingsw.conectamente.enums.ERol;
import com.ingsw.conectamente.exception.RoleNotFoundException;
import com.ingsw.conectamente.mapper.UsuarioMapper;
import com.ingsw.conectamente.model.entity.Paciente;
import com.ingsw.conectamente.model.entity.Psicologo;
import com.ingsw.conectamente.model.entity.Rol;
import com.ingsw.conectamente.model.entity.Usuario;
import com.ingsw.conectamente.repository.PacienteRepository;
import com.ingsw.conectamente.repository.PsicologoRepository;
import com.ingsw.conectamente.repository.RolRepository;
import com.ingsw.conectamente.repository.UsuarioRepository;
import com.ingsw.conectamente.security.TokenProvider;
import com.ingsw.conectamente.security.UserPrincipal;
import com.ingsw.conectamente.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PacienteRepository pacienteRepository;
    private final PsicologoRepository psicologoRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper usuarioMapper;

    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @Override
    public UsuarioPerfilDTO registroPaciente(UsuarioRegistroDTO registroDTO) {
        registerUsuarioWithRol(registroDTO, ERol.PACIENTE);
        return null;
    }

    @Override
    public UsuarioPerfilDTO registroPsicologo(UsuarioRegistroDTO registroDTO) {
        registerUsuarioWithRol(registroDTO, ERol.PSICOLOGO);
        return null;
    }

    @Override
    public AuthResponseDTO login(LoginDTO loginDTO) {
        //Autenticar al usuario utilizando AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );

        //Una vez autenticado, el objeto Authentication contiene la informacion del usuario autenticado
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Usuario usuario = userPrincipal.getUsuario();

        String token = tokenProvider.createAccessToken(authentication);

        AuthResponseDTO responseDTO = new AuthResponseDTO();

        return responseDTO;
    }

    @Override
    public UsuarioPerfilDTO updateUsuarioPerfil(Integer idUsuario, UsuarioPerfilDTO usuarioPerfilDTO) {
        return null;
    }

    @Override
    public UsuarioPerfilDTO getUsuarioPerfilById(Integer idUsuario) {
        return null;
    }

    private UsuarioPerfilDTO registerUsuarioWithRol(UsuarioRegistroDTO registroDTO, ERol rolEnum) {

        //Verificar si el emaiil ya esta registrado o si ya existe un usuario con el mismo nombre y apellido
        boolean existsByEmail = usuarioRepository.existsByEmail(registroDTO.getEmail());
        boolean existsAsPaciente = pacienteRepository.existsByNombreAndApellido(
                registroDTO.getNombre(), registroDTO.getApellido());
        boolean existsAsPsicologo = psicologoRepository.existsByNombreAndApellido(
                registroDTO.getNombre(), registroDTO.getApellido());

        if (existsByEmail) {
            throw new IllegalArgumentException("El email ya están registrados");
        }

        if (existsAsPaciente || existsAsPsicologo) {
            throw new IllegalArgumentException("Ya existe un usuario con el mismo nombre y apellido");
        }

        Rol rol = rolRepository.findByName(rolEnum)
                .orElseThrow(() -> new RoleNotFoundException("Error: Rol no encontrado: "));;

        registroDTO.setPassword(passwordEncoder.encode(registroDTO.getPassword()));

        Usuario usuario = usuarioMapper.toUsuarioEntity(registroDTO);
        usuario.setRol(rol);

        if(rolEnum == ERol.PACIENTE) {
            Paciente paciente = new Paciente();
            paciente.setNombre(registroDTO.getNombre());
            paciente.setApellido(registroDTO.getApellido());
            paciente.setDni(registroDTO.getDni());
            paciente.setEdad(registroDTO.getEdad());
            paciente.setCreatedAt(LocalDateTime.now());
            paciente.setUsuario(usuario);
            usuario.setPaciente(paciente);
        } else if (rolEnum == ERol.PSICOLOGO) {
            Psicologo psicologo = new Psicologo();
            psicologo.setNombre(registroDTO.getNombre());
            psicologo.setApellido(registroDTO.getApellido());
            psicologo.setNumColegiatura(registroDTO.getNumColegiatura());
            psicologo.setCreatedAt(LocalDateTime.now());
            psicologo.setUsuario(usuario);
            usuario.setPsicologo(psicologo);
        }

        Usuario savedUsuario = usuarioRepository.save(usuario);

        return usuarioMapper.toUsuarioPerfilDTO(savedUsuario);
    }



}
