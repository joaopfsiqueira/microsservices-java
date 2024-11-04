package br.com.joaopfsiqueira.pedidos.api.config;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Usado para definir uma classe de configuração
public class RabbitmqConfig {

    @Value("${rabbitmq.exchange.name}") // Usado para injetar valores de propriedades do application.properties
    private String exchangeName;

    @Bean
    public Exchange pedidosExchange() {
        return new
                FanoutExchange(exchangeName); // Cria um exchange do tipo Fanout, que envia mensagens para todas as filas que estão ligadas a ele
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory); // Cria um RabbitAdmin, que é usado para declarar exchanges, filas e bindings
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter(); // Cria um MessageConverter que converte mensagens para JSON
    }

    @Bean // Cria um RabbitTemplate, que é usado para enviar mensagens para o RabbitMQ sabendo que é para converter em JSON.
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory); // Cria um RabbitTemplate, que é usado para enviar mensagens para o RabbitMQ
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean // Cria um ApplicationListener que é chamado quando a aplicação está pronta para ser usada, é ele quem vai executar!
    // vai usar o evento application ready, que é disparado quando a aplicação está pronta para ser usada
    public ApplicationListener<ApplicationReadyEvent> applicationReadyEventApplicationListener(RabbitAdmin rabbitAdmin) {

        // como era feito antes!
        //return new ApplicationListener<ApplicationReadyEvent>() {
        //    @Override
        //    public void onApplicationEvent(ApplicationReadyEvent event) {
        //        rabbitAdmin.declareExchange(pedidosExchange());
        //    }
        //};


        return event -> {
            rabbitAdmin.declareExchange(pedidosExchange()); // Declara o exchange pedidosExchange
        };
    }
}
