package com.proyecto.proyecto.services;

import java.util.List;

import com.proyecto.proyecto.DTO.DTOAlerta;
import com.proyecto.proyecto.DTO.DTOLong;
import com.proyecto.proyecto.DTO.DTONotificaciones;
import com.proyecto.proyecto.DTO.DTOSuscripcionTema;
import com.proyecto.proyecto.DTO.DTOTexto;
import com.proyecto.proyecto.DTO.DTOUsuario;

public interface UsuarioService {

    String suscripcionTema(DTOSuscripcionTema dtoSuscripcionTema) throws Exception;

    String consultarSuscripciones(DTOAlerta dtoAlerta) throws Exception;

    String registrarUsuario(DTOUsuario dtoUsuario) throws Exception;

    List<DTONotificaciones> listarNotificacionesUsuario(DTOTexto nombreUsuario);

    String marcarNotificacionLeida(DTOLong idNotificacion);
    
}
