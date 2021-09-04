package br.com.webapi.webapi.mapper

interface Mapper<T, U> {

    fun map(t: T): U

}
