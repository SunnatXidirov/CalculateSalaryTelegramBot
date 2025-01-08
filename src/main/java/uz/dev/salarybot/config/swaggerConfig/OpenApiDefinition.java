package uz.dev.salarybot.config.swaggerConfig;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Intern Task",
                version = "1.0-SNAPSHOT",
                contact = @Contact(
                        name = "Intern Task",
                        url = "https://www.kun.uz",
                        email = "xidirovsunnat00@gmail.com"
                ),
                termsOfService = "Terms Of Service",
                description = "Description",
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        servers = @Server(
                url = "http://localhost:9090/",
                description = "Main Url"
        )
)
public class OpenApiDefinition {
}
