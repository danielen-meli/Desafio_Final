package com.meli.desafio_final.util;

import com.meli.desafio_final.model.Usuario;

public class TestUtilGenerator_UsuerAuth {

    public  static Usuario userAuthoAdmin() {
        Usuario usuarioAdmin = Usuario.builder()
                .user("administrador")
                .senha("admin")
                .build();

        return usuarioAdmin;}

}
