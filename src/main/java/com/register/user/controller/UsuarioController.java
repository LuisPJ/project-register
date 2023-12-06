package com.register.user.controller;

import com.register.user.modelo.Usuario;
import com.register.user.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarUsuario(@RequestBody Usuario usuario) {

        String mensaje = usuarioService.registrarUsuario(usuario);

        if (mensaje.startsWith("Registro") || mensaje.startsWith("Actualización")) {
            return ResponseEntity.status(HttpStatus.OK).body(mensaje);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensaje);
        }
    }
}
