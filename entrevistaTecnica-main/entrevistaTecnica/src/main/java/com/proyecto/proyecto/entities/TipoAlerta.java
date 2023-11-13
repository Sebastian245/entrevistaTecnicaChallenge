package com.proyecto.proyecto.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoAlerta {
    public static final TipoAlerta URGENTE = new TipoAlerta(1L, "URGENTE");
    public static final TipoAlerta INFORMATIVA = new TipoAlerta(2L, "INFORMATIVA");
    private Long idTipoAlerta;
    private String nombreTipoAlerta;
}
