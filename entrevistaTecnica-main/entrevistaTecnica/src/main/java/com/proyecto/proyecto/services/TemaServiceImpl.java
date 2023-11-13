package com.proyecto.proyecto.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.proyecto.DTO.DTOTema;
import com.proyecto.proyecto.entities.Tema;

@Service
public class TemaServiceImpl implements TemaService {
    List<Tema> listaTemas = new ArrayList<Tema>();
    @Override
    public String crearTema(DTOTema dtoTema) throws Exception {
        if (dtoTema.getNombreTema() == null) {
            throw new Exception("El nombre del tema no puede ser vacio");
        }
        if (dtoTema.getDescripcionTema() == null) {
            throw new Exception("La descripción del tema no puede ser vacia");
        }
        for (Tema tema : listaTemas) {
            if (tema.getNombreTema().equals(dtoTema.getNombreTema())) {
                throw new Exception("El tema ya existe");
            }
            
        }
        Tema temaCrear = new Tema();
        temaCrear.setNombreTema(dtoTema.getNombreTema());
        temaCrear.setDescripcionTema(dtoTema.getDescripcionTema());
        listaTemas.add(temaCrear);
        return "Tema creado con éxito";
    }

    // Método para obtener la lista de temas
    public List<Tema> getListaTemas() {
        return listaTemas;
    }
}
