package com.proyecto.proyecto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.proyecto.proyecto.DTO.DTOUsuario;
import com.proyecto.proyecto.entities.Usuario;
import com.proyecto.proyecto.services.UsuarioServiceImpl;

class UsuarioServiceImplTest {

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Mock
    private List<Usuario> usuarios;

    public UsuarioServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void registrarUsuario_UsuarioYaExiste_Falla() {
    
        DTOUsuario dtoUsuario = new DTOUsuario();
        dtoUsuario.setNombreUsuario("UsuarioExistente");
        dtoUsuario.setApellidoUsuario("ApellidoUsuario");
    
        // Act and Assert
        assertThrows(Exception.class, () -> {
            usuarioService.registrarUsuario(dtoUsuario);
        });
    
        verify(usuarios, never()).add(any(Usuario.class));
    }
    
}