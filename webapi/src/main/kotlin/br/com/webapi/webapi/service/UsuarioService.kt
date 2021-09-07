package br.com.webapi.webapi.service

import br.com.webapi.webapi.model.Usuario
import br.com.webapi.webapi.repository.UsuarioRepository
import org.springframework.stereotype.Service
import java.util.*
import java.util.Arrays.asList

@Service
class UsuarioService( private val repository: UsuarioRepository) {

    fun buscarPorId(id: Long): Usuario{
        return repository.getOne(id)

    }
}
