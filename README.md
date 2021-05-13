# Course Note: Spring Boot (CTF)

### Maven link
https://mvnrepository.com/

---
### Bean
- @SpringBootApplication
```
@SpringBootApplication 
public class HelloApplication { 

  public static void main(String[] args) { 
    SpringApplication.run(HelloApplication.class, args); 
  } 
  @Bean 
  public CommandLineRunner commandLineRunner00(ApplicationContext ctx) { 
    return args -> { 
      System.out.println("commandLineRunner00:"); 
    };
```
- @Configuration
```
@Configuration 
public class Hello02 { 
  @Bean 
  public CommandLineRunner commandLineRunner03(ApplicationContext ctx) { 
    return args -> { 
      System.out.println("commandLineRunner03: run me, run meee.."); 
    }; 
  } 
}
```

---
### Spring Web
- pom.xml
```
      <dependency> 
        <groupId>org.springframework.boot</groupId> 
        <artifactId>spring-boot-starter-web</artifactId> 
      </dependency>
```
- SimpleController.java
```
@RestController 
public class SimpleController { 
  @RequestMapping("/") 
  @ResponseBody 
  String home() { 
    return "Hello World"; 
  } 
}
```

---
### Fix Import Maven Project Error
	Maven Clean
	Maven Install
	Maven Build

---
### Spring Data (JPA)
- pom.xml
```
      <dependency> 
        <groupId>org.springframework.boot</groupId> 
        <artifactId>spring-boot-starter-data-jpa</artifactId> 
      </dependency> 
      <dependency> 
        <groupId>mysql</groupId> 
        <artifactId>mysql-connector-java</artifactId> 
      </dependency>
```
- BankAccount.java        (ORM)
```
@Entity 
@Table(name = "bank_account") 
public class BankAccount {
@Id 
  @Column(name = "account_no") 
  private String accountNo; 
  @Column(name = "balance") 
  private BigDecimal balance; 
  ... 
  public BankAccount() {}
  ... //Constructure with fields & setter/getter
  ...
}
```
- BankAccountRepository.java (JPA)
```
public interface BankAccountRepository extends CrudRepository<BankAccount, Integer> {  
}
```
- HelloApplication.java
```
@SpringBootApplication 
public class HelloApplication { 
  @Autowired 
  private BankAccountRepository bankRepository; 
  public static void main(String[] args) { 
    SpringApplication.run(HelloApplication.class, args); 
  } 
  @Bean 
  public CommandLineRunner commandLineRunner00(ApplicationContext ctx) { 
    return args -> { 
      BankAccount account = new BankAccount("00100123", new BigDecimal(1000.50), 0, "P001"); 
      bankRepository.save(account); 
    }; 
  }
```
- src/main/resources/application.properties
```
spring.jpa.hibernate.ddl-auto=update 
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/test 
spring.datasource.username=root 
spring.datasource.password=root
```

	additional information
	https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.named-parameters
	4.2. Query Methods - 
```
    interface PersonRepository extends Repository<Person, Long> { 
      List<Person> findByLastname(String lastname); 
      List<Person> findByLastnameAndFirstname(String lastname, String firstname);
      
      @Query("SELECT p FROM Person p WHERE YEAR(p.birthDate) = :year ORDER BY p.birthDate")
      List<Person> findByBirthYear( @Param("year") Integer year);
    }
```

	Request Mapping
	https://spring.io/guides/tutorials/rest/
	https://spring.io/guides/gs/actuator-service/
	https://www.baeldung.com/spring-new-requestmapping-shortcuts
```
    @GetMapping("/api/bankaccount/{accountNo}") 
    @ResponseBody 
    String retrieveAccount(@PathVariable("accountNo") String accountNo) {}
```

	Convert Iterable to list
```
    @GetMapping("/api/bankaccount") 
    @ResponseBody 
    List<BankAccount> getAllAccount() { 
      Iterable<BankAccount> accountIterable = bankRepository.findAll(); 
      List<BankAccount> accounts = new ArrayList<BankAccount>(); 
      accountIterable.forEach(accounts::add); 
    
      return accounts; 
    }
```


