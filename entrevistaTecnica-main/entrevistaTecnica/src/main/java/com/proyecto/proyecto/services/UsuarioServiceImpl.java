package com.proyecto.proyecto.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.proyecto.DTO.DTOAlerta;
import com.proyecto.proyecto.DTO.DTOLong;
import com.proyecto.proyecto.DTO.DTONotificaciones;
import com.proyecto.proyecto.DTO.DTOSuscripcionTema;
import com.proyecto.proyecto.DTO.DTOTexto;
import com.proyecto.proyecto.DTO.DTOUsuario;
import com.proyecto.proyecto.entities.Alerta;
import com.proyecto.proyecto.entities.NotificacionUsuario;
import com.proyecto.proyecto.entities.Tema;
import com.proyecto.proyecto.entities.TipoAlerta;
import com.proyecto.proyecto.entities.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    List<Usuario> usuarios = new ArrayList<Usuario>();

    @Autowired
    private AlertaServiceImpl alertaServiceImpl;
    @Autowired
    private TemaServiceImpl temaServiceImpl;

    @Override
    public String suscripcionTema(DTOSuscripcionTema dtoSuscripcionTema) throws Exception {
        if (dtoSuscripcionTema.getIdTema() == null) {
            throw new Exception("El tema no existe");
        }
        if (dtoSuscripcionTema.getNombreUsuario() == null) {
            throw new Exception("El nombre de usuario no puede ser vacío");
        }

        Usuario usuarioEncontrado = null;

        for (Usuario usuario : usuarios) {
            if (dtoSuscripcionTema.getNombreUsuario().equals(usuario.getNombreUsuario())) {
                usuarioEncontrado = usuario;
                break; // Termina el bucle una vez que se ha encontrado el usuario
            }
        }

        if (usuarioEncontrado == null) {
            throw new Exception("El usuario no existe");
        }

        List<Tema> temas = temaServiceImpl.getListaTemas();
        for (Tema tema : temas) {
            if (tema.getIdTema() == dtoSuscripcionTema.getIdTema()) {
                usuarioEncontrado.getTemas().add(tema);
                return "Suscripto al tema con éxito";
            }
        }

        throw new Exception("El tema no existe");
    }

    public Usuario buscarUsuarioPorNombre(String nombreUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public String registrarUsuario(DTOUsuario dtoUsuario) throws Exception {
        if (dtoUsuario.getNombreUsuario() == null) {
            throw new Exception("El nombre es obligatorio");
        }
        if (dtoUsuario.getApellidoUsuario() == null) {
            throw new Exception("El apellido es obligatorio");
        }
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(dtoUsuario.getNombreUsuario())) {
                throw new Exception("El usuario ya existe");
            }
        }
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(dtoUsuario.getNombreUsuario());
        usuario.setApellidoUsuario(dtoUsuario.getApellidoUsuario());
        usuario.setFechaHoraAltaUsuario(new Date());
        usuarios.add(usuario);
        return "Usuario creado con éxito";
    }

    @Override
    public List<DTONotificaciones> listarNotificacionesUsuario(DTOTexto nombreUsuario) {

        Usuario usuario = buscarUsuarioPorNombre(nombreUsuario.getCadena());
        List<DTONotificaciones> notificaciones = new ArrayList<>();

        if (usuario != null) {
            List<DTONotificaciones> notificacionesUrgentes = new ArrayList<>();
            List<DTONotificaciones> notificacionesInformativas = new ArrayList<>();

            for (Alerta alerta : alertaServiceImpl.getListaAlertas()) {
                if (alerta.getUsuarios().contains(usuario) && alerta.getFechaHoraFinVigenciaAlerta() == null) {
                    for (NotificacionUsuario notificacion : alerta.getNotificaciones()) {
                        if (!(notificacion.isLeida())) {
                            DTONotificaciones dtoNotificacion = new DTONotificaciones();
                            dtoNotificacion.setIdNotificacion(notificacion.getIdNotificacion());
                            dtoNotificacion.setNombreAlerta(alerta.getNombreAlerta());
                            dtoNotificacion.setNombreTema(alerta.getTema().getNombreTema());
                            // Comparar usando equals para evitar problemas con distintas instancias
                            if (TipoAlerta.URGENTE.equals(alerta.getTipoAlerta())) {
                                notificacionesUrgentes.add(dtoNotificacion); // Agregar a la lista de urgentes
                            } else {
                                notificacionesInformativas.add(dtoNotificacion); // Agregar a la lista de informativas
                            }
                        }
                    }
                }
            }

            // Agregar las notificaciones urgentes primero
            notificaciones.addAll(notificacionesUrgentes);
            // Luego, agregar las notificaciones informativas
            notificaciones.addAll(notificacionesInformativas);
        }

        return notificaciones;
    }

    @Override
    public String marcarNotificacionLeida(DTOLong idNotificacion, DTOTexto nombreUsuario) {
        
        NotificacionUsuario notificacion = buscarNotificacionPorIdYUsuario(idNotificacion.getId(),
                nombreUsuario.getCadena());
        if (notificacion != null) {
            notificacion.setLeida(true);
            return "Notificación marcada como leída con éxito";
        } else {
            return "No se encontró la notificación con el ID y nombre de usuario proporcionados";
        }
    }

    private NotificacionUsuario buscarNotificacionPorIdYUsuario(Long idNotificacion, String nombreUsuario) {
        for (Alerta alerta : alertaServiceImpl.getListaAlertas()) {
            for (NotificacionUsuario notificacion : alerta.getNotificaciones()) {
                if (notificacion.getIdNotificacion().equals(idNotificacion)
                        && notificacion.getUsuario().getNombreUsuario().equalsIgnoreCase(nombreUsuario)) {
                    return notificacion;
                }
            }
        }
        return null;
    }
    

}
