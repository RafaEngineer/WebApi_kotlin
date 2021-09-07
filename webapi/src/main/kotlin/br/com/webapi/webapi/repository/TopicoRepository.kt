package br.com.webapi.webapi.repository

import br.com.webapi.webapi.model.Topico
import org.springframework.data.jpa.repository.JpaRepository

interface TopicoRepository: JpaRepository<Topico, Long> {
}