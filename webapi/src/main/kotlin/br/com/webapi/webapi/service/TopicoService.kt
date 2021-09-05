package br.com.webapi.webapi.service

import br.com.webapi.webapi.dto.AtualizacaoTopicoForm
import br.com.webapi.webapi.dto.NovoTopicoForm
import br.com.webapi.webapi.dto.TopicoView
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
        private val topicoFormMapper: TopicoFormMapper
        ) {

    fun listar(): List<TopicoView> {
        return topicos.stream().map{
            t -> topicoViewMapper.map(t)
        }.collect(Collectors.toList())
    }
    fun buscarPorId(id: Long): TopicoView {
        val topico = topicos.stream().filter({t -> t.id == id}).findFirst().get()
        return topicoViewMapper.map(topico)
    }

    fun cadastrar(form: NovoTopicoForm) {
        val topico = topicoFormMapper.map(form)
        topico.id = topicos.size.toLong() +1
        topicos = topicos.plus(topico)
    }

    @PutMapping
    fun atualizar(@RequestBody @Valid form: AtualizacaoTopicoForm){
        val topico = topicos.stream().filter { t ->
            t.id == form.id
        }.findFirst().get()
        topicos = topicos.minus(topico).plus(Topico(
                id = form.id,
                titulo = form.titulo,
                mensagem = form.mensagem,
                autor = topico.autor,
                curso = topico.curso,
                respostas = topico.respostas,
                status = topico.status,
                dataCriacao = topico.dataCriacao
        ))
    }
}