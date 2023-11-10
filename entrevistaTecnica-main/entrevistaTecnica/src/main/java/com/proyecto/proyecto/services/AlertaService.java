package com.proyecto.proyecto.services;

import java.util.List;

import com.proyecto.proyecto.DTO.DTOAlerta;

public interface AlertaService {

    String crearAlerta(DTOAlerta dtoAlerta) throws Exception;

    

    String bajaAlerta(DTOAlerta dtoAlerta) throws Exception;

    List<DTOAlerta> consultarAlertas() throws Exception;

    
}
