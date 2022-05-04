package bstorm.akimts.reserv.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public Queue fQueue(){
        return new Queue("facturations");
    }

    @Bean
    public Queue rQueue(){
        return new Queue("reservations");
    }

    @Bean
    public DirectExchange exchange(){
        return new DirectExchange("direct.fact");
    }

    @Bean
    public Binding rBinding(DirectExchange exchange, Queue rQueue){
        return BindingBuilder.bind(rQueue).to(exchange).with("reserv");
    }
}
