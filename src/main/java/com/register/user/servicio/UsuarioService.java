package com.register.user.servicio;

import com.register.user.modelo.Usuario;
import com.register.user.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public String registrarUsuario(Usuario nuevoUsuario) {
        // Verificar si el correo electrónico ya está registrado
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(nuevoUsuario.getEmail());
        try {
            if (usuarioExistente.isPresent()) {
                // El correo electrónico ya está registrado, actualiza los datos
                Usuario usuarioExistenteActualizado = usuarioExistente.get();
                usuarioExistenteActualizado.setNombres(nuevoUsuario.getNombres());
                usuarioExistenteActualizado.setApellidos(nuevoUsuario.getApellidos());
                usuarioExistenteActualizado.setCelular(nuevoUsuario.getCelular());

                usuarioRepository.save(usuarioExistenteActualizado);
                return "Actualización exitosa";
            } else {
                // Guardar el nuevo usuario en la base de datos
                usuarioRepository.save(nuevoUsuario);
                return "Registro exitoso";
            }
        } catch(Exception e){
            return "Error interno: " + e.getMessage();
        }
    }
}
