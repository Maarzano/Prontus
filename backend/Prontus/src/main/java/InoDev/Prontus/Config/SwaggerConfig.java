package InoDev.Prontus.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "API do Meu Sistema", version = "1.0", description = "Documentação da API"))
public class SwaggerConfig {
//http://localhost:8080/swagger-ui/index.html
}
