package br.com.joaopfsiqueira.pedidos.api.entity;

import br.com.joaopfsiqueira.pedidos.api.entity.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    private UUID id = UUID.randomUUID();
    private String cliente;
    private List<ItemPedido> itens  = new ArrayList<>();
    private Double valorTotal;
    private String emailNotificacao;
    private Status status = Status.EM_PROCESSAMENTO;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataHora = LocalDateTime.now(); // na criação do pedido, a data e hora atual é atribuída


}
