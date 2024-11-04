package br.com.joaopfsiqueira.pedidos.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Usado para definir uma classe de configuração
public class SpringDocOpenApiConfig {

    @Bean // Usado para sinalizar um metodo de fábrica de beans
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info()
                .title("Pedidos Rest API")
                .description("Api para realização de pedidos")
                .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0"))
                        .contact(new Contact().email("joaopfsiqueiraa@gmail.com").name("João Pedro"))
                .version("v1"));

    }
}
