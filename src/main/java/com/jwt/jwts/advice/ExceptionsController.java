package com.jwt.jwts.advice;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.SizeLimitExceededException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@RestControllerAdvice
public class ExceptionsController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<CustomException> handleException(MethodArgumentNotValidException e) {
        final List<CustomException> customExceptions = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new CustomException(error.getField(), error.getDefaultMessage()))
                .toList();
        log.error(customExceptions.toString());
        return customExceptions;
    }
    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public List<CustomException> onConstraintValidationException(
            ConstraintViolationException e
    ) {
        final List<CustomException> violations = e.getConstraintViolations().stream()
                .map(
                        violation -> new CustomException(
                                violation.getPropertyPath().toString(),
                                violation.getMessage()
                        )
                )
                .collect(Collectors.toList());
        log.error(violations.toString());
        return violations;
    }
    @ExceptionHandler({ SizeLimitExceededException.class})
    public List<CustomException> FileSize(SizeLimitExceededException e) {
        final List<CustomException> customExceptions = new ArrayList<>();
        customExceptions.add(new CustomException("File", "Слишком большой размер файла"));
        log.error(customExceptions.toString());
        return customExceptions;
    }

}