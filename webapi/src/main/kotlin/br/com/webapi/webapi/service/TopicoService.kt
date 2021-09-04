package br.com.webapi.webapi.service

import br.com.webapi.webapi.dto.NovoTopicoForm
import br.com.webapi.webapi.dto.TopicoView
import br.com.webapi.webapi.mapper.TopicoFormMapper
import br.com.webapi.webapi.mapper.TopicoViewMapper
import br.com.webapi.webapi.model.Topico
import org.springframework.stereotype.Service
import java.util.stream.Collectors
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
}