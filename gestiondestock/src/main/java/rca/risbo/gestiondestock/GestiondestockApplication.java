package rca.risbo.gestiondestock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // Permet de dire a spring d'auditer la classe AbstractEntity au demarrage
public class GestiondestockApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestiondestockApplication.class, args);
	}

}
