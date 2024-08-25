package org.sparta.jpaschedule.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.sparta.jpaschedule.common.dto.CommonResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // 특정 exception 에 대한 글로벌 처리
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<CommonResponseDto<Object>> handleNotFoundDataException(NotFoundException ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return getErrorResponse(status, ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CommonResponseDto<Object>> handleRuntimeException(RuntimeException ex) {

        log.info("Runtime error : {}", ex.getMessage());

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return getErrorResponse(status, "알수없는 오류가 발생하였습니다. 잠시 후 다시 시도해 주십시오.");
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResponseDto<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String lastDefaultMessage = fieldErrors.get(fieldErrors.size() - 1).getDefaultMessage();

        return getErrorResponse(HttpStatus.BAD_REQUEST, lastDefaultMessage);
    }

    public ResponseEntity<CommonResponseDto<Object>> getErrorResponse(HttpStatus status, String message) {

        log.info(message);

        return new ResponseEntity<>(new CommonResponseDto<>(status.value(), message, null), status);
    }
}