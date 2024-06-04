package com.swappingcurrency.exceptions;

import com.swappingcurrency.dto.ErrorResponseDto;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;
import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private ErrorResponseDto errorResponseDto;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach((err) -> {
            String fieldName = ((FieldError) err).getField();
            String validationMsg = err.getDefaultMessage();
            validationErrors.put(fieldName, validationMsg);
        });
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> resourceNotFoundException(WebRequest webRequest, ResourceNotFoundException exception) {
        errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setApiPath(webRequest.getDescription(false));
        errorResponseDto.setStatus(HttpStatus.NOT_FOUND);
        errorResponseDto.setErrorMessage(exception.getMessage());
        errorResponseDto.setErrorTime(LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<ErrorResponseDto> resourceAlreadyExistException(WebRequest webRequest, ResourceAlreadyExistException exception) {
        errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setApiPath(webRequest.getDescription(false));
        errorResponseDto.setStatus(HttpStatus.CONFLICT);
        errorResponseDto.setErrorMessage(exception.getMessage());
        errorResponseDto.setErrorTime(LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> globalException(WebRequest webRequest, Exception exception) {
        errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setApiPath(webRequest.getDescription(false));
        errorResponseDto.setStatus(HttpStatus.EXPECTATION_FAILED);
        errorResponseDto.setErrorMessage(exception.getMessage());
        errorResponseDto.setErrorTime(LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseDto> badCredentialException(WebRequest webRequest, BadCredentialsException exception) {
        errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setApiPath(webRequest.getDescription(false));
        errorResponseDto.setStatus(HttpStatus.BAD_REQUEST);
        errorResponseDto.setErrorMessage(exception.getMessage());
        errorResponseDto.setErrorTime(LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }
}
