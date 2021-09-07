package br.com.webapi.webapi.repository

import br.com.webapi.webapi.model.Curso
import org.springframework.data.jpa.repository.JpaRepository

interface CursoRepository: JpaRepository<Curso, Long> {
}