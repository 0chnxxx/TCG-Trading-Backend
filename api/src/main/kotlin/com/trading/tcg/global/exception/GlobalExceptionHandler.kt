package com.trading.tcg.global.exception

import com.trading.tcg.global.dto.ErrorResponse
import com.trading.tcg.product.exception.ProductErrorCode
import com.trading.tcg.user.exception.UserErrorCode
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.resource.NoResourceFoundException

@RestControllerAdvice
class GlobalExceptionHandler {
    val errorCodes = arrayOf(
        RequestErrorCode::class.java,
        GlobalErrorCode::class.java,
        UserErrorCode::class.java,
        ProductErrorCode::class.java
    ).flatMap { it.enumConstants.asIterable() }.map { it as ErrorCode }

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
            errorCode = e.errorCode.errorCode,
            errorMessage = e.errorCode.errorMessage
        )

        return ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.valueOf(e.errorCode.statusCode))
    }

    @ExceptionHandler(value = [NoResourceFoundException::class])
    fun handleNoResourceFoundException(
        e: NoResourceFoundException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        val errorCode = RequestErrorCode.NOT_FOUND_API

        val errorResponse = ErrorResponse.of(
            path = request.requestURI,
            errorCode = errorCode.errorCode,
            errorMessage = errorCode.errorMessage
        )

        return ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.valueOf(errorCode.statusCode))
    }

    @ExceptionHandler(value = [AuthenticationException::class])
    fun handleAuthenticationException(
        e: AuthenticationException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        val errorCode = RequestErrorCode.UNAUTHORIZED_REQUEST

        val errorResponse = ErrorResponse.of(
            path = request.requestURI,
            errorCode = errorCode.errorCode,
            errorMessage = errorCode.errorMessage
        )

        return ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.valueOf(errorCode.statusCode))
    }

    @ExceptionHandler(value = [AccessDeniedException::class])
    fun handleAccessDeniedException(
        e: AccessDeniedException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        val errorCode = RequestErrorCode.FORBIDDEN_REQUEST

        val errorResponse = ErrorResponse.of(
            path = request.requestURI,
            errorCode = errorCode.errorCode,
            errorMessage = errorCode.errorMessage
        )

        return ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.valueOf(errorCode.statusCode))
    }

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun handleConstraintViolationException(
        e: ConstraintViolationException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        val errorCode = errorCodes.first { it ->
            e.constraintViolations.map { it.messageTemplate }.contains(it.errorMessage)
        }

        val errorResponse = ErrorResponse.of(
            path = request.requestURI,
            errorCode = errorCode.errorCode,
            errorMessage = errorCode.errorMessage
        )

        return ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.valueOf(errorCode.statusCode))
    }

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun handleMethodArgumentNotValidException(
        e: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        val errorCode = errorCodes.first {
            e.bindingResult.fieldError?.defaultMessage.equals(it.errorMessage)
        }

        val errorResponse = ErrorResponse.of(
            path = request.requestURI,
            errorCode = errorCode.errorCode,
            errorMessage = errorCode.errorMessage
        )

        return ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.valueOf(errorCode.statusCode))
    }
}
