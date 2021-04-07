package com.krit.test.hello;

import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HelloApplication {
  // @Autowired
  // private BankAccountRepository bankRepository;

  public static void main(String[] args) {
    SpringApplication.run(HelloApplication.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunner00(ApplicationContext ctx) {
    return args -> {
      System.out.println("commandLineRunner00:");

      // BankAccount account = new BankAccount("00100123", new BigDecimal(1000.50), 0, "P001");
      // bankRepository.save(account);
    };
  }

  @Bean
  public CommandLineRunner commandLineRunner01(ApplicationContext ctx) {
    return args -> {
      System.out.println("Let's inspect the bean");

      String[] beanNames = ctx.getBeanDefinitionNames();
      Arrays.sort(beanNames);
      for (String name : beanNames) {
        System.out.println(" - " + name);
      }
    };
  }

  @Bean
  public CommandLineRunner commandLineRunner02(ApplicationContext ctx) {
    return args -> {
      System.out.println("commandLineRunner02: run me toooo....");
    };
  }

}
