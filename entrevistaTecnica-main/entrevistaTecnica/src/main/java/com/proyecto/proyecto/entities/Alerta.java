package com.proyecto.proyecto.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Alerta {
    private static final AtomicLong idGenerator = new AtomicLong(0);

    @Builder.Default
    private Long idAlerta = idGenerator.incrementAndGet();
    
    private String nombreAlerta;
    private Date fechaHoraFinVigenciaAlerta;
    private Date fechaHoraAltaAlerta;
    private Tema tema;
    private List<Usuario> usuarios = new ArrayList<>();
    private TipoAlerta tipoAlerta;
    private boolean esDirigidaParaTodos;
    private List<NotificacionUsuario> notificaciones = new ArrayList<>();
}
