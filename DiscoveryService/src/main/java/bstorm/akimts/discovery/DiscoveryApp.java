package bstorm.akimts.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class DiscoveryApp {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryApp.class, args);
    }

    @GetMapping("/hello-world")
    public String hello(){
        return "Hello World!";
    }

}
