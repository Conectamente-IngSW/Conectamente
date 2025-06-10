package com.ingsw.conectamente.dto;

import com.ingsw.conectamente.enums.Departamento;
import com.ingsw.conectamente.enums.Distrito;
import com.ingsw.conectamente.enums.ERol;
import com.ingsw.conectamente.enums.Especialidad;
import lombok.Data;

@Data
public class UsuarioPerfilDTO {

    private Integer idUsuario;
    private String email;
    private ERol role;
    private String nombre;
    private String apellido;

    private String dni;
    private String telefono;
    private String fechaNacimiento;
    private String fotoPerfil;
    private String descripcion;
    private Departamento departamento;
    private Distrito distrito;
    private Especialidad especialidad;
    private String numColegiatura;




}
