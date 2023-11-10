package com.proyecto.proyecto.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.proyecto.DTO.DTOAlerta;
import com.proyecto.proyecto.entities.Alerta;
import com.proyecto.proyecto.entities.NotificacionUsuario;
import com.proyecto.proyecto.entities.Tema;
import com.proyecto.proyecto.entities.TipoAlerta;
import com.proyecto.proyecto.entities.Usuario;

@Service
public class AlertaServiceImpl implements AlertaService {

    private List<Alerta> listaAlertas = new ArrayList<>();

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;
    @Autowired
    private TemaServiceImpl temaServiceImpl;

    @Override
    public String crearAlerta(DTOAlerta dtoAlerta) throws Exception {
        // Validaciones iniciales

        // Verificar si ya existe una alerta con el mismo nombre
        if (buscarAlertaPorNombre(dtoAlerta.getNombreAlerta()) != null) {
            throw new Exception("Ya existe una alerta con ese nombre");
        }

        // Validar que el nombre del tema no sea vacío
        if (dtoAlerta.getNombreTema() == null) {
            throw new Exception("El nombre del tema no puede ser vacío");
        }
        // Validar que el nombre del tema no sea vacío
        if (dtoAlerta.getNombreTipoAlerta() == null) {
            throw new Exception("El nombre del tipo de alerta no puede ser vacío");
        }

        // Obtener la lista de temas
        List<Tema> temas = temaServiceImpl.getListaTemas();

        // Verificar si existen temas
        if (temas.isEmpty()) {
            throw new Exception("No existen temas para asignar a la alerta. Cree un tema antes de crear una alerta.");
        }

        // Inicializar la lista de usuarios de la alerta
        List<Usuario> usuariosAlerta = new ArrayList<>();

        // Verificar si la alerta es dirigida para todos los usuarios
        if (dtoAlerta.isEsDirigidaParaTodos()) {
            if(usuarioServiceImpl.usuarios.isEmpty()){
                throw new Exception("No existen usuarios para crear alerta");
            }
            usuariosAlerta.addAll(usuarioServiceImpl.usuarios);
        } else if (dtoAlerta.getNombreUsuario() != null) {
            // Buscar el usuario por nombre en la lista de usuarios
            Usuario usuario = usuarioServiceImpl.buscarUsuarioPorNombre(dtoAlerta.getNombreUsuario());

            // Verificar si se encontró el usuario
            if (usuario == null) {
                throw new Exception("El usuario con el nombre proporcionado no existe");
            }

            // Agregar el usuario a la lista de usuarios de la alerta
            usuariosAlerta.add(usuario);
        }

        // Iterar sobre los temas para encontrar el tema de la alerta
        Tema temaAlerta = null;
        for (Tema tema : temas) {
            if (tema.getNombreTema().equals(dtoAlerta.getNombreTema())) {
                temaAlerta = tema;
                break;
            }
        }

        // Verificar si se encontró el tema
        if (temaAlerta == null) {
            throw new Exception("El tema no existe");
        }

        // Crear la alerta
        Alerta alerta = new Alerta();
        alerta.setNombreAlerta(dtoAlerta.getNombreAlerta());
        alerta.setTema(temaAlerta);
        alerta.setUsuarios(usuariosAlerta);
        alerta.setEsDirigidaParaTodos(dtoAlerta.isEsDirigidaParaTodos());
        TipoAlerta tipoAlerta = new TipoAlerta();
        tipoAlerta.setNombreTipoAlerta(dtoAlerta.getNombreTipoAlerta());
        alerta.setTipoAlerta(tipoAlerta);

        // Agregar la alerta a la lista
        listaAlertas.add(alerta);

        // Crear notificaciones para cada usuario en la lista
        for (Usuario usuario : usuariosAlerta) {
            NotificacionUsuario notificacion = new NotificacionUsuario(usuario,alerta, false);
            alerta.getNotificaciones().add(notificacion);
        }
        return "Alerta creada con éxito";
    }

    @Override
    public String bajaAlerta(DTOAlerta dtoAlerta) throws Exception {
        // VALIDAR SI EXISTE LA ALERTA ANTES DE DARLA DE BAJA
        Alerta alerta = buscarAlertaPorNombre(dtoAlerta.getNombreAlerta());
        if (alerta != null) {
            // Lógica para dar de baja la alerta
            alerta.setFechaHoraFinVigenciaAlerta(new Date()); // Setear la fecha de fin de vigencia
            return "Alerta dada de baja con éxito";
        } else {
            throw new Exception("La alerta no existe");
        }
    }

    // Método para buscar una alerta por nombre
    public Alerta buscarAlertaPorNombre(String nombreAlerta) {
        for (Alerta alerta : listaAlertas) {
            if (alerta.getNombreAlerta().equals(nombreAlerta)) {
                return alerta;
            }
        }
        return null; // Si no se encuentra la alerta
    }

    // Método para obtener la lista de alertas
    public List<Alerta> getListaAlertas() {
        return listaAlertas;
    }

    @Override
    public List<DTOAlerta> consultarAlertas() throws Exception {
        if (listaAlertas.isEmpty()) {
            throw new Exception("La lista de alertas está vacia");
        }
        List<DTOAlerta> listaAlertasDTO = new ArrayList();
        for (Alerta alerta : listaAlertas) {
            DTOAlerta alertaDTO = new DTOAlerta();
            alertaDTO.setNombreAlerta(alerta.getNombreAlerta());
            alertaDTO.setNombreTema(alerta.getTema().getNombreTema());
            for (Usuario usuario : alerta.getUsuarios()) {
                alertaDTO.setNombreUsuario(usuario.getNombreUsuario());
            }
            alertaDTO.setNombreTipoAlerta(alerta.getTipoAlerta().getNombreTipoAlerta());
            listaAlertasDTO.add(alertaDTO);
        }
        return listaAlertasDTO;
    }

}
