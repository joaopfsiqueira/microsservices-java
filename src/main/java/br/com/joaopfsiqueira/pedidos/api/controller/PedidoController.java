package br.com.joaopfsiqueira.pedidos.api.controller;

import br.com.joaopfsiqueira.pedidos.api.entity.Pedido;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Pedidos", description = "API para pedidos") // Usado para documentar a API
@RestController // Usado para criar um controlador REST
@RequestMapping("/api/v1/pedidos") // Usado para mapear solicitações da web para métodos de manipulação de solicitações
public class PedidoController {

    // usado para logar algo!
    private final Logger logger = LoggerFactory.getLogger(PedidoController.class);

    @Operation(summary = "Cria um pedido", description = "contem as operações para criar um novo pedido",
            responses = @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pedido.class)))) // Usado para documentar a operação
    @PostMapping // Usado para mapear solicitações HTTP POST para métodos de manipulação de solicitações
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
        logger.info("Pedido recebido: {}", pedido.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }
}
