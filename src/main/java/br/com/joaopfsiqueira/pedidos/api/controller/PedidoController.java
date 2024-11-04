package br.com.joaopfsiqueira.pedidos.api.controller;

import br.com.joaopfsiqueira.pedidos.api.entity.Pedido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Usado para criar um controlador REST
@RequestMapping("/api/v1/pedidos") // Usado para mapear solicitações da web para métodos de manipulação de solicitações
public class PedidoController {

    // usado para logar algo!
    private final Logger logger = LoggerFactory.getLogger(PedidoController.class);

    @PostMapping // Usado para mapear solicitações HTTP POST para métodos de manipulação de solicitações
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
        logger.info("Pedido recebido: {}", pedido.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }
}
