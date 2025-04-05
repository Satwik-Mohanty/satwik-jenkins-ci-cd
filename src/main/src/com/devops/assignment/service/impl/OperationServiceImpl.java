package com.devops.assignment.service.impl;

import com.devops.assignment.service.OperationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OperationServiceImpl implements OperationService{


    @Override
    public int add(int a, int b) {
        return (a+b);
    }

    @Override
    public int subtract(int a, int b) {
        return (a-b);
    }

    @Override
    public int multiply(int a, int b) {
        return (a*b);
    }

    @Override
    public int divide(int a, int b) {
        return (a/b);
    }
}
