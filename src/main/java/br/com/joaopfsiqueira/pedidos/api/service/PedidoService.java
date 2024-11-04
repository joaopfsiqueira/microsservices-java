package br.com.joaopfsiqueira.pedidos.api.service;

import br.com.joaopfsiqueira.pedidos.api.entity.Pedido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;

public class PedidoService {

    private final Logger logger = LoggerFactory.getLogger(PedidoService.class);

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    // instanciando o RabbitTemplate via constructor
    private final RabbitTemplate rabbitTemplate;
    public PedidoService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    // também é possível injetar o rabbitTemplate via setter
    // @Autowired
    // private RabbitTemplate rabbitTemplate;


    public Pedido enfileirarPedido(Pedido pedido) {
        rabbitTemplate.convertAndSend(exchangeName, "", pedido); // Envia o pedido para o exchange
        // como eu estou usando Fanout Exchange, eu não preciso especificar a routing key
        logger.info("Pedido enfileirado: {}", pedido.toString());
        return pedido;
    }
}
