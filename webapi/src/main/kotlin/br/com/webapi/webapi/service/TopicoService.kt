package br.com.webapi.webapi.service

import br.com.webapi.webapi.model.Curso
import br.com.webapi.webapi.model.Topico
import br.com.webapi.webapi.model.Usuario
import org.springframework.stereotype.Service
import java.util.*

@Service
class TopicoService (private var topicos: List<Topico>) {

    init{
        val topico = Topico(
                id =1,
                titulo = "Duvida Kotlin",
                mensagem = "Variaveis no Kotlin",
                curso = Curso(
                        id = 1,
                        nome = "String",
                        categoria = "Programação"
                ),
                autor = Usuario(
                        id =1,
                        nome = "Ana da Silva",
                        email = "ana@gmail.com"
                )
        )
        val topico2 = Topico(
                id =2,
                titulo = "Duvida Kotlin",
                mensagem = "Variaveis no Kotlin2",
                curso = Curso(
                    id = 2,
                    nome = "String",
                    categoria = "Programação"
            ),
                autor = Usuario(
                    id =2,
                    nome = "Ana da Silva",
                    email = "ana@gmail.com"
            ),
        )
        val topico3 = Topico(
                id =3,
                titulo = "Duvida Kotlin",
                mensagem = "Variaveis no Kotlin2",
                curso = Curso(
                        id = 3,
                        nome = "String",
                        categoria = "Programação"
                ),
                autor = Usuario(
                        id =3,
                        nome = "Ana da Silva",
                        email = "ana@gmail.com"
                ),
        )
        topicos = Arrays.asList(topico, topico2, topico3)
    }
    fun listar(): List<Topico> {

        return topicos
    }

    fun buscarPorId(id: Long): Topico {
        return topicos.stream().filter({t -> t.id == id}).findFirst().get()
    }

}