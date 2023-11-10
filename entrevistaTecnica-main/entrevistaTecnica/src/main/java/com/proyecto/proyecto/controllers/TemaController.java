package com.proyecto.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.proyecto.DTO.DTOTema;
import com.proyecto.proyecto.services.TemaService;

@RestController
public class TemaController {
   @Autowired
   private TemaService temaService;

    @PostMapping("/crearTema")
    public String crearTema(@RequestBody DTOTema dtoTema) {
        try {
            return temaService.crearTema(dtoTema);
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        } catch (Exception e) {
            return "Error inesperado: " + e.getMessage();
        }
    } 
}
