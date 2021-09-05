package br.com.webapi.webapi.controller

import br.com.webapi.webapi.dto.AtualizacaoTopicoForm
import br.com.webapi.webapi.dto.NovoTopicoForm
import br.com.webapi.webapi.dto.TopicoView
import br.com.webapi.webapi.service.TopicoService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/topicos")
class topicoController (private val service: TopicoService) {

    @GetMapping
    fun listar(): List<TopicoView>{
        return service.listar()
    }
    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): TopicoView {
        return service.buscarPorId(id)
    }
    @PostMapping
    fun cadastrar(@RequestBody @Valid dto: NovoTopicoForm){
        service.cadastrar(dto)
        }
    @PutMapping
    fun atualizar(@RequestBody @Valid form: AtualizacaoTopicoForm){
        service.atualizar(form)
    }
    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long){
        service.deletar(id)

    }
}