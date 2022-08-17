package com.meli.desafio_final.service;

import com.meli.desafio_final.model.Usuario;
import com.meli.desafio_final.repository.UserRepository;
import com.meli.desafio_final.util.TestUtilGenerator_UsuerAuth;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

public class AutenticacaoServiceTest {

    @InjectMocks
    AutenticacaoService autenticacaoService;

    @Mock
    UserRepository usuario;


    @Test
    public void UsuarioAutenticado(){
        Usuario usuario = TestUtilGenerator_UsuerAuth.userAuthoAdmin();

        assertThat(usuario).isNotNull();
    }
}
