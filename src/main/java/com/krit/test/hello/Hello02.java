package com.krit.test.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.krit.test.hello.config.ApplicationConfig;
import com.krit.test.hello.service.HelloService;

@Configuration
@EnableConfigurationProperties(ApplicationConfig.class)
public class Hello02 {
  @Autowired
  private ApplicationConfig applicationConfig;
  @Autowired
  private HelloService helloService;

  @Bean
  public CommandLineRunner commandLineRunner03(ApplicationContext ctx) {
    return args -> {
      System.out.println("commandLineRunner03: run me, run meee..");
      System.out.println("Student : " + applicationConfig.getStudent() + ", Language : "
          + applicationConfig.getLanguage());
      helloService.sayHello();
    };
  }
}
