package me.sta.queue.configuration;

import me.sta.message.enums.NotifyDestinationNameEnum;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    private static final String SINGLE_EXCHAGE = "singleExchange";

    @Bean
    Queue queue(){
        return new Queue(NotifyDestinationNameEnum.MESSAGE_NOTIFY.name());
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("exchage");
    }


}