---
### Profile
- application.properties
```
    spring.profiles.active=development
```
- Target Bean (only load in development profile
```
@Configuration 
@Profile("development") 
public class DevConfig { 
  @Bean 
  public BankAccount bankAccount() { 
    ....
  } 
}
```


---
### Load properties to Bean
- application.properties
```
    application.codecamp.language=Java 
    application.codecamp.student=good
```
- ApplicationConfiguration.java
```
    @ConfigurationProperties(prefix = "application.codecamp") 
    public class ApplicationConfig { 
      private String language; 
      private String student;

      ... //setter & getter
    }
```
- Caller Class
```
    @Configuration 
    @EnableConfigurationProperties(ApplicationConfig.class) 
    public class Hello02 { 
      @Autowired 
      private ApplicationConfig applicationConfig;

      ... // call code...
    }
```


---
### Slide : Spring Boot Part 2
	Sending Email
	Uploading File
	Downloading File
	Web Compression
	Hashing (MD5, SHA-512, BCrypt)
	Base64 Encode/Decode
	Login System
	User Interface
	Config (CustomHikariConfig, ViewConfig, WebSecurityConfig)
	Controller (LoginController, AppUserService)
	Entity (AppRole, AppUser, UserRole)


---
### Simple Query (Spring Data)
	- Interface List<> findBy[field name]()
	- Interface Object findById()

### Create Jar File
	- mvn package
	- eclipse: right click project name -> Run As -> 4. Maven Build... -> Enter Goals: package
	- run : java -jar "jar file name"

### Create War File
	- Clip 23 - 24
	- pom.xml
		- remove line : spring-boot-maven-plugin
		- add line : <packaging>war</packaging>
		- add
```
    <dependency> 
      <groupId>org.springframework.boot</groupId> 
      <artifactId>spring-boot-starter-tomcat</artifactId> 
    </dependency>
```
				<dependency>
				    <groupId>org.springframework.boot</groupId>
				    <artifactId>spring-boot-starter-tomcat</artifactId>
				</dependency>
		- Application Class
```
    @SpringBootApplication 
    public class HelloApplication extends SpringBootServletInitializer
    { 
      public static void main(String[] args) { 
        SpringApplication.run(HelloApplication.class, args); 
      } 
    
      @Override 
      protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) { 
            return builder.sources(HelloApplication.class); 
      }
    }
```
		https://stackoverflow.com/questions/48047909/why-it-is-necessary-to-extendspringbootservletinitializer-while-deploying-it-t/48047949
		https://zetcode.com/springboot/springbootservletinitializer/
		https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/web/servlet/support/SpringBootServletInitializer.html
		https://www.baeldung.com/spring-boot-servlet-initializer


### Other deploy options
		https://docs.spring.io/spring-boot/docs/current/reference/html/deployment.html
		

### Join Tables
		https://roytuts.com/spring-boot-data-jpa-left-right-inner-and-cross-join-examples/
		
		
---
### Spring Boot & Angular
		https://www.baeldung.com/spring-boot-angular-web
		https://spring.io/guides/tutorials/spring-security-and-angular-js/
	
		https://spring.io/guides/tutorials/spring-security-and-angular-js/#_multiple_ui_applications_and_a_gateway_single_page_application_with_spring_and_angular_js_part_vi
		
		
---
### REFERENCE
	JPA
	https://spring.io/projects/spring-data-jpa
	https://spring.io/guides/gs/accessing-data-jpa/
	https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.repositories
	https://www.infoworld.com/article/3379043/what-is-jpa-introduction-to-the-java-persistence-api.html
	
	BEAN
	https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-definition
	https://docs.spring.io/spring-javaconfig/docs/1.0.0.M4/reference/html/ch02s02.html
	
	The different between each Spring Component
	https://stackoverflow.com/questions/6827752/whats-the-difference-between-component-repository-service-annotations-in#:~:text=Spring%20provides%20four%20different%20types,and%20within%20the%20defined%20layer.
	
	https://www.baeldung.com/spring-component-repository-service
	
	https://medium.com/@sendvjs/difference-between-component-service-controller-and-repository-in-spring-5f9fa05bcb1d
	

---
### Using application.yml
- pom.xml
```
        <dependency> 
               <groupId>org.springframework.boot</groupId> 
               <artifactId>spring-boot-configuration-processor</artifactId> 
               <optional>true</optional> 
       </dependency>
```
- src\main\resources\config\application.yml
```
        endpoints: 
          cors: 
            allowed-origins: "*" 
             
        --- 
         
        spring: 
          profiles: prod 
        endpoints: 
          cors: 
            allowed-origins: ""
```
- able to use application-prod.yml 
	- carefull about "blank line above the setting"
- select profile by using this command
	- mvnw spring-boot:run -Dspring-boot.run.profiles=prod
