package br.com.webapi.webapi.exception

import java.lang.RuntimeException

class NotFoundException(message: String?): RuntimeException(message) {
}