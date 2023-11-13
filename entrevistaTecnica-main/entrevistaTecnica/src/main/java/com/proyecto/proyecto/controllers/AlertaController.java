package com.proyecto.proyecto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.proyecto.DTO.DTOAlerta;
import com.proyecto.proyecto.services.AlertaService;

@RestController
public class AlertaController {
    @Autowired
    private AlertaService alertaService;

    @PostMapping("/crearAlerta")
    public String crearAlerta(@RequestBody DTOAlerta dtoAlerta) {
        try {
            return alertaService.crearAlerta(dtoAlerta);
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        } catch (Exception e) {
            return "Error inesperado: " + e.getMessage();
        }
    }

    @PostMapping("/bajaAlerta")
    public String bajaAlerta(@RequestBody DTOAlerta dtoAlerta) {
        try {
            return alertaService.bajaAlerta(dtoAlerta);
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        } catch (Exception e) {
            return "Error inesperado: " + e.getMessage();
        }
    }

    @GetMapping("/consultarAlertas")
    public ResponseEntity<?> consultarAlertas() {
        try {
            List<DTOAlerta> alertas = alertaService.consultarAlertas();
            return ResponseEntity.ok(alertas);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: " + e.getMessage());
        }
    }

}
