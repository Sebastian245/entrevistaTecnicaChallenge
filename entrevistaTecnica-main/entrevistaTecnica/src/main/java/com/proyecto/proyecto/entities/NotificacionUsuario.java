package com.proyecto.proyecto.entities;

import java.util.concurrent.atomic.AtomicLong;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NotificacionUsuario {
    private static final AtomicLong idGenerator = new AtomicLong(0);

    private Long idNotificacion;
    private Usuario usuario;
    private Alerta alerta;
    private boolean leida;

    public NotificacionUsuario(Usuario usuario, Alerta alerta, boolean leida) {
        this.idNotificacion = idGenerator.incrementAndGet();
        this.usuario = usuario;
        this.alerta = alerta;
        this.leida = leida;
    }
}





