package br.com.webapi.webapi.service

import br.com.webapi.webapi.dto.AtualizacaoTopicoForm
import br.com.webapi.webapi.dto.NovoTopicoForm
import br.com.webapi.webapi.dto.TopicoView
import br.com.webapi.webapi.exception.NotFoundException
import br.com.webapi.webapi.mapper.TopicoFormMapper
import br.com.webapi.webapi.mapper.TopicoViewMapper
import br.com.webapi.webapi.model.Topico
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import java.util.stream.Collectors
import javax.validation.Valid
import kotlin.collections.ArrayList
import kotlin.collections.plus as plus

@Service
class TopicoService (
        private var topicos: List<Topico> = ArrayList(),
       // private val cursoService: CursoService,
       // private val usuarioService: UsuarioService,
        private val topicoViewMapper: TopicoViewMapper,
        private val topicoFormMapper: TopicoFormMapper,
        private val notFoundMessage: String = "Topico não encontrado!"
        ) {

    fun listar(): List<TopicoView> {
        return topicos.stream().map{
            t -> topicoViewMapper.map(t)
        }.collect(Collectors.toList())
    }
    fun buscarPorId(id: Long): TopicoView {
        val topico = topicos.stream().filter({t -> t.id == id}).findFirst().orElseThrow{NotFoundException(notFoundMessage)}
        return topicoViewMapper.map(topico)
    }

    fun cadastrar(form: NovoTopicoForm): TopicoView {
        val topico = topicoFormMapper.map(form)
        topico.id = topicos.size.toLong() +1
        topicos = topicos.plus(topico)
        return topicoViewMapper.map(topico)
    }

    @PutMapping
    fun atualizar(@RequestBody @Valid form: AtualizacaoTopicoForm): TopicoView{
        val topico = topicos.stream().filter { t ->
            t.id == form.id
        }.findFirst().orElseThrow{NotFoundException(notFoundMessage)}
        val topicoAtualizado = Topico(
                id = form.id,
                titulo = form.titulo,
                mensagem = form.mensagem,
                autor = topico.autor,
                curso = topico.curso,
                respostas = topico.respostas,
                status = topico.status,
                dataCriacao = topico.dataCriacao
        )
        topicos = topicos.minus(topico).plus(topicoAtualizado)
        return topicoViewMapper.map(topicoAtualizado)
    }

    fun deletar(id: Long) {
        val topico = topicos.stream().filter { t ->
            t.id == id
        }.findFirst().orElseThrow{NotFoundException(notFoundMessage)}
        topicos = topicos.minus(topico)
    }
}