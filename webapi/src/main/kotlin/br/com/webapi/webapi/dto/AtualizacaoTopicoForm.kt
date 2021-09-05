package br.com.webapi.webapi.dto

import org.jetbrains.annotations.NotNull
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

data class AtualizacaoTopicoForm (

        @field:NotNull
        val id: Long,
        @field:NotEmpty
        @field:Size(min = 5, max = 100)
        val titulo: String,
        @field:NotEmpty
        val mensagem: String
)
