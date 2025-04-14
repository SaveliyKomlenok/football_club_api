package com.example.footballclubapi.exceptionhandler;

import com.example.footballclubapi.exception.AmountOfClothingExceededException;
import com.example.footballclubapi.exception.AmountOfShoesExceededException;
import com.example.footballclubapi.exception.CartClothingNotExistsException;
import com.example.footballclubapi.exception.CartShoesNotExistsException;
import com.example.footballclubapi.exception.ClothSizeNotExistsException;
import com.example.footballclubapi.exception.ClothingNotExistsException;
import com.example.footballclubapi.exception.ClothingTypeNotExistsException;
import com.example.footballclubapi.exception.ClothingWarehouseNotExistsException;
import com.example.footballclubapi.exception.ManufacturerNotExistsException;
import com.example.footballclubapi.exception.OrderNotExistException;
import com.example.footballclubapi.exception.ShoeSizeNotExistsException;
import com.example.footballclubapi.exception.ShoesNotExistsException;
import com.example.footballclubapi.exception.ShoesTypeNotExistsException;
import com.example.footballclubapi.exception.ShoesWarehouseNotExistsException;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {
    @ExceptionHandler({ClothingNotExistsException.class,
        ClothingTypeNotExistsException.class,
        ClothSizeNotExistsException.class,
        ManufacturerNotExistsException.class,
        ShoeSizeNotExistsException.class,
        ShoesNotExistsException.class,
        ShoesTypeNotExistsException.class,
        AmountOfClothingExceededException.class,
        AmountOfShoesExceededException.class,
        CartClothingNotExistsException.class,
        CartShoesNotExistsException.class,
        ClothingWarehouseNotExistsException.class,
        ShoesWarehouseNotExistsException.class,
        OrderNotExistException.class})
    public ResponseEntity<ErrorMessage> handleBadRequestErrors(RuntimeException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(exception.getMessage()));
    }

//    @ExceptionHandler()
//    public ResponseEntity<ErrorMessage> handleConflictErrors(RuntimeException exception) {
//        return ResponseEntity
//                .status(HttpStatus.CONFLICT)
//                .body(new ErrorMessage(exception.getMessage()));
//    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        final List<Violation> violations = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ValidationErrorResponse(violations);
    }
}