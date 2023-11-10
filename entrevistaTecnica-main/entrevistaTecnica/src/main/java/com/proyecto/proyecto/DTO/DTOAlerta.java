package com.proyecto.proyecto.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOAlerta {
    private Long idAlerta;
    private String nombreAlerta;
    private String nombreTema;
    private String descripcionTema;
    private String nombreTipoAlerta;
    private String nombreUsuario;
    private boolean esDirigidaParaTodos;
}
