package se331.lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("se331.lab")

public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
