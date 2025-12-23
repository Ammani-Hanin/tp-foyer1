package tn.esprit.tpfoyer.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springdoc.core.models.GroupedOpenApi;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Info info = new Info()
                .title("TP Foyer API")
                .version("v1.0.0")
                .description("OpenAPI documentation for CRUD controllers (Bloc, Chambre, Etudiant, Foyer, Reservation, Universite)")
                .contact(new Contact().name("TP Foyer Team").email("support@example.com"))
                .license(new License().name("MIT").url("https://opensource.org/licenses/MIT"));

        Server localServer = new Server()
                .url("http://localhost:8090")
                .description("Local Dev");

        return new OpenAPI()
                .components(new Components())
                .info(info)
                .addServersItem(localServer);
    }

    @Bean
    public GroupedOpenApi blocGroup() {
        return GroupedOpenApi.builder()
                .group("bloc")
                .pathsToMatch("/api/blocs/**")
                .build();
    }

    @Bean
    public GroupedOpenApi chambreGroup() {
        return GroupedOpenApi.builder()
                .group("chambre")
                .pathsToMatch("/api/chambres/**")
                .build();
    }

    @Bean
    public GroupedOpenApi etudiantGroup() {
        return GroupedOpenApi.builder()
                .group("etudiant")
                .pathsToMatch("/api/etudiants/**")
                .build();
    }

    @Bean
    public GroupedOpenApi foyerGroup() {
        return GroupedOpenApi.builder()
                .group("foyer")
                .pathsToMatch("/api/foyers/**")
                .build();
    }

    @Bean
    public GroupedOpenApi reservationGroup() {
        return GroupedOpenApi.builder()
                .group("reservation")
                .pathsToMatch("/api/reservations/**")
                .build();
    }

    @Bean
    public GroupedOpenApi universiteGroup() {
        return GroupedOpenApi.builder()
                .group("universite")
                .pathsToMatch("/api/universites/**")
                .build();
    }

    @Bean
    public GroupedOpenApi allGroup() {
        return GroupedOpenApi.builder()
                .group("all")
                .pathsToMatch("/api/**")
                .build();
    }
}
