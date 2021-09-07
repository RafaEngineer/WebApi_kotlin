package br.com.webapi.webapi.controller

import br.com.webapi.webapi.dto.AtualizacaoTopicoForm
import br.com.webapi.webapi.dto.NovoTopicoForm
import br.com.webapi.webapi.dto.TopicoView
import br.com.webapi.webapi.service.TopicoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriBuilder
import org.springframework.web.util.UriComponentsBuilder
import javax.transaction.Transactional
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
    @Transactional
    fun cadastrar(@RequestBody @Valid form: NovoTopicoForm, uriBuilder: UriComponentsBuilder):ResponseEntity<TopicoView>{
        val topicoView = service.cadastrar(form)
        val uri =  uriBuilder.path("/topicos/${topicoView.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicoView)
    }
    @PutMapping
    @Transactional
    fun atualizar(@RequestBody @Valid form: AtualizacaoTopicoForm): ResponseEntity<TopicoView>{
        val topicoView = service.atualizar(form)
        return ResponseEntity.ok(topicoView)
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun deletar(@PathVariable id: Long){
        service.deletar(id)

    }
}