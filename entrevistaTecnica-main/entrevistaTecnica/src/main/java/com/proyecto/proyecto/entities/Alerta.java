package com.proyecto.proyecto.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alerta {
    private Long idAlerta;
    private String nombreAlerta;
    private Date fechaHoraFinVigenciaAlerta;
    private Date fechaHoraAltaAlerta;
    private Tema tema;
    private List<Usuario> usuarios = new ArrayList<Usuario>();
    private TipoAlerta tipoAlerta;
    private boolean esDirigidaParaTodos;
    private List<NotificacionUsuario> notificaciones = new ArrayList<NotificacionUsuario>();
}
