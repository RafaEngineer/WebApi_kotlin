package br.com.webapi.webapi.service

import br.com.webapi.webapi.dto.AtualizacaoTopicoForm
import br.com.webapi.webapi.dto.NovoTopicoForm
import br.com.webapi.webapi.dto.TopicoView
import br.com.webapi.webapi.exception.NotFoundException
import br.com.webapi.webapi.mapper.TopicoFormMapper
import br.com.webapi.webapi.mapper.TopicoViewMapper
import br.com.webapi.webapi.model.Topico
import br.com.webapi.webapi.repository.TopicoRepository
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import java.util.stream.Collectors
import javax.validation.Valid
import kotlin.collections.ArrayList
import kotlin.collections.plus as plus

@Service
class TopicoService (
        private val repository: TopicoRepository,
        private val topicoViewMapper: TopicoViewMapper,
        private val topicoFormMapper: TopicoFormMapper,
        private val notFoundMessage: String = "Topico não encontrado!"
        ) {

    fun listar(): List<TopicoView> {
        return repository.findAll().stream().map{
            t -> topicoViewMapper.map(t)
        }.collect(Collectors.toList())
    }
    fun buscarPorId(id: Long): TopicoView {
        val topico = repository.findById(id).orElseThrow{NotFoundException(notFoundMessage)}
        return topicoViewMapper.map(topico)
    }

    fun cadastrar(form: NovoTopicoForm): TopicoView {
        val topico = topicoFormMapper.map(form)
        repository.save(topico)
        return topicoViewMapper.map(topico)
    }

    @PutMapping
    fun atualizar(@RequestBody @Valid form: AtualizacaoTopicoForm): TopicoView{
        val topico = repository.findById(form.id)
                .orElseThrow{NotFoundException(notFoundMessage)}
        topico.titulo = form.titulo
        topico.mensagem = form.mensagem
        return topicoViewMapper.map(topico)
    }

    fun deletar(id: Long) {
        repository.deleteById(id)
    }
}