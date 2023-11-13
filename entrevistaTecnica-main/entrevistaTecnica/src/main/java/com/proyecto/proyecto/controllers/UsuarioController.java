package com.proyecto.proyecto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.proyecto.DTO.DTOLong;
import com.proyecto.proyecto.DTO.DTONotificaciones;
import com.proyecto.proyecto.DTO.DTOSuscripcionTema;
import com.proyecto.proyecto.DTO.DTOTexto;
import com.proyecto.proyecto.DTO.DTOUsuario;
import com.proyecto.proyecto.services.UsuarioService;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/suscripcionAlerta")
    public String suscripcionTema(@RequestBody DTOSuscripcionTema dtoSuscripcionTema) {
        try {
            return usuarioService.suscripcionTema(dtoSuscripcionTema);
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        } catch (Exception e) {
            return "Error inesperado: " + e.getMessage();
        }
    }

    @GetMapping("/listarNotificacionesUsuario")
    public List<DTONotificaciones> listarNotificacionesUsuario(@RequestBody DTOTexto nombreUsuario) {
            return usuarioService.listarNotificacionesUsuario(nombreUsuario);
    }
   
    @PostMapping("/marcarAlertaLeida")
    public String marcarNotificacionLeida(@RequestBody DTOLong idNotificacion,@RequestParam DTOTexto nombreUsuario) {
        try {
            return usuarioService.marcarNotificacionLeida(idNotificacion,nombreUsuario);
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        } catch (Exception e) {
            return "Error inesperado: " + e.getMessage();
        }
    }
    
    @PostMapping("/registrarUsuario")
    public String registrarUsuario(@RequestBody DTOUsuario dtoUsuario) {
        try {
            return usuarioService.registrarUsuario(dtoUsuario);
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        } catch (Exception e) {
            return "Error inesperado: " + e.getMessage();
        }
    }
}
