package com.proyecto.proyecto.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DTOTema {
    private Long idTema;
    private String nombreTema;
    private String descripcionTema;
}
