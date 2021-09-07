package br.com.webapi.webapi.repository

import br.com.webapi.webapi.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository: JpaRepository<Usuario, Long> {
}