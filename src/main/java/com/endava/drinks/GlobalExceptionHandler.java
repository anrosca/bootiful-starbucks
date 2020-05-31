package com.endava.drinks;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String BAD_REQUEST_PAYLOAD = "Bad request payload";
    private static final String BAD_PARAMETER_VALUE = "Bad parameter value";
    private static final String DRINK_NOT_FOUND = "Drink not found";

    @ExceptionHandler(DrinkNotFoundException.class)
    public ResponseEntity<ErrorResponse> onDrinkNotFound(HttpServletRequest request, DrinkNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), DRINK_NOT_FOUND, e.getMessage(), request.getServletPath()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> onMethodArgumentMismatch(HttpServletRequest request, MethodArgumentTypeMismatchException e) {
        String message = "Parameter: " + e.getName() + " has invalid value: " + e.getValue();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), BAD_PARAMETER_VALUE, message, request.getServletPath()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> onMethodArgumentNotValid(HttpServletRequest request, MethodArgumentNotValidException e) {
        List<FieldError> allErrors = e.getBindingResult().getFieldErrors();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), BAD_REQUEST_PAYLOAD, makeErrorMessage(allErrors), request.getServletPath()));
    }

    private String makeErrorMessage(List<FieldError> allErrors) {
        StringBuilder builder = new StringBuilder();
        for (FieldError fieldError : allErrors) {
            builder.append("Field: ")
                    .append(fieldError.getField())
                    .append(" has invalid value: ")
                    .append(fieldError.getRejectedValue())
                    .append(",");

        }
        return builder.substring(0, builder.length() - 1);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> onHttpMessageNotReadable(HttpServletRequest request, HttpMessageNotReadableException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), BAD_REQUEST_PAYLOAD, "", request.getServletPath()));
    }
}
