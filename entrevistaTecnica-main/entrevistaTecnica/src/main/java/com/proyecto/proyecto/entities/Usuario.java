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
public class Usuario {
  private Long idUsuario; 
  private String nombreUsuario;
  private String apellidoUsuario;
  private Date fechaHoraAltaUsuario;
  private Date fechaHoraFinVigenciaUsuario; 
  private List<Tema> temas = new ArrayList<Tema>();
}
