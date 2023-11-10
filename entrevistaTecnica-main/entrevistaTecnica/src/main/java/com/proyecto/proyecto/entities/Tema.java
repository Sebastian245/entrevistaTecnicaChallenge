package com.proyecto.proyecto.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.concurrent.atomic.AtomicLong;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tema {

    private static final AtomicLong idGenerator = new AtomicLong(0);

    
    private String nombreTema;
    private String descripcionTema;

    // Constructor para generar automáticamente el ID en memoria
    @Builder.Default
    private final Long idTema = idGenerator.incrementAndGet();

    // Otros campos y métodos de la clase...
}




