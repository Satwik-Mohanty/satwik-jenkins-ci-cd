package com.devops.assignment.controller;

import com.devops.assignment.service.OperationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OperationController {

    @Autowired
    private OperationService operationService;

    @GetMapping("/add")
    public int add(@PathVariable("a") int a, @PathVariable("b") int b){
        return operationService.add(a, b);
    }

    @GetMapping("/subtract")
    public int subtract(@PathVariable("a") int a, @PathVariable("b") int b){
        return operationService.subtract(a, b);
    }

    @GetMapping("/multiply")
    public int multiply(@PathVariable("a") int a, @PathVariable("b") int b){
        return operationService.multiply(a, b);
    }

    @GetMapping("/divide")
    public int divide(@PathVariable("a") int a, @PathVariable("b") int b){
        return operationService.divide(a, b);
    }
}
