package bstorm.akimts.reserv.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfig {

//    @Bean
//    public ObjectMapper mapper(){
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.registerModule(new JavaTimeModule());
//        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
//        return mapper;
//    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
