package uz.pdp.simline.handle;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import uz.pdp.simline.dto.respone.ErrorResponse;
import uz.pdp.simline.exception.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(NullOrEmptyException.class)
    public ResponseEntity<?> handleNullOrEmptyException(NullOrEmptyException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<?> handleInvalidArgumentException(InvalidArgumentException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<?> handleAlreadyExistsException(AlreadyExistsException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<?> handleExpiredJwtException(ExpiredJwtException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse("Token expired"));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getName() + " must be " + e.getRequiredType().getName()));
    }

    @ExceptionHandler(AlreadyTakenException.class)
    public ResponseEntity<?> handleAlreadyTakenException(AlreadyTakenException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
    }
    @ExceptionHandler(TransactionFailedException.class)
    public ResponseEntity<?> handleTransactionFailedException(TransactionFailedException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
    }
}
