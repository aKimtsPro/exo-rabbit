package bstorm.akimts.exo.facture.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

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
    public Binding fBinding(DirectExchange exchange,Queue fQueue){
        return BindingBuilder.bind(fQueue).to(exchange).with("fact");
    }
}
