package br.com.webapi.webapi.dto

import br.com.webapi.webapi.model.StatusTopico
import java.time.LocalDateTime

data class TopicoView(
        val id: Long?,
        val titulo: String,
        val mensagem: String,
        val status: StatusTopico,
        val dataCriacao: LocalDateTime
)
