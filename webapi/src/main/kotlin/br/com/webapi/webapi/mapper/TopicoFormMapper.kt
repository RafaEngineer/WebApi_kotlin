package br.com.webapi.webapi.mapper

import br.com.webapi.webapi.dto.NovoTopicoForm
import br.com.webapi.webapi.model.Topico
import br.com.webapi.webapi.service.CursoService
import br.com.webapi.webapi.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class TopicoFormMapper(
        private val cursoService: CursoService,
        private val usuarioService: UsuarioService,
): Mapper<NovoTopicoForm, Topico> {

    override fun map(t: NovoTopicoForm): Topico {
        return Topico(
                titulo = t.titulo,
                mensagem = t.mensagem,
                curso = cursoService.buscarPorId(t.idCurso),
                autor = usuarioService.buscarPorId(t.idAutor)
        )
    }

}
