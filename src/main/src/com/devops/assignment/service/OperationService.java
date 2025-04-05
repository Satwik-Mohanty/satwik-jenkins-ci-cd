package com.devops.assignment.service;

import org.springframework.stereotype.Service;

@Service
public interface OperationService {

    public int add(int a, int b);

    public int subtract(int a, int b);

    public int multiply(int a, int b);

    public int divide(int a, int b);
}
