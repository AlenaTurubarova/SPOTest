package com.spo.controllers;

import com.spo.model.Distribution;
import com.spo.model.Order;
import com.spo.services.DistributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/distribute")
public class DistributionController {

    private DistributionService service;

    @Autowired
    public DistributionController(DistributionService service) {
        this.service = service;
    }

    @PostMapping
    public List<Distribution> distributeCleaners(@Valid @RequestBody Order order) {
        return service.distribute(order);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleExceptions(MethodArgumentNotValidException ex) {
        List<String> messages = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
        return new ResponseEntity<>(
                messages, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
