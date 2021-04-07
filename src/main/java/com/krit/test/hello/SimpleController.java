package com.krit.test.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

  @RequestMapping("/")
  @ResponseBody
  String home() {
    return "Hello World";
  }

}
