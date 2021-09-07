package br.com.webapi.webapi.service

import br.com.webapi.webapi.model.Curso
import br.com.webapi.webapi.repository.CursoRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CursoService( private val repository: CursoRepository) {

       fun buscarPorId(id: Long): Curso{
        return repository.getOne(id)
    }
}
