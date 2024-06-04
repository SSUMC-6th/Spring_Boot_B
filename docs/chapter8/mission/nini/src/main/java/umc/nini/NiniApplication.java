package umc.nini;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NiniApplication {

	public static void main(String[] args) {
		SpringApplication.run(NiniApplication.class, args);
	}

}
