package ar.edu.unlar.prog3.tp_comparable_comparator.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        // Usamos LinkedHashMap para mantener el orden de las claves en el JSON
        Map<String, String> response = new LinkedHashMap<>();
        response.put("error", "Bad Request");
        response.put("message", ex.getMessage());

        // Devolvemos el Map con el código HTTP 400
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}