package airquality.project.utils;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("airquality.project")
@EnableJpaRepositories("airquality.project") 
@EntityScan("airquality.project")
@EnableScheduling
public class AirQualityApplication {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/airquality");
		SpringApplication.run(AirQualityApplication.class, args);
	}
}
