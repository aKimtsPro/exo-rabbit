package bstorm.akimts.gateway2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayV2Application {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayV2Application.class, args);
    }

}
