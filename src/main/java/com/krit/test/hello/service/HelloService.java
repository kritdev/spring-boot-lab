package com.krit.test.hello.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
  public void sayHello() {
    System.out.println("HelloService: Hello....");
  }
}
