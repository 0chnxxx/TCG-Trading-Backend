package com.trading.tcg.global.exception

import com.trading.tcg.global.dto.ErrorResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.Exception

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(value = [Exception::class])
    fun handleException(e: Exception, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse.of(
            path = request.requestURI,
            errorCode = "UNKNOWN_SERVER_ERROR",
            errorMessage = "알 수 없는 에러입니다."
        )

        e.printStackTrace()

        return ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(value = [CustomException::class])
    fun handleCustomException(e: CustomException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse.of(
            path = request.requestURI,
            errorCode = e.errorCode.getErrorCode(),
            errorMessage = e.errorCode.getErrorMessage()
        )

        return ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.valueOf(e.errorCode.getStatusCode()))
    }

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun handleConstraintViolationException(e: ConstraintViolationException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        val errorCode = ServiceErrorCode.entries.first { it ->
            e.constraintViolations.map { it.messageTemplate }.contains(it.getErrorMessage())
        }

        val errorResponse = ErrorResponse.of(
            path = request.requestURI,
            errorCode = errorCode.getErrorCode(),
            errorMessage = errorCode.getErrorMessage()
        )

        return ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.valueOf(errorCode.getStatusCode()))
    }

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        val errorCode = ServiceErrorCode.entries.first { e.bindingResult.fieldError?.defaultMessage.equals(it.getErrorMessage()) }

        val errorResponse = ErrorResponse.of(
            path = request.requestURI,
            errorCode = errorCode.getErrorCode(),
            errorMessage = errorCode.getErrorMessage()
        )

        return ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.valueOf(errorCode.getStatusCode()))
    }
}
