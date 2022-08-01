package rca.risbo.gestiondestock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static rca.risbo.gestiondestock.utils.Constants.APP_ROOT;

@Configuration //demande à Spring d'executer cette classe au demarrage de l'application
@EnableSwagger2 // permet d'activer Swagger
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(
                new ApiInfoBuilder()
                    .description("Gestion de stock API documentation")
                    .title("Gestion de stock REST API")
                    .build()
            )
            .groupName("REST API V1")
            .select()
            .apis(RequestHandlerSelectors.basePackage("rca.risbo.gestiondestock"))
            .paths(PathSelectors.any())
            .build();
    }
}
