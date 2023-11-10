package com.proyecto.proyecto.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTONotificaciones {
    private Long idNotificacion;
    private String nombreAlerta;
    private String nombreTema;
    private String descripcionTema;
}
